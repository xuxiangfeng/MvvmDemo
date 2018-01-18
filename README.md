# MvvmDemo
mvvm demo with databinding

将databinding控制放在Model中，ViewModel与模块对应，专注于业务处理，其中的Callback为业务的回调。view专注于ui显示以及事件的响应。

view的交互:

  xml->activity
  1.android:onclick="addCount"
  2.android:onclick="@{()->vm.addCount(user)}"
  
  activity->xml
  与原先一样
