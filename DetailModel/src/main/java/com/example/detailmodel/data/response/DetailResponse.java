package com.example.detailmodel.data.response;

import java.util.List;

public
class DetailResponse {

    public List<NameInfo> listNames;

    public static class NameInfo{

        public String name;

        public int position;
    }
}
