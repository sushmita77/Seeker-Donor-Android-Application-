package com.giveandtake.sumi0717.seekersdonars;

/**
 * Created by sumi0717 on 05-11-2018.
 */
import java.lang.reflect.Field;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.util.Log;

public class SpinnerTrigger extends AppCompatSpinner {

    public SpinnerTrigger(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public SpinnerTrigger(Context context, AttributeSet attrs)
    { super(context, attrs); }

    public SpinnerTrigger(Context context, AttributeSet attrs, int defStyle)
    { super(context, attrs, defStyle); }


    @Override
    public void setSelection(int position, boolean animate) {
        ignoreOldSelectionByReflection();
        super.setSelection(position, animate);
    }

    private void ignoreOldSelectionByReflection() {
        try {
            Class<?> c = this.getClass().getSuperclass().getSuperclass().getSuperclass();
            Field reqField = c.getDeclaredField("mOldSelectedPosition");
            reqField.setAccessible(true);
            reqField.setInt(this, -1);
        } catch (Exception e) {
            Log.d("Exception Private", "ex", e);
            // TODO: handle exception
        }
    }

    @Override
    public void setSelection(int position) {
        ignoreOldSelectionByReflection();
        super.setSelection(position);
    }

}