package com.example.detailmodel.mvp;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.detailmodel.thread.BaseThread;
import com.example.detailmodel.thread.DetailLoadThread;
import com.example.share.base.data.BaseResponse;
import com.example.share.mvp.BaseNormalPresenter;

public class DetailPresenter extends BaseNormalPresenter<DetailView> {

    private DetailLoadThread mLoad = null;

    public void requestDetailData(){
        if(getView() == null){
            return;
        }
        getView().showLoadingDialog();
        mLoad = new DetailLoadThread(new BaseThread.CallBack<BaseResponse<DetailResponse>>() {
            @Override
            public void call(BaseResponse<DetailResponse> data) {
                if(getView() != null){
                    getView().hideLoadingDialog();
                    if(data != null && data.getData() != null && data.getCode() == BaseResponse.CODE_SUCCESS){
                        getView().onDetailSuccess(data.getData());
                    }else{
                        getView().onDetailFail(data.getMsg());
                    }
                }
            }
        });
        mLoad.loadNames();
    }
    public void destroy(){
        if(mLoad != null){
            mLoad.recycle();
            mLoad = null;
        }
    }
}
