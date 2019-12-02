package com.hhit.saier.oldController;


import com.alibaba.fastjson.JSON;
import com.hhit.common.controller.BaseController;
import com.hhit.common.redis.shiro.RedisManager;
import com.hhit.common.utils.R;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

/** 
 *
 * 
 * @Title: MedBoxController
 * @description:  
 * @author liujun
 * @create 2018-10-29
 * 
 */
@Controller
@Scope("prototype")
@RequestMapping("/box")
public class MedBoxController extends BaseController {

    @Resource  private RedisManager redisManager;

    /**
     *  接收嵌入式端传输的数据
     * @param tem 温度
     * @param hum 湿度
     * @param pm pm2.5
     * @return
     */
    @RequestMapping("/{tem}/{hum}/{pm}/{bir}")
    @ResponseBody
    public R setEnvironment(
            @PathVariable("tem") Double tem,
            @PathVariable("hum") Double hum,
            @PathVariable("pm") Double pm,
            @PathVariable("bir") String bir){
        System.out.println("-------------第三个API接口：接收嵌入式端传输的数据--------------");
        try {
            Environment environment = new Environment(tem,hum,pm,
                    new Date(System.currentTimeMillis()));
            environment.setBir(bir);
            redisManager.set("saier_environment_info",JSON.toJSONString(environment));
        }catch (Exception e){
            e.printStackTrace();
            return R.error(1,"store error : redis server connection bad;");
        }
        return R.ok();
    }

    @RequestMapping("/environment")
    @ResponseBody
    public R getEnvironment(){
        String result = redisManager.get("saier_environment_info");
        Environment environment = JSON.parseObject(result,Environment.class);
        return R.ok().put("environment",environment);
    }

}

class Environment{
    private Double tem;
    private Double hum;
    private Double pm;
    private String bir;
    private Date time;

    public Environment() {
    }

    public Environment(Double tem, Double hum, Double pm) {
        this.tem = tem;
        this.hum = hum;
        this.pm = pm;
    }

    public Environment(Double tem, Double hum, Double pm, Date time) {
        this.tem = tem;
        this.hum = hum;
        this.pm = pm;
        this.time = time;
    }

    public String getBir() {
        return bir;
    }

    public void setBir(String bir) {
        this.bir = bir;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Double getTem() {
        return tem;
    }

    public void setTem(Double tem) {
        this.tem = tem;
    }

    public Double getHum() {
        return hum;
    }

    public void setHum(Double hum) {
        this.hum = hum;
    }

    public Double getPm() {
        return pm;
    }

    public void setPm(Double pm) {
        this.pm = pm;
    }

    @Override
    public String toString() {
        return "Environment{" +
                "\"tem:\"" + tem +
                ",\"hum\":" + hum +
                ",\"p\"':" + pm +
                ",\"time\":" + time +
                '}';
    }
}
