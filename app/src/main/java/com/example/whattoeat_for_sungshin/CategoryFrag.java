package com.example.whattoeat_for_sungshin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CategoryFrag extends Fragment{
    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 카테고리별로 전환되는 프래그먼트가 다르다.
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.category_frag, container, false);

        final Button category_kor = (Button)rootView.findViewById(R.id.category_kor);
        final Button category_chi = (Button)rootView.findViewById(R.id.category_chi);
        final Button category_west = (Button)rootView.findViewById(R.id.category_west);
        final Button category_jpn = (Button)rootView.findViewById(R.id.category_jpn);
        final Button category_snack = (Button)rootView.findViewById(R.id.category_snak);
        final Button category_meat = (Button)rootView.findViewById(R.id.category_meat);
        final Button category_etc = (Button)rootView.findViewById(R.id.category_etc);

        category_kor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(3);
            }
        });
        category_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(4);
            }
        });
        category_west.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(5);
            }
        });
        category_jpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(6);
            }
        });
        category_snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(7);
            }
        });
        category_meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(8);
            }
        });
        category_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.onFragmentChanged(9);
            }
        });

        return rootView;
    }
}