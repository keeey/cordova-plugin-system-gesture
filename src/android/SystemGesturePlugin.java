package org.apache.cordova.system.gesture;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import androidx.core.view.ViewCompat;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.Collections;
import java.util.List;


public class SystemGesturePlugin extends CordovaPlugin {
    private static final float DEFAULT_EXCLUSION_RECT_SIZE = 200.0f;

    private Context applicationContext = null;
    private Activity cordovaActivity = null;

    @Override
    protected void pluginInitialize() {
        cordovaActivity = this.cordova.getActivity();
        applicationContext = cordovaActivity.getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("setExclusionRects")) {
                View view = cordovaActivity.getWindow().getDecorView();
                FrameLayout contentView = view.findViewById(android.R.id.content);
                contentView.post(() -> {
                    Rect exclusionRect = new Rect();
                    List<Rect> exclusions = Collections.singletonList(exclusionRect);

                    float exclusionRectSize = convertDpToPx(applicationContext, DEFAULT_EXCLUSION_RECT_SIZE);
                    exclusionRect.set(contentView.getLeft(), (contentView.getBottom() - (int) exclusionRectSize), (int) exclusionRectSize, contentView.getBottom());
                    ViewCompat.setSystemGestureExclusionRects(contentView, exclusions);

                    callbackContext.success("success");
                });

                return true;
            }
        } catch(Exception e) {
            callbackContext.error("error");

            e.printStackTrace();
        }

        return false;
    }

    private float convertDpToPx(Context context, float dp){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp * displayMetrics.density;
    }
}
