package com.example.detailmodel.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.detailmodel.R;
import com.example.detailmodel.adapter.holder.DetailContentItemHolder;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.tv.widget.recyclerview.BaseRecyclerAdapter;

public
class DetailContentAdapter extends BaseRecyclerAdapter<DetailResponse.NameInfo, DetailContentItemHolder> {

    @Override
    protected View onCreateView(ViewGroup viewGroup) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.activity_home_content_item_layout,viewGroup,false);
        return view;
    }

    @Override
    public void onBindViewHolder(final DetailContentItemHolder homeContentItemHolder, int i) {
        View layout = homeContentItemHolder.itemView.findViewById(R.id.activity_home_content_name_layout);
        if(i % 7 == 6 || i == getDataList().size() - 1){
            layout.setNextFocusRightId(layout.getId());
        }else{
            layout.setNextFocusRightId(-1);
        }
        super.onBindViewHolder(homeContentItemHolder, i);
    }
}
