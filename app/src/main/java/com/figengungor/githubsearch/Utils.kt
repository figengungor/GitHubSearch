package com.figengungor.githubsearch

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import android.view.inputmethod.InputMethodManager.HIDE_NOT_ALWAYS
import android.app.Activity
import android.view.inputmethod.InputMethodManager


/**
 * Created by figengungor on 2/8/2018.
 */

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun ImageView.loadUrl(url: String) {
    Picasso.with(context).load(url).into(this)
}

fun Activity.hideKeyboard() {
    val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        inputManager.hideSoftInputFromWindow(currentFocus
                .windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }


}
