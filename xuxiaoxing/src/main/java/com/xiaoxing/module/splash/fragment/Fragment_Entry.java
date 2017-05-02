package com.xiaoxing.module.splash.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.ab.R;
import com.xiaoxing.common.base.BaseFragment;
import com.xiaoxing.module.splash.activity.Activity_Guide;

/**
 * 描述：引导页最后一页 entry：条目
 * 作者：徐小星 on 2016/7/20 0020 11:50
 * 邮箱：xx@yougudongli.com
 */
public class Fragment_Entry extends BaseFragment implements View.OnClickListener {

    private Button btn_entry;

    @Override
    public int bindLayout() {
        return R.layout.fragment_entry;
    }


    @Override
    public void initView(View view, LayoutInflater inflater) {
        super.initView(view, inflater);
        btn_entry = (Button) view.findViewById(R.id.btn_entry);
        btn_entry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Activity_Guide activity = (Activity_Guide) getActivity();
        activity.entryApp();
    }
}
