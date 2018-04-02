package minhna.android.mercari.view.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import minhna.android.mercari.R
import java.text.DecimalFormat

/**
 * Created by Minh on 3/3/2018.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

operator fun ViewGroup.get(pos: Int): View = getChildAt(pos)

fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.getDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

fun AppCompatActivity.getAppCompatDrawable(resId: Int): Drawable? = ContextCompat.getDrawable(this, resId)

fun ImageView.load(url: String) {
    Glide.with(context)
            .load(url)
            .apply(RequestOptions().placeholder(R.drawable.ic_launcher_background))
            .into(this)
}

fun TextView.setFormattedPrice(value: Int) {
    var formatter = DecimalFormat("#,###")
    this.text = "$ " + formatter.format(value)
}