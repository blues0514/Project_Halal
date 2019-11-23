package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MapsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent=new Intent(getActivity(),MapsActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        return inflater.inflate(R.layout.activity_maps, container, false);

    }


}
