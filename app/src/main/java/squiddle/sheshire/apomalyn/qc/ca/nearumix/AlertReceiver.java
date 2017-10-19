package squiddle.sheshire.apomalyn.qc.ca.nearumix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;

/**
 * Created by sheshire on 17-10-19.
 */

public class AlertReceiver extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {

        // Vaudra true par défaut si on ne trouve pas l'extra booléen dont la clé est LocationManager.KEY_PROXIMITY_ENTERING

        boolean entrer = (boolean)(intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true));

    }

}