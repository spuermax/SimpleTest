package com.developers.test.view;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:19
 * @Description
 */
public class ESWebViewClient extends WebViewClient {

    private ProgressWebView mWebview;

    public ESWebViewClient(ProgressWebView mWebview) {
        this.mWebview = mWebview;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        String title = view.getTitle();
        if (!TextUtils.isEmpty(title) && !view.getUrl().contains(title) && mWebview.callBack != null) {
            mWebview.callBack.showTitle(title);
        }
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);
        if ((failingUrl != null && !failingUrl.equals(view.getUrl()) && !failingUrl.equals(view.getOriginalUrl())) /* not subresource error*/
                || (failingUrl == null && errorCode != -12) /*not bad url*/
                || errorCode == -1) { //当 errorCode = -1 且错误信息为 net::ERR_CACHE_MISS
            return;
        }

        if (!TextUtils.isEmpty(failingUrl)) {
            if (failingUrl.equals(view.getUrl())) {
                //TODO url处理加载失败
            }
        }
    }
}
