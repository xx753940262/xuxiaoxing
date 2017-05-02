package com.xiaoxing.common.view.l_recycler_view.interfaces;

import android.view.View;

/**
 * Click and LongClick
 */
public interface OnItemClickListener {
    void onItemClick(View view, int position);
    void onItemLongClick(View view, int position);
}
