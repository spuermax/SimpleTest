package com.developers.test.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsoluteLayout;
import android.widget.ProgressBar;

import com.developers.test.R;
import com.developers.test.view.BridgePluginContext;
import com.developers.test.view.ESWebChromeClient;
import com.developers.test.view.ESWebViewClient;

/**
 * @Author yinzh
 * @Date 2019/11/3 14:15
 * @Description
 */
public class ProgressWebView extends WebView {

    protected Activity mActivity;

    protected ProgressBar progressbar;

    protected WebViewCallBack callBack;
    private BridgePluginContext mPluginContext;

    public void setWebViewCallBack(WebViewCallBack callBack) {
        this.callBack = callBack;
    }

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new AbsoluteLayout.LayoutParams(AbsoluteLayout.LayoutParams.MATCH_PARENT, 8, 0, 0));
        progressbar.setProgressDrawable(context.getResources().getDrawable(R.drawable.progress_bar_green_webview));
        addView(progressbar);
        setWebChromeClient(new ESWebChromeClient(this));
        setWebViewClient(new ESWebViewClient(this));
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        AbsoluteLayout.LayoutParams lp = (AbsoluteLayout.LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void initPlugin(Activity activity) {
        this.mActivity = activity;
        mPluginContext = new BridgePluginContext(mActivity);
    }

    public void setCustomWebChromeClient(WebChromeClient webChromeClient) {
        setWebChromeClient(webChromeClient);
    }

    public void setCustomWebViewClient(WebViewClient webViewClient) {
        setWebViewClient(webViewClient);
    }

    public BridgePluginContext getBridgePluginContext() {
        return mPluginContext;
    }

    public interface WebViewCallBack {
        void showTitle(String urlTitle);
    }
}
