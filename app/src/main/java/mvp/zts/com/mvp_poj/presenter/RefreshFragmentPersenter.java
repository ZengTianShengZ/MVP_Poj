package mvp.zts.com.mvp_poj.presenter;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IRefreshView;

/**
 * Created by Administrator on 2016/7/18.
 */
public class RefreshFragmentPersenter extends BasePresenter<IRefreshView> {

    public RefreshFragmentPersenter(Activity context, IRefreshView view) {
        super(context, view);
    }

    public void initData(){
        Log.i("log",".............initData .........");
        mView.showRefresh();

        List<String> strList = new ArrayList<String>();
        for (int i=0;i<10;i++){
            strList.add(""+i);
        }

        mView.getDataFinish();
        mView.fillData(strList);

    }

    public void addMoreData(){
        Log.i("log",".............addMoreData .........");
        mView.showRefresh();

        List<String> strList = new ArrayList<String>();
        for (int i=0;i<10;i++){
            strList.add("more_"+i);
        }

        mView.getDataFinish();
        mView.appendMoreDataToView(strList);

    }
}
