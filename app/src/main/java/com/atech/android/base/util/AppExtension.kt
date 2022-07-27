package com.atech.android.base.util

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Paint
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.style.UnderlineSpan
import android.util.DisplayMetrics
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun SpannableString.setUnderline(wordToUnderline: String): SpannableString {
    val index = this.indexOf(wordToUnderline)
    this.setSpan(UnderlineSpan(), index, index + wordToUnderline.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun SpannableString.setClickable(wordToClick: String, clickableSpan: ClickableSpan): SpannableString {
    val index = this.indexOf(wordToClick)
    this.setSpan(clickableSpan, index, index + wordToClick.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
    return this
}

fun TextView.underlined() {
    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
}

fun Context.convertDpToPx(dp: Int): Int {
    val displayMetrics: DisplayMetrics = resources.displayMetrics
    return dp.times(displayMetrics.xdpi.div(DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun View.hideKeyboard() {
    val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(this.windowToken, 0)
}

fun Fragment.restartActivity() {
    val intent = requireActivity().intent
    requireActivity().finishAffinity()
    startActivity(intent)
}

fun Fragment.hideStatusAndNavBar() {
    this.let {
        it.requireActivity().apply {
            WindowCompat.setDecorFitsSystemWindows(window, false)
            WindowInsetsControllerCompat(window, it.requireView()).let { controller ->
                controller.hide(WindowInsetsCompat.Type.systemBars())
                controller.hide(WindowInsetsCompat.Type.statusBars())
                controller.hide(WindowInsetsCompat.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        }
    }
}

fun Fragment.showStatusAndNavBar() {
    this.let {
        it.requireActivity().apply {
            WindowCompat.setDecorFitsSystemWindows(window, true)
            WindowInsetsControllerCompat(window, it.requireView()).let { controller ->
                controller.show(WindowInsetsCompat.Type.systemBars())
                controller.show(WindowInsetsCompat.Type.statusBars())
                controller.show(WindowInsetsCompat.Type.navigationBars())
            }
        }
    }
}

fun String.formatFromIsoDate(
    isoFormat: String = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ",
    expectFormat: String = "dd-MM-yyyy",
    expectLocale: String = "",
    ignoreTimeZone: Boolean = false
): String {
    return try {
        val isoDateFormat = SimpleDateFormat(isoFormat, Locale.getDefault())
        if (!ignoreTimeZone) {
            isoDateFormat.timeZone = TimeZone.getTimeZone("GMT")
        }
        val rawDate = isoDateFormat.parse(this)
        val dateFormat =
            if (expectLocale.isBlank()) SimpleDateFormat(expectFormat)
            else SimpleDateFormat(expectFormat, Locale.forLanguageTag(expectLocale))
        dateFormat.format(rawDate)
    } catch (exception: Exception) {
        ""
    }
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

//fun BaseFragment<*, *>.hasPermissions(context: Context, permissions: Array<String>): Boolean = permissions.all {
//    ActivityCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
//}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}
