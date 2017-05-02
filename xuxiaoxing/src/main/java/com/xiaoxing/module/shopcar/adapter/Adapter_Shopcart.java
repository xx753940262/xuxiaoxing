package com.xiaoxing.module.shopcar.adapter;

/**
 * 描述：
 * 作者：xiaoxing on 17/4/12 16:03
 * 邮箱：2235445233@qq.com
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.base.BaseApi;
import com.xiaoxing.common.base.BaseConstant;
import com.xiaoxing.common.image.AbImageLoader;
import com.xiaoxing.common.util.SharedPreferencesHelper;
import com.xiaoxing.module.shopcar.model.GoodsInfo2;
import com.xiaoxing.module.shopcar.model.StoreInfo;

import java.util.List;
import java.util.Map;

/**
 * 描述：购物车数据适配器
 * 作者：徐小星 on 2016/8/31 0031 17:58
 * 邮箱：xx@yougudongli.com
 */
public class Adapter_Shopcart extends BaseExpandableListAdapter {
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

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public Adapter_Shopcart(List<StoreInfo> groups, Map<String, List<GoodsInfo2>> children, Context context, SharedPreferencesHelper sharedPreferencesHelper, Fragment fragment) {
        this.groups = groups;
        this.children = children;
        this.context = context;
        this.sHelper = sharedPreferencesHelper;
        this.mFragment = fragment;
        //图片下载器
        mAbImageLoader = AbImageLoader.getInstance(context);
    }

    public Adapter_Shopcart(List<StoreInfo> groups, Map<String, List<GoodsInfo2>> children, Context context, SharedPreferencesHelper sharedPreferencesHelper) {
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
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
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
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
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
            gholder.store_edtor.setText("編輯");
        }
        gholder.store_edtor.setOnClickListener(new GroupViewClick(groupPosition, gholder.store_edtor, group));
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
        final ChildViewHolder cholder;

        if (convertView == null) {
            cholder = new ChildViewHolder();
            convertView = View.inflate(context, R.layout.item_shopcart_product_01, null);
            cholder.cb_check = (CheckBox) convertView.findViewById(R.id.check_box);
            cholder.tv_product_desc = (TextView) convertView.findViewById(R.id.tv_intro);
            cholder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);
            cholder.iv_increase = (TextView) convertView.findViewById(R.id.tv_add);
            cholder.iv_decrease = (TextView) convertView.findViewById(R.id.tv_reduce);
            cholder.tv_count = (TextView) convertView.findViewById(R.id.tv_num);
            cholder.rl_no_edtor = (RelativeLayout) convertView.findViewById(R.id.rl_no_edtor);
            cholder.tv_color_size = (TextView) convertView.findViewById(R.id.tv_color_size);
            cholder.tv_discount_price = (TextView) convertView.findViewById(R.id.tv_discount_price);
            cholder.tv_buy_num = (TextView) convertView.findViewById(R.id.tv_buy_num);
            cholder.ll_edtor = (LinearLayout) convertView.findViewById(R.id.ll_edtor);
            cholder.tv_colorsize = (TextView) convertView.findViewById(R.id.tv_colorsize);
            cholder.tv_goods_delete = (TextView) convertView.findViewById(R.id.tv_goods_delete);
            cholder.iv_adapter_list_pic = (ImageView) convertView.findViewById(R.id.iv_adapter_list_pic);
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        if (groups.get(groupPosition).isEdtor() == true) {
            cholder.ll_edtor.setVisibility(View.VISIBLE);
            cholder.rl_no_edtor.setVisibility(View.GONE);
        } else {
            cholder.ll_edtor.setVisibility(View.GONE);
            cholder.rl_no_edtor.setVisibility(View.VISIBLE);
        }
        final GoodsInfo2 goodsInfo2 = (GoodsInfo2) getChild(groupPosition, childPosition);
        final StoreInfo group = (StoreInfo) getGroup(groupPosition);
        if (goodsInfo2 != null) {
            final String cart_id = goodsInfo2.getRecId();
            cholder.tv_product_desc.setText(goodsInfo2.getDesc());
            cholder.tv_price.setText("NT￥" + goodsInfo2.getPrice() + "");
            cholder.tv_count.setText(goodsInfo2.getCount() + "");
//            cholder.iv_adapter_list_pic.setImageResource(goodsInfo2.getGoodsImg());

            //图片的下载
            mAbImageLoader.display(cholder.iv_adapter_list_pic, goodsInfo2.getGoodsImg(), 150, 150);
            cholder.tv_color_size.setText("");
            SpannableString spanString = new SpannableString("￥" + String.valueOf(goodsInfo2.getDiscountPrice()));
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, String.valueOf(goodsInfo2.getDiscountPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //避免无限次的appand
            if (cholder.tv_discount_price.getText().toString().length() > 0) {
                cholder.tv_discount_price.setText("");
            }
            cholder.tv_discount_price.append(spanString);
            cholder.tv_buy_num.setText("x" + goodsInfo2.getCount());
            cholder.cb_check.setChecked(goodsInfo2.isChoosed());
            cholder.cb_check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    goodsInfo2.setChoosed(((CheckBox) v).isChecked());
                    cholder.cb_check.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口

//                    group.setChoosed(((CheckBox) v).isChecked());
//                    checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口

                }
            });
            cholder.iv_increase.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露增加接口
                    BaseApi.carupdateNum(mFragment, sHelper.getString(BaseConstant.TOKEN), goodsInfo2.getRecId(), cholder.tv_count.getText().toString());
//                    Api.addToCart(mFragment, sHelper.getString(Constant.UID), sHelper.getString(Constant.TOKEN), goodsInfo2.getGoodsId(), "1", goodsInfo2.getGoods_attr_id(), goodsInfo2.getGoodsId());
//
                }
            });
            final int[] mCartNum = {Integer.parseInt(cholder.tv_count.getText().toString()) + 1};
            cholder.iv_decrease.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.tv_count, cholder.cb_check.isChecked());// 暴露删减接口
//                    ToastUtil.showMessage(mFragment.getActivity(), "" + mCartNum[0]);
                    int cartnum = Integer.parseInt(cholder.tv_count.getText().toString());
                    if (mCartNum[0] != 1) {
                        mCartNum[0] = mCartNum[0] - 1;
                    }
                    if (mCartNum[0] > 1) {
                        BaseApi.carupdateNum(mFragment, sHelper.getString(BaseConstant.TOKEN), goodsInfo2.getRecId(), cholder.tv_count.getText().toString());
                        // Api.addToCart(mFragment, sHelper.getString(Constant.UID), sHelper.getString(Constant.TOKEN), goodsInfo2.getGoodsId(), "-1", goodsInfo2.getGoods_attr_id(), goodsInfo2.getGoodsId());
                    }
                }
            });
            //删除 购物车
            cholder.tv_goods_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alert = new AlertDialog.Builder(context).create();
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
                                    BaseApi.carDel(mFragment, sHelper.getString(BaseConstant.TOKEN), cart_id, sHelper.getString(BaseConstant.UID));
                                    modifyCountInterface.childDelete(groupPosition, childPosition);

                                }
                            });
                    alert.show();

                }
            });
        }
        return convertView;
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
