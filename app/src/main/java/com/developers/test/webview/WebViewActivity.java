package com.developers.test.webview;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.developers.test.R;
import com.supermax.base.mvp.QsActivity;

import java.util.List;

import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:13
 * @Description
 */
public class WebViewActivity extends QsActivity implements EasyPermissions.PermissionCallbacks{

    public static final int LIVE = 1;
    public static final int REPLAY = 2;

    private WebView webView;
    private String url = "";
    private String title = "";

    private int mType;

    @Override
    public int layoutId() {
        return R.layout.activity_webview;
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        url = getIntent().getStringExtra("url");
        title = getIntent().getStringExtra("title");
        mType = getIntent().getIntExtra("type", 0);

        webView = findViewById(R.id.webView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
//        webSettings.setAllowFileAccess(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setAppCacheEnabled(false);
        webSettings.setDomStorageEnabled(true);
        //允许 http/https混用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        webView.setInitialScale(100);

        //下载监听
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        webView.loadUrl(url);

    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webView != null) {

            // 如果先调用destroy()方法，则会命中if (isDestroyed()) return;这一行代码，需要先onDetachedFromWindow()，再
            // destory()
            ViewParent parent = webView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(webView);
            }

            webView.stopLoading();
            // 退出时调用此方法，移除绑定的服务，否则某些特定系统会报错
            webView.getSettings().setJavaScriptEnabled(false);

            webView.removeAllViews();
            webView.clearCache(true);
            webView.clearHistory();
            webView.clearFormData();
            webView.destroy();
            webView = null;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //这个方法有个前提是，用户点击了“不再询问”后，才判断权限没有被获取到
            new AppSettingsDialog.Builder(this)
                    .setRationale("缺少权限，此应用程序可能无法正常工作。请点击「应用权限」打开所需权限")
                    .setTitle("必需权限")
                    .build()
                    .show();
        }
    }



}
