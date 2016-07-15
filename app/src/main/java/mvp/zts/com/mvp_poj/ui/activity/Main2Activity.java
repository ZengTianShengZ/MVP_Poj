package mvp.zts.com.mvp_poj.ui.activity;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import mvp.zts.com.mvp_poj.R;

public class Main2Activity extends AppCompatActivity implements
        SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout swipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.sssssssss);
        swipeLayout.setOnRefreshListener(this);
        //加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
        swipeLayout.setColorSchemeResources(mvp.zts.com.mvp_base.R.color.colorPrimary, mvp.zts.com.mvp_base.R.color.colorPrimaryDark, mvp.zts.com.mvp_base.R.color.colorAccent);


    }



    @Override
    public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            public void run() {
                swipeLayout.setRefreshing(false);

            }
        }, 3000);
    }
}
