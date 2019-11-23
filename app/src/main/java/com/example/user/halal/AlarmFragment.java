package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AlarmFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent intent = new Intent(getActivity(), AlarmActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
        return inflater.inflate(R.layout.fragment_alarm, container, false);
    }

}