package com.xiaoxing.module.shopcar.activity;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/12 16:02
 * 邮箱：2235445233@qq.com
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ab.R;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseApplication;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.swipyrefreshlayout.SwipyRefreshLayout;
import com.xiaoxing.common.view.swipyrefreshlayout.SwipyRefreshLayoutDirection;
import com.xiaoxing.module.shopcar.adapter.Adapter_Shopcart;
import com.xiaoxing.module.shopcar.model.CarDelDzfk;
import com.xiaoxing.module.shopcar.model.GoodsInfo2;
import com.xiaoxing.module.shopcar.model.M_QueRenDingDanGoods;
import com.xiaoxing.module.shopcar.model.OrderConfirmDzfk;
import com.xiaoxing.module.shopcar.model.ShopCarJiuTangGuoJi;
import com.xiaoxing.module.shopcar.model.StoreInfo;
import com.socks.library.KLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：购物车界面
 * 作者：徐小星 on 2016/7/21 0021 12:42
 * 邮箱：xx@yougudongli.com
 */
public class Activity_Car_JiuTangGuoJi extends BaseActivity implements Adapter_Shopcart.CheckInterface,
        Adapter_Shopcart.ModifyCountInterface, Adapter_Shopcart.GroupEdtorListener, OnMessageResponse, View.OnClickListener {

    public static Activity_Car_JiuTangGuoJi instance;
    ImageView back;
    TextView title;
    TextView subtitle;
    LinearLayout topBar;
    ExpandableListView exListView;
    TextView tvTotalPrice;
    CheckBox allChekbox;
    TextView tvDelete;
    TextView tvGoToPay;

    LinearLayout llShar;
    LinearLayout llInfo;

    TextView tvShare;
    TextView tvSave;
    LinearLayout llCart;
    LinearLayout cart_empty;
    LinearLayout ll_jie_suan;
    private Context context;
    private int totalPrice = 0;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private Adapter_Shopcart mAdapterShopcart;
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo2>> children = new HashMap<String, List<GoodsInfo2>>();// 子元素数据列表
    private int flag = 0;


    SwipyRefreshLayout mSwipyRefreshLayout;
    private static int currentPage = 1;


    @Override
    public int bindLayout() {
        return R.layout.fragment_car;
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        context = this;
        setTitleHidden();
    }

    public static Activity_Car_JiuTangGuoJi getSingleton() {
        if (instance == null) {
            synchronized (BaseApplication.class) {
                if (instance == null) {
                    instance = new Activity_Car_JiuTangGuoJi();
                }
            }
        }
        return instance;
    }

    @Override
    public void initView(View view) {
        super.initView(view);

        back = (ImageView) view.findViewById(R.id.back);
        title = (TextView) view.findViewById(R.id.title);
        subtitle = (TextView) view.findViewById(R.id.subtitle);
        topBar = (LinearLayout) view.findViewById(R.id.top_bar);
        exListView = (ExpandableListView) view.findViewById(R.id.exListView);
        tvTotalPrice = (TextView) view.findViewById(R.id.tv_total_price);
        allChekbox = (CheckBox) view.findViewById(R.id.all_chekbox);
        tvDelete = (TextView) view.findViewById(R.id.tv_delete);
        tvGoToPay = (TextView) view.findViewById(R.id.tv_go_to_pay);
        llShar = (LinearLayout) view.findViewById(R.id.ll_shar);
        llInfo = (LinearLayout) view.findViewById(R.id.ll_info);
        tvShare = (TextView) view.findViewById(R.id.tv_share);
        tvSave = (TextView) view.findViewById(R.id.tv_save);
        llCart = (LinearLayout) view.findViewById(R.id.ll_cart);
        cart_empty = (LinearLayout) view.findViewById(R.id.layout_cart_empty);
        ll_jie_suan = (LinearLayout) view.findViewById(R.id.ll_jie_suan);
        mSwipyRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.main_SwipyRefreshLayout);

        subtitle.setOnClickListener(this);
        allChekbox.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
        tvGoToPay.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        // 设置分页第一页的索引 默认为0
        mSwipyRefreshLayout.setFirstIndex(currentPage);
        mSwipyRefreshLayout.setDirection(SwipyRefreshLayoutDirection.TOP);
        // 监听事件
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {

                groups.clear();
                children.clear();
                mAdapterShopcart.notifyDataSetChanged();

                KLog.e("== ======>>>获取web段购物车数据===>>getWebCartList");
                BaseApi.carList(Activity_Car_JiuTangGuoJi.this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID));
            }

            @Override
            public void onLoad(int index) {
                mSwipyRefreshLayout.setRefreshing(false);
            }
        });

        initEvents();
        if (isLogin()) {
//            ProgressDialogUtils.showProgressDialog(getActivity(), "数据加载中...");
            cartList();
        } else {
            setCartNum();
        }

    }

    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {

            cartList();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        KLog.e("resume======");
        if (isLogin()) {
//            ProgressDialogUtils.showProgressDialog(getActivity(), "数据加载中...");

            cartList();
        } else {
            setCartNum();
        }
    }

    /**
     * 获取购物车列表数据
     */
    public void cartList() {
        if (groups != null && children != null && mAdapterShopcart != null) {

            groups.clear();
            children.clear();
            mAdapterShopcart.notifyDataSetChanged();

            KLog.e("== ======>>>获取web段购物车数据===>>getWebCartList");
            BaseApi.carList(Activity_Car_JiuTangGuoJi.this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID));
        }
    }


    private void initEvents() {
        mAdapterShopcart = new Adapter_Shopcart(groups, children, this, sHelper);
        mAdapterShopcart.setCheckInterface(this);// 关键步骤1,设置复选框接口
        mAdapterShopcart.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
        mAdapterShopcart.setmListener(this);
        exListView.setAdapter(mAdapterShopcart);

    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo2> childs = children.get(group.getId());
            for (GoodsInfo2 goodsInfo2 : childs) {
                count += 1;
            }
        }

        //购物车已清空
        if (count == 0) {
            clearCart();
        } else {
            title.setText("購物車" + "(" + count + ")");
            totalPrice = 0;
            tvTotalPrice.setText("NT￥" + totalPrice);
        }
    }

    /**
     * 显示购物车数据
     */
    private void showCart() {
        subtitle.setVisibility(View.VISIBLE);
        llCart.setVisibility(View.VISIBLE);
        cart_empty.setVisibility(View.GONE);
    }

    /**
     * 购物车为空
     */
    private void clearCart() {
        title.setText("購物車" + "(" + 0 + ")");
        subtitle.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        cart_empty.setVisibility(View.VISIBLE);
    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<StoreInfo> toBeDeleteGroups = new ArrayList<StoreInfo>();// 待删除的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<GoodsInfo2> toBeDeleteProducts = new ArrayList<GoodsInfo2>();// 待删除的子元素列表
            List<GoodsInfo2> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    BaseApi.carDel(Activity_Car_JiuTangGuoJi.this, sHelper.getString(BaseConstant.TOKEN), childs.get(j).getRecId() + "", sHelper.getString(BaseConstant.UID));
                    toBeDeleteProducts.add(childs.get(j));
                }
            }
            childs.removeAll(toBeDeleteProducts);
        }
        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        setCartNum();
        mAdapterShopcart.notifyDataSetChanged();

    }


    /**
     * 收藏
     */
    protected void collect() {
        List<StoreInfo> toBeCollect = new ArrayList<StoreInfo>();// 待收藏的组元素列表
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                toBeCollect.add(group);
            }
            List<GoodsInfo2> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    BaseApi.productCollectCar(Activity_Car_JiuTangGuoJi.this, childs.get(j).getGoodsId(), sHelper.getString(BaseConstant.UID), sHelper.getString(BaseConstant.TOKEN));
                }
            }
            ToastUtil.showMessage(this, "收藏成功");
        }

    }

    @Override
    public void doIncrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {
        GoodsInfo2 product = (GoodsInfo2) mAdapterShopcart.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        currentCount++;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        mAdapterShopcart.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void doDecrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        GoodsInfo2 product = (GoodsInfo2) mAdapterShopcart.getChild(groupPosition,
                childPosition);
        int currentCount = product.getCount();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setCount(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        mAdapterShopcart.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        children.get(groups.get(groupPosition).getId()).remove(childPosition);
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo2> childs = children.get(group.getId());
        if (childs.size() == 0) {
            groups.remove(groupPosition);
        }
        mAdapterShopcart.notifyDataSetChanged();
        //     handler.sendEmptyMessage(0);
        calculate();
    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        StoreInfo group = groups.get(groupPosition);
        List<GoodsInfo2> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        mAdapterShopcart.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion,
                           boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        StoreInfo group = groups.get(groupPosition);

        List<GoodsInfo2> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        group.setChoosed(true);

        if (isAllCheck() && allChildSameState) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        mAdapterShopcart.notifyDataSetChanged();
        calculate();

    }

    /**
     * 全不取消
     */
    private void unCheckAll() {
        allChekbox.setChecked(false);
    }

    /**
     * 全选
     */
    private void checkAll() {
        allChekbox.setChecked(true);
    }


    private boolean isAllCheck() {

        for (StoreInfo group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {

        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            StoreInfo group = groups.get(i);
            List<GoodsInfo2> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        mAdapterShopcart.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0;
        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            List<GoodsInfo2> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                GoodsInfo2 product = childs.get(j);
                if (product.isChoosed()) {
                    totalCount++;
                    totalPrice += product.getPrice() * product.getCount();
                }
            }
        }

        tvTotalPrice.setText("NT￥" + totalPrice);
        tvGoToPay.setText("去支付(" + totalCount + ")");
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0) {
            setCartNum();
        } else {
            title.setText("購物車" + "(" + totalCount + ")");
        }
    }

    @Override
    public void onClick(View view) {
        AlertDialog alert;
        int i = view.getId();
        if (i == R.id.all_chekbox) {
            doCheckAll();

        } else if (i == R.id.tv_delete) {
            if (totalCount == 0) {
                Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                return;
            }
            alert = new AlertDialog.Builder(context).create();
            alert.setTitle("操作提示");
            alert.setMessage("你確定要將這些商品從購物車中移除嗎？");
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "確定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            doDelete();

                        }
                    });
            alert.show();

        } else if (i == R.id.tv_go_to_pay) {
            if (totalCount == 0) {
                Toast.makeText(context, "请选择要支付的商品", Toast.LENGTH_LONG).show();
                return;
            }
            alert = new AlertDialog.Builder(context).create();
            alert.setTitle("操作提示");
            alert.setMessage("總計:\n" + totalCount + "種商品\n" + totalPrice + "元");
            alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "確定",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

//                                checkAll();
//                            Intent intent = new Intent();
//                            intent.setClass(Activity_Car_JiuTangGuoJi.this, QueRenDingDanActivity.class);
//                            intent.putExtra("M_QueRenDingDanGoods", getSelectedGoods());
//                            intent.putExtra("type", "gouwuche");
//                            startActivityForResult(intent, 200);
                            getSelectedGoods();
                            BaseApi.orderConfirmOrder(Activity_Car_JiuTangGuoJi.this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID), AbStrUtil.cutLastString(mRec_id), "", "", "", "", "");

                            return;
                        }
                    });
            alert.show();

        } else if (i == R.id.subtitle) {
            if (flag == 0) {
                llInfo.setVisibility(View.GONE);
                tvGoToPay.setVisibility(View.GONE);
                llShar.setVisibility(View.VISIBLE);
                subtitle.setText("完成");
            } else if (flag == 1) {
                llInfo.setVisibility(View.VISIBLE);
                tvGoToPay.setVisibility(View.VISIBLE);
                llShar.setVisibility(View.GONE);
                subtitle.setText("编辑");
            }
            flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能

        } else if (i == R.id.tv_share) {
            if (totalCount == 0) {
                Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
                return;
            }
            ToastUtil.showMessage(this, "分享成功");

        } else if (i == R.id.tv_save) {
            if (totalCount == 0) {
                Toast.makeText(context, "请选择要保存的商品", Toast.LENGTH_LONG).show();
                return;
            }
            collect();

        }
    }

    private String mRec_id = "";

    /**
     * 获取被选择的商品
     *
     * @return
     */
    private M_QueRenDingDanGoods getSelectedGoods() {
        mRec_id = "";
        M_QueRenDingDanGoods m_queRenDingDanGoods = new M_QueRenDingDanGoods();
        List<StoreInfo> selectedGoodsGroups = new ArrayList<>();
        List<List<GoodsInfo2>> toBeSelectedGoodsProducts = new ArrayList<>();

        List<GoodsInfo2> goodsProducts = null;

        for (int i = 0; i < groups.size(); i++) {
            StoreInfo group = groups.get(i);
            if (group.isChoosed()) {
                selectedGoodsGroups.add(group);
            }

            goodsProducts = new ArrayList<GoodsInfo2>();
            List<GoodsInfo2> childs = children.get(group.getId());
            if (childs.size() > 0) {
                for (int j = 0; j < childs.size(); j++) {
                    if (childs.get(j).isChoosed()) {

                        goodsProducts.add(childs.get(j));
                        mRec_id += childs.get(j).getRecId() + ",";

                    }
                }

            }
            if (group.isChoosed()) {
                toBeSelectedGoodsProducts.add(goodsProducts);
            }
        }

//        for (int i = 0; i < toBeSelectedGoodsProducts.size(); i++) {
//            mRec_id += toBeSelectedGoodsProducts.get(i).get(i).getRecId()+ ",";
//
//        }
        m_queRenDingDanGoods.setStoreInfoList(selectedGoodsGroups);
        m_queRenDingDanGoods.setGoodsInfo2List(toBeSelectedGoodsProducts);
        return m_queRenDingDanGoods;
    }

    @Override
    public void groupEdit(int groupPosition) {
        groups.get(groupPosition).setIsEdtor(true);
        mAdapterShopcart.notifyDataSetChanged();
    }

    //    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            //删除购物车后动态改变数量
