package com.example.cinema.data

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val id: Int,
    val localized_name: String?,
    val name: String?,
    val year: Int?,
    val rating: Double?,
    val image_url: String?,
    val description: String?,
    val genres: List<String>?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readIntOrNull(),
        parcel.readDoubleOrNull(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(localized_name)
        parcel.writeString(name)
        parcel.writeInt(year ?: 0)
        parcel.writeDouble(rating ?: 0.0)
        parcel.writeString(image_url)
        parcel.writeString(description)
        parcel.writeStringList(genres)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

// Вспомогательные функции для чтения данных
fun Parcel.readIntOrNull(): Int? {
    return if (readInt() == -1) null else readInt()
}

fun Parcel.readDoubleOrNull(): Double? {
    return if (readDouble() == -1.0) null else readDouble()
}


