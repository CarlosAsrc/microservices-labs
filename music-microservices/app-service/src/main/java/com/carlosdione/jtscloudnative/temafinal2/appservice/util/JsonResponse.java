package com.carlosdione.jtscloudnative.temafinal2.appservice.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonResponse {

    private JsonObject responseJson;
    private JsonParser jsonParser;

    public JsonResponse() {
        responseJson = new JsonObject();
        jsonParser = new JsonParser();
    }

    public void add(String key, String value) {
        JsonElement element = jsonParser.parse(value);
        responseJson.add(key, element);
    }

    public JsonObject getResponseJson() {
        return this.responseJson;
    }
}