package com.min.resultdialog.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.min.resultdialog.Interface.DialogShowingCallBack;
import com.min.resultdialog.R;
import com.min.resultdialog.view.ResultDialog;
import com.min.resultdialog.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private Button loadingButton, succeedButton, failedButton, warningButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initData() {

    }

    private void initView() {
        loadingButton = (Button) findViewById(R.id.loading_button);
        succeedButton = (Button) findViewById(R.id.succeed_button);
        failedButton = (Button) findViewById(R.id.failed_button);
        warningButton = (Button) findViewById(R.id.warning_button);

        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(ResultDialog.LOADING, "LOADING");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismissResultDialog();
                    }
                }, 3000);
            }
        });
        succeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(ResultDialog.SUCCEED, "SUCCEED", new DialogShowingCallBack() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "FINISH", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        failedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(ResultDialog.FAILED, "FAILED", new DialogShowingCallBack() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "FINISH", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        warningButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultDialog(ResultDialog.WARNING, "WARNING", new DialogShowingCallBack() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(MainActivity.this, "FINISH", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}