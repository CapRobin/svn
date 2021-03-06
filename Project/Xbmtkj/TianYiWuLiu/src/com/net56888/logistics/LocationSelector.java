package com.net56888.logistics;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.net56888.logistics.data.EntitySet;
import com.net56888.logistics.data.LocationEntity;
import com.net56888.logistics.ui.ExpandableGridView;

public class LocationSelector extends Fragment {

    private ViewGroup mView;

    private ExpandableGridView mGridView;

    private ArrayList<Button> mBtnArr;
    private ArrayList<Integer> mBtnIdArr;
    private ArrayList<Integer> mBtnStrArr;
    private int mBtnIdx;

    private int MAX_BTN_CNT = 3;
    private int MIN_BTN_CNT = 1;
    private int mBtnCnt = this.MAX_BTN_CNT;

    @Override
    public void onInflate(Activity activity, AttributeSet attrs,
            Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);

        TypedArray a = activity.obtainStyledAttributes(attrs,
                R.styleable.LocationSelector);
        int btnCnt = a.getInt(R.styleable.LocationSelector_button_count,
                this.MAX_BTN_CNT);
        if (this.MIN_BTN_CNT <= btnCnt && btnCnt <= this.MAX_BTN_CNT)
            this.mBtnCnt = btnCnt;
        a.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        mBtnIdx = 0;

        mView = (ViewGroup) inflater.inflate(R.layout.fragment_locations, null);
        mGridView = (ExpandableGridView) mView.findViewById(R.id.locations);
        mGridView.setExpanded(true);

        mBtnIdArr = new ArrayList<Integer>();
        mBtnIdArr.add(R.id.province_btn);
        mBtnIdArr.add(R.id.city_btn);
        mBtnIdArr.add(R.id.area_btn);

        mBtnStrArr = new ArrayList<Integer>();
        mBtnStrArr.add(R.string.province_btn);
        mBtnStrArr.add(R.string.city_btn);
        mBtnStrArr.add(R.string.area_btn);

        mBtnArr = new ArrayList<Button>();
        for (int i = 0; i < this.MAX_BTN_CNT; i++) {
            if (i < this.mBtnCnt) {
                mBtnArr.add((Button) mView.findViewById(mBtnIdArr.get(i)));
            } else {
                ((Button) mView.findViewById(mBtnIdArr.get(i)))
                        .setVisibility(View.GONE);
            }
        }

        for (int idx = 0; idx < mBtnArr.size(); idx++) {
            Button btn = mBtnArr.get(idx);
            btn.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    int index = mBtnIdArr.indexOf(v.getId());
                    if (index > 0 && mBtnArr.get(index - 1).getTag() == null) {
                        String warning = LocationSelector.this.getActivity()
                                .getString(R.string.loc_warning);
                        String area = LocationSelector.this.getActivity()
                                .getString(mBtnStrArr.get(index - 1));
                        Toast.makeText(LocationSelector.this.getActivity(),
                                warning + area, Toast.LENGTH_SHORT).show();

                        return;
                    }

                    if (index > 0) {
                        LocationEntity le = (LocationEntity) mBtnArr.get(
                                index - 1).getTag();
                        if (null != le) {
                            setGridViewContent(le.getMsg_ID());
                        }

                    } else {
                        setGridViewContent(0);
                    }

                    for (int i = index + 1; i < mBtnArr.size(); i++) {
                        Button btn = (Button) mBtnArr.get(i);
                        btn.setTag(null);
                        btn.setText(mBtnStrArr.get(i));

                        Drawable d = btn.getBackground();
                        btn.invalidateDrawable(d);
                        d.clearColorFilter();
                    }

                    mBtnIdx = index;
                }

            });
        }

        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View view,
                    int position, long id) {
                LocationEntity le = (LocationEntity) adapter
                        .getItemAtPosition(position);

                Button btn = (Button) mBtnArr.get(mBtnIdx);
                btn.setTag(le);
                btn.setText(le.getMsg_Location());

                Drawable d = btn.getBackground();
                PorterDuffColorFilter filter = new PorterDuffColorFilter(
                        Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                d.setColorFilter(filter);

                if (++mBtnIdx < mBtnCnt) {
                    LocationSelector.this.setGridViewContent(le.getMsg_ID());
                } else {
                    LocationSelector.this.setGridViewContent(-1);
                }
            }

        });

        return mView;
    }

    private void setGridViewContent(int parentId) {
        Context context = LocationSelector.this.getActivity();
        EntitySet es = App.getLogisticsDB().getLocationTable()
                .fetchByParentID(parentId);
        LocationSelector.this.mGridView.setAdapter(new LocationAdapter(context,
                es));
    }

    public EntitySet getLocations() {
        EntitySet es = new EntitySet();
        for (int i = 0; i < mBtnArr.size(); i++) {
            Button btn = (Button) mBtnArr.get(i);
            LocationEntity e = (LocationEntity) btn.getTag();
            if (null != e)
                es.add(e);
        }
        return es;
    }
    
    
    public String getLocationString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mBtnArr.size(); i++) {
            Button btn = (Button) mBtnArr.get(i);
            LocationEntity e = (LocationEntity) btn.getTag();
            if (null != e)
                sb.append(e.getMsg_Location());
        }
        return sb.toString();
    }

    public void reset() {

        for (int i = 0; i < mBtnArr.size(); i++) {
            Button btn = (Button) mBtnArr.get(i);
            btn.setTag(null);
            btn.setText(mBtnStrArr.get(i));

            Drawable d = btn.getBackground();
            btn.invalidateDrawable(d);
            d.clearColorFilter();
        }
        
        setGridViewContent(-1);
    }

    public class LocationAdapter extends BaseAdapter {
        private Context mContext;
        private EntitySet mLocations;

        public LocationAdapter(Context c, EntitySet locations) {
            mContext = c;
            mLocations = locations;
        }

        public int getCount() {
            return mLocations.size();
        }

        public Object getItem(int position) {
            return mLocations.get(position);
        }

        public long getItemId(int position) {
            LocationEntity le = (LocationEntity) mLocations.get(position);
            return le.getMsg_ID();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView;
            if (convertView == null) {
                textView = new TextView(mContext);
                textView.setTextColor(Color.parseColor("#000000"));
                textView.setPadding(16, 16, 16, 16);
            } else {
                textView = (TextView) convertView;
            }

            LocationEntity le = (LocationEntity) mLocations.get(position);

            textView.setText(le.getMsg_Location());
            return textView;
        }

    }
}
