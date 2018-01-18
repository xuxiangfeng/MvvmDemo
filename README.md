# MvvmDemo
mvvm demo with databinding

将databinding控制放在Model中，ViewModel与模块对应，专注于业务处理，其中的Callback为业务的回调。view专注于ui显示以及事件的响应。
其中xml-》activity使用类似于android:onclick="addCount"的方式，或着若是没有前置ui处理的话，可以直接使用databinding的android:onclick="@{()->vm.addCount(user)}"直接调用vm的业务逻辑.而activity->xml则与原先一样
