package com.andrew.samir.manastmovies.activities.peopleActivity;

import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;


import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.adapters.PeopleAdapter;
import com.andrew.samir.manastmovies.databinding.PeopleListBinding;

import java.util.Observable;

public class PeopleView extends AppCompatActivity implements java.util.Observer {
    private PeopleViewModel peopleViewModel;
    PeopleListBinding peopleListBinding;

    Button btnTest;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListPeopleView(peopleListBinding.listPeople);
        setupObserver(peopleViewModel);
        btnTest = findViewById(R.id.btnTest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        peopleViewModel.reset();
    }

    private void initDataBinding() {
        peopleListBinding = DataBindingUtil.setContentView(this, R.layout.people_list);
        peopleViewModel = new PeopleViewModel(this);
        peopleListBinding.setPeopleViewModel(peopleViewModel);
        setLiveDataListiner();
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter();
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setLiveDataListiner() {
        peopleViewModel.testLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                btnTest.setText(integer + "");
            }
        });
    }


    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        String flag = (String) arg;

        switch (flag) {
            case "test":

                break;
            case "call":
                if (o instanceof PeopleViewModel) {
                    PeopleAdapter peopleAdapter = (PeopleAdapter) peopleListBinding.listPeople.getAdapter();
                    PeopleViewModel peopleViewModel = (PeopleViewModel) o;
                    peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
                }
                break;
        }

    }
}
