package com.bw.movie.zjh.module.ui.cinema;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.zjh.module.adapter.RecommendAdapter;
import com.bw.movie.zjh.module.base.BaseFragment;
import com.bw.movie.zjh.module.beans.cinema.CinemaMessage;
import com.bw.movie.zjh.module.beans.cinema.LikeCinemaBean;
import com.bw.movie.zjh.module.beans.cinema.RecommendTjBean;
import com.bw.movie.zjh.module.beans.cinema.UnLikeCinemaBean;
import com.bw.movie.zjh.module.utils.config.Config;
import com.bw.movie.zjh.module.utils.mvp.presenter.IPresenterImpl;
import com.bw.movie.zjh.module.utils.mvp.util.Apis;
import com.bw.movie.zjh.module.utils.mvp.view.IView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.header.WaveSwipeHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author : zjh
 * e-mail : zjh@163.com
 * date   : 2019/5/13 9:38
 * desc   :  推荐影院
 * version: 1.0
 */
public class CinemaRecommendFragment extends BaseFragment implements IView {
    private Unbinder bind;
    private IPresenterImpl iPresenter;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private int page = 0;
    private RecommendAdapter mAdapter;

    @Override
    protected int setView() {
        return R.layout.fragment_cineme_recommend;
    }

    @Override
    protected void init(View view) {
        bind = ButterKnife.bind(this, view);
        iPresenter = new IPresenterImpl(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        page = 1;
        //适配器
        mAdapter = new RecommendAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
        isLikeCinema();

        //刷新方法
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                joinApi(page);
                refreshlayout.finishRefresh(2000);
            }
        });
        //加载更多
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                page ++;
                joinApi(page);
                refreshlayout.finishLoadmore(2000);
            }
        });
        joinApi(page);
        //设置 Header 为 Material风格
        //refreshLayout.setRefreshHeader(new MaterialHeader(getActivity()).setShowBezierWave(true));
        //全屏水滴
        refreshLayout.setRefreshHeader(new WaveSwipeHeader(getActivity()));
        //设置 Footer 为 球脉冲
        refreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));
    }

    /*
     *   p层
     * */
    private void joinApi(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page + "");
        map.put("count", Config.COUNT_NUMBER_HOME + "");
        iPresenter.getPresenterData(Apis.RECOMMEND_GET, map, RecommendTjBean.class);
        //布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    /*
     *   点点关注
     * */
    private void isLikeCinema() {
        mAdapter.setRecommendListener(new RecommendAdapter.RecommendListener() {
            // 关注
            @Override
            public void onLike(int id, boolean isLike) {
                Map<String, String> map = new HashMap<>();
                map.put("cinemaId", id + "");
                if (isLike) {
                    iPresenter.getPresenterData(Apis.LOVECINEMA_GET, map, LikeCinemaBean.class);
                } else {
                    iPresenter.getPresenterData(Apis.NOLOVECINEMA_GET, map, UnLikeCinemaBean.class);
                }
                mAdapter.notifyDataSetChanged();
            }

            //点击条目进入详情
            @Override
            public void OnClick(String id, String logo, String name, String address) {
                CinemaMessage cinemaMessage = new CinemaMessage();
                cinemaMessage.setCinemaId(id);
                cinemaMessage.setLogo(logo);
                cinemaMessage.setName(name);
                cinemaMessage.setAddress(address);
                EventBus.getDefault().postSticky(cinemaMessage);
                startActivity(new Intent(getActivity(), CinemaDetailsActivity.class));
            }
        });
    }


    /*
     *   回调方法
     * */
    @Override
    public void viewDataSuccess(Object data) {
        if (data instanceof RecommendTjBean) {
            RecommendTjBean recommendTjBean = (RecommendTjBean) data;
            if (page == 1) {
                mAdapter.setDatas(recommendTjBean.getResult());
            } else {
                mAdapter.addDatas(recommendTjBean.getResult());
            }

        } else if (data instanceof LikeCinemaBean) {
            LikeCinemaBean likeCinemaBean = (LikeCinemaBean) data;
            Toast.makeText(getActivity(), likeCinemaBean.getMessage(), Toast.LENGTH_SHORT).show();
            joinApi(page);
        } else if (data instanceof UnLikeCinemaBean) {
            UnLikeCinemaBean unLikeCinemaBean = (UnLikeCinemaBean) data;
            Toast.makeText(getActivity(), unLikeCinemaBean.getMessage(), Toast.LENGTH_SHORT).show();
            joinApi(page);
        }


    }


    /*
     *   内存优化
     * */

    @Override
    public void onDestroy() {
        super.onDestroy();
        iPresenter.onDetach();
        bind.unbind();
    }
}
