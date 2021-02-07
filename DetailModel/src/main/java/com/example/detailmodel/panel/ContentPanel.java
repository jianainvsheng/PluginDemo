package com.example.detailmodel.panel;

import android.view.View;
import android.view.ViewGroup;
import com.example.detailmodel.R;
import com.example.detailmodel.adapter.DetailContentAdapter;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.detailmodel.processor.RecyclerViewProcessor;
import com.example.tv.panel.eventmanger.internal.event.BaseEvent;
import com.example.tv.panel.panelmanger.panel.BaseSubscriberPanel;

public class ContentPanel extends BaseSubscriberPanel<DetailResponse> {

    private ViewGroup mParentView;

    private RecyclerViewProcessor mRecyclerViewProcessor;

    private DetailContentAdapter mAdapter;

    @Override
    public void bindView(View view) {
        mParentView = (ViewGroup) view;
        mRecyclerViewProcessor = new RecyclerViewProcessor(getContext(),mParentView);
        mRecyclerViewProcessor.onCreateRecycler();
        mAdapter = new DetailContentAdapter();
        mRecyclerViewProcessor.setAdapter(mAdapter);
    }

    @Override
    public int getContextViewId() {
        return R.id.activity_detail_content_layout;
    }

    @Override
    public void builder(BaseEvent baseEvent, DetailResponse detailResponse) {
        mAdapter.setDataList(detailResponse.listNames);
    }
}
