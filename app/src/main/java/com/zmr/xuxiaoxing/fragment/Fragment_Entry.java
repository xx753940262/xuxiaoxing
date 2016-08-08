package com.zmr.xuxiaoxing.fragment;

import com.zmr.xuxiaoxing.R;
import com.zmr.xuxiaoxing.activity.Activity_Guide;
import com.zmr.xuxiaoxing.base.AppBaseFragment;

import butterknife.OnClick;

/**
 * 描述：引导页最后一页 entry：条目
 * 作者：徐小星 on 2016/7/20 0020 11:50
 * 邮箱：xx@yougudongli.com
 */
public class Fragment_Entry extends AppBaseFragment {

    @Override
    public int bindLayout() {
        return R.layout.fragment_entry;
    }

    @OnClick(R.id.btn_entry)
    public void onClick() {
        Activity_Guide activity = (Activity_Guide) getActivity();
        activity.entryApp();
    }
}
