/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.andrew.samir.manastmovies.adapters;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.andrew.samir.manastmovies.Data.PersonDetailsData;
import com.andrew.samir.manastmovies.R;
import com.squareup.picasso.Picasso;


public class ItemPeopleViewModel extends BaseObservable {

    private PersonDetailsData category;
    private Context context;

    public ItemPeopleViewModel(PersonDetailsData people, Context context) {
        this.category = people;
        this.context = context;
    }


    public String getCategoryName() {
        return category.getName();
    }

    public String getCategoryId() {
        return category.getId() + "";
    }


    public void onItemClick(View view) {
//        context.startActivity(new Intent(view.getContext(), MainActivity.class));
    }

    public void setCategory(PersonDetailsData people) {
        this.category = people;
        notifyChange();
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)

        return "http://image.tmdb.org/t/p/w185/" + category.getProfilePath();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(view);
    }
}
