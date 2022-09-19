package com.example.odemetakip

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


 val DB_NAME="ODEMEDATABASE"
 val Db_VERSION=  1
 val TABLE_ODEMETIPI="TableOdemeTipi"
 val COL_TIPID="id"
 val COL_BASLİK="baslik"

 val TABLE_ODEMEDETAY= "TableOdemeDetay"
 val COL_ODEMEID="id"
 val COL_TUTAR="tutar"
 val COL_TARIH="tarih"
 val COL_ODEMETIPIID="ödemetipiid"

class DBHandler(var context: Context):SQLiteOpenHelper(context,DB_NAME ,null, Db_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createOdemeTipi = "CREATE TABLE " + TABLE_ODEMETIPI + "(" +

                COL_TIPID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_BASLİK + "TEXT)"



        val createOdemeDetay = "CREATE TABLE " + TABLE_ODEMEDETAY + "("


        COL_ODEMEID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                COL_TUTAR + "TEXT," +
                COL_TARIH + "TEXT," +
                COL_ODEMETIPIID + "INTEGER REFERANCES" + TABLE_ODEMETIPI + "(" + COL_TIPID + ")" +")"



        db?.execSQL(createOdemeTipi)
        db?.execSQL(createOdemeDetay)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("Drop table if exists"+ TABLE_ODEMETIPI)
        db?.execSQL("Drop table if exists"+ TABLE_ODEMEDETAY)
        onCreate(db)
    }

    fun insertOdemeTipiEkle(odemetipi: Odemetipi) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_BASLİK, odemetipi.baslik)
        db.insert(TABLE_ODEMETIPI, null, values)
        db.close()


    }

    fun insertOdemeDetayEkleme(odemedetay: OdemeDetay) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TUTAR, odemedetay.tutar)
        values.put(COL_TARIH, odemedetay.tarih)
        db.insert(TABLE_ODEMEDETAY, null, values)
        db.close()
    }


    @SuppressLint("Range")
    fun gosterTip(): ArrayList<Odemetipi> {
        val list = ArrayList<Odemetipi>()
        val db = this.readableDatabase
        val query = "SELECT * FROM " + TABLE_ODEMETIPI
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {
            val odemetipi = Odemetipi()
            odemetipi.id = cursor.getInt(cursor.getColumnIndex(COL_TIPID))
            odemetipi.baslik = cursor.getString(cursor.getColumnIndex(COL_BASLİK))
            list.add(odemetipi)


        }
        db.close()
        return list
    }


    @SuppressLint("Range")
    fun gosterDetay(): ArrayList<OdemeDetay> {

        val liste2 = ArrayList<OdemeDetay>()
        val db = this.readableDatabase
        val query2 = "SELECT * FROM" + TABLE_ODEMEDETAY
        val cursor = db.rawQuery(query2, null)

        while (cursor.moveToNext()) {
            val odemeDetay = OdemeDetay()
            odemeDetay.id = cursor.getInt(cursor.getColumnIndex(COL_ODEMEID))
            odemeDetay.tutar = cursor.getString(cursor.getColumnIndex(COL_TUTAR))
            odemeDetay.tarih = cursor.getString(cursor.getColumnIndex(COL_TARIH))
            liste2.add(odemeDetay)

        }
        db.close()
        return liste2
    }

    fun updateTip(odemetipi: Odemetipi) {

        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_BASLİK, odemetipi.baslik)
        db.update(TABLE_ODEMETIPI, values, "id=?", arrayOf(odemetipi.id.toString()))
        db.close()
    }

    fun updateodemeDetay(odemedetay: OdemeDetay) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_TUTAR, odemedetay.tutar)
        values.put(COL_TARIH, odemedetay.tarih)
        db.update(TABLE_ODEMEDETAY, values, "id=?", arrayOf(odemedetay.id.toString()))
        db.close()

    }

    fun deletetip(id : Int){
        val db=this.writableDatabase
        db.delete(TABLE_ODEMETIPI,"id=?", arrayOf(id.toString()))
        db.close()
    }

    fun deletedetay(id : Int){
        val db=this.writableDatabase
        db.delete(TABLE_ODEMEDETAY,"id=?", arrayOf(id.toString()))
        db.close()
    }
}