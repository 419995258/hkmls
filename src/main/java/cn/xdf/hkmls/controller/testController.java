package cn.xdf.hkmls.controller;

import com.google.common.hash.Hashing;

import com.alibaba.fastjson.JSON;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.xdf.hkmls.model.ChildDTO;
import cn.xdf.hkmls.service.DateConvertService;
import cn.xdf.hkmls.util.BaseUtil;
import cn.xdf.hkmls.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("testC")
@Slf4j
public class testController {
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ConcurrentHashMap<String, ChildDTO> childDTOMapBak;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> childDTOMap;

    @Autowired
    private DateConvertService dateConvertService;

    @GetMapping("/test")
    public String test(){
        dateConvertService.convertCityMatch();
        dateConvertService.convertCountyMatch();
        return "!!!!!!!";
    }

    /**
     * 处理数据，把map数据作处理
     * @return
     */
    @GetMapping("/convert")
    public void convert() throws IOException {
        try {
            for (Map.Entry<String, ChildDTO> stringChildDTOEntry : childDTOMapBak.entrySet()) {
                String childId = stringChildDTOEntry.getKey();
                String jsonVal = JSON.toJSONString(stringChildDTOEntry.getValue());
                int mo = Integer.valueOf(childId) % 16;
                FileWriter fw = new FileWriter("H:\\tempFile" + File.separator + "out_" + mo + ".txt",true);
                fw.write(mo  + "\t" + childId + "\t" + jsonVal + "\n");
                fw.close();
            }
        }catch (Exception e){

        }finally {

        }
    }

}
