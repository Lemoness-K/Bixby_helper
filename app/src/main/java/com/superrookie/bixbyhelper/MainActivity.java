package com.superrookie.bixbyhelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Home on 2017-10-11.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Catch_Bixbi_Service bs = new Catch_Bixbi_Service();
        bs.startHelper(this);
        finish();
    }
}
