package com.example.detailmodel.panel;
import android.view.View;
import android.widget.TextView;
import com.example.detailmodel.R;
import com.example.tv.panel.eventmanger.internal.event.BaseEvent;
import com.example.tv.panel.panelmanger.panel.BaseSubscriberPanel;

public class TopPanel extends BaseSubscriberPanel<Object> implements View.OnClickListener{

    public static final int LOAD_NAMES = 1;

    private TextView mTextView1;

    private TextView mTextView2;

    @Override
    public void bindView(View view) {
        mTextView1 = view.findViewById(R.id.activity_detail_top_one);
        mTextView2 = view.findViewById(R.id.activity_detail_top_two);
        mTextView1.setOnClickListener(this);
        mTextView2.setOnClickListener(this);
        mTextView1.requestFocus();
    }

    @Override
    public int getContextViewId() {
        return R.id.activity_detail_top_layout;
    }

    @Override
    public void builder(BaseEvent baseEvent, Object response) {

    }

    @Override
    public void onClick(View v) {
        if(v == mTextView1){
            BaseEvent.builder(getContext())
                    .setEventType(LOAD_NAMES)
                    .setFromClass(this.getClass())
                    .sendEvent(getContext(),getTargetClass());
        }else if(v == mTextView2){

        }
    }

}
