package com.stx.xhb.dmgameapp.utils;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 *  Created by jxnk25 on 2017-09-21
 */
public class JsonParser {

    protected JSONObject jsonObject = new JSONObject();

    public JsonParser() {
    }

    public JsonParser(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    protected String getString(String key) {
        if (jsonObject == null) {
            return "";
        }
        String value = jsonObject.optString(key);
        if (value.equalsIgnoreCase("null")) {
            return "";
        }
        return value;
    }

    protected JSONObject getJSONObject(String key) {
        if (jsonObject == null) {
            return new JSONObject();
        }
        JSONObject value = jsonObject.optJSONObject(key);
        if (value == null) {
            return new JSONObject();
        }
        return value;
    }

    protected JSONArray getJSONArray(String key) {
        if (jsonObject == null) {
            return new JSONArray();
        }
        JSONArray value = jsonObject.optJSONArray(key);
        if (value == null) {
            return new JSONArray();
        }
        return value;
    }

    protected int getInt(String key) {
        if (jsonObject == null) {
            return 0;
        }
        return jsonObject.optInt(key);
    }

    protected long getLong(String key) {
        if (jsonObject == null) {
            return 0;
        }
        return jsonObject.optLong(key);
    }

    protected double getDouble(String key) {
        if (jsonObject == null) {
            return 0;
        }
        double value = jsonObject.optDouble(key);
        if (Double.isNaN(value)) {
            return 0;
        }
        return value;
    }

    protected boolean getBoolean(String key) {
        if (jsonObject == null) {
            return false;
        }
        return jsonObject.optBoolean(key);
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
