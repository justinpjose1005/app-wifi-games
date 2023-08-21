package com.justinjose.wifigames.widgets.core

import android.app.Activity
import android.content.IntentSender
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import com.justinjose.wifigames.ui.viewmodels.GamesViewModel

@Composable
fun CheckForAppUpdate(activity: Activity, gamesViewModel: GamesViewModel = viewModel()) {
    val context = LocalContext.current
    val appUpdateManager = AppUpdateManagerFactory.create(context)
    val appUpdateInfoTask = appUpdateManager.appUpdateInfo
    appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_NOT_AVAILABLE) {
            gamesViewModel.getUpdateResponse(AppUpdateState.NotAvailable)
            return@addOnSuccessListener
        }
        if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
            && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)
        ) {
            try {
                gamesViewModel.getUpdateResponse(AppUpdateState.Updating)
                appUpdateManager.startUpdateFlowForResult(
                    appUpdateInfo,
                    AppUpdateType.IMMEDIATE,
                    activity,
                    1000
                )
            } catch (e: IntentSender.SendIntentException) {
                e.printStackTrace()
                gamesViewModel.getUpdateResponse(AppUpdateState.NotAvailable)
            }
        }
    }.addOnFailureListener {
        gamesViewModel.getUpdateResponse(AppUpdateState.NotAvailable)
    }
}

sealed class AppUpdateState {
    object Loading : AppUpdateState()
    object Updating : AppUpdateState()
    object NotAvailable : AppUpdateState()
}