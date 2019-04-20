
package com.andrew.samir.manastmovies.adapters.peopleAdapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrew.samir.manastmovies.Data.PeopleResponseData.PersonDetailsData;
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
                mRvItemPersonBinding.getItemPeopleViewModel().setPersonDetailsData(people);
            }
        }
    }
}
