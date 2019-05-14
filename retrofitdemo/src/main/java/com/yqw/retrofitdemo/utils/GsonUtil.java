package com.yqw.retrofitdemo.utils;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Gson封装
 * Created by yqw on 2019/1/22.
 */
public class GsonUtil {

    public static <T> T jsonToBean(String gsonString, Class<T> cls) {
        T t = null;
        t = new Gson().fromJson(gsonString, cls);
        return t;
    }

    public static <T> List<T> jsonToList(String json, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for (final JsonElement elem : array) {
            list.add(new Gson().fromJson(elem, cls));
        }
        return list;
    }

    public static <T> List<T> jsonToList2(String s, Class<T[]> cls) {
        T[] arr = new Gson().fromJson(s, cls);
        return Arrays.asList(arr);
    }
    /**
     * 转成list中有map的
     * @param gsonString
     * @return
     */
    public static <T> List<Map<String, T>> jsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
//        if (gson != null) {
            list = new Gson().fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
//        }
        return list;
    }

    /**
     * 转成map的
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> jsonToMaps(String gsonString) {
        Map<String, T> map = null;
//        if (gson != null) {
            map = new Gson().fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
//        }
        return map;
    }
}