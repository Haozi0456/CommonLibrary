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
