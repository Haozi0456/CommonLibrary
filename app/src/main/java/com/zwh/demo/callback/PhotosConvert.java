//package com.zwh.demo.callback;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.lzy.okgo.convert.Converter;
//import com.zwh.demo.utils.Convert;
//
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//
//import okhttp3.Response;
//
///**
// * @author Zhaohao
// * @Description:
// * @Date 2016/11/25 15:27
// */
//
//public class PhotosConvert<T> implements Converter<T> {
//    private Type type;
//
//    public void setType(Type type) {
//        this.type = type;
//    }
//
//    @Override
//    public T convertSuccess(Response response) throws Exception {
////        JsonReader jsonReader = new JsonReader(response.body().charStream());
//        if (type == null) {
//            //以下代码是通过泛型解析实际参数,泛型必须传
//            Type genType = getClass().getGenericSuperclass();
//            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
//            type = params[0];
//        }
//        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");
//        Type rawType = ((ParameterizedType) type).getRawType();
//
//        //无数据类型
////        if (rawType == Void.class) {
////            SimpleResponse baseWbgResponse = Convert.fromJson(jsonReader, SimpleResponse.class);
////            //noinspection unchecked
////            return (T) baseWbgResponse.toLzyResponse();
////        }
//
//        Gson gson = new Gson();
//        JsonParser jsonParser = new JsonParser();
//        JsonElement jsonElement = jsonParser.parse(response.body().charStream());
//        JsonObject jsonObject = jsonElement.getAsJsonObject();
//        JsonElement jsonData =  jsonObject.get("results");
//
//        return Convert.fromJson(jsonData.toString(), type);
//
//
////        //有数据类型
////        if (rawType == DataBackResult.class) {
//////            DataBackResult lzyResponse = Convert.fromJson(jsonReader, type);
//////            int code = lzyResponse.code;
////            if (code == 0) {
////                //noinspection unchecked
////                return (T) lzyResponse;
////            } else if (code == 104) {
////                //比如：用户授权信息无效，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
////                throw new IllegalStateException("用户授权信息无效");
////            } else if (code == 105) {
////                //比如：用户收取信息已过期，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
////                throw new IllegalStateException("用户收取信息已过期");
////            } else if (code == 106) {
////                //比如：用户账户被禁用，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
////                throw new IllegalStateException("用户账户被禁用");
////            } else if (code == 300) {
////                //比如：其他乱七八糟的等，在此实现相应的逻辑，弹出对话或者跳转到其他页面等,该抛出错误，会在onError中回调。
////                throw new IllegalStateException("其他乱七八糟的等");
////            } else {
////                throw new IllegalStateException("错误代码：" + code + "，错误信息：" + lzyResponse.msg);
////            }
////        }
////        throw new IllegalStateException("基类错误无法解析!");
//    }
//}
