package com.zmr.xuxiaoxing.ui.car.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.socks.library.KLog;
import com.xiaoxing.common.base.BaseActivity;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.dialog.loadingDialog.LoadingDialogUtil;
import com.xiaoxing.common.http.OnMessageResponse;
import com.xiaoxing.common.util.AbStrUtil;
import com.xiaoxing.common.util.ListUtils;
import com.xiaoxing.common.util.ToastUtil;
import com.xiaoxing.common.view.dialog.StyledDialog;
import com.xiaoxing.common.view.dialog.bottomsheet.BottomSheetBean;
import com.xiaoxing.common.view.dialog.interfaces.MyItemDialogListener;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.ui.address.activity.ChangeAddressActivity;
import com.zmr.xuxiaoxing.ui.address.model.AddressetGetDefault;
import com.zmr.xuxiaoxing.ui.car.adapter.Adapter_QueRenDingDanGoods;
import com.zmr.xuxiaoxing.ui.car.model.GoodsInfo2;
import com.zmr.xuxiaoxing.ui.car.model.M_QueRenDingDanGoods;
import com.zmr.xuxiaoxing.ui.car.model.OrderConfirm;
import com.zmr.xuxiaoxing.ui.car.model.StoreInfo;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.xiaoxing.common.util.AbMapUtil.transStringToMap;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/14 15:14
 * 邮箱：2235445233@qq.com
 */
public class QueRenDingDanActivity extends BaseActivity implements OnMessageResponse, View.OnClickListener {


    public static QueRenDingDanActivity instance;

    LinearLayout ll_zhi_fu_fang_shi;
    LinearLayout ll_change_address;
    LinearLayout ll_song_huo_shi_jian;
    LinearLayout ll_que_huo_chu_li;

    TextView tv_address_name;
    TextView tv_address_tel;
    TextView tv_address_address;

    ExpandableListView exListView;

    EditText et_bei_zhu;

    TextView tv_goods_total_price;
    TextView tv_totle_money;
    TextView tv_zhi_fu_name;
    TextView tv_song_chu_shi_jian;
    TextView tv_zhi_fu_fang_shi;
    TextView tv_pei_song_fang_shi;

    Button btn_ti_jiao_ding_dan;


    private Adapter_QueRenDingDanGoods mAdapter_queRenDingDanGoods;
    private List<StoreInfo> groups = new ArrayList<StoreInfo>();// 组元素数据列表
    private Map<String, List<GoodsInfo2>> children = new HashMap<String, List<GoodsInfo2>>();// 子元素数据列表

    private String mAddressName, mAddress, mAddressId, mTelphone, mStreet, mDetail, mDefault;


    private String mZhiFuType;

    private String mSupplierShipping;
    private Map<String, String> mMap = new HashMap<>();
    private Map<String, String> mResult;
    private List mSupplierShippingList = new ArrayList();

    private String mZhiFuFangShi;

    private double mGoodsTotalPrice = 0;
    private double mGoodsYingFuKuanPrice = 0;
    private String mGoodsYingFuKuanGoodsName = "";
    private String mRec_id = "";
    private String mGoods_Id = "";
    private String mItemFee;
    public static List<String> mAddressIds; //省市区地址id列表


    private String mSongHuoShiJian;
    private String mQueHuoChuLi;

    private String mType;

    public void queRenDingDan(Intent intent) {
        String supplierShipping = intent.getStringExtra("supplierShipping");
        mItemFee = intent.getStringExtra("itemFee");
//        tv_pei_song_fei_yong.setText(mItemFee);

        mResult = transStringToMap(AbStrUtil.cutLastString(supplierShipping));

        KLog.e("supplierShipping==" + transStringToMap(supplierShipping));
        for (Map.Entry<String, String> entry : mResult.entrySet()) {
            //Map.entry<Integer,String> 映射项（键-值对）  有几个方法：用上面的名字entry
            //entry.getKey() ;entry.getValue(); entry.setValue();
            //map.entrySet()  返回此映射中包含的映射关系的 Set视图。
//                    KLog.e("entry.getKey()==" + entry.getKey() + "entry.getValue()" + entry.getValue());
            mMap.put(entry.getKey(), entry.getValue());

        }
    }


    @Override
    public int bindLayout() {
        return R.layout.activity_que_ren_ding_dan;
    }


