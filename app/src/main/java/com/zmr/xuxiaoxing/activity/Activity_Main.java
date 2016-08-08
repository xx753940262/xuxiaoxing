package com.zmr.xuxiaoxing.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.ab.view.sliding.AbBottomTabView;
import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.base.AppBaseActivity;
import com.zmr.xuxiaoxing.base.Constant;
import com.zmr.xuxiaoxing.fragment.Fragment_Five;
import com.zmr.xuxiaoxing.fragment.Fragment_Four;
import com.zmr.xuxiaoxing.fragment.Fragment_One;
import com.zmr.xuxiaoxing.fragment.Fragment_Three;
import com.zmr.xuxiaoxing.fragment.Fragment_Two;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 描述：主界面
 * 作者：徐小星 on 2016/7/20 11:46
 * 邮箱：xx@yougudongli.com
 */
public class Activity_Main extends AppBaseActivity {

    @Bind(R.id.mBottomTabView)
    AbBottomTabView mBottomTabView;
    private List<Drawable> tabDrawables = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        //如果里面的页面列表不能下载原因：
        //Fragment里面用的AbTaskQueue,由于有多个tab，顺序下载有延迟，还没下载好就被缓存了。改成用AbTaskPool，就ok了。
        //或者setOffscreenPageLimit(0)

        //缓存数量
        mBottomTabView.getViewPager().setOffscreenPageLimit(5);

        //禁止滑动
        /*mBottomTabView.getViewPager().setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return true;
			}

		});*/

        //mBottomTabView.setOnPageChangeListener(listener);

        Fragment_One page1 = new Fragment_One();
        Fragment_Two page2 = new Fragment_Two();
        Fragment_Three page3 = new Fragment_Three();
        Fragment_Four page4 = new Fragment_Four();
        Fragment_Five page5 = new Fragment_Five();

        List<Fragment> mFragments = new ArrayList<Fragment>();
        mFragments.add(page1);
        mFragments.add(page2);
        mFragments.add(page3);
        mFragments.add(page4);
        mFragments.add(page5);

        List<String> tabTexts = new ArrayList<String>();
        tabTexts.add(Constant.WO);
        tabTexts.add(Constant.SHI);
        tabTexts.add(Constant.XU);
        tabTexts.add(Constant.XIAO);
        tabTexts.add(Constant.XING);

        //设置样式
        mBottomTabView.setTabTextColor(Color.BLACK);
        mBottomTabView.setTabTextSize(25);
        mBottomTabView.setTabSelectColor(Color.rgb(255, 118, 0));
//        mBottomTabView.setTabBackgroundResource(R.drawable.tab_bg2);
        mBottomTabView.setTabLayoutBackgroundResource(R.mipmap.tab_bg);

        //注意图片的顺序
        tabDrawables = new ArrayList<Drawable>();
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.yan_zheng_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.yan_zheng_pressed));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.ping_jia_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.ping_jia_pressed));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.jing_ying_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.jing_ying_pressed));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.my_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.my_pressed));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.more_normal));
        tabDrawables.add(this.getResources().getDrawable(R.mipmap.more_pressed));
        mBottomTabView.setTabCompoundDrawablesBounds(0, 0, 40, 40);
        //演示增加一组
        mBottomTabView.addItemViews(tabTexts, mFragments, tabDrawables);

        mBottomTabView.setTabPadding(10, 10, 10, 10);

        setHeaderTitle(R.string.wo);
        mBottomTabView.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setHeaderTitle(R.string.wo);
                        break;
                    case 1:
                        setHeaderTitle(R.string.shi);
                        break;
                    case 2:
                        setHeaderTitle(R.string.xu);
                        break;
                    case 3:
                        setHeaderTitle(R.string.xiao);
                        break;
                    case 4:
                        setHeaderTitle(R.string.xing);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initTitleRightLayout() {
//        mAbTitleBar.clearRightView();
//        View rightViewMore = mInflater.inflate(R.layout.geren_shuaxin, null);
//        mAbTitleBar.addRightView(rightViewMore);

//        Button about = (Button) rightViewMore.findViewById(R.id.moreBtn);
//
//        about.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                startActivity(MainActivity.this, LoginActivity.class);
//            }
//
//        });
    }

}
