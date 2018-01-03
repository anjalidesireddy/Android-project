package app.grepthor.fragmentex26;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ImageAdapter extends BaseAdapter {
    private Context mContext;



    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;

        LayoutInflater in = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if (convertView == null) {

            view = new ViewHolder();
            convertView = in.inflate(R.layout.country_row, null);

            view.txtView = (TextView) convertView
                    .findViewById(R.id.countryName);
            view.txtView1 = (TextView) convertView
                    .findViewById(R.id.desi);


            view.txtView.setText(countryName[position]);
            view.txtView1.setText(desi[position]);

            convertView.setTag(view);

        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.imgView = (ImageView) convertView.findViewById(R.id.countryImage);
        view.imgView.setImageResource(mThumbIds[position]);

        return convertView;
    }

    private Integer[] mThumbIds = { R.drawable.s1,
            R.drawable.s1,R.drawable.s1, R.drawable.s1,R.drawable.s1,

            R.drawable.s1,R.drawable.s1, R.drawable.s1,R.drawable.s1,

            R.drawable.s1,R.drawable.s1, R.drawable.s1,R.drawable.s1,

            R.drawable.s1,R.drawable.s1, R.drawable.s1,R.drawable.s1,
            R.drawable.s1,R.drawable.s1, R.drawable.s1,R.mipmap.ic_launcher,




    };
    private String[] countryName = { "India", "China", "Argentina", "England",
            "France", "United States", "Uruguay", "Pakistan",
            "United Kingdom",
            "Italy", "Germany", "Brazil", "Belgium",

            "Czech Republic", "Jamaica", "Indonesia",
            "Kenya", "Korea", "Ireland", "Denmark",

    };
    private String[] desi = { "India", "China", "Argentina", "England",
            "France", "United States", "Uruguay", "Pakistan",
            "United Kingdom",
            "Italy", "Germany", "Brazil", "Belgium",

            "Czech Republic", "Jamaica", "Indonesia",
            "Kenya", "Korea", "Ireland", "Denmark",

    };


    public static class ViewHolder {
        public ImageView imgView;
        public TextView txtView;
        public TextView txtView1;
    }
}