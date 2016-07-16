package mvp.zts.com.mvp_base.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;

/**
 * BaseFragment
 * @param <P> BasePresenter
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected Context mContext;
    protected View mView;
    protected P mPresenter;

    protected abstract int getLayout();
    protected abstract void initPresenter();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(getLayout(), container, false);
        mContext = getActivity();

        initPresenter();
        checkPresenterIsNull();

        return mView;
    }

    private void checkPresenterIsNull(){
        if(mPresenter == null){
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
