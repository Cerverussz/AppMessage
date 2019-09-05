package co.com.ceiba.mobile.pruebadeingreso.core

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}

fun EditText.onChange(cb: (String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            cb(s.toString())
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}