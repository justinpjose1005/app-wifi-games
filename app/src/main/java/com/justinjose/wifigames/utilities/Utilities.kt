package com.justinjose.wifigames.utilities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent

object Utilities {

    fun openCustomTab(context: Context, url: String) {
        runCatching {
            val customTabIntent = CustomTabsIntent.Builder().build()
            customTabIntent.launchUrl(context, Uri.parse(url))
        }.onFailure {
            launchUrl(context, url)
        }
    }

    private fun launchUrl(context: Context, url: String): Boolean {
        with(context) {
            runCatching {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                    addCategory(Intent.CATEGORY_BROWSABLE)
                }
                startActivity(intent)
                return true
            }.getOrElse {
                Toast.makeText(this, "No Application found", Toast.LENGTH_SHORT).show()
                return false
            }
        }
    }
}