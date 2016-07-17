package mvp.zts.com.mvp_poj.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.Bind;
import mvp.zts.com.mvp_base.Presenter.IView.ISwipeRefreshView;
import mvp.zts.com.mvp_base.ui.fragment.BaseSwipseRefreshFragment;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.IView.IRefreshView;
import mvp.zts.com.mvp_poj.presenter.RefreshFragmentPersenter;

/**
 * Created by Administrator on 2016/7/18.
 */
public class SwipseRefreshFragment extends BaseSwipseRefreshFragment<RefreshFragmentPersenter> implements IRefreshView {

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.fragment_RecyclerView)
    RecyclerView fragment_RecyclerView;

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

    }
    @Override
    protected void onRefreshStarted() {

    }


    @Override
    public void fillData(List mData) {

    }

    @Override
    public void appendMoreDataToView(List mData) {

    }

}
