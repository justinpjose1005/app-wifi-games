package com.justinjose.wifigames

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

object FirebaseEvent {
    private var firebaseAnalytics: FirebaseAnalytics = Firebase.analytics

    fun moreGamesOnClick() {
        firebaseAnalytics.logEvent(FirebaseEventNames.MoreGamesOnClick.name, null)
    }

    fun onAppUpdateCancelled() {
        firebaseAnalytics.logEvent(FirebaseEventNames.OnAppUpdateCancelled.name, null)
    }

    private sealed class FirebaseEventNames(val name: String) {
        object MoreGamesOnClick : FirebaseEventNames("more_games_onclick")
        object OnAppUpdateCancelled : FirebaseEventNames("on_app_update_cancelled")
    }
}