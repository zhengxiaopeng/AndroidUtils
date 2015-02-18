package com.rocko.android.common.ui.view;

import android.app.Activity;
import android.view.View;

/**
 * Created by Rocko on 2014/12/29 0029.
 */
public class ToolbarUtils {

    public static View findActionBarContainer(Activity activity) {
        int id = activity.getResources().getIdentifier("action_bar_container", "id", "android");
        return activity.findViewById(id);
    }

    public static View findSplitActionBar(Activity activity) {
        int id = activity.getResources().getIdentifier("split_action_bar", "id", "android");
        return activity.findViewById(id);
    }
}
