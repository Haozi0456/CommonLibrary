package com.zwh.common.tools.glideModule;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.GlideModule;

/**
 * @author Zhaohao
 * @Description: Glide 缓存大小和路径配置类
 * @date 2016-09-06 12:50
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        int cacheSize100MegaBytes = 104857600*2; //磁盘缓存200M
        String downloadDirectoryPath = context.getExternalCacheDir().getPath();
        //设置磁盘缓存大小和路径
        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes)
        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
