package com.superrookie.bixbyhelper;

import android.app.ActivityManager;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Catch_Bixbi_Service extends IntentService {

    private static Context mContext;

    public Catch_Bixbi_Service()
    {
        super("Catch_Bixbi_Service");
    }

    private boolean isBixbyRunning(){
        ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> rs = am.getRunningServices(1000);

        for(int i=0; i<rs.size(); i++){
            ActivityManager.RunningServiceInfo rsi = rs.get(i);
            if(rsi.service.getClassName().equals("com.samsung.android.bixby.WinkService")) return true;
            Log.d("BixbyFinder","get Bixby Class name!");
        }
        return false;
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startHelper(Context context) {
        mContext = context;

        Intent intent = new Intent(context, Catch_Bixbi_Service.class);
        context.startService(intent);

        Toast.makeText(context, "Bixby_Helper Service start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        boolean sw = true;
        boolean hasBixby = false;
        mContext = this;
        IntentFilter itf = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        itf.setPriority(9999);

        while(true)
        {
            BCaster bc = new BCaster();
            registerReceiver(bc, itf);
            try {
                Thread.sleep(600000);
            } catch (InterruptedException e) {
                bc.clearAbortBroadcast();
                unregisterReceiver(bc);
                bc = null;
                break;
            }
            bc.clearAbortBroadcast();
            unregisterReceiver(bc);
            bc = null;
        }
        Toast.makeText(this, "Bixby_Helper Service dead", Toast.LENGTH_SHORT).show();
    }

    public class BCaster extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
                Log.d("mybroadcastlistener", "get msg");
                Intent intn = new Intent(mContext, DialogActivity.class);
                startActivity(intn);
            }
        }
    }
}
