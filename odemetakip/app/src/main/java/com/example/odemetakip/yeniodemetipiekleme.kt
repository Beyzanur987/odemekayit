package com.example.odemetakip

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_yeniodemetipiekleme.*

class yeniodemetipiekleme : AppCompatActivity() {

    var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yeniodemetipiekleme)



        try {
            val bundle: Bundle? = intent.extras
            id=bundle!!.getInt("id",0)

            if(id!=0){
                editTextbaslik.setText(bundle.getString("baslik"))
            }

        } catch(e:Exception){
            e.printStackTrace()

        }
    }

    fun addTip(view: View){

        val db=DBHandler(this)
        val baslik=editTextbaslik.text.toString()

        if (id==0){
            if (baslik.isNotEmpty()){
                val tip=Odemetipi(baslik)
                db.insertOdemeTipiEkle(tip)
                finish()
            }else {

                Toast.makeText(this, "Boş bırakmayınız", Toast.LENGTH_SHORT).show()
            }

        }else{
            if (baslik.isNotEmpty()){
                val tip=Odemetipi(id, baslik)
                db.updateTip(tip)
                finish()
            }
            else{
                Toast.makeText(this,"boş bırakmayınız",Toast.LENGTH_SHORT).show()
            }
        }
    }
}