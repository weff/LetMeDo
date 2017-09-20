package com.jtsoft.letmedo.utils.Model;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by admin on 2017/8/11.
 */

public class ToastUtil {

    private static Toast toast;
    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, String message) {
        if (null == toast) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            // toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

}
