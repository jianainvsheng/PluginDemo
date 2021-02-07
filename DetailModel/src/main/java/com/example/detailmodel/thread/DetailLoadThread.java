package com.example.detailmodel.thread;
import com.example.detailmodel.BuildConfig;
import com.example.detailmodel.data.response.DetailResponse;
import com.example.share.base.data.BaseResponse;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class DetailLoadThread extends BaseThread<BaseResponse<DetailResponse>, DetailResponse> {

    public DetailLoadThread(CallBack<BaseResponse<DetailResponse>> callBack) {
        super(callBack);
    }

    public void loadNames() {
        start();
    }

    @Override
    public void execute(BaseResponse<DetailResponse> response) {
        //处理逻辑
        try {
            loadData(response);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadData(BaseResponse<DetailResponse> response) {
        DetailResponse detailResponse = new DetailResponse();
        detailResponse.listNames = new ArrayList();

        try {
            JSONArray jsonArray = new JSONArray(BuildConfig.testNames);
            DetailResponse.NameInfo info;
            for (int i = 0; i < jsonArray.length(); i++) {
                info = new DetailResponse.NameInfo();
                info.name = jsonArray.getString(i);
                info.position = i;
                detailResponse.listNames.add(info);
            }
            response.setData(detailResponse);
            response.setCode(BaseResponse.CODE_SUCCESS);
            response.setMsg("加载完成");
        } catch (JSONException e) {
            e.printStackTrace();
            response.setCode(BaseResponse.CODE_FAIL);
            response.setMsg("读取文件失败");
        }

    }
}
