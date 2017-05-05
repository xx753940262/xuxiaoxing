package com.xiaoxing.module.shopcar.adapter;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/14 15:30
 * 邮箱：2235445233@qq.com
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.image.abimage.AbImageLoader;
import com.xiaoxing.common.util.ListUtils;
import com.xiaoxing.common.util.SharedPreferencesHelper;
import com.xiaoxing.module.shopcar.model.GoodsInfo2;
import com.xiaoxing.module.shopcar.model.M_Cart_One;
import com.xiaoxing.module.shopcar.model.StoreInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：确认订单商品数据适配器
 * 作者：徐小星 on 2016/8/31 0031 17:58
 * 邮箱：xx@yougudongli.com
 */
public class Adapter_QueRenDingDanGoods extends BaseExpandableListAdapter {
    private List<StoreInfo> groups;
    private Map<String, List<GoodsInfo2>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    public int flag = 0;
    private GroupEdtorListener mListener;

    public GroupEdtorListener getmListener() {
        return mListener;
    }

    //图片下载器
    private AbImageLoader mAbImageLoader = null;

    public void setmListener(GroupEdtorListener mListener) {
        this.mListener = mListener;
    }

    private SharedPreferencesHelper sHelper;
    private Fragment mFragment;

    private List<M_Cart_One.ContentBean.ShippingListBean.AreaListBean> mAreaListBean;

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */

