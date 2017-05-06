# 头部菜单，下拉选择
- 如何使用：
```python
public class Fragment_Two extends AppBaseFragment {
    @Bind(R.id.menu)
    DropDownMenu mMenu;
    @Bind(R.id.lv_list)
    ListView mList;

    private int all_index;
    private int zhi_neng_index;
    private List<Map<String, Object>> data;
    final String[] arr1 = new String[]{"全部分类", "李沧", "市南", "市北", "黄岛", "青岛", "胶州"};
    final String[] arr2 = new String[]{"智能分类", "goods1", "goods2"};

    final String[] strings = new String[]{"全部分类", "智能分类"};

    @Override
    public int bindLayout() {
        return R.layout.fragment_two;
    }

    @Override
    public void initView(View view, LayoutInflater inflater) {
        super.initView(view, inflater);

        mMenu.setmMenuCount(2);
        mMenu.setmShowCount(6);
        mMenu.setShowCheck(true);
        mMenu.setmMenuTitleTextSize(16);
        mMenu.setmMenuTitleTextColor(Color.parseColor("#777777"));
        mMenu.setmMenuListTextSize(16);
        mMenu.setmMenuListTextColor(Color.BLACK);
        mMenu.setmMenuBackColor(Color.parseColor("#eeeeee"));
        mMenu.setmMenuPressedBackColor(Color.WHITE);
        mMenu.setmMenuPressedTitleTextColor(Color.BLACK);

        mMenu.setmCheckIcon(R.drawable.ico_make);

        mMenu.setmUpArrow(R.drawable.arrow_up);
        mMenu.setmDownArrow(R.drawable.arrow_down);

        mMenu.setDefaultMenuTitle(strings);


        mMenu.setShowDivider(false);
        mMenu.setmMenuListBackColor(getResources().getColor(R.color.white));
        mMenu.setmMenuListSelectorRes(R.color.white);
        mMenu.setmArrowMarginTitle(20);

        mMenu.setMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onSelected(View listview, int RowIndex, int ColumnIndex) {
                if (ColumnIndex == 0) {
                    all_index = RowIndex;
                } else if (ColumnIndex == 1) {
                    zhi_neng_index = RowIndex;
                }
                //过滤筛选
                setFilter();
            }
        });
        List<String[]> items = new ArrayList<>();
        items.add(arr1);
        items.add(arr2);
        mMenu.setmMenuItems(items);
        mMenu.setIsDebug(false);
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), getData(), R.layout.item_re_xiao, new String[]{"img", "tv_title", "tv_address", "tv_price", "tv_renshu"}, new int[]{R.id.img, R.id.tv_title, R.id.tv_address, R.id.tv_price, R.id.tv_renshu});
        mList.setAdapter(adapter);
    }

    @Override
    public void doBusiness(Context mContext) {
        super.doBusiness(mContext);
        setFragmentTopBar(getString(R.string.two));
    }

    private void setFilter() {
        List<Map<String, Object>> temp = new ArrayList<>();
        for (int i = 0; i < getData().size(); i++) {
            boolean all = (all_index == 0) ? true : data.get(i).toString().contains(arr1[all_index]);
            boolean zhi_neng = ((zhi_neng_index == 0) ? true : data.get(i).toString().contains(arr2[zhi_neng_index]));
            if (all && zhi_neng) {
                temp.add(data.get(i));
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), temp, R.layout.item_re_xiao, new String[]{"img", "tv_title", "tv_address", "tv_price", "tv_renshu"}, new int[]{R.id.img, R.id.tv_title, R.id.tv_address, R.id.tv_price, R.id.tv_renshu});
        mList.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() {
        //map.put(参数名字,参数值)
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("img", R.drawable.goods1);
        map.put("tv_title", "goods1");
        map.put("tv_address", "山东省青岛市李沧区");
        map.put("tv_price", "￥1008.00");
        map.put("tv_renshu", "10人付款");
        list.add(map);

        map = new HashMap<>();
        map.put("img", R.drawable.goods2);
        map.put("tv_title", "goods2");
        map.put("tv_address", "山东省青岛市市南区");
        map.put("tv_price", "￥2008.00");
        map.put("tv_renshu", "20人付款");
        list.add(map);

        map = new HashMap<>();
        map.put("tv_title", "goods3");
        map.put("img", R.drawable.goods3);
        map.put("tv_address", "山东省青岛市胶州");
        map.put("tv_price", "￥2008.00");
        map.put("tv_renshu", "1000人付款");
        list.add(map);
        data = list;
        return list;
    }

}
```


<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="#ffffff">

    <include layout="@layout/fragment_top_bar" />

    <LinearLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="@dimen/height_48"
        android:orientation="vertical">

        <com.ab.view.dropdownmenu.DropDownMenu
            android:id="@+id/menu"
            android:layout_width="fill_parent"
            android:layout_height="@dimen/height_45"
            android:background="@color/ripple_material_dark"
            android:orientation="horizontal" />

        <ListView
            android:id="@+id/lv_list"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_below="@id/menu"
            android:background="#ffffff"></ListView>
    </LinearLayout>
</RelativeLayout>
