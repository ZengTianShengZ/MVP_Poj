package mvp.zts.com.mvp_poj.presenter;

import android.app.Activity;
import android.widget.ImageView;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IFragmentView;

/**
 * Created by Administrator on 2016/7/15.
 */
public class FragmentPresenter  extends BasePresenter<IFragmentView> {

    public FragmentPresenter(Activity context, IFragmentView view) {
        super(context, view);
    }
}
