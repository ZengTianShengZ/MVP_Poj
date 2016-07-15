package mvp.zts.com.mvp_poj.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import mvp.zts.com.mvp_base.BaseActivity;
import mvp.zts.com.mvp_poj.R;
import mvp.zts.com.mvp_poj.presenter.FragmentPresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IFragmentView;
import mvp.zts.com.mvp_poj.ui.fragment.DeFragment;
import mvp.zts.com.mvp_poj.ui.fragment.DtFragment;

/**
 * Created by Administrator on 2016/7/15.
 */
public class FragmentActivity  extends BaseActivity<FragmentPresenter>  implements IFragmentView {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    private FragmentManager fm;

    private DeFragment mDeFragment;
    private DtFragment mDtFragment;


    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter  = new FragmentPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initFragment();

    }

    private void initFragment() {
        fm = getSupportFragmentManager() ;
        FragmentTransaction mTransaction = fm.beginTransaction();

        if(mDeFragment==null){
            mDeFragment =new DeFragment();
            mTransaction.add(R.id.fragement_layout, mDeFragment);
        }else{
            mTransaction.show(mDeFragment);
        }
        mTransaction.commit();


    }

    @Override
    protected int getMenuRes() {
        return R.menu.mian_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        FragmentTransaction mTransaction = fm.beginTransaction();

        hideFragment(mTransaction);

        int id = item.getItemId();
        switch (id){
            case R.id.menu_1:

                if(mDeFragment==null){
                    mDeFragment =new DeFragment();
                    mTransaction.add(R.id.fragement_layout, mDeFragment);
                }else{
                    mTransaction.show(mDeFragment);
                }

                break;
            case R.id.menu_2:

                if(mDtFragment==null){
                    mDtFragment =new  DtFragment();
                    mTransaction.add(R.id.fragement_layout, mDtFragment);
                }else{
                    mTransaction.show(mDtFragment);
                }

                break;

        }
        mTransaction.commit();
        return super.onOptionsItemSelected(item);
    }

    private void hideFragment(FragmentTransaction mTransaction) {
        if(mDtFragment !=null){
            mTransaction.hide(mDtFragment);
        }
        if(mDeFragment !=null){
            mTransaction.hide(mDeFragment);
        }
    }
}
