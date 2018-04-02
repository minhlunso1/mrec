package minhna.android.mercari.model

import android.support.annotation.IntDef
import android.support.annotation.LongDef
import android.support.annotation.StringDef

/**
 * Created by minhnguyen on 3/5/18.
 */
data class Item(val id: String, val name: String, val status: String, val num_likes: Int, val num_comments: Int,
                val price: Int, val photo: String) {

    companion object {
        @LongDef(ALL, MEN, WOMEN)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ItemType
        const val ALL = 0L
        const val MEN = 1L
        const val WOMEN = 2L

        @StringDef(ON_SALE, SOLD_OUT)
        @Retention(AnnotationRetention.SOURCE)
        annotation class ItemSaleType
        const val ON_SALE = "on_sale"
        const val SOLD_OUT = "sold_out"
    }

}