package com.developers.test.start;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.test.main.MainActivity;
import com.developers.test.R;
import com.supermax.base.common.viewbind.annotation.Bind;
import com.supermax.base.mvp.QsActivity;

/**
 * @Author yinzh
 * @Date 2019/11/2 16:13
 * @Description 启动页
 */
public class SplashActivity extends QsActivity {

    @Bind(R.id.iv_ad)
    ImageView ivAd;
    @Bind(R.id.tv_ad_skip)
    TextView tvAdSkip;

    private CountDownTimer countDownTimer;
    //显示广告页
    private boolean isShowAdImg = false;
    //倒计时
    private int mCountDownTime = 2000;

    @Override
    public int layoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        countDownTimer = new CountDownTimer(mCountDownTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                tvAdSkip.setText(String.format(getString(R.string.jump_ad), (millisUntilFinished + 500) / 1000));
            }

            @Override
            public void onFinish() {
                startApp();
            }
        };

        countDownTimer.start();
    }

    /**
     * 跳转到主界面
     */
    protected void startApp() {
        intent2Activity(MainActivity.class);

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
