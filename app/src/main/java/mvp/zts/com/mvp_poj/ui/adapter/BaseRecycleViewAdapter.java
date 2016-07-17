package mvp.zts.com.mvp_poj.ui.adapter;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mvp.zts.com.mvp_poj.R;


/**
 *  RecyclerView.Adapter  基类
 *
 * @ClassName: BaseRecycleViewAdapter
 * @Description: TODO
 * @author zss
 * @date 2016-4-29 PM
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {

	//正常条目
	private static final int TYPE_NORMAL_ITEM = 0;
	//加载条目
	private static final int TYPE_LOADING_ITEM = 1;

	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;

	public static final int LAST_POSITION = -1;

	public BaseRecycleViewAdapter(Context context, int layoutId, List<T> datas) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayoutId = layoutId;
		mDatas = datas;
	}

	@Override
	public int getItemCount() {
		return mDatas.size() == 0 ? 0 : mDatas.size();
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
/*	@Override
	public int getItemViewType(int position) {
		*//*if (position + 1 == getItemCount()) {
			return TYPE_LOADING_ITEM;
		} else {
			return TYPE_NORMAL_ITEM;
		}*//*
	}*/

	@Override
	public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
		holder.updatePosition(position);
		convert(holder, mDatas.get(position), holder.getAdapterPosition());
		/*if(position + 1 == getItemCount()){
			TextView lodingTv = holder.getView(R.id.tv_loading);
			lodingTv.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(mOnLoadingListener!=null){
						mOnLoadingListener.loading(holder.getView(R.id.progress_loading));
					}
				}
			});
		}else {
			convert(holder, mDatas.get(position), holder.getPosition());
		}*/
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

		/*if(viewType == TYPE_NORMAL_ITEM){
			final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
			return viewHolder;
		}else{
			final RecycleViewHolder loadingViewHolder = RecycleViewHolder.get(mContext, parent, R.layout.loading_layout);
			return loadingViewHolder;
		}*/
		final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
		return viewHolder;

	}

	public abstract void convert(RecycleViewHolder holder, T t, int holderPosition);

	/**
	 * 添加一条数据
	 * @param t
	 * @param position
     */
	public void insertedItem(T t,int position){
		mDatas.add(position,t);
		notifyItemInserted(position);
	}
	public void insertedAllItem(List<T> data){
		mDatas.addAll(data);
		notifyDataSetChanged();
	}
	public void appendMoreItem(List<T> data){
		mDatas.addAll(0,data);
		notifyDataSetChanged();
	}
	/**
	 * 移除一条数据
	 * @param position
     */
	public void removePosition(int position){
		if(position >=0) {
			mDatas.remove(position);
			notifyItemRemoved(position);

		}
	}


}
