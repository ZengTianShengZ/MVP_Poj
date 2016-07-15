package mvp.zts.com.mvp_poj.presenter;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

import mvp.zts.com.mvp_base.Presenter.BasePresenter;
import mvp.zts.com.mvp_poj.presenter.IView.IMainView;

/**
 * Created by Administrator on 2016/7/15.
 */
public class MainPresenter extends BasePresenter<IMainView>{

    public MainPresenter(Activity context, IMainView view) {
        super(context, view);
    }

    public void initData(){

        mView.showRefresh();

        List<String> strList = new ArrayList<String>();
        for (int i=0;i<10;i++){
            strList.add(""+i);
        }

        mView.getDataFinish();
        mView.fillData(strList);

    }

    public void addMoreData(){

        mView.showRefresh();

        String str = "more_";

        mView.getDataFinish();
        mView.appendMoreDataToView(str);

    }

}
