package com.stx.xhb.dmgameapp.utils;

import android.text.TextUtils;

import com.stx.xhb.dmgameapp.entity.ChapterListEntity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xhb on 2016/1/19
 * json解析工具类
 */
public class JsonUtils {

    public static List<ChapterListEntity> parseChapterJson(String json) {
        List<ChapterListEntity> list = new ArrayList<>();
        try {
            JSONObject object = new JSONObject(removeBOM(json));
            JSONObject data = object.optJSONObject("data");
            for (int index = 0; index < data.length(); index++) {
                //根据键值对来进行json解析
                JSONObject jsonObject = (JSONObject) data.get(index + "");
                String id = jsonObject.getString("id");
                String typeid = jsonObject.getString("typeid");
                String title = jsonObject.getString("title");
                String senddate = jsonObject.getString("senddate");
                String litpic = jsonObject.getString("litpic");
                String arcurl = jsonObject.getString("arcurl");
                String feedback = jsonObject.getString("feedback");
                ChapterListEntity chapterListEntity = new ChapterListEntity(id, typeid, title, senddate, litpic, feedback, arcurl);
                list.add(chapterListEntity);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 异常信息：org.json.JSONException: Value ﻿ of type java.lang.String cannot be converted to JSONObject
     * json串头部出现字符："\ufeff" 解决方法
     * @param data
     * @return
     */
    public static final String removeBOM(String data) {
        if (TextUtils.isEmpty(data)) {
            return data;
        }
        if (data.startsWith("\ufeff")) {
            return data.substring(1);
        } else {
            return data;
        }
    }

}
