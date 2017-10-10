package com.superrookie.bixbyhelper;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Catch_Bixbi_Service extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.superrookie.bixbyhelper.action.FOO";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.superrookie.bixbyhelper.extra.PARAM1";

    private static Context mContext = null;
//    private static Camera_Dialog mCameraDialog;

    public Catch_Bixbi_Service()
    {
        super("Catch_Bixbi_Service");
//        mCameraDialog = camera;
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1) {
        Intent intent = new Intent(context, Catch_Bixbi_Service.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        context.startService(intent);

        mContext = context;
//        mCameraDialog = camera;

        Toast.makeText(context, "Test start", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                handleActionFoo(param1);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1) {
        // TODO: Handle action Foo
        Toast.makeText(mContext, "Test : " + param1, Toast.LENGTH_SHORT).show();
//        mCameraDialog.show();
//        this.stopSelf();
    }
}
