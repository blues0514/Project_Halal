package com.example.user.halal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReceipeDetailActivity extends AppCompatActivity {

    private ImageView Detail_imageView1;
    private TextView Detail_Title;

    private TextView Detail_Description;
    private String Receive_title;
    private TextView Foodkinds;
    private TextView Price;
    private TextView Operatingtime;
    private TextView Phone;
    private TextView INGREDIENTS;
    private TextView METHOD;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ChildEventListener child;
    private ListView text_ListView;
    private EditText review;
    private static Button m_button;
    private String msg;
    private ArrayAdapter<String> text_adapter;
    List<Object> Array_text = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detail);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        initDatabase();

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final String getTime = dateFormat.format(date);

        //댓글 관련 뷰들을 불러옴
        //name_ListView = findViewById(R.id.name_listView);

        text_ListView = findViewById(R.id.text_listView);
        m_button = findViewById(R.id.transfer);
        review = findViewById(R.id.review);
        INGREDIENTS = findViewById(R.id.INGREDIENTS);
        METHOD = findViewById(R.id.Food_Price);

        //name_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<String>());
        //name_ListView.setAdapter(name_adapter);

        text_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, new ArrayList<String>());
        text_ListView.setAdapter(text_adapter);


        //intent 선언
        Intent intent = getIntent();
        String dir = intent.getStringExtra("dir");

        //View 불러오기
        Detail_imageView1 = findViewById(R.id.Detail_imageView1);
        Detail_Title = findViewById(R.id.Detail_Title);
//        Detail_Description = findViewById(R.id.Detail_Description);

        //이미지 url 넘겨받아와서 띄우기
        String Receive_url1 = intent.getStringExtra("url1");
        Glide.with(this).load(Receive_url1).into(Detail_imageView1);

//        String Receive_url2 = intent.getStringExtra("url2");
//        Glide.with(this).load(Receive_url2).into(Detail_imageView2);
//        String Receive_url3 = intent.getStringExtra("url3");
//        Glide.with(this).load(Receive_url3).into(Detail_imageView3);

        //이미지 title 넘겨받아와서 띄우기
        Receive_title = intent.getStringExtra("title");
        Detail_Title.setText(Receive_title);

//        String Receive_description = intent.getStringExtra("description");
//        Detail_Description.setText(Receive_description);

        //상세정보 받와와서 띄우기
        String Receive_FoodKinds = intent.getStringExtra("INGREDIENTS");
        String Receive_PriceRange = intent.getStringExtra("METHOD");
//        String Receive_operatingTime = intent.getStringExtra("operatingTime");
//        String Receive_phone = intent.getStringExtra("phone");


        INGREDIENTS.setText(Receive_FoodKinds);
        METHOD.setText(Receive_PriceRange);
//        Operatingtime.setText(Receive_operatingTime);
//        Phone.setText(Receive_phone);

        m_button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {
                msg = review.getText().toString();
                databaseReference.push().setValue(getTime + " " + auth.getCurrentUser().getDisplayName() + " : " + msg);
                review.setText("");
            }
        });

        databaseReference = database.getReference().child(dir).child(Receive_title).child("Message");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //name_adapter.clear();
                text_adapter.clear();

                for (DataSnapshot messageData : dataSnapshot.getChildren()) {

                    String msg2 = messageData.getValue().toString();

                    // 유저의 이름을 리스트에 추가
                    //Array_text.add(auth.getCurrentUser().getDisplayName());
                    //text_adapter.add(auth.getCurrentUser().getDisplayName());

                    // 리뷰를 리스트에 추가
                    //Array_text.add(msg2);
                    //text_adapter.add(msg2);

                    Array_text.add(msg2);
                    text_adapter.add(msg2);
                }
                //name_adapter.notifyDataSetChanged();
                //name_ListView.setSelection(name_adapter.getCount() - 1);

                text_adapter.notifyDataSetChanged();
                text_ListView.setSelection(text_adapter.getCount() - 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void initDatabase() {

        databaseReference = database.getReference().child("Message");
        //databaseReference.child("Message").setValue(auth.getCurrentUser().getDisplayName() + " : " + msg);
        //databaseReference.child("Message").setValue(auth.getCurrentUser().getDisplayName());

        child = new ChildEventListener(){


            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        databaseReference.addChildEventListener(child);
    }

    @Override
    protected  void onDestroy() {
        super.onDestroy();
        databaseReference.removeEventListener(child);
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        LatLng Restaurant = new LatLng(getIntent().getDoubleExtra("lat",0.0),getIntent().getDoubleExtra("lon",0.0));
//        googleMap.addMarker(new MarkerOptions().position(Restaurant).title("Restaurant"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Restaurant,15));
//    }
}