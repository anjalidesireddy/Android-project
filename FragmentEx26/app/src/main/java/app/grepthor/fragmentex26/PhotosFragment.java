package app.grepthor.fragmentex26;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

/**
 * Created by Grepthor_4 on 1/2/2018.
 */

public class PhotosFragment extends Fragment {
    Intent intent;
    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_layout, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.photogridview);
        gridView.setAdapter(new PhotoImageAdapter(view.getContext()));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                switch (position) {
                    case 0:
                       /* image.setDrawingCacheEnabled(true);
                        Bitmap b=image.getDrawingCache();
                        Intent i = new Intent(this, ImageSingle.class);

                        i.putExtra("Bitmap", b);
                        startActivity(i);*/
                        Toast.makeText(getContext(), "position"+position, Toast.LENGTH_SHORT).show();

                        break;
                    case 1:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();

                        break;
                    case 3:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();

                        break;
                    case 5:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:   Toast.makeText(getContext(), "position", Toast.LENGTH_SHORT).show();

                        break;
                    case 7:
                        break;
                    case 8:

                        break;
                    case 10:
                        break;

                }

            }
        });


        // uses the view to get the context instead of getActivity().
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}