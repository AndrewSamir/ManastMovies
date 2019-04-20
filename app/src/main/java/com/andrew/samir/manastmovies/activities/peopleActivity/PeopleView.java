package com.andrew.samir.manastmovies.activities.peopleActivity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.andrew.samir.manastmovies.R;
import com.andrew.samir.manastmovies.adapters.peopleAdapter.PeopleAdapter;
import com.andrew.samir.manastmovies.databinding.PeopleListBinding;
import com.andrew.samir.manastmovies.interfaces.OnRequestMoreListener;
import com.andrew.samir.manastmovies.utlities.HelpMe;

import java.util.Observable;

public class PeopleView extends AppCompatActivity implements java.util.Observer, OnRequestMoreListener {
    private PeopleViewModel peopleViewModel;
    PeopleListBinding peopleListBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataBinding();
        setupListPeopleView(peopleListBinding.listPeople);
        setupObserver(peopleViewModel);
        HelpMe.getInstance(this).hideKeyBoard(this);
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
    }

    private void setupListPeopleView(RecyclerView listPeople) {
        PeopleAdapter adapter = new PeopleAdapter(this);
        listPeople.setAdapter(adapter);
        listPeople.setLayoutManager(new LinearLayoutManager(this));
    }


    public void setupObserver(Observable observable) {
        observable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        String flag = (String) arg;

        switch (flag) {
            case "call":
                if (o instanceof PeopleViewModel) {
                    PeopleAdapter peopleAdapter = (PeopleAdapter) peopleListBinding.listPeople.getAdapter();
                    PeopleViewModel peopleViewModel = (PeopleViewModel) o;
                    peopleAdapter.setPeopleList(peopleViewModel.getPeopleList());
                }
                break;
        }
    }

    @Override
    public void requestMoreData(RecyclerView.Adapter adapter, int position) {
        if (peopleViewModel.isSearch)
            peopleViewModel.callSearchPeopleMore();
        else
            peopleViewModel.fetchCategoriesMore();
    }
}
