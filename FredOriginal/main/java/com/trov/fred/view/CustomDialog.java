package com.trov.fred.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.trov.fred.R;

public class CustomDialog extends FrameLayout implements View.OnClickListener {

    Activity mActivity;

    AppCompatTextView mTitle;
    AppCompatTextView mMessage;
    AppCompatButton mPositiveButton;
    AppCompatButton mNegativeButton;

    OnClickListener mOnPositiveClickListener;
    OnClickListener mOnNegativeClickListener;

    public CustomDialog(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();

        setOnClickListener(this);

        mActivity = (Activity) getContext();

        inflate(getContext(), R.layout.view_custom_dialog, this);

        mTitle = (AppCompatTextView) findViewById(R.id.title);
        mMessage = (AppCompatTextView) findViewById(R.id.message);
        mPositiveButton = (AppCompatButton) findViewById(R.id.positive);
        mPositiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                if (mOnPositiveClickListener == null) {
                    return;
                }

                mOnPositiveClickListener.onClick(CustomDialog.this);
            }
        });

        mNegativeButton = (AppCompatButton) findViewById(R.id.negative);
        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

                if (mOnNegativeClickListener == null) {
                    return;
                }

                mOnNegativeClickListener.onClick(CustomDialog.this);
            }
        });
    }

    public void dismiss() {
        ViewGroup parent = (ViewGroup) getParent();
        parent.removeView(this);
    }

    public CustomDialog(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomDialog(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public void add(@IdRes int parentId) {
        ViewGroup parent = (ViewGroup) mActivity.findViewById(parentId);
        parent.addView(this);
    }

    public void setMessage(String message) {
        mMessage.setText(message);
    }

    public void setOnPositiveClickListener(OnClickListener onPositiveClickListener) {
        mOnPositiveClickListener = onPositiveClickListener;
    }

    public void setOnNegativeClickListener(OnClickListener onNegativeClickListener) {
        mOnNegativeClickListener = onNegativeClickListener;
    }

    public interface OnClickListener {
        void onClick(CustomDialog dialog);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            dismiss();
            return true;
        }

        return super.onKeyPreIme(keyCode, event);
    }
}
