package com.example.user.halal;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_PERMISSION = 1000;
    private GoogleMap mMap;
    private GoogleApiClient mGoogleApiClient;
    private FusedLocationProviderClient mFusedLocationClient;


    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private ArrayList<String> temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<String>();
        temp = new ArrayList<>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        Log.d("하위",  "값 : "+ arraylist.get(0));

        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(temp, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });
    }

    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            //list.addAll(arraylist)
        }
        // 문자 입력을 할때..
        else
        {
            //list.addAll(arraylist);

            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < arraylist.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }

            adapter.setList(list);
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        list.add("The Halal Guys Itaewon Store");
        list.add("Kerban Itaewon Store");
        list.add("Gurkha");
        list.add("Eid");
        list.add("HAJJkorea Halalfood");
        list.add("Murree");
        list.add("Makan");
        list.add("Hibiscus Asian Restaurant");
        list.add("Adnan Kebab Halal");
        list.add("Gurkha Myeong-dong Store");
        list.add("Kampungku Restaurant");
        list.add("5555555555");
        list.add("5555555555");
        list.add("The Halal Guys Gang-nam Store");
        list.add("Yang-gug");
        list.add("Kerban Gang-nam Store");
        list.add("Halal Kitchen");
        list.add("Nirvana Halal Indian Kitchen");
        list.add("babaindia");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        final LatLng food1 = new LatLng(37.534683, 126.994756);
        mMap.addMarker(new MarkerOptions().position(food1).title("The Halal Guys Itaewon Store"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(food1));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(food1, 15.5f));       //처음카메라 위치

        final LatLng food2 = new LatLng(37.534332, 126.995358);
        mMap.addMarker(new MarkerOptions().position(food2).title("Kerban Itaewon Store"));

        final LatLng food3 = new LatLng(37.563038, 126.985159);
        mMap.addMarker(new MarkerOptions().position(food3).title("Gurkha"));

        final LatLng food4 = new LatLng(37.532298, 126.999177);
        mMap.addMarker(new MarkerOptions().position(food4).title("Eid"));

        final LatLng food5 = new LatLng(37.533317, 126.998010);
        mMap.addMarker(new MarkerOptions().position(food5).title("HAJJkorea Halalfood"));

        final LatLng food6 = new LatLng(37.533058, 126.996667);
        mMap.addMarker(new MarkerOptions().position(food6).title("Murree"));

        final LatLng food7 = new LatLng(37.532663, 126.998178);
        mMap.addMarker(new MarkerOptions().position(food7).title("Makan"));

        final LatLng food8 = new LatLng(37.564296, 126.968500);
        mMap.addMarker(new MarkerOptions().position(food8).title("Hibiscus Asian Restaurant"));

        final LatLng food9 = new LatLng(37.558864, 126.978483);
        mMap.addMarker(new MarkerOptions().position(food9).title("Adnan Kebab Halal"));

        final LatLng food10 = new LatLng(37.563035, 126.985160);
        mMap.addMarker(new MarkerOptions().position(food10).title("Gurkha Myeong-dong Store"));

        final LatLng food11 = new LatLng(37.558997, 126.986018);
        mMap.addMarker(new MarkerOptions().position(food11).title("Kampungku Restaurant"));

        final LatLng food12 = new LatLng(37.501838, 127.024688);
        mMap.addMarker(new MarkerOptions().position(food12).title("The Halal Guys Gang-nam Store"));

        final LatLng food13 = new LatLng(37.502023, 127.034719);
        mMap.addMarker(new MarkerOptions().position(food13).title("Yang-gug"));

        final LatLng food14 = new LatLng(37.500072, 127.036239);
        mMap.addMarker(new MarkerOptions().position(food14).title("Kerban Gang-nam Store"));

        final LatLng food15 = new LatLng(37.583125, 126.982063);
        mMap.addMarker(new MarkerOptions().position(food15).title("Halal Kitchen"));

        final LatLng food16 = new LatLng(37.575113, 126.983747);
        mMap.addMarker(new MarkerOptions().position(food16).title("Nirvana Halal Indian Kitchen"));

        final LatLng food17 = new LatLng(37.583123, 126.982058);
        mMap.addMarker(new MarkerOptions().position(food17).title("babaindia"));

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                String strUri = "http://www.naver.com";

                if (marker.getPosition().equals(food1)) {
                    strUri = "https://www.google.co.kr/maps/place/%ED%95%A0%EB%9E%84%EA%B0%80%EC%9D%B4%EC%A6%88+%EC%9D%B4%ED%83%9C%EC%9B%90%EB%B3%B8%EC%A0%90/@37.5346791,126.9925677,17z/data=!3m1!4b1!4m5!3m4!1s0x357ca24af94bbc6d:0x91646e303ef3292c!8m2!3d37.5346791!4d126.9947564?hl=ko";
                } else if (marker.getPosition().equals(food2)) {
                    strUri = "https://www.google.co.kr/maps/place/%EC%BC%80%EB%A5%B4%EB%B0%98/@37.5282822,126.9819847,13z/data=!3m1!5s0x357ca41251f85e93:0x7d84526e84fecb18!4m8!1m2!2m1!1z7LyA66W067CY!3m4!1s0x357ca46a63edf39b:0x1020ba22986c41ed!8m2!3d37.5114397!4d127.0588405?hl=ko";
                } else if (marker.getPosition().equals(food3)) {
                    strUri = "https://www.google.co.kr/maps/place/%EA%B5%AC%EB%A5%B4%EC%B9%B4+%EC%9D%B8%EB%8F%84%EB%A0%88%EC%8A%A4%ED%86%A0%EB%9E%91+%ED%95%A0%EB%9E%84+%EC%9D%8C%EC%8B%9D/@37.563068,126.9850064,21z/data=!4m12!1m6!3m5!1s0x357ca2f01827888b:0xb41395d36d6dbdf8!2z6rWs66W07Lm0IOyduOuPhOugiOyKpO2GoOuekSDtlaDrnoQg7J2M7Iud!8m2!3d37.563034!4d126.9851598!3m4!1s0x357ca2f01827888b:0xb41395d36d6dbdf8!8m2!3d37.563034!4d126.9851598?hl=ko";
                } else if (marker.getPosition().equals(food4)) {
                    strUri = "https://www.google.co.kr/maps/place/%EC%9D%B4%EB%93%9C/@37.5323389,126.9990294,21z/data=!4m8!1m2!2m1!1z7ZWg656E7J2M7Iud7KCQ!3m4!1s0x0:0xebf553fbb5b91fab!8m2!3d37.5322969!4d126.9991777?hl=ko";
                } else if (marker.getPosition().equals(food5)) {
                    strUri = "https://www.google.co.kr/maps/place/HAJJkoreahalalfood/@37.5333134,126.9978891,21z/data=!4m12!1m6!3m5!1s0x357ca3b1a051255f:0x88712941038b7f91!2sHAJJkoreahalalfood!8m2!3d37.5333147!4d126.9980106!3m4!1s0x357ca3b1a051255f:0x88712941038b7f91!8m2!3d37.5333147!4d126.9980106?hl=ko";
                } else if (marker.getPosition().equals(food6)) {
                    strUri = "https://www.google.co.kr/maps/place/Murree/@37.5330556,126.994478,17z/data=!4m8!1m2!2m1!1z7ZWg656E7J2M7Iud7KCQIG11cnJlZQ!3m4!1s0x357ca24ab647f93f:0x4a14a3d13781477d!8m2!3d37.5330556!4d126.9966667?hl=ko";
                } else if (marker.getPosition().equals(food7)) {
                    strUri = "https://www.google.co.kr/maps/place/Makan+Restorant+Korean/@37.5327043,126.9982147,21z/data=!4m5!3m4!1s0x0:0xca4acb472cac7764!8m2!3d37.532661!4d126.9982158?hl=ko";
                } else if (marker.getPosition().equals(food8)) {
                    strUri = "https://www.google.co.kr/maps/place/Hibiscus+Asian+Restaurant/@37.5643977,126.9683587,21z/data=!4m5!3m4!1s0x357ca2893c0897f7:0x5eb30c0a06c2d114!8m2!3d37.5644002!4d126.9685025?hl=ko";
                } else if (marker.getPosition().equals(food9)) {
                    strUri = "https://www.google.co.kr/maps/place/Adnan+Kebab+Halal/@37.5588526,126.978386,21z/data=!4m5!3m4!1s0x357ca2f44daf0f8f:0x3eb2f838924863b!8m2!3d37.5588616!4d126.9784831?hl=ko";
                } else if (marker.getPosition().equals(food10)) {
                    strUri = "https://www.google.co.kr/maps/place/%EA%B5%AC%EB%A5%B4%EC%B9%B4+%EC%9D%B8%EB%8F%84%EB%A0%88%EC%8A%A4%ED%86%A0%EB%9E%91+%ED%95%A0%EB%9E%84+%EC%9D%8C%EC%8B%9D/@37.5588635,126.9783797,21z/data=!4m5!3m4!1s0x357ca2f01827888b:0xb41395d36d6dbdf8!8m2!3d37.563034!4d126.9851598?hl=ko";
                } else if (marker.getPosition().equals(food11)) {
                    strUri = "https://www.google.co.kr/maps/place/Kampungku+Restaurant(Restoran+Malaysia)/@37.5589926,126.9838307,17z/data=!3m1!4b1!4m5!3m4!1s0x357ca336b5dfac7b:0x394e02932d97f58a!8m2!3d37.5589926!4d126.9860194?hl=ko";
                } else if (marker.getPosition().equals(food12)) {
                    strUri = "https://www.google.co.kr/maps/place/%ED%95%A0%EB%9E%84%EA%B0%80%EC%9D%B4%EC%A6%88+%EA%B0%95%EB%82%A8%EC%97%AD%EC%A0%90/@37.5182503,126.9922147,14z/data=!4m8!1m2!2m1!1sThe+Halal+Guys+Gang-nam+Store!3m4!1s0x357ca3e30e95893b:0x820b7d1f0d30512d!8m2!3d37.5018241!4d127.0246922?hl=ko";
                } else if (marker.getPosition().equals(food13)) {
                    strUri = "https://www.google.co.kr/maps/place/%EC%96%91%EA%B5%AD/@37.5019451,127.0345819,20.89z/data=!4m5!3m4!1s0x357ca3fe802ed7c9:0x290107784b5fd8d2!8m2!3d37.5020145!4d127.0347186?hl=ko";
                } else if (marker.getPosition().equals(food14)) {
                    strUri = "https://www.google.co.kr/maps/place/%EC%BC%80%EB%A5%B4%EB%B0%98/@37.5001169,127.0361437,21z/data=!4m8!1m2!2m1!1za2VyYmFuIOyXreyCvA!3m4!1s0x0:0xd785c52824d8660e!8m2!3d37.5000505!4d127.0362293?hl=ko";
                } else if (marker.getPosition().equals(food15)) {
                    strUri = "https://www.google.co.kr/maps/place/Halal+Kitchen/@37.58312,126.9798683,17z/data=!3m1!4b1!4m5!3m4!1s0x357ca2c5f3ea0c93:0x96cab38966da4336!8m2!3d37.58312!4d126.982057?hl=ko";
                } else if (marker.getPosition().equals(food16)) {
                    strUri = "https://www.google.co.kr/maps/place/Nirvana+Halal+Indian+Kitchen+(%EB%8B%88%EB%A5%B4%EB%B0%94%EB%82%98+%EC%9D%B8%EB%8F%84%EC%9D%8C%EC%8B%9D%EC%A0%90)/@37.5751114,126.9815579,17z/data=!3m1!4b1!4m5!3m4!1s0x357ca3dcc1939bf7:0x638df04438b127dd!8m2!3d37.5751114!4d126.9837466?hl=ko";
                } else if (marker.getPosition().equals(food17)) {
                    strUri = "https://www.google.co.kr/maps/place/%EB%B0%94%EB%B0%94%EC%9D%B8%EB%94%94%EC%95%84+%EA%B5%BF%EB%AA%A8%EB%8B%9D%EC%8B%9C%ED%8B%B0%EC%A0%90/@37.5669392,127.0073788,21z/data=!4m8!1m2!2m1!1zYmFiYWluZGlhIOq1v-uqqOuLneyLnO2LsOygkA!3m4!1s0x357ca322d40654c1:0xa9f5bc6773c08f03!8m2!3d37.5669483!4d127.00752?hl=ko";
                }


                Uri uri = Uri.parse(strUri);
                intent.setData(uri);
                startActivity(intent);

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }

    public void onLastLocationButtonClicked(View view) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    REQUEST_CODE_PERMISSION);
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions()
                            .position(myLocation)
                            .title("현재 위치"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));
                    mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
                }

            }
        });
    }

    public void onZoom(View view) {
        if (view.getId() == R.id.zoomin) {
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if (view.getId() == R.id.zoomout) {
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION:
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "권한 체크 거부 됨", Toast.LENGTH_SHORT).show();

                }

        }
    }

}