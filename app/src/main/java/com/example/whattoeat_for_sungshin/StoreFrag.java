package com.example.whattoeat_for_sungshin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class StoreFrag extends Fragment{
    TextView clockText;
    TextView title, store_frag_text;
    private String tablename;
    static final MenuAdapter adapter = new MenuAdapter();
    public static String selected_store;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MenuAdapter.items.clear();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.store_frag, container, false);
        MainActivity mainActivity = (MainActivity) getActivity();
        clockText = rootView.findViewById(R.id.clock_text);
        title = rootView.findViewById(R.id.textView);
        store_frag_text = rootView.findViewById(R.id.store_frag_text);
        final ImageView storeImg = (ImageView)rootView.findViewById(R.id.store_img);

        // 카테고리에 따라 이미지 바꾸기
        switch (MainActivity.storename){
            case "store_kor":
                storeImg.setImageResource(R.drawable.recommendation_kor);
                break;
            case "store_chi":
                storeImg.setImageResource(R.drawable.recommendation_chi);
                break;
            case "store_west":
                storeImg.setImageResource(R.drawable.recommendation_west);
                break;
            case "store_jpn":
                storeImg.setImageResource(R.drawable.recommendation_jpn);
                break;
            case "store_snack":
                storeImg.setImageResource(R.drawable.recommendation_snack);
                break;
            case "store_meat":
                storeImg.setImageResource(R.drawable.recommendation_meat);
                break;
            case "store_etc":
                storeImg.setImageResource(R.drawable.recommendation_etc);
                break;
        }
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat mFormat = new SimpleDateFormat("HH");
        int hour = Integer.parseInt(mFormat.format(date));

        // 시간에 따라 멘트 다르게 설정
        if(6 <= hour&& hour <=10)
            clockText.setText("아침 먹기 딱 좋은 시간입니다!");
        else if(11 <= hour && hour <= 16)
            clockText.setText("점심 먹기 딱 좋은 시간입니다!");
        else if(17 <= hour && hour <= 20)
            clockText.setText("저녁 먹기 딱 좋은 시간입니다!");
        else
            clockText.setText("야식 먹기 딱 좋은 시간입니다!");


        RecyclerView recyclerView = rootView.findViewById(R.id.store_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getActivity()).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        tablename = MainActivity.storename; //어떤 store에서 넘어왔는지 알아옴.

        adapter.setOnItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onItemClick(MenuAdapter.ViewHolder holder, View view, int position) {
                Menu item = adapter.getItem(position);
                selected_store = item.getName();

                MainActivity.onFragmentChanged(11);
            }
        });

        if(MainActivity.homeBtnName.equals("random_btn")) {
            title.setText("랜덤 룰렛");
            store_frag_text.setText("랜덤으로 선택된\n음식점을 추천드려요!");
            mainActivity.showdatalist(tablename);
        }
        else {
            title.setText("음식점 월드컵");
            store_frag_text.setText("당신의 취향과 일치하는\n음식점을 추천드려요!");
            mainActivity.selectData2(tablename);
        }

        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void addadapter(String result, String tag){
        adapter.addItem(new Menu(result, tag));
    }
}
