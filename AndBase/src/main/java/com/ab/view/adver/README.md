# 文字垂直滚动，广告
- 如何使用:
```python
initData();
JDViewAdapter adapter = new JDViewAdapter(datas);
tbView.setAdapter(adapter);
//开启线程滚东
tbView.start();

private void initData() {
    datas.add(new AdverNotice("瑞士维氏军刀 新品满200-50", "最新"));
    datas.add(new AdverNotice("家居家装焕新季，讲199减100！", "最火爆"));
    datas.add(new AdverNotice("带上相机去春游，尼康低至477", "HOT"));
    datas.add(new AdverNotice("价格惊呆！电信千兆光纤上市", "new"));
}
```