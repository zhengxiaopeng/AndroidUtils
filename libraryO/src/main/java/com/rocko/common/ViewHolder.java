package com.rocko.common;

import android.util.SparseArray;
import android.view.View;

/**
 * 用于Adapter优化的view holder
 *
 * @author Mr.Zheng
 * @date 2014年7月31日19:02:07
 */
public class ViewHolder {
    /**
     * @param view   content view
     * @param viewId 控件的Id
     * @return
     */
    public static <T extends View> T get(View view, int viewId) {
        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<View>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(viewId);
        if (childView == null) {
            childView = view.findViewById(viewId);
            viewHolder.put(viewId, childView);
        }

        return (T) childView;
    }
}
