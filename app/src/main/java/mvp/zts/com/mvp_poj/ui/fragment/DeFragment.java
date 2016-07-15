package mvp.zts.com.mvp_poj.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mvp.zts.com.mvp_poj.R;

/**
 * Created by Administrator on 2016/7/15.
 */
public class DeFragment extends Fragment {

    Context mContext;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        mContext = getActivity();

        return view;
    }

}
