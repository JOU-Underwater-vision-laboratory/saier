package com.hhit.system.controller;

import com.hhit.common.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/weather")
    @ResponseBody
    public Map weather(){
        Location location = new Location();
        Now now = new Now();
        Map<String,Object> map = new HashMap<String,Object>(16);
        map.put("location",location);
        map.put("now",now);
        map.put("last_update","2019-04-20T20:35:00+08:00");
        HashMap hashMap = new HashMap<>();

        ArrayList arrayList = new ArrayList();
        arrayList.add(map);
        hashMap.put("results",arrayList);
        return hashMap;
    }

    @RequestMapping("/weathera")
    @ResponseBody
    public HashMap<Object, Object> weather1(){
        Location location = new Location();
        Now now = new Now();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("location",location);
        objectObjectHashMap.put("now",now);
        objectObjectHashMap.put("last_update","2019-04-20T20:35:00+08:00");;
        return objectObjectHashMap;
    }

    @RequestMapping("/weatherb")
    @ResponseBody
    public R weather2(){
        Location location = new Location();
        Now now = new Now();
        Map map = new HashMap(16);
        map.put("location",location);
        map.put("now",now);
        map.put("last_update","2019-04-20T20:35:00+08:00");

        return R.ok().put("results",map);
    }

}

class Now{
    private String text;
    private int code;
    private int temperature;

    public String getText() {
        return "多云";
    }

    public int getCode() {
        return 4;
    }

    public int getTemperature() {
        return 23;
    }
}

class Location{
    private String id;
    private String name;
    private String country;
    private String path;
    private String timezone;
    private String timezone_offset;

    public String getId() {
        return "WS10730EM8EV";
    }

    public String getName() {
        return "深圳";
    }

    public String getCountry() {
        return "CN";
    }

    public String getPath() {
        return "深圳,深圳,广东,中国";
    }

    public String getTimezone() {
        return "Asia/Shanghai";
    }

    public String getTimezone_offset() {
        return "+08:00";
    }
}