    public Adapter_QueRenDingDanGoods(List<StoreInfo> groups, Map<String, List<GoodsInfo2>> children, Context context, SharedPreferencesHelper sharedPreferencesHelper) {
        this.groups = groups;
        this.children = children;
        this.context = context;
        this.sHelper = sharedPreferencesHelper;
        //图片下载器
        mAbImageLoader = AbImageLoader.getInstance(context);
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {

        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        //这里需要注意的是在getChildrenCount方法里要返回1，不然就会每个二级View里就会生成很多ListView
        String groupId = groups.get(groupPosition).getId();
//        return children.get(groupId).size();
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<GoodsInfo2> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder gholder;
        if (convertView == null) {
            gholder = new GroupViewHolder();
            convertView = View.inflate(context, R.layout.item_que_ren_ding_dan_goods_group, null);
            gholder.cb_check = (CheckBox) convertView.findViewById(R.id.determine_chekbox);
            gholder.tv_group_name = (TextView) convertView.findViewById(R.id.tv_source_name);
            gholder.store_edtor = (TextView) convertView.findViewById(R.id.tv_store_edtor);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        final StoreInfo group = (StoreInfo) getGroup(groupPosition);

        gholder.tv_group_name.setText(group.getName());
        gholder.cb_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)

            {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
            }
        });
        gholder.cb_check.setChecked(group.isChoosed());
        if (group.isEdtor()) {
            gholder.store_edtor.setText("完成");
        } else {
            gholder.store_edtor.setText("编辑");
        }
        gholder.store_edtor.setOnClickListener(new GroupViewClick(groupPosition, gholder.store_edtor, group));
        notifyDataSetChanged();
        return convertView;
    }

    String supplierShipping = "";
    final String[] shippingIds = {"27", "7"};
    final String[] itemFee = {"15", "0"};


    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        String mYunFei = "0";
        String supplierId = "";
        String shippingId = "";
        String free_money = "";
        String is_shipping = "0";
        boolean has = false;
        if (convertView == null) {
            final List<GoodsInfo2> goodsInfo2List = children.get(groups.get(groupPosition).getId()); //二级菜单的商品的数量


            convertView = View.inflate(context, R.layout.item_que_ren_ding_dan_goods_product_list_view, null);
            ListView mListView = (ListView) convertView.findViewById(R.id.lv_goods);


            final List<Map<String, Object>> list = new ArrayList<>();
            Map<String, Object> map = null;

            Double mGoodsTotalPrice = 0.0;
            Double mPackmonet = 0.0;
            Double mShippongfee = 0.0;

            for (int i = 0; i < goodsInfo2List.size(); i++) {
                map = new HashMap<String, Object>();
                map.put("itemsImg", goodsInfo2List.get(i).getGoodsImg());
                map.put("itemsTitle", goodsInfo2List.get(i).getName());
                map.put("itemsColorSize", "");
                map.put("itemsPrice", goodsInfo2List.get(i).getPrice());
                map.put("itemsDiscountPrice", goodsInfo2List.get(i).getDiscountPrice());
                map.put("itemsBuyNum", "x" + goodsInfo2List.get(i).getCount());
                map.put("itemsPackmoney", goodsInfo2List.get(i).getPackagemoney());
                map.put("itemsShippingfee", goodsInfo2List.get(i).getShipping_fee());
                list.add(map);
                mGoodsTotalPrice += (goodsInfo2List.get(i).getPrice() * goodsInfo2List.get(i).getCount());
                mPackmonet += Double.parseDouble(goodsInfo2List.get(i).getPackagemoney());
                mShippongfee += Double.parseDouble(goodsInfo2List.get(i).getShipping_fee());
            }

            // 使用自定义的Adapter
            Adapter_QueRenDingDanGoods_ErJi_List myListViewAdapter = new Adapter_QueRenDingDanGoods_ErJi_List(context, list,
                    R.layout.item_que_ren_ding_dan_goods_product,
                    new String[]{"头像", "标题", "颜色：黑色；尺码：29", "tv_price", "tv_discount_price", "tv_buy_num"},
                    new int[]{R.id.iv_adapter_list_pic, R.id.tv_intro, R.id.tv_color_size, R.id.tv_price, R.id.tv_discount_price, R.id.tv_buy_num});
//        final List<M_Cart_One.ContentBean.ShippingListBean> contentBeanList = groups.get(groupPosition).getM_peiSongFangShis();
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View bottomLayoutContent = layoutInflater.inflate(R.layout.footer_que_ren_ding_dan_er_ji, null);

            TextView tv_package_money = (TextView) bottomLayoutContent.findViewById(R.id.tv_package_money);
            TextView tv_shipping_fee = (TextView) bottomLayoutContent.findViewById(R.id.tv_shipping_fee);

            tv_package_money.setText(mPackmonet + "");
            tv_shipping_fee.setText(mShippongfee + "");

            List<String> list1 = new ArrayList<String>();
            for (int i = 0; i < 1; i++) {
                list1.add("普通快递");
            }


//            noticeUpdateSupplierShipping(supplierShipping, mYunFei);


            mListView.addFooterView(bottomLayoutContent);
            mListView.setAdapter(myListViewAdapter);
            ListUtils.setListViewHeightBasedOnChildren(mListView);
        }

        return convertView;
    }

    public boolean compare(double num1, double num2) {

        BigDecimal val1 = new BigDecimal(num1);

        BigDecimal val2 = new BigDecimal(num2);

        if (val1.compareTo(val2) < 0) {
            return true;
        }
        return false;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }

    /**
     * 组元素绑定器
     */
    private class GroupViewHolder {
        CheckBox cb_check;
        TextView tv_group_name;
        TextView store_edtor;
    }

    /**
     * 子元素绑定器
     */
    private class ChildViewHolder {
        CheckBox cb_check;
        ImageView iv_adapter_list_pic;
        TextView tv_product_name;
        TextView tv_product_desc;
        TextView tv_price;
        TextView iv_increase;
        TextView tv_count;
        TextView iv_decrease;
        RelativeLayout rl_no_edtor;
        TextView tv_color_size;
        TextView tv_discount_price;
        TextView tv_buy_num;
        LinearLayout ll_edtor;
        TextView tv_colorsize;
        TextView tv_goods_delete;
    }

    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param groupPosition
         * @param childPosition
         */
        void childDelete(int groupPosition, int childPosition);
    }

    /**
     * 监听编辑状态
     */
    public interface GroupEdtorListener {
        void groupEdit(int groupPosition);
    }

    /**
     * 使某个组处于编辑状态
     * <p>
     * groupPosition组的位置
     */
    class GroupViewClick implements View.OnClickListener {
        private int groupPosition;
        private TextView edtor;
        private StoreInfo group;

        public GroupViewClick(int groupPosition, TextView edtor, StoreInfo group) {
            this.groupPosition = groupPosition;
            this.edtor = edtor;
            this.group = group;
        }

        @Override
        public void onClick(View v) {
            int groupId = v.getId();
            if (groupId == edtor.getId()) {
                if (group.isEdtor()) {
                    group.setIsEdtor(false);
                } else {
                    group.setIsEdtor(true);

                }
                notifyDataSetChanged();
            }
        }
    }
}