    @Override
    public void initView(View view) {
        super.initView(view);
        ll_zhi_fu_fang_shi = (LinearLayout) view.findViewById(R.id.ll_zhi_fu_fang_shi);
        ll_change_address = (LinearLayout) view.findViewById(R.id.ll_change_address);
        ll_song_huo_shi_jian = (LinearLayout) view.findViewById(R.id.ll_song_huo_shi_jian);
        ll_que_huo_chu_li = (LinearLayout) view.findViewById(R.id.ll_que_huo_chu_li);

        tv_address_name = (TextView) view.findViewById(R.id.tv_address_name);
        tv_address_tel = (TextView) view.findViewById(R.id.tv_address_tel);
        tv_address_address = (TextView) view.findViewById(R.id.tv_address_address);
        exListView = (ExpandableListView) view.findViewById(R.id.exListView);
        et_bei_zhu = (EditText) view.findViewById(R.id.et_bei_zhu);
        tv_goods_total_price = (TextView) view.findViewById(R.id.tv_goods_total_price);
        tv_totle_money = (TextView) view.findViewById(R.id.tv_totle_money);
        tv_zhi_fu_name = (TextView) view.findViewById(R.id.tv_zhi_fu_name);
        tv_song_chu_shi_jian = (TextView) view.findViewById(R.id.tv_song_chu_shi_jian);
        tv_zhi_fu_fang_shi = (TextView) view.findViewById(R.id.tv_zhi_fu_fang_shi);
        tv_pei_song_fang_shi = (TextView) view.findViewById(R.id.tv_pei_song_fang_shi);

        btn_ti_jiao_ding_dan = (Button) view.findViewById(R.id.btn_ti_jiao_ding_dan);


        ll_zhi_fu_fang_shi.setOnClickListener(this);
        tv_pei_song_fang_shi.setOnClickListener(this);
        ll_change_address.setOnClickListener(this);
        ll_song_huo_shi_jian.setOnClickListener(this);
        ll_que_huo_chu_li.setOnClickListener(this);
        btn_ti_jiao_ding_dan.setOnClickListener(this);
        initEvents();

    }

    /**
     * 设置提交数据的支付id
     *
     * @param i
     */
    private void setZhiFuFangShiTypeId(int i) {
        switch (i) {
            case 0:
                mZhiFuFangShi = "1";
                break;
            case 2:
                mZhiFuFangShi = "7";
                break;
            case 1:
                mZhiFuFangShi = "4";
                break;
        }
    }

    /**
     * 初始化商品的列表
     */
    private void initEvents() {
        mAdapter_queRenDingDanGoods = new Adapter_QueRenDingDanGoods(groups, children, this, sHelper);
        exListView.setAdapter(mAdapter_queRenDingDanGoods);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getDefaultAddress();
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        instance = this;
        setHeaderBack();
        setHeaderTitle(R.string.que_ren_ding_dan);


        mAddressIds = new ArrayList<>();

        String country_id = "";
        String province_id = "";
        String city_id = "";
        String district_id = "";

        country_id = sHelper.getString("country_id");
        province_id = sHelper.getString("province_id");
        city_id = sHelper.getString("city_id");
        district_id = sHelper.getString("district_id");


        mAddressIds.add(country_id);
        mAddressIds.add(province_id);
        mAddressIds.add(city_id);
        mAddressIds.add(district_id);
        mAddressIds.add("1");

        mAddressId = sHelper.getString("dizhiid");

        mAddressName = sHelper.getString("shouhuoren");
        mAddress = sHelper.getString("shouhuodizhi");


        if (!AbStrUtil.isEmpty(mAddressId) && !AbStrUtil.isEmpty(mAddressName) && !AbStrUtil.isEmpty(mAddress)) {
            tv_address_name.setText("收货人：" + mAddressName);
            tv_address_address.setText("收货地址：" + mAddress);
        }
//        checkAddress();
        setZhiFuFangShiTypeId(0);

        getBundleValue();


    }

