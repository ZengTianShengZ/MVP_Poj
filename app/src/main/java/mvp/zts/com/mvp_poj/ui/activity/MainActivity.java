package mvp.zts.com.mvp_poj.ui.activity;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseSwipeRefreshActivity;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.IView.IRefreshView;
import mvp.zts.com.mvp_poj.presenter.MainPresenter;
import mvp.zts.com.mvp_poj.ui.adapter.DataAdapter;

public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IRefreshView<String> {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.main_RecyclerView)
    RecyclerView main_RecyclerView;

    private DataAdapter mMianActivityAdapter;
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

    }

    /**
     * 初始化请求数据
     */
    @Override
    protected void intiData() {
        // 初始化数据
        mPresenter.initData();
        // 可刷新状态准备好了
        mPrepareRefresh = true;
    }

    /**
     * 刷新请求数据
     */
    @Override
    protected void onRefreshStarted() {
        mPresenter.addMoreData();

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
    public void hasNoMoreData() {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"无更多数据");
    }

    /**
     * 初始化填充数据
     * @param mData
     */
    @Override
    public void fillData(List mData) {

        mMianActivityAdapter.insertedAllItem(mData);
    }

    /**
     * 加载更多数据
     * @param mData
     */
    @Override
    public void appendMoreDataToView(List mData) {
        mMianActivityAdapter.appendMoreItem(mData);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.mian_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_1:
                SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"FragmentActivity");
                Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_2:
                SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"FragmentActivity");
                Intent intent1 = new Intent(MainActivity.this,FragmentActivity.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycleView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        main_RecyclerView.setLayoutManager(layoutManager);
        mMianActivityAdapter = new DataAdapter(mContext,adapterList);
        main_RecyclerView.setAdapter(mMianActivityAdapter);
    }
}
