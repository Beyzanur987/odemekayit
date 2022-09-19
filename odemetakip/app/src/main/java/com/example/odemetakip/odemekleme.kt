package com.example.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_odemekleme.*

class odemekleme : AppCompatActivity() {
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_odemekleme)

        try {
            val  bundle : Bundle?= intent.extras
            id=bundle!!.getInt("id",0)
            if (id!=0){

                et_odemetutari.setText(bundle.getString("tutar"))
                et_odemetarih.setText(bundle.getString("tarih"))

            }

        }catch (e : Exception){
            e.printStackTrace()
        }
    }


    fun addOdemeEkle(view: View) {
        val db=DBHandler(this)
        val tutar=et_odemetutari.text.toString()
        val tarih=et_odemetarih.text.toString()

        if (id==0){

            if(tutar.isNotEmpty()&&tarih.isNotEmpty()){
                val odemeDetay=OdemeDetay(tutar,tarih)
                db.insertOdemeDetayEkleme(odemeDetay)
                finish()


            }else{
                Toast.makeText(this,"boş bırakmayınız",Toast.LENGTH_SHORT).show()
            }
        }else{
            if (tutar.isNotEmpty()&&tarih.isNotEmpty()){
                val odemeDetay=OdemeDetay(tutar, tarih)
                db.updateodemeDetay(odemeDetay)
                finish()
            }else{
                Toast.makeText(this,"boş bırakmayınız",Toast.LENGTH_SHORT).show()
            }
        }
    }
}