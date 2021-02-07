package com.example.detailmodel;
import android.os.Bundle;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.detailmodel.mvp.DetailPresenter;
import com.example.detailmodel.mvp.DetailView;
import com.example.detailmodel.panel.ContentPanel;
import com.example.detailmodel.panel.TopPanel;
import com.example.share.base.BaseSubscriberActivity;
import com.example.share.utils.Paths;
import com.example.tv.panel.annotation.IPanel;
import com.example.tv.panel.annotation.internal.IPanelInfo;
import com.example.tv.panel.eventmanger.internal.event.BaseEvent;
import com.source.crouter.annotation.IActivity;
@IActivity(Paths.DETAIL_PATH)
@IPanel({
        @IPanelInfo(TopPanel.class),
        @IPanelInfo(ContentPanel.class)
})
public class DetailActivity extends BaseSubscriberActivity<DetailView, DetailPresenter> implements DetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int onCreateLayoutResId() {
        return R.layout.activity_detail_layout;
    }

    @Override
    public void onDetailSuccess(DetailResponse data) {
        BaseEvent.builder(this)
                .setData(data)
                .sendEvent(this,ContentPanel.class);
    }

    @Override
    public void onDetailFail(String errorMsg) {

    }

    @Override
    public void onMessageEvent(BaseEvent baseEvent) {
        if(baseEvent.getFromClass() == TopPanel.class){
            if(baseEvent.getEventType() == TopPanel.LOAD_NAMES){
                getPresenter().requestDetailData();
            }
        }
    }

    @Override
    protected void onDestroy() {
        if(getPresenter() != null){
            getPresenter().destroy();
        }
        super.onDestroy();
    }
}
