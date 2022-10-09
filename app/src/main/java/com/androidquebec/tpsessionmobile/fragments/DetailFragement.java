package com.androidquebec.tpsessionmobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquebec.tpsessionmobile.R;
import com.androidquebec.tpsessionmobile.activity.HomeActivity;

public class DetailFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match


    private HomeActivity context;

    public DetailFragement(HomeActivity homeActivity) {
        // Required empty public constructor
        context = homeActivity;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}