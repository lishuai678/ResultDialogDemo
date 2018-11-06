package com.min.resultdialog.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.min.resultdialog.R;

public class ResultDialog extends Dialog {
    public static final int LOADING = 1;
    public static final int SUCCEED = 2;
    public static final int FAILED = 3;
    public static final int WARNING = 4;

    private Context context;

    private TextView resultTextView;
    private LoadingView loadingView;
    private SucceedView succeedView;
    private FailedView failedView;
    private WarningView warningView;
    private View view;

    // 会话框出现位置
    private int gravity;

    // 对话款宽度
    private int width;

    // 对话框高度
    private int height;

    // 对话框显示回调
    private OnShowingListener listener;

    public ResultDialog(Context context) {
        super(context, R.style.CustomDialog);
        this.context = context;
    }

    public ResultDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    private ResultDialog createDialog() {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_result, null);

        resultTextView = (TextView) view.findViewById(R.id.result_textview);
        loadingView = (LoadingView) view.findViewById(R.id.loading_view);
        succeedView = (SucceedView) view.findViewById(R.id.succeed_view);
        failedView = (FailedView) view.findViewById(R.id.failed_view);
        warningView = (WarningView) view.findViewById(R.id.warning_view);

        show();
        setContentView(view);
        getWindow().getAttributes().gravity = gravity != 0 ? gravity : Gravity.CENTER;
        getWindow().setLayout(width != 0 ? width : WindowManager.LayoutParams.WRAP_CONTENT,
                height != 0 ? height : WindowManager.LayoutParams.WRAP_CONTENT);
        setCanceledOnTouchOutside(false);
        setCancelable(true);

        return this;
    }

    /**
     * 开始播放动画
     *
     * @param type 动画类型，可选项：LOADING、SUCCEED、FAILED、WARNING
     * @param text
     */
    public void start(int type, String text) {
        if (loadingView != null && loadingView.isLoading()) {
            loadingView.stop();
        }
        transform(type);
        resultTextView.setText(text);
        if (type == LOADING) {
            loadingView.start();
        } else if (type == SUCCEED) {
            succeedView.setOnShowAnimationListener(new SucceedView.OnShowAnimationListener() {
                @Override
                public void onStart() {
                    if (listener != null) listener.onStart();
                }

                @Override
                public void onFinish() {
                    if (listener != null) listener.onFinish();
                }

                @Override
                public void onCancel() {
                    if (listener != null) listener.onCancel();
                }
            });
            succeedView.start();
        } else if (type == FAILED) {
            failedView.setOnShowAnimationListener(new FailedView.OnShowAnimationListener() {
                @Override
                public void onStart() {
                    if (listener != null) listener.onStart();
                }

                @Override
                public void onFinish() {
                    if (listener != null) listener.onFinish();
                }

                @Override
                public void onCancel() {
                    if (listener != null) listener.onCancel();
                }
            });
            failedView.start();
        } else if (type == WARNING) {
            warningView.setOnShowAnimationListener(new WarningView.OnShowAnimationListener() {
                @Override
                public void onStart() {
                    if (listener != null) listener.onStart();
                }

                @Override
                public void onFinish() {
                    if (listener != null) listener.onFinish();
                }

                @Override
                public void onCancel() {
                    if (listener != null) listener.onCancel();
                }
            });
            warningView.start();
        }
    }

    public void stop() {
        succeedView.stop();
        failedView.stop();
        warningView.stop();
    }

    public void clear() {
        succeedView.clear();
        failedView.clear();
        warningView.clear();
    }

    /**
     * 根据动画类型显示相应的View
     *
     * @param type 动画类型
     */
    private void transform(int type) {
        loadingView.setVisibility(View.GONE);
        succeedView.setVisibility(View.GONE);
        failedView.setVisibility(View.GONE);
        warningView.setVisibility(View.GONE);
        if (type == LOADING) {
            loadingView.setVisibility(View.VISIBLE);
        } else if (type == SUCCEED) {
            succeedView.setVisibility(View.VISIBLE);
        } else if (type == FAILED) {
            failedView.setVisibility(View.VISIBLE);
        } else if (type == WARNING) {
            warningView.setVisibility(View.VISIBLE);
        }
    }

    private void setGravity(int gravity) {
        this.gravity = gravity;
    }

    private void setDialogSize(int width, int height) {
        this.width = dp2px(width);
        this.height = dp2px(height);
    }

    /**
     * 对话框建造者
     */
    public static class DialogBuilder {
        ResultDialog dialog;

        public DialogBuilder(Context context) {
            dialog = new ResultDialog(context);
        }

        /**
         * 设置对话框位置
         *
         * @param gravity
         * @return
         */
        public DialogBuilder setGravity(int gravity) {
            dialog.setGravity(gravity);
            return this;
        }

        /**
         * 对话框宽高
         *
         * @param width  宽度值,单位dp
         * @param height 高度值，单位dp
         * @return
         */
        public DialogBuilder setDialogSize(int width, int height) {
            dialog.setDialogSize(width, height);
            return this;
        }

        /**
         * 创建对话框
         *
         * @return
         */
        public ResultDialog create() {
            return dialog.createDialog();
        }
    }

    /**
     * 动画回调
     */
    public interface OnShowingListener {
        /**
         * 动画开始播放时回调
         */
        void onStart();

        /**
         * 动画播放结束时回调
         */
        void onFinish();

        /**
         * 动画播放取消时回调
         */
        void onCancel();
    }

    public void setOnShowingListener(OnShowingListener listener) {
        this.listener = listener;
    }

    private int dp2px(float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}