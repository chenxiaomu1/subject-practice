package com.chenhan.mainFunc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chenhan.enums.HeaderEnum;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import sun.applet.Main;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MainFuncTest {

    public static void main(String[] args) {

        MainFuncTest.loadSpringFactories();

        //Map转json字符串
        Map<String,String> map = new HashMap<>();
        map.put("agent_project_no","X9807");
        map.put("name","chenhan");
        map.put("now","2020");
        String jsonString = JSONObject.toJSONString(map);
        System.out.println(jsonString);

        //json字符串转map
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        Map<String, Object> innerMap = jsonObject.getInnerMap();
        System.out.println(innerMap);

        //json字符串转map
        Map jsonMap = JSON.parseObject(jsonString, Map.class);
        System.out.println(jsonMap);
    }

    private static final Map<String, MultiValueMap<String, String>> cache = new ConcurrentReferenceHashMap<>();

    private static Map<String, List<String>> loadSpringFactories() {
        MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
        List<String> list = new ArrayList<>();
        list.add("chenhan");
        map.put("name",list);
        cache.put("how",map);
        MultiValueMap<String, String> result = cache.get("how");
        return result;
    }
}
