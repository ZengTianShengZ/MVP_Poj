package mvp.zts.com.mvp_poj.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseFragmentActivity;
import mvp.zts.com.mvp_base.ui.fragment.BaseFragment;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.FragmentPresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IFragmentView;
import mvp.zts.com.mvp_poj.ui.fragment.HomeFragment;

public class FragmentActivity extends BaseFragmentActivity<FragmentPresenter> implements IFragmentView {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;
    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }


    private HomeFragment mHomeFragment;

    @Override
    protected int getLayout() {
        return R.layout.ac_fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new FragmentPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("onCreate","11111");

    }

    @Override
    protected BaseFragment initHomeFragment() {
        mHomeFragment = new HomeFragment();
        return  mHomeFragment;
    }
}
