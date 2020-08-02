package com.example.whattoeat_for_sungshin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.StringTokenizer;


public class StoreDetailFrag extends Fragment implements OnMapReadyCallback {
    TextView storeName, storeTag, storeMenuName1, storeMenuName2, storeAdress;
    ImageView storeImg;
    String storeDetailName;
    double v, v1;
    public static String resultFromDB;
    LinearLayout menu2Layout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.store_detail_frag, container, false);

        storeName = rootView.findViewById(R.id.storeName);
        storeTag = rootView.findViewById(R.id.storeTag);
        storeMenuName1 = rootView.findViewById(R.id.storeMenuName1);
        storeMenuName2 = rootView.findViewById(R.id.storeMenuName2);
        storeAdress = rootView.findViewById(R.id.storeAdr);
        storeImg = rootView.findViewById(R.id.store_detail_img);
        menu2Layout = (LinearLayout)rootView.findViewById(R.id.menu2_layout);

        MainActivity mainActivity = (MainActivity) getActivity();
        /* DB에 필요한 변수 선언 */
        String selected_store_name = StoreFrag.selected_store;
        String selected_table_name = MainActivity.storename; //어떤 카테고리에서 넘어왔는지 알아옴.

        mainActivity.getStoreDetail(selected_table_name, selected_store_name);
        resultFromDB = mainActivity.putData();
        StringTokenizer str = new StringTokenizer(resultFromDB, ",");

        if(MainActivity.notMenuCnt != 0) {
            //메뉴가 1개
            menu2Layout.setVisibility(View.GONE);
            String st_id = str.nextToken();
            String packName = getActivity().getPackageName();
            int lid = getResources().getIdentifier(st_id, "string", packName);
            int lid2 = getResources().getIdentifier(st_id, "drawable", packName);
            String hashtag = getResources().getString(lid);
            storeImg.setImageResource(lid2);
            storeDetailName = str.nextToken();
            storeName.setText(storeDetailName);
            storeTag.setText(hashtag);
            storeMenuName1.setText(str.nextToken());
            storeAdress.setText(str.nextToken());
        }
        else{
            //메뉴가 2개
            String st_id = str.nextToken();
            String packName = getActivity().getPackageName();
            int lid = getResources().getIdentifier(st_id, "string", packName);
            int lid2 = getResources().getIdentifier(st_id, "drawable", packName);
            String hashtag = getResources().getString(lid);
            storeImg.setImageResource(lid2);
            storeDetailName = str.nextToken();
            storeName.setText(storeDetailName);
            storeTag.setText(hashtag);
            storeMenuName1.setText(str.nextToken());
            storeMenuName2.setText(str.nextToken());
            storeAdress.setText(str.nextToken());
        }

        MapView mapview = (MapView) rootView.findViewById(R.id.map);
        mapview.onCreate(savedInstanceState);
        mapview.onResume();
        mapview.getMapAsync(this);

        // 구글맵 위치 넣기
        String x = str.nextToken();
        v = Double.parseDouble(x);
        String y = str.nextToken();
        v1 = Double.parseDouble(y);

        return rootView;
    }

    //구글맵 보이기
    @Override
    public void onMapReady(GoogleMap googleMap) {

        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        googleMap.animateCamera(zoom);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(v, v1), 18));

        MarkerOptions marker = new MarkerOptions();
        marker.position(new LatLng(v, v1)).title(storeDetailName);

        googleMap.addMarker(marker).showInfoWindow();
    }
}