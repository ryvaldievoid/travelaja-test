package com.atech.android.base.util

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.atech.android.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

object BindingAdapters {

    @BindingAdapter("imageViewUrl")
    @JvmStatic
    fun ImageView.bindImageViewUrl(
        imageViewUrl: String?
    ) {
        imageViewUrl.let {
            if (imageViewUrl != null) {
                if (imageViewUrl.contains(".jpg", true) ||
                    imageViewUrl.contains(".jpeg", true) ||
                    imageViewUrl.contains(".png", true) ||
                    imageViewUrl.contains(".gif", true) ||
                    imageViewUrl.contains(".mp4", true) ||
                    imageViewUrl.contains(".mov", true)
                ) {
                    GlideHelper.showThumbnailWithPlaceHolder(
                        it!!,
                        this,
                        context,
                        R.drawable.rawg_logo
                    )
                } else if (imageViewUrl.contains(".pdf", false) ||
                    imageViewUrl.contains(".doc", true) ||
                    imageViewUrl.contains(".txt", true)
                ) {
                    this.setImageDrawable(
                        ContextCompat.getDrawable(this.context, R.drawable.rawg_logo)
                    )
                    this
                } else {
                    this.setImageDrawable(
                        ContextCompat.getDrawable(this.context, R.drawable.rawg_logo)
                    )
                }
            } else {
                this.setImageDrawable(
                    ContextCompat.getDrawable(this.context, R.drawable.rawg_logo)
                )
            }
        }
    }

    @BindingAdapter("imageViewResource")
    @JvmStatic
    fun ImageView.bindImageViewResource(imageViewResource: Int) {
        if (imageViewResource != 0)
            this.setImageResource(imageViewResource)
    }

    @BindingAdapter("selected")
    @JvmStatic
    fun LinearLayoutCompat.bindSelected(selected: Boolean) {
        this.isSelected = selected
    }

    @BindingAdapter("textViewSelected")
    @JvmStatic
    fun TextView.bindTextViewSelected(textViewSelected: Boolean) {
        this.isSelected = textViewSelected
    }

    @BindingAdapter("imageViewSelected")
    @JvmStatic
    fun ImageView.bindImageViewSelected(imageViewSelected: Boolean) {
        this.isSelected = imageViewSelected
    }

    @BindingAdapter("itemPosition")
    @JvmStatic
    fun LinearLayout.bindItemPosition(imageViewSelected: Boolean) {
        this.isSelected = imageViewSelected
    }

    @BindingAdapter("layout_height")
    @JvmStatic
    fun bindLayoutHeight(layout: View, dimen: Int) {
        val layoutParams = layout.layoutParams
        val inPixels = layout.context.resources.getDimension(dimen)
        layoutParams.height = inPixels.toInt()
        layout.layoutParams = layoutParams
    }

    @SuppressLint("SetJavaScriptEnabled")
    @BindingAdapter(
        value = ["webviewClient", "webviewUrl"],
        requireAll = false
    )
    @JvmStatic
    fun WebView.bindWebViewData(webviewClient: WebViewClient, webviewUrl: String) {
        this.webViewClient = webviewClient
        this.settings.javaScriptEnabled = true
        this.settings.javaScriptCanOpenWindowsAutomatically = true
        this.setDownloadListener { url, _, _, _, _ ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            this.context.startActivity(intent)
        }
        this.loadUrl(webviewUrl)
    }

    @BindingAdapter("phoneCodes")
    @JvmStatic
    fun TextInputLayout.bindPhoneCodes(phoneCodes: Int) {
        val models = resources.getStringArray(phoneCodes).toList()
        val listPopupWindow = ListPopupWindow(context)
        listPopupWindow.setAdapter(
            ArrayAdapter<String>(
                context,
                android.R.layout.simple_list_item_1,
                models
            )
        )
        listPopupWindow.anchorView = this

        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            editText?.setText(models[position])
            listPopupWindow.dismiss()
        }

        this.editText?.setOnClickListener {
            listPopupWindow.show()
        }
        this.editText?.setText(models[0])
    }

    @BindingAdapter("spinnerItems")
    @JvmStatic
    fun TextInputLayout.bindSpinnerItems(spinnerItems: Int) {
        val models = resources.getStringArray(spinnerItems).toList()
        val listPopupWindow = ListPopupWindow(context)
        listPopupWindow.setAdapter(
            ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                models
            )
        )
        listPopupWindow.anchorView = this

        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            editText?.setText(models[position])
            listPopupWindow.dismiss()
        }

        this.editText?.setOnClickListener {
            listPopupWindow.show()
        }
        this.editText?.setText(models[0])
    }

    @BindingAdapter(
        "spinnerItemsWithOther",
        "otherEditTextLayout",
        "otherEditText",
        requireAll = true
    )
    @JvmStatic
    fun TextInputLayout.bindSpinnerItemsWithOther(
        spinnerItems: Int,
        textEditView: TextInputLayout,
        otherEditText: TextInputEditText
    ) {
        val models = resources.getStringArray(spinnerItems).toList()
        val listPopupWindow = ListPopupWindow(context)
        listPopupWindow.setAdapter(
            ArrayAdapter(
                context,
                android.R.layout.simple_list_item_1,
                models
            )
        )
        listPopupWindow.anchorView = this

        listPopupWindow.setOnItemClickListener { _, _, position, _ ->
            if (models[position] == "其他") {
                textEditView.visibility = View.VISIBLE
            } else {
                textEditView.visibility = View.GONE
            }
            otherEditText.setText("")
            editText?.setText(models[position])
            listPopupWindow.dismiss()
        }

        this.editText?.setOnClickListener {
            listPopupWindow.show()
        }
        this.editText?.setText(models[0])
    }

    @BindingAdapter("errorText")
    @JvmStatic
    fun TextInputLayout.bindErrorText(errorText: Int) {
        if (errorText != 0) {
            error = context.getString(errorText)
        } else {
            error = null
        }
    }

    @BindingAdapter("errorText")
    @JvmStatic
    fun EditText.bindErrorText(errorText: Int) {
        if (errorText != 0) {
            error = context.getString(errorText)
        } else {
            error = null
        }
    }

    @BindingAdapter("backgroundResource")
    @JvmStatic
    fun View.bindingBackgroundResource(resid: Int) {
        var d: Drawable? = null
        if (resid != 0) {
            d = ContextCompat.getDrawable(this.context, resid)
        }
        background = d
    }

    @BindingAdapter("checkNullOrZero")
    @JvmStatic
    fun TextView.checkNullOrZero(
        text: String?
    ) {
        text?.let { textNotNull ->
            if (textNotNull == "null" || textNotNull == "0" || textNotNull.isEmpty()) {
                this.text = "-"
            } else {
                this.text = textNotNull
            }
        } ?: kotlin.run {
            this.text = "-"
        }
    }

}
