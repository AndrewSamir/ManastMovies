
package com.andrew.samir.manastmovies.adapters.imagesAdapter;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.andrew.samir.manastmovies.Data.PersonImagesResponseData.Profiles;
import com.andrew.samir.manastmovies.R;
import com.squareup.picasso.Picasso;


public class ItemPersonImagesViewModel extends BaseObservable {

    private Profiles profiles;
    private Context context;

    public ItemPersonImagesViewModel(Profiles profiles, Context context) {
        this.profiles = profiles;
        this.context = context;
    }


    public String getPersonImageUrl() {
        return context.getString(R.string.base_url) + profiles.getFilePath();
    }

    public void setProfile(Profiles profile) {
        this.profiles = profile;
        notifyChange();
    }
    @BindingAdapter({"personImageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
