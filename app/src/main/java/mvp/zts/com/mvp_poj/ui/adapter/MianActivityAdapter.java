package mvp.zts.com.mvp_poj.ui.adapter;

import android.content.Context;
import android.widget.TextView;
import java.util.List;

import mvp.zts.com.mvp_poj.R;


/**
 * Created by Administrator on 2016/5/20.
 */
public class MianActivityAdapter extends BaseRecycleViewAdapter   {

    private Context mContext;


    public MianActivityAdapter(Context context, List<String> mListItems) {
        super(context, R.layout.item_mian_activity, mListItems);
        this.mContext  =context;
    }

    @Override
    public void convert(RecycleViewHolder holder, Object obj, int holderPosition) {
        final String item = (String)obj;

        TextView context_Tv = holder.getView( R.id.item_mian_textview);
        context_Tv.setText(""+item);
    }

}
