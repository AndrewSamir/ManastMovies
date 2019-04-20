package com.andrew.samir.manastmovies.adapters.peopleAdapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.andrew.samir.manastmovies.Data.PeopleResponseData.PersonDetailsData;
import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.activities.personDetailsActivity.PersonDetailsView;
import com.andrew.samir.manastmovies.utlities.DataEnum;
import com.squareup.picasso.Picasso;


public class ItemPeopleViewModel extends BaseObservable {

    private PersonDetailsData personDetailsData;
    private Context context;

    public ItemPeopleViewModel(PersonDetailsData people, Context context) {
        this.personDetailsData = people;
        this.context = context;
    }


    public String getPersonalDetailsName() {
        return personDetailsData.getName();
    }



    public void onItemClick(View view) {
        context.startActivity(new Intent(view.getContext(), PersonDetailsView.class)
                .putExtra(DataEnum.personId.name(), personDetailsData.getId()));
    }

    void setPersonDetailsData(PersonDetailsData people) {
        this.personDetailsData = people;
        notifyChange();
    }

    public String getImageUrl() {
        return context.getString(R.string.base_url) + personDetailsData.getProfilePath();
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
