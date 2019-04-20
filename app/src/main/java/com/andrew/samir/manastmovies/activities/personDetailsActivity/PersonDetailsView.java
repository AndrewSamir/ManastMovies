package com.andrew.samir.manastmovies.activities.personDetailsActivity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.adapters.imagesAdapter.ImagesAdapter;
import com.andrew.samir.manastmovies.databinding.PersonDetailsBinding;
import com.andrew.samir.manastmovies.utlities.DataEnum;

import java.util.Observable;

public class PersonDetailsView extends AppCompatActivity implements java.util.Observer {
    private PersonDetailsViewModel personDetailsViewModel;
    PersonDetailsBinding personDetailsBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        initDataBinding(intent.getIntExtra(DataEnum.personId.name(), 0));
        setupListImagesView(personDetailsBinding.listImages);
        setupObserver(personDetailsViewModel);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        personDetailsViewModel.reset();
    }

    private void initDataBinding(int id) {
        personDetailsBinding = DataBindingUtil.setContentView(this, R.layout.person_details);
        personDetailsViewModel = new PersonDetailsViewModel(id, this);
        personDetailsBinding.setViewModel(personDetailsViewModel);
    }

    private void setupListImagesView(RecyclerView profiles) {
        ImagesAdapter adapter = new ImagesAdapter();
        profiles.setAdapter(adapter);
        profiles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }


    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        String flag = (String) arg;

        switch (flag) {
            case "call":
                if (o instanceof PersonDetailsViewModel) {
                    ImagesAdapter imagesAdapter = (ImagesAdapter) personDetailsBinding.listImages.getAdapter();
                    PersonDetailsViewModel personDetailsViewModel = (PersonDetailsViewModel) o;
                    imagesAdapter.setProfilesList(personDetailsViewModel.getProfilesList());
                }
                break;
        }
    }

}
