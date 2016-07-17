package mvp.zts.com.mvp_poj.presenter;

import android.app.Activity;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IRefreshView;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RefreshFragmentPersenter extends BasePresenter<IRefreshView> {

    public RefreshFragmentPersenter(Activity context, IRefreshView view) {
        super(context, view);
    }
}
