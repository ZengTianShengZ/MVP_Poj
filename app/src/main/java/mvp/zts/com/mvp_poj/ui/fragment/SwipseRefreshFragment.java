package mvp.zts.com.mvp_poj.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.fragment.BaseSwipseRefreshFragment;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.IView.IRefreshView;
import mvp.zts.com.mvp_poj.presenter.RefreshFragmentPersenter;
import mvp.zts.com.mvp_poj.ui.adapter.DataAdapter;

/**
 * Created by Administrator on 2016/7/18.
 */
public class SwipseRefreshFragment extends BaseSwipseRefreshFragment<RefreshFragmentPersenter> implements IRefreshView {

    @Bind(R.id.fragment_SwipeRefreshLayout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.fragment_RecyclerView)
    protected RecyclerView fragment_RecyclerView;

    private DataAdapter mDataAdapter;
    private List<String> adapterList = new ArrayList<String>();

    @Override
    protected int getLayout() {
        return R.layout.swipse_refresh_fragment;
    }
    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }
    @Override
    protected void initPresenter() {
        mPresenter = new RefreshFragmentPersenter(getActivity(),this);
    }



    @Override
    public void showEmptyView() {
        SnackbarUtil.PrimarySnackbar(mContext,fragment_RecyclerView,"请求数据为空");
    }

    @Override
    public void showErrorView(Throwable throwable) {
        SnackbarUtil.PrimarySnackbar(mContext,fragment_RecyclerView,"请求数据出错");
    }

    @Override
    public void hasNoMoreData() {
        SnackbarUtil.PrimarySnackbar(mContext,fragment_RecyclerView,"无更多数据");
    }


    @Override
    protected void intiData() {
        initRecycleView();
        mPresenter.initData();
        mPrepareRefresh = true;
    }
    @Override
    protected void onRefreshStarted() {
        mPresenter.addMoreData();
    }


    @Override
    public void fillData(List mData) {
        mDataAdapter.insertedAllItem(mData);
    }

    @Override
    public void appendMoreDataToView(List mData) {
        mDataAdapter.appendMoreItem(mData);
    }


    private void initRecycleView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        fragment_RecyclerView.setLayoutManager(layoutManager);
        mDataAdapter = new DataAdapter(mContext,adapterList);
        fragment_RecyclerView.setAdapter(mDataAdapter);
    }

}
