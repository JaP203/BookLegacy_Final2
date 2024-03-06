package com.marisma.booklegacy.sampledata

import android.os.Parcel
import android.os.Parcelable

//Clase con los objetos del RecyclerView.
data class Book(
    val nombre: String?,
    val paginas: Int,
    val autor: String?,
    val fotografia: String?,
    var fav: Boolean,
    val description: String?,
    val genero:String?

):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString()

        ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(paginas)
        parcel.writeString(autor)
        parcel.writeString(fotografia)
        parcel.writeString(description)
        parcel.writeString(genero)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}