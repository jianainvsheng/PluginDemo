package com.example.detailmodel.adapter.holder;

import android.graphics.Typeface;
import android.view.View;

import com.example.detailmodel.R;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.detailmodel.widget.DetailGradientTextView;
import com.example.tv.widget.recyclerview.BaseRecyclerHolder;

public
class DetailContentItemHolder extends BaseRecyclerHolder<DetailResponse.NameInfo> implements View.OnFocusChangeListener{

    private View mContentLayout;

    private DetailGradientTextView mPositionView;

    private DetailGradientTextView mNameView;

    private View mBackground;

    public DetailContentItemHolder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
        mContentLayout = itemView.findViewById(R.id.activity_home_content_name_layout);
        mPositionView = itemView.findViewById(R.id.activity_home_content_name_position);
        mNameView = itemView.findViewById(R.id.activity_home_content_name_text);
        mBackground = itemView.findViewById(R.id.activity_home_content_background);
        mContentLayout.setOnFocusChangeListener(this);
    }

    @Override
    public void onBuilder(DetailResponse.NameInfo data) {
        mPositionView.setText(data.position + "");
        mNameView.setText(data.name);
        setTextUnFocusColor((data.position -1) % 4);
        setTextTypeface();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(!hasFocus){
            int position = (getData().position - 1) % 4;
            setTextUnFocusColor(position);
            mBackground.setBackgroundResource(R.drawable.player_bg_actor_tag_unselect);
        }else{
            mPositionView.setTextColor(itemView.getContext().getResources().getColor(R.color.color_FFFFFF));
            mNameView.setTextColor(itemView.getContext().getResources().getColor(R.color.color_FFFFFF));
            mBackground.setBackgroundResource(R.drawable.player_bg_actor_tag_select);
        }
    }
    private void setTextTypeface(){
        Typeface typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Uni-Sans-Heavy-Italic-5.ttf");
        mPositionView.setTypeface(typeface);
        typeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/Aurora_BdCn_BT_Bold.ttf");
        mNameView.setTypeface(typeface);
    }

    private void setTextUnFocusColor(int position){
        if(position == 0){
            mPositionView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_start_color),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color));
            mNameView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_start_color),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color));
        }else if(position == 1){

            mPositionView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_two),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_two));
            mNameView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_two),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_two));
        }else if(position == 2){

            mPositionView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_three),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_three));
            mNameView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_three),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_three));
        }else {
            mPositionView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_other),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_other));
            mNameView.setTextColor(getContext().getResources().getColor(R.color.detail_activity_rank_end_color_other),
                    getContext().getResources().getColor(R.color.detail_activity_rank_end_color_other));
        }
    }
}
