package com.stx.xhb.dmgameapp.utils;

import com.stx.xhb.dmgameapp.config.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Mr.xiao
 */
public class JsonResponse extends JsonParser {

    private int code=0;
    private String msg="";
    private JSONArray rankkeyList = new JSONArray();
    private int totalrow = 0;
    private JSONObject object = new JSONObject();

    public JsonResponse(JSONObject jsonObject) {
        super(jsonObject);
        msg = getString("msg");
        code = getInt("code");
        rankkeyList = getJSONArray("rankkey");
        totalrow = getInt("totalrow");
        object = getJSONObject("html");
    }

    public JSONArray getDataList() {
        return rankkeyList;
    }

    public JSONObject getObject() {
        return object;
    }

    public int getTotalCount() {
        return totalrow;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return code== Constants.SERVER_SUCCESS;
    }

}
