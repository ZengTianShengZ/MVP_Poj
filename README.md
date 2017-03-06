这个项目简单封装了一个简单的MVP设计框架，根据框架可以很容易的在你自己的项目中实现 MVP 设计模式。继承我封装好的 BaseActivity，BaseFragmentActivity，BaseSwipeRefreshActivity，BaseFragment，BaseSwipseRefreshFragment 可以很好的实现 MVP  模式的项目开发。
也许你知道 所谓的MVP 设计模式就是：

```
M就是Model ，这里主要负责的就是业务处理，数据的获取，例如数据库的读写，http的网络数据的处理。
V就是View ，顾名思义视图的意思，这里主要的任务就是处理各个界面ui控件的处理。
P就是Presenter ，控制器，这里负责的是Model与View之间的联系操作。
```
其实简单的用一句话描述就是：将View层抽象成view接口，将业务逻辑统统交给 Presenter 层去做。

也许还不太了解或是已经了解的可以来看下面的 demo 

下面的一个 activity 需要完成的功能是 

(1)显示初始化数据 list data 
(2)下拉刷新能加载新数据
(3)数据加载成功，或出错做一些提示交互。

其实这些基本内容是我们经常和大量用到的一些场景。那来看看咱们怎么利用mvp模式来分层实现：
首先继承我封装好了的 BaseSwipeRefreshActivity ，并且 自己 实现 MainPresenter 类 和 IRefreshView 接口，那么 MainActivity  就可以实现 简单的 mvp 设计模式了。

**先分析 MVP 中 V层的实现，及 MainActivity   的实现：**

```
public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IRefreshView<String> {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.main_RecyclerView)
    RecyclerView main_RecyclerView;

    private DataAdapter mMianActivityAdapter;
    private List<String> adapterList = new ArrayList<String>();

    @Override
    protected Toolbar getToolbar() {
        return mToolbar;
    }
    
    @Override
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }
    
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initRecycleView();
        
    }

    /**
     * 初始化请求数据
     */
    @Override
    protected void intiData() {
        // 初始化数据
        mPresenter.initData();
        // 可刷新状态准备好了
        mPrepareRefresh = true;
    }

    /**
     * 刷新请求数据
     */
    @Override
    protected void onRefreshStarted() {
        mPresenter.addMoreData();

    }

    @Override
    public void showEmptyView() {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据为空");
    }

    @Override
    public void showErrorView(Throwable throwable) {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据出错");
    }

    @Override
    public void hasNoMoreData() {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"无更多数据");
    }

    /**
     * 初始化填充数据
     * @param mData
     */
    @Override
    public void fillData(List mData) {

        mMianActivityAdapter.insertedAllItem(mData);
    }

    /**
     * 加载更多数据
     * @param mData
     */
    @Override
    public void appendMoreDataToView(List mData) {
        mMianActivityAdapter.appendMoreItem(mData);
    }
    
    @Override
    protected int getMenuRes() {
        return R.menu.mian_menu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_1:
                SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"FragmentActivity");
                Intent intent = new Intent(MainActivity.this,FragmentActivity.class);
                startActivity(intent);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void initRecycleView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        main_RecyclerView.setLayoutManager(layoutManager);
        mMianActivityAdapter = new DataAdapter(mContext,adapterList);
        main_RecyclerView.setAdapter(mMianActivityAdapter);
    }
}
```
代码看的有点多，不过相对那种把什么功能都放在 activity 来讲已经很少了，而且看上面代码结构清晰，功能明确，职责分明，耦合度低，很适合扩展。 ^-^ ///

其实上面的 activity 主要负责 
(1) view 的 一些初始化，如：

```
@Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.main_RecyclerView)
    RecyclerView main_RecyclerView;
```

```
   private void initRecycleView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        main_RecyclerView.setLayoutManager(layoutManager);
        mMianActivityAdapter = new DataAdapter(mContext,adapterList);
        main_RecyclerView.setAdapter(mMianActivityAdapter);
    }
```
（2）view 的一些更新，如：

```
 @Override
    public void showEmptyView() {
        SnackbarUtil.PrimarySnackbar(mContext,mToolbar,"请求数据为空");
    }

```

```
 /**
     * 初始化填充数据
     * @param mData
     */
    @Override
    public void fillData(List mData) {

        mMianActivityAdapter.insertedAllItem(mData);
    }
```
而数据的请求部分只有单单两句：

```
 mPresenter.initData();
 mPresenter.addMoreData();
```
那么再来看一眼 什么是 MVP 设计模式：

```
M就是Model ，这里主要负责的就是业务处理，数据的获取，例如数据库的读写，http的网络数据的处理。
V就是View ，顾名思义视图的意思，这里主要的任务就是处理各个界面ui控件的处理。
P就是Presenter ，控制器，这里负责的是Model与View之间的联系操作。
```
咱们的 activity 就是 mvp 中的 v 层 ，而且职责明确，只负责 ui 处理的 部分。

其他都交给了 Presenter  去做， 那咱们接下来再来分析分析 Presenter  是怎么做到 操作
model 和 view 之间的联系的。

**分析 MVP 中 P 层的实现 及 MainPresenter：**

先看 activity 有继承 IRefreshView 这个接口

```
public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IRefreshView<String> {
}
```
那么咱们在 Presenter 取得数据 并调用 IRefreshView 接口，并在 MainActivity  实现 该接口的方法，这不就是：

```
P就是Presenter ，控制器，这里负责的是Model与View之间的联系操作。
```

具体看一下 MainPresenter  类：
```
public class MainPresenter extends BasePresenter<IRefreshView>{

    public MainPresenter(Activity context, IRefreshView view) {
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

        List<String> strList = new ArrayList<String>();
        for (int i=0;i<10;i++){
            strList.add("more_"+i);
        }

        mView.getDataFinish();
        mView.appendMoreDataToView(strList);

    }

}
```

看 

```
 mPresenter.initData();
 mPresenter.addMoreData();
```
就是 MainPresenter  类 里面的 方法 ，及Presenter 层，其实请求数据应该是 Model 层的，但咱们的示例代码请求模拟数据太简单的，就没有再弄个 类（及Model 层）来封装。
数据请求前有个：

```
mView.showRefresh();
```
数据请求后有个：

```
 mView.getDataFinish();
```
这就是 persenter 层控制 model 层和 view 层的 作用了。

**接下来看一下 抽象 view ：**

```
public interface IRefreshView<T> extends ISwipeRefreshView {

    void fillData(List<T> mData);

    void appendMoreDataToView(List<T> mData);

    void hasNoMoreData();
 
}
```

```
public interface ISwipeRefreshView extends IBaseView {


    void getDataFinish();

    void showEmptyView();

    void showErrorView(Throwable throwable);

    void showRefresh();

    void hideRefresh();
}
```

好了，看到这里不知道明白了 MVP 设计模式的原理和好处了没。大概终结一下：
activity 或 fragment 或是 视图层要做的一些数据请求从而跟新 视图，可以将中间这些操作交给 persenter 去做，视图只负责 ui  的处理，而 persenter 需要 去操作 modle 得到数据后通知跟新视图，怎么通知呢，就是 利用 接口回调 的形式 更新视图。也就是这开头讲的这么一句话：

```
将View层抽象成view接口，将业务逻辑统统交给 Presenter 层去做。
```

建议可以下载源码结合本片介绍，会有助于理解，本片博只是简单介绍一下流程，源码做了一点封装，可以到我的[github clone][1] ,欢迎stars ，此项目会继续更新维护


  [1]: https://github.com/ZengTianShengZ/MVP_Poj
