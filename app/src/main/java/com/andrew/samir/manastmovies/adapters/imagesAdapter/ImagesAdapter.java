
package com.andrew.samir.manastmovies.adapters.imagesAdapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.andrew.samir.manastmovies.Data.PersonImagesResponseData.Profiles;
import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.databinding.RvItemImageBinding;

import java.util.Collections;
import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ImagesAdapterViewHolder> {

    private List<Profiles> profilesList;

    public ImagesAdapter() {
        this.profilesList = Collections.emptyList();
    }

    @NonNull
    @Override
    public ImagesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvItemImageBinding itemImageBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.rv_item_image,
                        parent,false);
        return new ImagesAdapterViewHolder(itemImageBinding);
    }

    @Override
    public void onBindViewHolder(ImagesAdapterViewHolder holder, int position) {
        holder.bindImages(profilesList.get(position));

    }

    @Override
    public int getItemCount() {
        return profilesList.size();
    }

    public void setProfilesList(List<Profiles> profilesList) {
        this.profilesList = profilesList;
        notifyDataSetChanged();
    }

    static class ImagesAdapterViewHolder extends RecyclerView.ViewHolder {
        RvItemImageBinding mRvItemImageBinding;

        ImagesAdapterViewHolder(RvItemImageBinding itemImageBinding) {
            super(itemImageBinding.itemPersonImage);
            this.mRvItemImageBinding = itemImageBinding;
        }

        void bindImages(Profiles profiles) {
            if (mRvItemImageBinding.getItemPersonImagesViewModel() == null) {
                mRvItemImageBinding.setItemPersonImagesViewModel(
                        new ItemPersonImagesViewModel(profiles, itemView.getContext()));
            } else {
                mRvItemImageBinding.getItemPersonImagesViewModel().setProfile(profiles);
            }
        }
    }
}
