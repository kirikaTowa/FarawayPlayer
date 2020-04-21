package com.ywjh.farawayplayer.utils;

import android.util.Log;

public class YWJHURLProviderUtils {
    /**
     * 获取首页的url
     *
     * @param type 数据偏移量
     *
     * @return url
     */
    public static String getHomeUrl(int type) {
        String url = "https://api.apiopen.top/musicRankingsDetails?type="+ type;
        Log.i("Main_url", url);
        return url;
    }

    public static String getYueDanUrl(int limit,int offset) {//从27开始
        //http://music.163.com/api/search/get/?s=尚闻雨诉&limit=3&type=1000&offset=0
        String url = "http://music.163.com/api/search/get/?s=尚闻雨诉&type=1000&offset="+offset+"&limit="+limit;

        return url;
    }
    public static String getMVareaUrl() {
        //http://music.163.com/api/search/get/?s=尚闻雨诉&limit=3&type=1000&offset=0
        String url = "http://mobileservice.kugou.com/api/v5/video/recommend_channel?version=9108&plat=0&type=2";
        return url;
    }
    public static String getMVlistUrl(int codeint,int pagesize,int page) {
        //http://mobilecdnbj.kugou.com/api/v5/video/list?   pagesize=20&page=1&sort=1
        String url = "http://mobilecdnbj.kugou.com/api/v5/video/list?&page="+page+"&pagesize="+pagesize+"&sort="+codeint;
        return url;
    }

}
