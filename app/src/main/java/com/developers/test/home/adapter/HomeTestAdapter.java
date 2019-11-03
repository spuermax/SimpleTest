package com.developers.test.home.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.developers.test.home.bean.HomeTestBean;

import java.util.List;

/**
 * @Author yinzh
 * @Date 2019/11/2 17:20
 * @Description
 */
public class HomeTestAdapter extends BaseQuickAdapter<HomeTestBean, BaseViewHolder> {

    public HomeTestAdapter(int layoutResId, @Nullable List<HomeTestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTestBean item) {

    }
}
