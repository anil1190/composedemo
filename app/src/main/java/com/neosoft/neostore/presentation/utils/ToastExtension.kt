package com.neosoft.neostore.presentation.utils

import android.content.Context
import android.widget.Toast

fun Context.toast(messageId: Int) {
    Toast.makeText(this, getString(messageId), Toast.LENGTH_SHORT).show()
}