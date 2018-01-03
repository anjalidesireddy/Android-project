package app.grepthor.fragmentex26;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Grepthor_4 on 1/2/2018.
 */

public class PhotoImageAdapter extends BaseAdapter {
    private Context mContext;

    public PhotoImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(320, 320));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

            /*imageView.setPadding(2, 2, 2, 2);*/
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    private Integer[] mThumbIds = {
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1,
            R.drawable.s1, R.drawable.s1

    };
}