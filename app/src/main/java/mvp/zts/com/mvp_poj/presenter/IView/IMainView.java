package mvp.zts.com.mvp_poj.presenter.IView;

import java.util.List;

import mvp.zts.com.mvp_base.Presenter.IView.ISwipeRefreshView;

/**
 * Created by Administrator on 2016/7/15.
 */
public interface IMainView<T> extends ISwipeRefreshView {

    void fillData(List<T> mData);

    void appendMoreDataToView(T data);

    void hasNoMoreData();



}
