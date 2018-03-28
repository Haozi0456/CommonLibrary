# CommonLibrary
公共库模版

[![](https://jitpack.io/v/Haozi0456/CommonLibrary.svg)](https://jitpack.io/#Haozi0456/CommonLibrary)
 
 
 使用方法:

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Haozi0456:CommonLibrary:v1.0.7'
	}


 
包含的库有:

    //绑定注解，专业解决各种findViewById 此库必须写到app的bulider.gradle里,不然无效
    compile 'com.jakewharton:butterknife:8.4.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'

    //网络请求
    compile 'com.lzy.net:okgo:3.0.4'

    //json解析
    compile 'com.google.code.gson:gson:2.8.2'

    // glide图片加载库
    compile 'com.github.bumptech.glide:glide:4.6.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
    //glide的网路请求插件
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'

    //帮助工具类
    compile 'com.blankj:utilcode:1.9.12'

    //图片缩放 沉浸式状态栏
    compile 'com.github.chrisbanes:PhotoView:2.1.3'

    //照片选择 仿微信
    compile('com.lzy.widget:imagepicker:0.6.1') {
        //exclude module: 'PhotoView'//移除重复的photoview包
        exclude group: 'com.github.chrisbanes.photoview', module: 'library'
    }
    
   
   ## 常用库可选项,此CommonLibrary暂未引入,需按需引用
   
   
    ///********************常用必备***********************************/
    
    
    //绑定注解，专业解决各种findViewById 此库必须写到app的bulider.gradle里,不然无效
    compile 'com.jakewharton:butterknife:8.4.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.4.0'

    //网络请求
    compile 'com.lzy.net:okgo:3.0.4'

    //列表适配器
    compile 'com.github.CymChad:BaseRecyclerViewAdapterHelper:2.9.22'

    //json解析
    compile 'com.google.code.gson:gson:2.8.2'

    // glide图片加载库
    compile 'com.github.bumptech.glide:glide:4.6.1'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
    
    //glide的网路请求插件
    compile 'com.github.bumptech.glide:okhttp3-integration:1.4.0@aar'

    //material-dialogs 弹出对话框
    compile 'com.afollestad.material-dialogs:core:0.9.4.5'

    //帮助工具类
    compile 'com.blankj:utilcode:1.9.12'

    //权限控制
    compile 'me.weyye.hipermission:library:1.0.7'

    //图片缩放 沉浸式状态栏
    compile 'com.github.chrisbanes:PhotoView:2.1.3'

    ///********************常用必备***********************************/


    ///********************按需增加***********************************/

    //底部导航栏
    compile 'com.ashokvarma.android:bottom-navigation-bar:2.0.2'

//    compile 'com.nineoldandroids:library:2.4.0'

    //下拉刷新
    compile 'cn.bingoogolapple:bga-refreshlayout:1.1.6@aar'

    //照片选择 仿微信
    compile('com.lzy.widget:imagepicker:0.6.1') {
        //exclude module: 'PhotoView'//移除重复的photoview包
        exclude group: 'com.github.chrisbanes.photoview', module: 'library'
    }
    
    //全屏适配库
//    compile 'com.zhy:autolayout:1.4.5'

    //流程指示器
    //compile 'com.github.baoyachi:StepView:1.9'


    //actionSheet 操作表控件
    compile 'com.baoyz.actionsheet:library:1.1.7'

    //底部选择
    compile 'com.contrarywind:Android-PickerView:3.2.5'
    
    //蓝牙链接
//    compile 'com.junkchen.blelib:blelib:1.2.4'
    compile 'com.inuker.bluetooth:library:1.4.0'
    
    //图片压缩
    compile 'com.zxy.android:tiny:0.1.0'

    //在 AdapterView 和 RecyclerView 中通用的 Adapter 和 ViewHolder。https://github.com/bingoogolapple/BGABaseAdapter-Android
    compile 'cn.bingoogolapple:bga-baseadapter:1.2.7@aar'
    compile 'cn.bingoogolapple:bga-photopicker:1.2.8'

    //数据库
    compile 'org.greenrobot:greendao:3.2.0'
    compile 'org.greenrobot:greendao-generator:3.0.0'

    //WebView
    compile 'com.just.agentweb:agentweb:2.0.1'
    compile 'com.github.lzyzsd:jsbridge:1.0.4'

    //城市选择
    compile 'com.desmond:CityPicker:0.4.6'

//    //二维码必备库
//    compile 'com.google.zxing:core:3.3.0'
//    compile 'cn.bingoogolapple:bga-qrcodecore:1.1.9@aar'
//    compile 'cn.bingoogolapple:bga-zxing:1.1.9@aar'

    compile 'org.greenrobot:eventbus:3.0.0'
    //包含各种工具: 二维码扫描,等
    compile 'com.github.vondear:RxTools:v1.7.6.4'
    //通讯录
    compile 'com.github.mcxtzhang:SuspensionIndexBar:V1.0.0'
    ///********************按需增加***********************************/
