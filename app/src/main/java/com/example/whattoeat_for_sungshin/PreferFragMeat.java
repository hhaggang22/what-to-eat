package com.example.whattoeat_for_sungshin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PreferFragMeat extends Fragment{
    static String attrarray[] = new String[1];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MenuAdapter.items.clear();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.prefer_frag_meat, container, false);

        final Button prefer1_1_1 = (Button) rootView.findViewById(R.id.meat_prefer1_1_1);   //돼지, 닭, 소, 양, 오리
        final Button prefer1_1_2 = (Button) rootView.findViewById(R.id.meat_prefer1_1_2);
        final Button prefer1_2_1 = (Button) rootView.findViewById(R.id.meat_prefer1_2_1);
        final Button prefer1_2_2 = (Button) rootView.findViewById(R.id.meat_prefer1_2_2);
        final Button prefer1_3_1 = (Button) rootView.findViewById(R.id.meat_prefer1_3_1);
        final Button prefer1_3_2 = (Button) rootView.findViewById(R.id.meat_prefer1_3_2);
        final Button prefer1_4_1 = (Button) rootView.findViewById(R.id.meat_prefer1_4_1);
        final Button prefer1_4_2 = (Button) rootView.findViewById(R.id.meat_prefer1_4_2);
        final Button prefer1_5_1 = (Button) rootView.findViewById(R.id.meat_prefer1_5_1);
        final Button prefer1_5_2 = (Button) rootView.findViewById(R.id.meat_prefer1_5_2);
        final Button result_btn = (Button) rootView.findViewById(R.id.meat_result_btn);
        final MainActivity mainActivity = (MainActivity) getActivity();

        //1_1_1이 눌리면 1_1_2인 보라색으로 변하고, 1_2_2는 1_2_1인 하얀색으로 변한다. (선택된 것이 무엇인지 알려주기 위해)
        prefer1_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.GONE);
                prefer1_1_2.setVisibility(View.VISIBLE);
                prefer1_2_1.setVisibility(View.VISIBLE);
                prefer1_2_2.setVisibility(View.GONE);
                prefer1_3_1.setVisibility(View.VISIBLE);
                prefer1_3_2.setVisibility(View.GONE);
                prefer1_4_1.setVisibility(View.VISIBLE);
                prefer1_4_2.setVisibility(View.GONE);
                prefer1_5_1.setVisibility(View.VISIBLE);
                prefer1_5_2.setVisibility(View.GONE);
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "돼지";
            }
        });
        prefer1_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.VISIBLE);
                prefer1_1_2.setVisibility(View.GONE);
                prefer1_2_1.setVisibility(View.GONE);
                prefer1_2_2.setVisibility(View.VISIBLE);
                prefer1_3_1.setVisibility(View.VISIBLE);
                prefer1_3_2.setVisibility(View.GONE);
                prefer1_4_1.setVisibility(View.VISIBLE);
                prefer1_4_2.setVisibility(View.GONE);
                prefer1_5_1.setVisibility(View.VISIBLE);
                prefer1_5_2.setVisibility(View.GONE);
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "닭";
            }
        });
        prefer1_3_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.VISIBLE);
                prefer1_1_2.setVisibility(View.GONE);
                prefer1_2_1.setVisibility(View.VISIBLE);
                prefer1_2_2.setVisibility(View.GONE);
                prefer1_3_1.setVisibility(View.GONE);
                prefer1_3_2.setVisibility(View.VISIBLE);
                prefer1_4_1.setVisibility(View.VISIBLE);
                prefer1_4_2.setVisibility(View.GONE);
                prefer1_5_1.setVisibility(View.VISIBLE);
                prefer1_5_2.setVisibility(View.GONE);
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "소";
            }
        });
        prefer1_4_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.VISIBLE);
                prefer1_1_2.setVisibility(View.GONE);
                prefer1_2_1.setVisibility(View.VISIBLE);
                prefer1_2_2.setVisibility(View.GONE);
                prefer1_3_1.setVisibility(View.VISIBLE);
                prefer1_3_2.setVisibility(View.GONE);
                prefer1_4_1.setVisibility(View.GONE);
                prefer1_4_2.setVisibility(View.VISIBLE);
                prefer1_5_1.setVisibility(View.VISIBLE);
                prefer1_5_2.setVisibility(View.GONE);
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "양";
            }
        });
        prefer1_5_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.VISIBLE);
                prefer1_1_2.setVisibility(View.GONE);
                prefer1_2_1.setVisibility(View.VISIBLE);
                prefer1_2_2.setVisibility(View.GONE);
                prefer1_3_1.setVisibility(View.VISIBLE);
                prefer1_3_2.setVisibility(View.GONE);
                prefer1_4_1.setVisibility(View.VISIBLE);
                prefer1_4_2.setVisibility(View.GONE);
                prefer1_5_1.setVisibility(View.GONE);
                prefer1_5_2.setVisibility(View.VISIBLE);
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "오리";
            }
        });

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.storename = "store_meat";
                MainActivity.attrcount = 1;
                MainActivity.attrarray = attrarray;
                mainActivity.selectData2(MainActivity.storename);
                int cursorCnt = MainActivity.getCursorCnt();
                // 조건에 맞는 음식점이 0개일 때 Toast 띄우기
                if(cursorCnt==0)
                    Toast.makeText(getActivity().getApplicationContext(), "조건에 맞는 음식점을 찾지 못했습니다:(", Toast.LENGTH_SHORT).show();
                else
                    MainActivity.onFragmentChanged(10);
            }
        });

        return rootView;
    }
}
