package com.example.user.halal;

import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    public interface onKeyBackpressedListener{
        void onBackKey();
    }

    private onKeyBackpressedListener mOnKeyBackPressedListener;

    public void setmOnKeyBackPressedListener(onKeyBackpressedListener listener) {
        mOnKeyBackPressedListener = listener;
    }

    @Override
    public void onBackPressed() {
        if(mOnKeyBackPressedListener != null)
            mOnKeyBackPressedListener.onBackKey();
        else
            super.onBackPressed();
    }
}
