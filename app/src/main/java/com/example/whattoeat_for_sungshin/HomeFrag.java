package com.example.whattoeat_for_sungshin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFrag extends Fragment {
    Button homeBtn1;
    Button homeBtn2;
    String buttonResult;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_frag, container, false);

        final Button homeBtn1 = (Button)rootView.findViewById(R.id.random_btn);
        final Button homeBtn2 = (Button)rootView.findViewById(R.id.worldcup_btn);

        homeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.homeBtnName = "random_btn"; // 사용자가 "랜덤 룰렛 돌리기"를 선택하면 StoreFrag의 title을 "랜덤 룰렛"으로 바꾸기 위한 코드
                MainActivity.onFragmentChanged(1);
            }
        });

        homeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.homeBtnName = "worldcup_btn"; // 사용자가 "음식점 월드컵"를 선택하면 StoreFrag의 title을 "음식점 월드컵"으로 바꾸기 위한 코드
                MainActivity.onFragmentChanged(2);
            }
        });

        return rootView;
    }
}

