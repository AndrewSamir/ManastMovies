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

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrew.samir.manastmovies.Data.PersonDetailsData;
import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.databinding.RvItemPersonBinding;
import com.andrew.samir.manastmovies.interfaces.OnRequestMoreListener;

import java.util.Collections;
import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.PeopleAdapterViewHolder> {

    private List<PersonDetailsData> peopleList;
    private OnRequestMoreListener onRequestMoreListener;

    public PeopleAdapter(OnRequestMoreListener onRequestMoreListener) {
        this.peopleList = Collections.emptyList();
        this.onRequestMoreListener = onRequestMoreListener;
    }

    @NonNull
    @Override
    public PeopleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemPersonBinding itemPeopleBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_person,
                        parent, false);
        return new PeopleAdapterViewHolder(itemPeopleBinding);
    }

    @Override
    public void onBindViewHolder(PeopleAdapterViewHolder holder, int position) {
        holder.bindPeople(peopleList.get(position));

        if (position >= getItemCount() - 1 && onRequestMoreListener != null) {
            onRequestMoreListener.requestMoreData(this, position);
        }
    }

    @Override
    public int getItemCount() {
        return peopleList.size();
    }

    public void setPeopleList(List<PersonDetailsData> peopleList) {
        this.peopleList = peopleList;
        notifyDataSetChanged();
    }

    static class PeopleAdapterViewHolder extends RecyclerView.ViewHolder {
        RvItemPersonBinding mRvItemPersonBinding;

        PeopleAdapterViewHolder(RvItemPersonBinding itemPeopleBinding) {
            super(itemPeopleBinding.itemPeople);
            this.mRvItemPersonBinding = itemPeopleBinding;
        }

        void bindPeople(PersonDetailsData people) {
            if (mRvItemPersonBinding.getItemPeopleViewModel() == null) {
                mRvItemPersonBinding.setItemPeopleViewModel(
                        new ItemPeopleViewModel(people, itemView.getContext()));
            } else {
                mRvItemPersonBinding.getItemPeopleViewModel().setCategory(people);
            }
        }
    }
}
