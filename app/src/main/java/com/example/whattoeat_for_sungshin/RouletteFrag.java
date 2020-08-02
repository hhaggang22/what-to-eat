package com.example.whattoeat_for_sungshin;

import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RouletteFrag extends Fragment {
    private ImageView wheel;
    TextView roulette_storeName;
    TextView roulette_text;
    LinearLayout roulette_result;
    Button roulette_result_btn;

    private float init_angle = 0.0f;
    int sum = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.roulette_frag, container, false);

        wheel = (ImageView) rootView.findViewById(R.id.wheel);
        roulette_storeName = (TextView) rootView.findViewById(R.id.roulette_storeName);
        roulette_text = (TextView) rootView.findViewById(R.id.roulette_text);
        roulette_result = (LinearLayout) rootView.findViewById(R.id.roulette_result);
        roulette_result_btn = (Button) rootView.findViewById(R.id.roulette_result_btn);
        final  MainActivity mainActivity = (MainActivity) getActivity();

        //룰렛 돌아갈 때의 효과음 넣기
        final SoundPool sound_pool;
        sound_pool = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        final int SoundID = sound_pool.load(getActivity(), R.raw.prize_wheel_sound, 1);



        Button btn = (Button) rootView.findViewById(R.id.rotate_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                onWheelImage();
                sound_pool.play(SoundID, 1f, 1f, 0, 0, 1f); // 효과음 재생

                roulette_text.setVisibility(view.GONE);
                roulette_result.setVisibility(view.GONE);

                roulette_result.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        roulette_result.setVisibility(view.VISIBLE);
                    }
                }, 3000);
            }
        });


        //
        roulette_result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String storeName = roulette_storeName.getText().toString();
                if(storeName.equals("'한식 집'"))
                    MainActivity.storename="store_kor";
                else if(storeName.equals("'중식 집'"))
                    MainActivity.storename="store_chi";
                else if(storeName.equals("'양식 집'"))
                    MainActivity.storename="store_west";
                else if(storeName.equals("'일식 집'"))
                    MainActivity.storename="store_jpn";
                else if(storeName.equals("'분식 집'"))
                    MainActivity.storename="store_snack";
                else if(storeName.equals("'고기 집'"))
                    MainActivity.storename="store_meat";
                else if(storeName.equals("'기타'"))
                    MainActivity.storename="store_etc";
                mainActivity.showdatalist(MainActivity.storename);
                MainActivity.onFragmentChanged(10);
            }
        });
        return rootView;
    }

    private int getRandom(int maxNumber) {
        return (int) (Math.random() * maxNumber);
    }

    private void onWheelImage() { // 룰렛 회전
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int r = getRandom(360); // 0~360의 수 중에 랜덤
                float fromAngle = r + 720 + init_angle; // 회전수 제어(랜덤(0~360)+720도+회전각)
                RotateAnimation rAnim = new RotateAnimation(init_angle, fromAngle, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

                init_angle = fromAngle; // 초기 시작 각도 업데이트
                rAnim.setDuration(3000);
                rAnim.setFillEnabled(true);
                rAnim.setFillAfter(true);
                wheel.startAnimation(rAnim);

                // 룰렛이 돌아간 각도에 따라 선택된 카테고리 읽어오기
                sum += r;
                if ((sum % 360) >= 308.4) {
                    roulette_storeName.setText("'중식 집'");
                } else if ((sum % 360) >= 257 && (sum % 360) < 308.4) {
                    roulette_storeName.setText("'한식 집'");
                } else if ((sum % 360) >= 205.6 && (sum % 360) < 257) {
                    roulette_storeName.setText("'일식 집'");
                } else if ((sum % 360) >= 154.2 && (sum % 360) < 205.6) {
                    roulette_storeName.setText("'분식 집'");
                } else if ((sum % 360) >= 102.8 && (sum % 360) < 154.2) {
                    roulette_storeName.setText("'고기 집'");
                } else if ((sum % 360) >= 51.4 && (sum % 360) < 102.8) {
                    roulette_storeName.setText("'기타'");
                } else {
                    roulette_storeName.setText("'양식 집'");
                }

            }
        });
    }

}