package mvp.zts.com.mvp_poj.ui.fragment;

import mvp.zts.com.mvp_base.ui.fragment.BaseFragment;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.HFragmentPresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IHFragmentView;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HomeFragment extends BaseFragment<HFragmentPresenter> implements IHFragmentView {
    @Override
    protected int getLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initPresenter() {
       mPresenter = new HFragmentPresenter(getActivity(),this);
    }
}
