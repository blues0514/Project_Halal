package com.example.user.halal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private TextView nameTextView;
    private TextView emailTextView;
    private ImageView imageView;
    private Button logout_button;
    private FirebaseAuth auth;
    MainActivity mainActivity;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        auth = FirebaseAuth.getInstance();
        nameTextView = view.findViewById(R.id.nameTextView);
        emailTextView = view.findViewById(R.id.emailTextView);
        imageView = view.findViewById(R.id.imageView);

        mainActivity = new MainActivity();


        NavigationView navigationView = view.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        Thread.sleep(100);
                    }
                    catch(InterruptedException e)
                    {

                    }
                    if(getActivity() == null) return;
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try{
                                nameTextView.setText(auth.getCurrentUser().getDisplayName());
                                emailTextView.setText(auth.getCurrentUser().getEmail());
                            }
                            catch(Exception e){

                            }
                        }
                    });
                }}
        });
        t.start();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    Fragment newFragment = new HomeFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, newFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    return true;
                }

                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch (item.getItemId()) {
            case R.id.nav_home:
                item.setChecked(true);
                selectedFragment = new HomeFragment();
                break;
            case R.id.nav_map:
                item.setChecked(true);
                selectedFragment = new MapsFragment();
                break;
            case R.id.nav_pray:
                item.setChecked(true);
                selectedFragment = new AlarmFragment();
                break;
            case R.id.nav_mosque:
                item.setChecked(true);
                selectedFragment = new CompassFragment();
                break;
            case R.id.nav_barcode:
                item.setChecked(true);
                selectedFragment = new ScannerFragment();
                break;
            case R.id.nav_logout:
                auth.signOut();
                getActivity().finish();
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    }
}
