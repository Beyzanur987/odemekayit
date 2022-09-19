package com.example.odemetakip

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Adapter
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_detaygoruntuleme.view.*
import kotlinx.android.synthetic.main.activity_yeniodemetipiekleme.view.*
import java.text.FieldPosition
import kotlinx.android.synthetic.main.tip.view.*

class TipAdapter (var context: Context,var tipList: ArrayList<Odemetipi>) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var view: View
        if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            view = inflater.inflate(R.layout.tip, parent, false)
        } else{
            view = convertView
    }
    val myTipList=tipList[position]
        view.textViewbaslik.text=myTipList.baslik


        //tip sil
        view.button3.setOnClickListener {
            val database = DBHandler(context)
            database.deletetip(myTipList.id)
            tipList=database.gosterTip()
            notifyDataSetChanged()
        }

        // tip güncelle
        view.buttonduzenle.setOnClickListener {
            gotoTipUpdate(myTipList)
            notifyDataSetChanged()
        }




        return view
}

    fun gotoTipUpdate(odemetipi: Odemetipi){

        val intent= Intent(context,yeniodemetipiekleme::class.java)
        intent.putExtra("id",odemetipi.id)
        intent.putExtra("baslik",odemetipi.baslik)
    }


    fun gotoOdemeDetayUpdate(odemeDetay: OdemeDetay){

        val intent=Intent(context,odemekleme::class.java)
        intent.putExtra("tutar",odemeDetay.tutar)
        intent.putExtra("tarih",odemeDetay.tarih)

    }

    override fun getCount(): Int {
     return tipList.size
    }

    override fun getItem(position: Int): Any {
   return  tipList[position]
    }

    override fun getItemId(position: Int): Long {
        return  position.toLong()
    }

}