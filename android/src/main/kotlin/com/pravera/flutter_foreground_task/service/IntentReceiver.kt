package com.pravera.flutter_foreground_task.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.pravera.flutter_foreground_task.models.ForegroundServiceAction
import com.pravera.flutter_foreground_task.models.ForegroundServiceStatus
import com.pravera.flutter_foreground_task.models.ForegroundTaskOptions

/**
 * The receiver that receives the BOOT_COMPLETED event.
 *
 * @author Dev-hwang
 * @version 1.0
 */
class IntentReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context != null && (intent?.action == Intent.ACTION_BOOT_COMPLETED || intent?.action == Intent.ACTION_MY_PACKAGE_REPLACED)) {

            val options = ForegroundTaskOptions.getData(context)

            // Check whether to start the service at boot time.
            if (!options.autoRunOnBoot) return

            //Check whether to start the service on my package replaced time.
            if (!options.autoRunOnMyPackageReplaced) return

            // Create an intent for calling the service and store the action to be executed
            val nIntent = Intent(context, ForegroundService::class.java)
            ForegroundServiceStatus.putData(context, ForegroundServiceAction.REBOOT)
            ContextCompat.startForegroundService(context, nIntent)
        }
    }
}
