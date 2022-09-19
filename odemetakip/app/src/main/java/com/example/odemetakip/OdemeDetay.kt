package com.example.odemetakip

class OdemeDetay {
    var id: Int=0
    var tutar: String = ""
    var tarih: String= ""
    var odemetipiid: Int=0

    constructor( id :Int, tutar:String ,tarih:String,odemetipiid: Int){
        this.id=id
        this.tutar=tutar
        this.tarih=tarih
        this.odemetipiid=odemetipiid
    }

    constructor(tutar: String, tarih:String,) {

        this.tutar = tutar
        this.tarih = tarih

    }
    constructor()
}