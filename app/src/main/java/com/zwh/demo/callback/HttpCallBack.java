//package com.zwh.demo.callback;
//
//import com.google.gson.Gson;
//import com.google.gson.internal.$Gson$Types;
//import com.lzy.okgo.callback.AbsCallback;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//
//import okhttp3.Response;
//
//
//public abstract class HttpCallBack<T> extends AbsCallback<T> {
//
//    private Type type;
//
//    public void setType(Type type) {
//        this.type = type;
//    }
//
//
//    public Type getSuperclassTypeParameter(Class<?> subclass) {
//        Type superclass = subclass.getGenericSuperclass();
//        if (superclass instanceof Class) return null;
//        ParameterizedType parameterized = (ParameterizedType) superclass;
//        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
//    }
//
//
//    @Override
//    public T convertSuccess(Response response) throws Exception {
//        JSONObject json;
//        try {
//            json = new JSONObject(response.body().string());
//        } catch (JSONException e) {
//            e.printStackTrace();
//            throw new IllegalStateException("服务器异常，请稍后重试");
//        }
//        int code = json.optInt("code");
//        String msg = json.getString("msg");
//        if (code == 1) {
//            if (type == null) {
//                //以下代码是通过泛型解析实际参数,泛型必须传
//                type = getSuperclassTypeParameter(getClass());
//            }
//
//            //无数据类型
//            if (type == Void.class)
//                return null;
//
//
//            return new Gson().fromJson(json.optString("data"), type);
//        } else if (code == 0) {
//            throw new IllegalStateException(msg);
//        } else if (code == 300) {
//            //比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
//            throw new IllegalStateException("其他乱七八糟的等");
//        } else {
//            throw new IllegalStateException("错误代码：" + code + "，错误信息：" + json.optString("msg"));
//        }
//    }
//}