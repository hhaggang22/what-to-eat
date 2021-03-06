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

public class PreferFragChi extends Fragment{
    static String attrarray[] = new String[5];
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MenuAdapter.items.clear();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.prefer_frag_chi, container, false);

        final Button prefer1_1_1 = (Button) rootView.findViewById(R.id.chi_prefer1_1_1);    //국물O,국물X
        final Button prefer1_1_2 = (Button) rootView.findViewById(R.id.chi_prefer1_1_2);
        final Button prefer1_2_1 = (Button) rootView.findViewById(R.id.chi_prefer1_2_1);
        final Button prefer1_2_2 = (Button) rootView.findViewById(R.id.chi_prefer1_2_2);
        final Button prefer2_1_1 = (Button) rootView.findViewById(R.id.chi_prefer2_1_1);    //고기O, 고기X
        final Button prefer2_1_2 = (Button) rootView.findViewById(R.id.chi_prefer2_1_2);
        final Button prefer2_2_1 = (Button) rootView.findViewById(R.id.chi_prefer2_2_1);
        final Button prefer2_2_2 = (Button) rootView.findViewById(R.id.chi_prefer2_2_2);
        final Button prefer3_1_1 = (Button) rootView.findViewById(R.id.chi_prefer3_1_1);    //매움, 안매움
        final Button prefer3_1_2 = (Button) rootView.findViewById(R.id.chi_prefer3_1_2);
        final Button prefer3_2_1 = (Button) rootView.findViewById(R.id.chi_prefer3_2_1);
        final Button prefer3_2_2 = (Button) rootView.findViewById(R.id.chi_prefer3_2_2);
        final Button prefer4_1_1 = (Button) rootView.findViewById(R.id.chi_prefer4_1_1);    //찬, 뜨거운
        final Button prefer4_1_2 = (Button) rootView.findViewById(R.id.chi_prefer4_1_2);
        final Button prefer4_2_1 = (Button) rootView.findViewById(R.id.chi_prefer4_2_1);
        final Button prefer4_2_2 = (Button) rootView.findViewById(R.id.chi_prefer4_2_2);
        final Button prefer5_1_1 = (Button) rootView.findViewById(R.id.chi_prefer5_1_1);    //밥, 밀가루
        final Button prefer5_1_2 = (Button) rootView.findViewById(R.id.chi_prefer5_1_2);
        final Button prefer5_2_1 = (Button) rootView.findViewById(R.id.chi_prefer5_2_1);
        final Button prefer5_2_2 = (Button) rootView.findViewById(R.id.chi_prefer5_2_2);
        final Button result_btn = (Button) rootView.findViewById(R.id.chi_result_btn);
        final MainActivity mainActivity = (MainActivity) getActivity();


        //1_1_1이 눌리면 1_1_2인 보라색으로 변하고, 1_2_2는 1_2_1인 하얀색으로 변한다. (선택된 것이 무엇인지 알려주기 위해)
        prefer1_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.GONE);
                prefer1_1_2.setVisibility(View.VISIBLE);
                prefer1_2_1.setVisibility(View.VISIBLE);
                prefer1_2_2.setVisibility(View.GONE);
                attrarray[0] = "국물O";

                if ((prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE); // 마지막 속성을 먼저 선택한 후 누르지 않았던 모든 속성을 눌렀을 때 결과 보이게 하기
            }
        });
        prefer1_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer1_1_1.setVisibility(View.VISIBLE);
                prefer1_1_2.setVisibility(View.GONE);
                prefer1_2_1.setVisibility(View.GONE);
                prefer1_2_2.setVisibility(View.VISIBLE);
                attrarray[0] = "국물X";

                if ((prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer2_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer2_1_1.setVisibility(View.GONE);
                prefer2_1_2.setVisibility(View.VISIBLE);
                prefer2_2_1.setVisibility(View.VISIBLE);
                prefer2_2_2.setVisibility(View.GONE);
                attrarray[1] = "고기O";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer2_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer2_1_1.setVisibility(View.VISIBLE);
                prefer2_1_2.setVisibility(View.GONE);
                prefer2_2_1.setVisibility(View.GONE);
                prefer2_2_2.setVisibility(View.VISIBLE);
                attrarray[1] = "고기X";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer3_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer3_1_1.setVisibility(View.GONE);
                prefer3_1_2.setVisibility(View.VISIBLE);
                prefer3_2_1.setVisibility(View.VISIBLE);
                prefer3_2_2.setVisibility(View.GONE);
                attrarray[2] = "매운";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer3_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer3_1_1.setVisibility(View.VISIBLE);
                prefer3_1_2.setVisibility(View.GONE);
                prefer3_2_1.setVisibility(View.GONE);
                prefer3_2_2.setVisibility(View.VISIBLE);
                attrarray[2] = "안매운";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer4_1_2.getVisibility() == View.VISIBLE || prefer4_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer4_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer4_1_1.setVisibility(View.GONE);
                prefer4_1_2.setVisibility(View.VISIBLE);
                prefer4_2_1.setVisibility(View.VISIBLE);
                prefer4_2_2.setVisibility(View.GONE);
                attrarray[3] = "찬";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer4_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer4_1_1.setVisibility(View.VISIBLE);
                prefer4_1_2.setVisibility(View.GONE);
                prefer4_2_1.setVisibility(View.GONE);
                prefer4_2_2.setVisibility(View.VISIBLE);
                attrarray[3] = "뜨거운";

                if ((prefer1_1_2.getVisibility() == View.VISIBLE || prefer1_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer2_1_2.getVisibility() == View.VISIBLE || prefer2_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer3_1_2.getVisibility() == View.VISIBLE || prefer3_2_2.getVisibility() == View.VISIBLE) &&
                        (prefer5_1_2.getVisibility() == View.VISIBLE || prefer5_2_2.getVisibility() == View.VISIBLE))
                    result_btn.setVisibility(View.VISIBLE);
            }
        });
        prefer5_1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer5_1_1.setVisibility(View.GONE);
                prefer5_1_2.setVisibility(View.VISIBLE);
                prefer5_2_1.setVisibility(View.VISIBLE);
                prefer5_2_2.setVisibility(View.GONE);
                attrarray[4] = "밥";

                if ((prefer1_1_1.getVisibility() == View.VISIBLE && prefer1_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer2_1_1.getVisibility() == View.VISIBLE && prefer2_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer3_1_1.getVisibility() == View.VISIBLE && prefer3_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer4_1_1.getVisibility() == View.VISIBLE && prefer4_2_1.getVisibility() == View.VISIBLE)) { // 선택하지 않은 속성이 있는데 마지막 속성을 선택한 경우
                    Toast.makeText(getActivity(), "선택하지 않은 항목이 있습니다.\n    모든 항목을 선택해 주세요! ", Toast.LENGTH_SHORT).show();
                } else { // 모든 속성을 선택한 경우
                    result_btn.setVisibility(View.VISIBLE);
                }
            }
        });
        prefer5_2_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefer5_1_1.setVisibility(View.VISIBLE);
                prefer5_1_2.setVisibility(View.GONE);
                prefer5_2_1.setVisibility(View.GONE);
                prefer5_2_2.setVisibility(View.VISIBLE);
                attrarray[4] = "밀가루";

                if ((prefer1_1_1.getVisibility() == View.VISIBLE && prefer1_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer2_1_1.getVisibility() == View.VISIBLE && prefer2_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer3_1_1.getVisibility() == View.VISIBLE && prefer3_2_1.getVisibility() == View.VISIBLE) ||
                        (prefer4_1_1.getVisibility() == View.VISIBLE && prefer4_2_1.getVisibility() == View.VISIBLE)) {
                    Toast.makeText(getActivity(), "선택하지 않은 항목이 있습니다.\n    모든 항목을 선택해 주세요! ", Toast.LENGTH_SHORT).show();
                } else {
                    result_btn.setVisibility(View.VISIBLE);
                }
            }
        });

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.storename = "store_chi";
                MainActivity.attrcount = 5;
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
