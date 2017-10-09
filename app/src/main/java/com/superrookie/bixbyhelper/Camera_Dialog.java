package com.superrookie.bixbyhelper;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.PixelFormat;
import android.media.FaceDetector;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.superrookie.bixbyhelper.R;

/**
 * Created by Lemoness on 2017-10-05.
 */

public class Camera_Dialog extends Dialog {

    public Camera_Dialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_layer);

        getWindow().setFormat(PixelFormat.UNKNOWN);


    }
}
