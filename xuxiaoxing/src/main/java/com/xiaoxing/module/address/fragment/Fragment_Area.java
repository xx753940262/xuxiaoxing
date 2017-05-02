package com.xiaoxing.module.address.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.R;
import com.xiaoxing.common.util.AbJsonUtil;
import com.xiaoxing.module.address.model.AreaInfo;
import com.xiaoxing.module.address.model.QuInfo;
import com.show.api.ShowApiRequest;
import com.socks.library.KLog;

import java.util.List;

import butterknife.ButterKnife;


/**
 * 地区、街道Fragment,通过ShowAPi接口查询地区、街道数据，网址：https://www.showapi.com/api/lookPoint/101
 * 作者：徐小星 on 2016/9/27 0027 13:20
 * 邮箱：xx@yougudongli.com
 */
public class Fragment_Area extends Fragment implements AdapterView.OnItemClickListener {

    private static final String ARG_PARAM1 = "parentCode";
    private static final String ARG_PARAM2 = "provinceName";
    private static final String ARG_PARAM3 = "id";
    private String appid = "25026";//要替换成自己的
    private String secret = "abb580c58cd4488e98936239dc651cce";//要替换成自己的
    ListView mRefreshListView;
    ProgressBar mLoadingBar;

    private String mParam1 = "";    //级别
    private String mParam2 = "";    //省的名称
    private String mParam3 = "";    //省的id


    private OnFragmentInteractionListener mListener;

    private AreaAdapter adapter;

    private AreaInfo mAreaInfo;

    private QuInfo mQuInfo;

    private String mAreaUrl;

    public Fragment_Area() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment AreaFragment.
     */
    public static Fragment_Area newInstance(String param1, String param2, String param3) {
        Fragment_Area fragment = new Fragment_Area();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
        }
    }

    protected Handler mHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_area, container, false);
        ButterKnife.bind(this, view);
        mRefreshListView.setOnItemClickListener(this);

        mAreaUrl = "http://route.showapi.com/101-39"; //查询某级别的区域，级别1、级别2使用此接口

        if (!mParam3.equals("")) {
            mAreaUrl = "https://route.showapi.com/101-113"; //根据id查询区域，级别3、级别4使用此接口
        }

        new Thread() {
            //在新线程中发送网络请求
            public void run() {

                final String res = new ShowApiRequest(mAreaUrl, appid, secret)
                        .addTextPara("level", mParam1)
                        .addTextPara("areaName", mParam2)
                        .addTextPara("id", mParam3)
                        .post();

                //把返回内容通过handler对象更新到界面
                mHandler.post(new Thread() {
                    public void run() {
                        if (res != null) {
                            AreaInfo jsonResult = null;
                            QuInfo jsonResult2 = null;
                            if (!mParam3.equals("")) {
                                jsonResult2 = AbJsonUtil.fromJson(res, QuInfo.class);
                                mQuInfo = jsonResult2;
                                if (jsonResult2.getShowapi_res_code() == 0) {
                                    List list = (List) jsonResult2.getShowapi_res_body().getData().getChildren();
                                    adapter = new AreaAdapter(mParam3, list);
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mRefreshListView.setAdapter(adapter);
                                            mLoadingBar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            } else {
                                jsonResult = AbJsonUtil.fromJson(res, AreaInfo.class);
                                mAreaInfo = jsonResult;
                                if (jsonResult.getShowapi_res_code() == 0) {
                                    List list = (List) jsonResult.getShowapi_res_body().getData();
                                    adapter = new AreaAdapter(getContext(), list);

                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mRefreshListView.setAdapter(adapter);
                                            mLoadingBar.setVisibility(View.GONE);
                                        }
                                    });
                                }
                            }


                        }
                    }
                });
            }
        }.start();


        initView(view);


        return view;
    }

    private void initView(View view) {

        mRefreshListView = (ListView) view.findViewById(R.id.refresh_list_view);
        mLoadingBar = (ProgressBar) view.findViewById(R.id.loadingBar);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean childrenBean = null;
        AreaInfo.ShowapiResBodyBean.DataBean dataBean = null;
        if (!mParam3.equals("")) {
            KLog.e("mParam3=" + mParam3);
            KLog.e("ChildrenBean");
            childrenBean = (QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean) parent.getAdapter().getItem(position);
            if (childrenBean == null) return;
        } else {
            KLog.e("DataBean");
            KLog.e("mParam3=" + mParam3);
            dataBean = (AreaInfo.ShowapiResBodyBean.DataBean) parent.getAdapter().getItem(position);
            if (dataBean == null) return;
        }
        if (mListener != null) {
            mListener.onFragmentInteraction(mAreaInfo, dataBean, childrenBean, mQuInfo);
        }

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(AreaInfo areaInfo, AreaInfo.ShowapiResBodyBean.DataBean dataBean, QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean childrenBean, QuInfo quDataBean);

    }


    class AreaAdapter extends BaseAdapter {

        private List list;

        private int lastPosition;

        private String param3;

        public AreaAdapter(Context context, List<AreaInfo.ShowapiResBodyBean.DataBean> list) {
            this.list = list;
        }

        public AreaAdapter(String param3, List<QuInfo.ShowapiResBodyBean.DataBean> list) {
            this.list = list;
            this.param3 = param3;
        }


        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.area_list_item, parent, false);
                viewHolder = new ViewHolder();
                viewHolder.textView = (TextView) convertView.findViewById(android.R.id.text1);
                convertView.setTag(viewHolder);
            }
            viewHolder = (ViewHolder) convertView.getTag();

            if (param3 != null) {
                QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean item = (QuInfo.ShowapiResBodyBean.DataBean.ChildrenBean) list.get(position);
                viewHolder.textView.setText(item.getAreaName());
            } else {
                AreaInfo.ShowapiResBodyBean.DataBean item = (AreaInfo.ShowapiResBodyBean.DataBean) list.get(position);
                viewHolder.textView.setText(item.getAreaName());
            }
            if (lastPosition < position && lastPosition != 0) {
                ObjectAnimator.ofFloat(convertView, "translationY", convertView.getHeight() * 2, 0).setDuration(500).start();
            }
            lastPosition = position;

            return convertView;
        }

        class ViewHolder {
            TextView textView;
        }
    }
}

