package com.net56888.logistics;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IconView extends LinearLayout
{
        private ImageView mIcon;
        private TextView mName;

        public IconView(Context context, int iconResId, String name)
        {
                super(context);
                
                this.setOrientation(VERTICAL);
                this.setPadding(20, 20, 20, 20);
                this.setGravity(Gravity.CENTER_HORIZONTAL);
                
                mIcon = new ImageView(context);
                mIcon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                mIcon.setImageResource(iconResId);
                //addView(mIcon, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                addView(mIcon, new LinearLayout.LayoutParams(96, 96));
                
                mName = new TextView(context);
                mName.setSingleLine();
                mName.setEllipsize(TextUtils.TruncateAt.END);
                mName.setText(name);
                addView(mName, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        }
        
        public void setIconResId(int resId)
        {
                mIcon.setImageResource(resId);
        }

        public void setName(String name)
        {
                mName.setText(name);
        }
}