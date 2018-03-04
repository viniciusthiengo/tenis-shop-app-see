package thiengo.com.br.tenisshop.domain

import android.os.Parcel
import android.os.Parcelable
import java.util.*


class Sneaker(
        val imageResource: Int,
        val album: IntArray,
        val model: String,
        val brand: Brand,
        val isForMale: Boolean,
        val isForFemale: Boolean,
        val rating: Rating,
        val price: Double) : Parcelable {
    fun getPriceAsString(): String {
        return String.format(Locale.FRANCE, "R$ %.2f", price)
    }

    fun getPriceParcelsAsString(): String {
        val value = price / 10
        return String.format(Locale.FRANCE, "10x R$ %.2f", value)
    }

    constructor(source: Parcel) : this(
            source.readInt(),
            source.createIntArray(),
            source.readString(),
            source.readParcelable<Brand>(Brand::class.java.classLoader),
            1 == source.readInt(),
            1 == source.readInt(),
            source.readParcelable<Rating>(Rating::class.java.classLoader),
            source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(imageResource)
        writeIntArray(album)
        writeString(model)
        writeParcelable(brand, 0)
        writeInt((if (isForMale) 1 else 0))
        writeInt((if (isForFemale) 1 else 0))
        writeParcelable(rating, 0)
        writeDouble(price)
    }

    companion object {
        val KEY = "SNEAKER_KEY"

        @JvmField
        val CREATOR: Parcelable.Creator<Sneaker> = object : Parcelable.Creator<Sneaker> {
            override fun createFromParcel(source: Parcel): Sneaker = Sneaker(source)
            override fun newArray(size: Int): Array<Sneaker?> = arrayOfNulls(size)
        }
    }
}