
package com.chomper.watermap.ui;

import android.content.Context;
import android.widget.TextView;
import com.chomper.watermap.R;
import com.daasuu.bl.BubbleLayout;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;


/**
 * Custom implementation of the MarkerView.
 *
 * @author Philipp Jahoda
 */
public class MyMarkerView extends MarkerView {

    private TextView tvContent;
    private BubbleLayout bubbleLayout;

    public MyMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        tvContent = (TextView) findViewById(R.id.tvContent);
        bubbleLayout = (BubbleLayout) findViewById(R.id.bu_layout);
    }

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        tvContent.setText("PH: " + (long) e.getY() + "\n" +
                "时间：" + (long) e.getX() + "");
      /*  tvContent.measure(MeasureSpec.EXACTLY, MeasureSpec.EXACTLY);
        bubbleLayout.setArrowPosition((float) tvContent.getWidth() / 2f- Utils.convertDpToPixel(7.2f));*/
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }
}
