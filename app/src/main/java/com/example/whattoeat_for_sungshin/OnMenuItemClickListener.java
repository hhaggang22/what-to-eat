package com.example.whattoeat_for_sungshin;

import android.view.View;

//recyclerView의 아이템을 클릭했을 때 이벤트가 발생하게 하기 위한 인터페이스
public interface OnMenuItemClickListener {
    public void onItemClick(MenuAdapter.ViewHolder holder, View view, int position);
}
