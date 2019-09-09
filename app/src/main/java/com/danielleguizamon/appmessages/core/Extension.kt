package com.danielleguizamon.appmessages.core

import android.view.View

internal infix fun View.onClick(function: () -> Unit) {
    setOnClickListener { function() }
}