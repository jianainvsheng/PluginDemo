package com.example.detailmodel.mvp;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.share.mvp.BaseNormalView;

public interface DetailView extends BaseNormalView {

    /**
     * 请求数据成功
     * @param data
     */
     void onDetailSuccess(DetailResponse data);

    /**
     * 请求数据失败
     * @param errorMsg
     */
     void onDetailFail(String errorMsg);
}