    private void getDefaultAddress() {
        BaseApi.addressGetDefault(this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID));
    }

    /**
     * 获取在购物车中选取的商品列表
     */
    private void getBundleValue() {

        M_QueRenDingDanGoods m_QueRenDingDanGoods = (M_QueRenDingDanGoods) getIntent().getSerializableExtra("M_QueRenDingDanGoods");
        mType = (String) getIntent().getSerializableExtra("type");

        if (m_QueRenDingDanGoods != null) {

            List<StoreInfo> listStoreInfo = m_QueRenDingDanGoods.getStoreInfoList();

            if (listStoreInfo.size() > 0) {


                for (int i = 0; i < listStoreInfo.size(); i++) {

                    List<GoodsInfo2> listGoodsInfo2 = m_QueRenDingDanGoods.getGoodsInfo2List().get(i);
                    groups.add(listStoreInfo.get(i));

                    List<GoodsInfo2> products = new ArrayList<GoodsInfo2>();

                    if (listGoodsInfo2.size() > 0) {

                        for (int j = 0; j < listGoodsInfo2.size(); j++) {

                            products.add(new GoodsInfo2(listGoodsInfo2.get(j).getRecId(), listGoodsInfo2.get(j).getGoodsId(), listGoodsInfo2.get(j).getName(), listGoodsInfo2.get(j).getName(),
                                    listGoodsInfo2.get(j).getPrice(), listGoodsInfo2.get(j).getCount(), "", "",
                                    listGoodsInfo2.get(j).getGoodsImg(), listGoodsInfo2.get(j).getDiscountPrice(), listGoodsInfo2.get(j).getIs_shipping(), listGoodsInfo2.get(j).getGoods_attr_id(),
                                    listGoodsInfo2.get(j).getPackagemoney(), listGoodsInfo2.get(j).getShipping_fee()));


                            mGoodsTotalPrice += listGoodsInfo2.get(j).getPrice() * listGoodsInfo2.get(j).getCount();
                            mGoodsYingFuKuanPrice += listGoodsInfo2.get(j).getPrice() * listGoodsInfo2.get(j).getCount() +
                                    Double.parseDouble(listGoodsInfo2.get(j).getPackagemoney()) + Double.parseDouble(listGoodsInfo2.get(j).getShipping_fee());
                            mGoodsYingFuKuanGoodsName += "," + listGoodsInfo2.get(j).getName();

                            mRec_id += listGoodsInfo2.get(j).getRecId() + ",";
                            mGoods_Id += listGoodsInfo2.get(j).getGoodsId() + "|" + listGoodsInfo2.get(j).getCount() + ",";


                        }
                        children.put(groups.get(i).getId(), products);// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
                    }
                }
            }
            mAdapter_queRenDingDanGoods.notifyDataSetChanged();
            for (int i = 0; i < mAdapter_queRenDingDanGoods.getGroupCount(); i++) {
                exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
            }
            ListUtils.setListViewHeightBasedOnChildren(exListView);
        }
        tv_goods_total_price.setText("￥" + mGoodsTotalPrice + "");
        tv_totle_money.setText("￥" + mGoodsTotalPrice + "");
    }

    @Override
    public void onClick(View view) {
        int i = view.getId();
        if (i == R.id.ll_change_address) {
            chooseAddress();

        } else if (i == R.id.ll_song_huo_shi_jian) {
            chooseTime();

        } else if (i == R.id.ll_que_huo_chu_li) {

        } else if (i == R.id.ll_zhi_fu_fang_shi) {
            chooseZhiFuFangShi();

        } else if (i == R.id.btn_ti_jiao_ding_dan) {
            tiJiaoDingDan();

        } else if (i == R.id.tv_pei_song_fang_shi) {
            choosePeiSongFangShi();

        }

    }

    private void chooseTime() {
        List<BottomSheetBean> datas2 = new ArrayList<>();
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, getAfterTime(15)));
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, getAfterTime(30)));
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, getAfterTime(45)));
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, getAfterTime(60)));

        StyledDialog.buildBottomSheetLv("选择时间", datas2, "选择时间", new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
//                ToastUtil.showMessage(QueRenDingDanActivity.this, text + "---" + position);
                tv_song_chu_shi_jian.setText("立即送出(大约" + text + "送达)");
            }
        }).show();
    }

    private void chooseZhiFuFangShi() {
        List<BottomSheetBean> datas2 = new ArrayList<>();
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, "在线支付"));

        StyledDialog.buildBottomSheetLv("支付方式", datas2, "支付方式", new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
                tv_zhi_fu_fang_shi.setText("在线支付");
            }
        }).show();
    }

    private void choosePeiSongFangShi() {
        List<BottomSheetBean> datas2 = new ArrayList<>();
        datas2.add(new BottomSheetBean(R.drawable.ic_launcher, "卖家配送"));

        StyledDialog.buildBottomSheetLv("配送方式", datas2, "卖家配送", new MyItemDialogListener() {
            @Override
            public void onItemClick(CharSequence text, int position) {
                tv_pei_song_fang_shi.setText("卖家配送");
            }
        }).show();
    }


    /**
     * 获取15、30、45、60分钟后的时间
     *
     * @param time
     * @return
     */
    private String getAfterTime(int time) {
        long curren = System.currentTimeMillis();
        curren += time * 60 * 1000;
        Date da = new Date(curren);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        return dateFormat.format(da);
    }

    /**
     * 提交订单
     */
    private void tiJiaoDingDan() {

        String song_chu_shi_jian = getTextViewValue(tv_song_chu_shi_jian);

        if (AbStrUtil.isEmpty(mAddressId)) {
            ToastUtil.showMessage(this, "请选择地址");
            checkAddress();
            return;
        }
        if (AbStrUtil.isEmpty(song_chu_shi_jian)) {
            ToastUtil.showMessage(this, "请选择送货时间");
            chooseTime();
            return;
        }
        if (mType.equals("lijigoumai")) {
            LoadingDialogUtil.showGifdialog(this);
            BaseApi.orderConfirmOrderNow(this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID), AbStrUtil.cutLastString(mGoods_Id), mAddressId, "online", getTextViewValue(tv_song_chu_shi_jian), "商家配送");

        } else {
            LoadingDialogUtil.showGifdialog(this);
            BaseApi.orderConfirmOrder(this, sHelper.getString(BaseConstant.TOKEN), sHelper.getString(BaseConstant.UID), AbStrUtil.cutLastString(mRec_id), mAddressId, "online", getTextViewValue(tv_song_chu_shi_jian), "商家配送","");

        }

    }

    /**
     * 检查是否有默认地址
     *
     * @return
     */
    private boolean checkAddress() {
        if (mAddress.equals("")) {
            chooseAddress();
            return true;
        }
        return false;
    }

    private void chooseAddress() {
        Bundle bundle1 = new Bundle();
        getOperation().startActivityForResult(bundle1, 100, ChangeAddressActivity.class);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 105) {
            mAddressIds = new ArrayList<>();

            Bundle bundle = data.getBundleExtra("data");
            mAddressName = bundle.getString("username");
            mTelphone = bundle.getString("phone");
            mStreet = bundle.getString("street");
            mDetail = bundle.getString("detail");
            mAddress = bundle.getString("address");
            mAddressId = bundle.getString("addressId");

            tv_address_name.setText("收货人：" + mAddressName);
            tv_address_tel.setText("手机号码：" + mTelphone);
            tv_address_address.setText("收货地址：" + mAddress + mStreet + mDetail);

        }
        if (resultCode == 200) {

        }
    }


    @Override
    public void onMessageResponse(String url, JSONObject jo) throws JSONException {
        if (url.equals(BaseConstant.ADDRESS_GETDEFAULT) && jo != null) {
            AddressetGetDefault addressetGetDefault = new AddressetGetDefault(jo.toString());

            if (addressetGetDefault != null) {


                if (addressetGetDefault.getCode().equals("200")) {

                    AddressetGetDefault.DataBean dataBean = addressetGetDefault.getData();
                    mDefault = dataBean.getDefaultX();
//                    if (checkAddress()) {

                    mAddressId = dataBean.getId();

                    tv_address_name.setText("收货人：" + dataBean.getAccept_name());
                    tv_address_tel.setText("手机号码：" + dataBean.getTelephone());
                    tv_address_address.setText("收货地址：" + dataBean.getAddress() + dataBean.getStreet() + dataBean.getDetail());
//                    }

                } else {
                    ToastUtil.showMessage(this, addressetGetDefault.getMsg());
                    chooseAddress();
                }
            }else {
                chooseAddress();
            }

        }

        if (url.equals(BaseConstant.ORDER_CONFIRMORDER) || url.equals(BaseConstant.ORDER_CONFIRM_ORDERNOW) && jo != null) {
            OrderConfirm orderConfirm = new OrderConfirm(jo.toString());

            if (orderConfirm != null) {


                if (orderConfirm.getCode().equals("200")) {

//                    AppBroadcastReceiver.sendAppBroadcastReceiverMessage(this, Constant.INTENT_KEY.UPDATE_CART);
//                    ToastUtil.showMessage(this, "订单交成功");
////                    startActivity(OrderActivity.class);
//                    new AlipayUtil(this, orderConfirm.getData().getPay_money() + "", orderConfirm.getData().getPay_sn(), "", sHelper).alipay();
                } else {
                    ToastUtil.showMessage(this, "订单提交失败");
                }
            }

        }
    }

}
