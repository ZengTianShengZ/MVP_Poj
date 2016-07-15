package mvp.zts.com.mvp_poj.ui;

/**
 * Created by Administrator on 2016/7/15.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.ListView;

import mvp.zts.com.mvp_poj.R;


public class sss extends Activity implements
        SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeLayout;
    private ListView listView;
    private boolean isRefresh = false;//是否刷新中

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sss);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(this);
        //加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
        swipeLayout.setColorSchemeResources(mvp.zts.com.mvp_base.R.color.colorPrimary, mvp.zts.com.mvp_base.R.color.colorPrimaryDark, mvp.zts.com.mvp_base.R.color.colorAccent);


    }

    public void onRefresh() {
        if(!isRefresh){

            isRefresh = true;

        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);

            }
        }, 3000); }
    }
}
