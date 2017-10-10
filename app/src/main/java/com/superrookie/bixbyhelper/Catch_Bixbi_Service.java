package com.superrookie.bixbyhelper;

import android.app.ActivityManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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
            if(rsi.service.getClassName().equals("com.samsung.android.bixby.agent.ExecutorManagerService")) return true;
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
        Intent intn = new Intent(this, DialogActivity.class);

        while(true)
        {
            hasBixby = isBixbyRunning();
            if(hasBixby && sw)
            {
                startActivity(intn);
                sw = false;
            }else if(!hasBixby && !sw){
                sw = true;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        Toast.makeText(this, "Bixby_Helper Service dead", Toast.LENGTH_SHORT).show();
    }
}
