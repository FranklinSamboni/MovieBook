package com.frank.moviebook.movies;

import android.databinding.BindingAdapter;
import android.media.Image;
import android.widget.ImageView;
import android.widget.ListView;

import com.frank.moviebook.R;
import com.frank.moviebook.Util.Globals;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by FRANK on 21/11/2017.
 */

public class MovieImageListBindings {

    @BindingAdapter({"bind:loadImage"})
    public static void loadImage(ImageView imageView, String path) {
        String url = Globals.URL_IMAGES + path;
        Picasso.with(imageView.getContext()).load(url).error(R.mipmap.ic_launcher).into(imageView);
    }
}
