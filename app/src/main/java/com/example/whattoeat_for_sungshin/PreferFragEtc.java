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

public class PreferFragEtc extends Fragment {
    static String attrarray[] = new String[1];
    //frag별로 추가해야하는 변수
    static int cursorcount=0;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MenuAdapter.items.clear();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.prefer_frag_etc, container, false);

        final Button prefer1_1_1 = (Button) rootView.findViewById(R.id.etc_prefer1_1_1);    //식사, 간편식, 카페
        final Button prefer1_1_2 = (Button) rootView.findViewById(R.id.etc_prefer1_1_2);
        final Button prefer1_2_1 = (Button) rootView.findViewById(R.id.etc_prefer1_2_1);
        final Button prefer1_2_2 = (Button) rootView.findViewById(R.id.etc_prefer1_2_2);
        final Button prefer1_3_1 = (Button) rootView.findViewById(R.id.etc_prefer1_3_1);
        final Button prefer1_3_2 = (Button) rootView.findViewById(R.id.etc_prefer1_3_2);
        final Button result_btn = (Button) rootView.findViewById(R.id.etc_result_btn);
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
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "식사";
                MainActivity.storename = "store_etc";
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
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "간편식";
                MainActivity.storename = "store_etc";
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
                result_btn.setVisibility(View.VISIBLE);
                attrarray[0] = "카페";
                MainActivity.storename = "store_cafe";
            }
        });

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.cursorCnt = 0;
                MainActivity.attrcount = 1;
                MainActivity.attrarray = attrarray;
                mainActivity.selectData2(MainActivity.storename);
                // 조건에 맞는 음식점이 0개일 때 Toast 띄우기
                if(MainActivity.getCursorCnt()==0)
                    Toast.makeText(getActivity().getApplicationContext(), "조건에 맞는 음식점을 찾지 못했습니다:(", Toast.LENGTH_SHORT).show();
                else
                    MainActivity.onFragmentChanged(10);

            }
        });

        return rootView;
    }

}
