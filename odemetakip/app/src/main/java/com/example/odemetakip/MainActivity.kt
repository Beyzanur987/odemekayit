package com.example.odemetakip

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.odemetakip.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    var list=ArrayList<Odemetipi>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonAdd.setOnClickListener {
            val intent= Intent(this,yeniodemetipiekleme::class.java)
            startActivity(intent)
        }


    }

    fun loadData(){
        val database = DBHandler(this)
        list=database.gosterTip()
         val TipAdapter = TipAdapter(this,list)
        listView.adapter=TipAdapter


    }




}