package mvp.zts.com.mvp_poj.presenter;

import android.app.Activity;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_base.Presenter.IView.IBaseView;
import mvp.zts.com.mvp_poj.presenter.IView.IHFragmentView;
import mvp.zts.com.mvp_poj.ui.fragment.HomeFragment;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HFragmentPresenter extends BasePresenter<IHFragmentView> {
    public HFragmentPresenter(Activity context, IHFragmentView view) {
        super(context, view);
    }

}
