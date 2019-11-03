package com.developers.test.mine;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.developers.test.R;
import com.developers.test.home.adapter.HomeTestAdapter;
import com.developers.test.home.bean.HomeTestBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.header.FalsifyHeader;
import com.scwang.smartrefresh.layout.header.TwoLevelHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.supermax.base.common.log.L;
import com.supermax.base.common.viewbind.annotation.Bind;
import com.supermax.base.common.widget.toast.QsToast;
import com.supermax.base.mvp.fragment.QsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yinzh
 * @Date 2019/11/2 16:38
 * @Description
 */
public class MineFragment extends QsFragment {


    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.smart_layout)
    SmartRefreshLayout smartRefreshLayout;

    private HomeTestAdapter adapter;
    private List<HomeTestBean> list = new ArrayList<>();


    @Override
    public int layoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeTestAdapter(R.layout.adapter_home, null);
        recyclerView.setAdapter(adapter);

        for (int i = 0; i < 10; i++) {
            HomeTestBean bean = new HomeTestBean();
            list.add(bean);
        }

        adapter.setNewData(list);

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                QsToast.show("你好");
            }
        });

        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                adapter.setNewData(createData());
                smartRefreshLayout.finishRefresh(true);
                Log.i("AAAAAAAAAAA", adapter.getData().size() + "--onRefresh---");
                QsToast.show("刷新成功");
            }
        });

        smartRefreshLayout.setRefreshHeader(new TwoLevelHeader(getContext()));


        smartRefreshLayout.setEnableLoadMore(true);

        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                adapter.addData(createData());
                Log.i("AAAAAAAAAAA", adapter.getData().size() + "---onLoadMore--");
                smartRefreshLayout.finishLoadMore(true);
            }
        });


    }

    private List<HomeTestBean> createData() {
        List<HomeTestBean> list1 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeTestBean bean = new HomeTestBean();
            list1.add(bean);
            list1.add(bean);
        }

        return list1;
    }

}
