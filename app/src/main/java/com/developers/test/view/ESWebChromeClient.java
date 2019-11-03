package com.developers.test.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.developers.test.R;
import com.developers.test.common.PhotoUtil;
import com.supermax.base.common.widget.toast.QsToast;

import java.io.File;
import java.util.ArrayList;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:18
 * @Description
 */
public class ESWebChromeClient extends WebChromeClient {

    private ProgressWebView mWebview;

    private ValueCallback<Uri> mUploadCallbackBelow;
    private ValueCallback<Uri[]> mUploadCallbackAboveL;

    public ESWebChromeClient(ProgressWebView webView) {
        this.mWebview = webView;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if (newProgress == 100) {
            mWebview.progressbar.setVisibility(GONE);
        } else {
            if (mWebview.progressbar.getVisibility() == GONE) {
                mWebview.progressbar.setVisibility(VISIBLE);
            }
            mWebview.progressbar.setProgress(newProgress);
        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onReceivedTitle(WebView view, String title) {
        super.onReceivedTitle(view, title);
        if (title != null && !view.getUrl().contains(title) && mWebview.callBack != null) {
            mWebview.callBack.showTitle(title);
        }
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert);
        builder.setTitle("提示")
                .setMessage(message)
                .setPositiveButton("确定", null);
        builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                return false;
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
        return true;
    }

    /**
     * 16(Android 4.1.2) <= API <= 20(Android 4.4W.2)回调此方法
     */
    public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
        mUploadCallbackBelow = uploadMsg;
        takePhoto();
    }

    /**
     * API >= 21(Android 5.0.1)回调此方法
     */
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        // (1)该方法回调时说明版本API >= 21，此时将结果赋值给 mUploadCallbackAboveL，使之 != null
        mUploadCallbackAboveL = filePathCallback;
        takePhoto();
        return true;
    }

    private void takePhoto() {
//        PhotoUtil.openAlbum(mWebview.mActivity, false);// 打开相机需要自己去写
        mWebview.getBridgePluginContext().setActivityResult(new BridgePluginContext.Callback() {
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent data) {
                //相册
                if (mUploadCallbackBelow != null) {
                    chooseBelow(requestCode, data);
                } else if (mUploadCallbackAboveL != null) {
                    chooseAbove(requestCode, data);
                } else {
                    QsToast.show("发生错误");
                }
            }
        });
    }

    /**
     * Android API < 21(Android 5.0)版本的回调处理
     */
    private void chooseBelow(int requestCode, Intent data) {
//        if (PhotoUtil.CHOOSE_PHOTO == requestCode) {
//            if (data != null) {
//                ArrayList<String> pathList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//                if (pathList.size() > 0) {
//                    mUploadCallbackBelow.onReceiveValue(Uri.fromFile(new File(pathList.get(0))));
//                } else {
//                    mUploadCallbackBelow.onReceiveValue(null);
//                }
//            } else {
//                mUploadCallbackBelow.onReceiveValue(null);
//            }
//        } else {
//            mUploadCallbackBelow.onReceiveValue(null);
//        }
//        mUploadCallbackBelow = null;
    }

    /**
     * Android API >= 21(Android 5.0) 版本的回调处理
     */
    private void chooseAbove(int requestCode, Intent data) {
//        if (PhotoUtil.CHOOSE_PHOTO == requestCode) {
//            if (data != null) {
//                ArrayList<String> pathList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
//                if (pathList.size() > 0) {
//                    mUploadCallbackAboveL.onReceiveValue(new Uri[]{Uri.fromFile(new File(pathList.get(0)))});
//                } else {
//                    mUploadCallbackAboveL.onReceiveValue(null);
//                }
//            } else {
//                mUploadCallbackAboveL.onReceiveValue(null);
//            }
//        } else {
//            mUploadCallbackAboveL.onReceiveValue(null);
//        }
//        mUploadCallbackAboveL = null;
    }
}
