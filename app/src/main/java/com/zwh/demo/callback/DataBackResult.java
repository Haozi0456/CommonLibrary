package com.zwh.demo.callback;



import java.io.Serializable;

/**
 * @author Zhaohao
 * @Description: 数据返回适配类 大部分的Http服务可能都是这样设置，resultCode和resultMessage的内容相对比较稳定，而data的内容变化多端
 * @Date 2016/09/30 09:41
 */

public class DataBackResult<T> implements Serializable{

//    public int code; //状态码
    public String error; // 状态信息

    public T results;




}
