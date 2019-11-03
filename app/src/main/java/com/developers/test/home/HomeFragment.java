package com.developers.test.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.developers.test.R;
import com.developers.test.home.adapter.HomeTestAdapter;
import com.developers.test.home.bean.HomeTestBean;
import com.developers.test.webview.WebViewActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.supermax.base.common.log.L;
import com.supermax.base.common.utils.ImageHelper;
import com.supermax.base.common.utils.QsHelper;
import com.supermax.base.common.viewbind.annotation.Bind;
import com.supermax.base.common.widget.toast.QsToast;
import com.supermax.base.mvp.fragment.QsFragment;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yinzh
 * @Date 2019/11/2 16:36
 * @Description 首页
 */
public class HomeFragment extends QsFragment {

    @Bind(R.id.recycler)
    RecyclerView recyclerView;
    @Bind(R.id.smart_layout)
    SmartRefreshLayout smartRefreshLayout;
    @Bind(R.id.mz_banner)
    MZBannerView mzBannerView;

    private HomeTestAdapter adapter;
    private List<HomeTestBean> list = new ArrayList<>();
    private List<String> listStr = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.fragment_home;
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

        for (int i = 0; i < 4; i++) {
            listStr.add("https://scd4a2b5c5k9-sb-qn.qiqiuyun.net/files/system/2019/09-17/15000990b3ee834592.png?7.5.14.57");
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
                L.i("AAAAAAAAAAAAA", Thread.currentThread() + "-------");
                adapter.setNewData(list);
                smartRefreshLayout.finishRefresh(true);
                QsToast.show("刷新成功");
            }
        });



        mzBannerView.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int i) {
                Bundle bundle = new Bundle();
                bundle.putString("url", "https://blog.csdn.net/qq_37492806/article/details/84939157");
                bundle.putString("title", "标题为null");

                intent2Activity(WebViewActivity.class, bundle);
            }
        });



        mzBannerView.setPages(listStr, new MZHolderCreator() {
            @Override
            public MZViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });





    }


    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.image_banner);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String data) {
            // 数据绑定
            QsHelper.getInstance().getImageHelper().createRequest(context).load(data).into(mImageView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mzBannerView.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        mzBannerView.start();//开始轮播
    }
}
