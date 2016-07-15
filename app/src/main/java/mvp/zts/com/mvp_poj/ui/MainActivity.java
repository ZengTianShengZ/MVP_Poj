package mvp.zts.com.mvp_poj.ui;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import mvp.zts.com.mvp_base.BaseSwipeRefreshActivity;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.IView.IMainView;
import mvp.zts.com.mvp_poj.presenter.MainPresenter;
import mvp.zts.com.mvp_poj.ui.adapter.MianActivityAdapter;

public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IMainView<String> {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.main_RecyclerView)
    RecyclerView main_RecyclerView;
    @Bind(R.id.bttt)
    Button bttt;

    private MianActivityAdapter mMianActivityAdapter;
    private List<String> adapterList = new ArrayList<String>();

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }


    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRecycleView();

        mPresenter.initData();
        prepareRefresh = true;

        bttt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRefreshStarted() {
        mPresenter.addMoreData();
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据");
    }

    @Override
    public void showEmptyView() {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据为空");
    }

    @Override
    public void showErrorView(Throwable throwable) {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据出错");
    }

    @Override
    public void fillData(List mData) {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"fillData");
        Log.i("fillData","fillData");
        mMianActivityAdapter.insertedAllItem(mData);
    }

    @Override
    public void appendMoreDataToView(String data) {
        mMianActivityAdapter.insertedItem(data,0);
    }

    @Override
    public void hasNoMoreData() {

    }


    private void initRecycleView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        main_RecyclerView.setLayoutManager(layoutManager);
        mMianActivityAdapter = new MianActivityAdapter(mContext,adapterList);
        main_RecyclerView.setAdapter(mMianActivityAdapter);
    }


}
