package com.min.resultdialog.base;

import android.app.Activity;
import android.os.Bundle;

import com.min.resultdialog.Interface.DialogShowingCallBack;
import com.min.resultdialog.view.ResultDialog;

public abstract class BaseActivity extends Activity {
    private ResultDialog resultDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void showResultDialog(int type, String text, final boolean isFinishAfterShowing, final DialogShowingCallBack callBack) {
        if (resultDialog == null) {
            resultDialog = new ResultDialog.DialogBuilder(this)
                    .setDialogSize(145, 145)
                    .create();
        }
        resultDialog.setOnShowingListener(new ResultDialog.OnShowingListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onFinish() {
                if (callBack != null) {
                    callBack.onFinish();
                } else if (isFinishAfterShowing) {
                    finish();
                }
                dismissResultDialog();
            }

            @Override
            public void onCancel() {

            }
        });
        if (!resultDialog.isShowing()) resultDialog.show();
        resultDialog.start(type, text);
    }

    public void showResultDialog(int type, String text) {
        showResultDialog(type, text, false, null);
    }

    public void showResultDialog(int type, String text, boolean isFinishAfterShowing) {
        showResultDialog(type, text, isFinishAfterShowing, null);
    }

    public void showResultDialog(int type, String text, DialogShowingCallBack callBack) {
        showResultDialog(type, text, true, callBack);
    }

    public void dismissResultDialog() {
        if (resultDialog != null && resultDialog.isShowing()) {
            resultDialog.dismiss();
        }
    }
}