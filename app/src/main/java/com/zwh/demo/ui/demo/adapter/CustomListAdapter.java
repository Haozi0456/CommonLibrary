package com.zwh.demo.ui.demo.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zwh.demo.R;
import com.zwh.demo.ui.demo.bean.TestBean;

import java.util.List;

/**
 * @author Zhaohao
 * @Description:
 * @date 2017-03-12 17:41
 */

public class CustomListAdapter extends BaseQuickAdapter<TestBean,BaseViewHolder> {

    public CustomListAdapter(int layoutResId, List<TestBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, TestBean bean) {
        holder.setText(R.id.text, bean.text);
    }
}
