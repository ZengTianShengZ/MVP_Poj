package mvp.zts.com.mvp_poj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import butterknife.Bind;
import mvp.zts.com.mvp_base.ui.activity.BaseFragmentActivity;
import mvp.zts.com.mvp_base.ui.fragment.BaseFragment;
import mvp.zts.com.mvp_base.utils.SnackbarUtil;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.FragmentPresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IFragmentView;
import mvp.zts.com.mvp_poj.ui.fragment.HomeFragment;
import mvp.zts.com.mvp_poj.ui.fragment.SwipseRefreshFragment;

public class FragmentActivity extends BaseFragmentActivity<FragmentPresenter> implements IFragmentView {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }


    private HomeFragment mHomeFragment;
    private SwipseRefreshFragment mSwipseRefreshFragment;

    @Override
    protected int getLayout() {
        return R.layout.refresh_fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new FragmentPresenter(this,this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Back",true);
    }

    @Override
    protected BaseFragment initHomeFragment() {
        mHomeFragment = new HomeFragment();
        return  mHomeFragment;
    }

    @Override
    protected int getMenuRes() {
        return R.menu.fragment_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        hideFragment();
        FragmentTransaction mTransaction = fm.beginTransaction();
        int id = item.getItemId();
        switch (id){
            case R.id.menu_1:

                 ;

                if(mSwipseRefreshFragment==null){
                    mSwipseRefreshFragment = new SwipseRefreshFragment();
                    mTransaction.add(mvp.zts.com.mvp_base.R.id.fragement_layout, mSwipseRefreshFragment);
                }else{
                    mTransaction.show(mSwipseRefreshFragment);
                }

                break;
            case R.id.menu_2:

                if(mHomeFragment==null){
                    mHomeFragment = new HomeFragment();
                    mTransaction.add(mvp.zts.com.mvp_base.R.id.fragement_layout, mHomeFragment);
                }else{
                    mTransaction.show(mHomeFragment);
                }

                break;

        }
        mTransaction.commit();
        return super.onOptionsItemSelected(item);
    }

    private void hideFragment() {
        FragmentTransaction mTransaction = fm.beginTransaction();
        if(mSwipseRefreshFragment != null){
            mTransaction.hide(mSwipseRefreshFragment);
        }
        if(mHomeFragment != null){
            mTransaction.hide(mHomeFragment);
        }
        mTransaction.commit();
    }


}