//            setCartNum();
//        }
//    };


    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (myFragmentFourReceiver != null) {
//            getActivity().unregisterReceiver(myFragmentFourReceiver);
//        }
        mAdapterShopcart = null;
        groups.clear();
        totalPrice = 0;
        totalCount = 0;
        children.clear();
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        mSwipyRefreshLayout.setRefreshing(false);

        if (url.equals(BaseConstant.CART_LIST) && jo != null) {
            showCart();
            groups.clear();
            children.clear();
            try {
                ShopCarJiuTangGuoJi m_cart_one = new ShopCarJiuTangGuoJi(jo.toString());
                if (m_cart_one.getCode().equals("200")) {

                    List<ShopCarJiuTangGuoJi.DataBean.GoodsDataBean> contentBeanList = m_cart_one.getData().getGoods_data();

                    if (contentBeanList != null) {
                        for (int i = 0; i < contentBeanList.size(); i++) {
                            groups.add(new StoreInfo(i + "", contentBeanList.get(i).getType_name(), contentBeanList.get(i).getType_id()));
                            List<GoodsInfo2> products = new ArrayList<GoodsInfo2>();


                            List<ShopCarJiuTangGuoJi.DataBean.GoodsDataBean.ProductsBean> goodsList = contentBeanList.get(i).getProducts();
                            if (goodsList.size() > 0) {

                                for (int j = 0; j < goodsList.size(); j++) {
                                    products.add(new GoodsInfo2(goodsList.get(j).getCart_id(), goodsList.get(j).getProduct_id(), goodsList.get(j).getProduct_name(),
                                            goodsList.get(j).getProduct_name(),
                                            Double.parseDouble(String.valueOf(goodsList.get(j).getPrice() == null ? 0 : goodsList.get(j).getPrice())),
                                            Integer.parseInt(goodsList.get(j).getGoods_num()), "", "",
                                            goodsList.get(j).getProduct_image(),
                                            Double.parseDouble(String.valueOf(goodsList.get(j).getSale_price() == null ? 0 : goodsList.get(j).getSale_price())), "", "", "0", "0"));

                                }
                            }


                            children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                        }

                        mAdapterShopcart.notifyDataSetChanged();
                        for (int i = 0; i < mAdapterShopcart.getGroupCount(); i++) {
                            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
                        }

                        unCheckAll();

                        ll_jie_suan.setVisibility(View.VISIBLE);

                    } else {
                        ll_jie_suan.setVisibility(View.GONE);
                    }

                } else {
                    ToastUtil.showMessage(this, m_cart_one.getMsg());
                }
                setCartNum();
            } catch (Exception e) {
                e.printStackTrace();
                ll_jie_suan.setVisibility(View.GONE);
                title.setText("購物車" + "(" + 0 + ")");
                cart_empty.setVisibility(View.VISIBLE);

            }


        }

        if (url.equals(BaseConstant.CART_DEL) && jo != null) {
            CarDelDzfk carDel = new CarDelDzfk(jo.toString());
            if (carDel.getCode().equals("200")) {
                ToastUtil.showMessage(this, "删除成功");
            }
        }
        if (url.equals(BaseConstant.PRODUCT_COLLECT) && jo != null) {
            CarDelDzfk carDel = new CarDelDzfk(jo.toString());
            if (carDel.getCode().equals("200")) {
                ToastUtil.showMessage(this, "收藏成功");
            }
        }
        if (url.equals(BaseConstant.CART_UPDATENUM) && jo != null) {
            CarDelDzfk carDel = new CarDelDzfk(jo.toString());
            if (carDel.getCode().equals("200")) {
                ToastUtil.showMessage(this, "数量修改成功");
            }
        }

        if (url.equals(BaseConstant.ORDER_CONFIRMORDER) && jo != null) {
            OrderConfirmDzfk orderConfirm = new OrderConfirmDzfk(jo.toString());

            if (orderConfirm != null) {


                if (orderConfirm.getCode().equals("200")) {
                    ToastUtil.showMessage(this, "訂單發送成功");
                    ll_jie_suan.setVisibility(View.GONE);
                    cartList();

                } else {
                    ToastUtil.showMessage(this, "訂單發送失败");
                }
            }

        }

    }

    @Override
    public void onMessageResponse(String url, JSONArray jo) throws JSONException {

    }

    @Override
    public void onMessageResponse(String url, String str) throws Exception {

    }
}
