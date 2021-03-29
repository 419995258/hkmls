package cn.xdf.hkmls.init;

import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import sun.rmi.runtime.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import cn.xdf.hkmls.model.ChildDTO;
import cn.xdf.hkmls.model.MatchDTO;
import cn.xdf.hkmls.model.ResourceDTO;
import cn.xdf.hkmls.model.SimDTO;
import cn.xdf.hkmls.model.SimList;
import cn.xdf.hkmls.util.DateFormater;
import cn.xdf.hkmls.util.FileUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化学生数据
 */
@Component
@Slf4j
public class InitChild {
    @Autowired
    private ConcurrentHashMap<String, ChildDTO> childDTOMapBak;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> childDTOMap;

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> countryMatchMap;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> cityMatchMap;


    @Autowired
    private ConcurrentHashMap<Integer, Double> countryMatchMapMax;
    @Autowired
    private ConcurrentHashMap<Integer, Double> cityMatchMapMax;

    /**
     * 初始化学生的基础数据
     */
    @PostConstruct
    @Order(1)
    public void initChild() {

        /*for (int i = 1; i < 10; i++) {
            int finalI = i;
            taskExecutor.execute(()->{
                initCity(finalI);
            });
            taskExecutor.execute(()->{
                initCountry(finalI);
            });
        }*/
        for (int i = 0; i < 16; i++) {
            int finalI = i;
            taskExecutor.execute(() -> {
                initChildMsg(finalI);
            });
            taskExecutor.execute(() -> {
                initCityMatch(finalI);
            });
            taskExecutor.execute(() -> {
                initCountryMatch(finalI);
            });
        }
    }

    /**
     * 初始化农村孩子数据
     */
    private void initChildMsg(Integer i) {
        log.info("initChildMsg--start,num:{}", i);
        Map<String,String> map = new HashMap<>(17);
        String lineSep = System.getProperty("line.separator");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "out_"+ i +".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                map.put(value[1], value[2]);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        childDTOMap.put(i,map);
        log.info("initChildMsg--over,num:{},time:{}",i, DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
//        log.info("initCountry--over,time:{}", DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
    }


    /**
     * 初始化city匹配孩子数据
     */
    private void initCityMatch(Integer i) {
        log.info("initCityMatch--start,num:{}", i);
        Map<String,String> map = new HashMap<>(17);
        String lineSep = System.getProperty("line.separator");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        /*

        Double max = cityMatchMapMax.get(i);
        if(max == null){
            max = 0.0;
        }
        */
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "city_out_"+ i +".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                map.put(value[0], value[1]);
                /*
                String jsonMatchStr = value[1];
                SimDTO simDTO = JSONObject.parseObject(jsonMatchStr,SimDTO.class);
                // 匹配孩子的基础信息
                for (SimList sim : simDTO.getSimList()) {
                    Double maxSim = Double.valueOf(sim.getSimvalues());
                    if(maxSim >= max){
                        max = maxSim;
                    }
                }
                 */
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        cityMatchMap.put(i,map);
        /*
        cityMatchMapMax.put(i,max);
         */
        log.info("initCityMatch--over,num:{},time:{}",i, DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
    }

    /**
     * 初始化city匹配孩子数据
     */
    private void initCountryMatch(Integer i) {
        log.info("initCountryMatch--start,num:{}", i);
        Map<String,String> map = new HashMap<>(17);
        String lineSep = System.getProperty("line.separator");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        /*
        Double max = countryMatchMapMax.get(i);
        if(max == null){
            max = 0.0;
        }
         */
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "country_out_"+ i +".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                map.put(value[0], value[1]);
                /*
                String jsonMatchStr = value[1];
                SimDTO simDTO = JSONObject.parseObject(jsonMatchStr,SimDTO.class);
                // 匹配孩子的基础信息
                for (SimList sim : simDTO.getSimList()) {
                    Double maxSim = Double.valueOf(sim.getSimvalues());
                    if(maxSim >= max){
                        max = maxSim;
                    }
                }
                 */
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        countryMatchMap.put(i,map);
        /*
        countryMatchMapMax.put(i,max);
         */
        log.info("initCountryMatch--over,num:{},time:{}",i, DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
    }







    /**
     * 初始化农村孩子数据
     */
    private void initCountyBak() {
//        String lineSep = System.getProperty("line.separator");
        String childId = "";
        String lineSep = ("\\\n");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        try {
            String buffer = FileUtils.readToStringByFilePath("dataset_country_format.txt");
            String[] bufferList = buffer.split(lineSep);
            for (String childStr : bufferList) {
                String[] value = buffer.split("\\\t", -1);
                childId = value[0];
                resourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[8].split(",")))
                        .toys(Arrays.asList(value[9].split(",")))
                        .build();
                hpResourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[10].split(",")))
                        .toys(Arrays.asList(value[11].split(",")))
                        .build();
                childDTO = ChildDTO.builder()
                        .childId(value[0])
                        .location(value[1])
                        .age(value[2])
                        .character(value[3])
                        .gender(value[4])
                        .hobby(new ArrayList<>(Arrays.asList(value[5].split(","))))
                        .hpGender(value[6])
                        .hopAges(value[7])
                        .resource(resourceDTO)
                        .hpResource(hpResourceDTO)
                        .build();
                childDTOMapBak.put(childDTO.getChildId(), childDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 初始化农村孩子数据
     */
    private void initCountry(Integer i ) {
        log.info("initCountry--start,num:{}", i);
        String lineSep = System.getProperty("line.separator");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "dataset_country_format_" + i + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                resourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[8].split(",")))
                        .toys(Arrays.asList(value[9].split(",")))
                        .build();
                hpResourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[10].split(",")))
                        .toys(Arrays.asList(value[11].split(",")))
                        .build();
                childDTO = ChildDTO.builder()
                        .childId(value[0])
                        .location(value[1])
                        .age(value[2])
                        .character(value[3])
                        .gender(value[4])
                        .hobby(new ArrayList<>(Arrays.asList(value[5].split(","))))
                        .hpGender(value[6])
                        .hopAges(value[7])
                        .resource(resourceDTO)
                        .hpResource(hpResourceDTO)
                        .build();
                childDTOMapBak.put(childDTO.getChildId(), childDTO);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("initCountry--over,num:{},time:{}",i, DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
    }

    /**
     * 初始化农村孩子数据
     */
    private void initCity(Integer i) {
        log.info("initCity--start,num:{}", i);
        String lineSep = System.getProperty("line.separator");
        ChildDTO childDTO = new ChildDTO();
        ResourceDTO resourceDTO = new ResourceDTO();
        ResourceDTO hpResourceDTO = new ResourceDTO();
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "dataset_city_format_" + i + ".txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                resourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[8].split(",")))
                        .toys(Arrays.asList(value[9].split(",")))
                        .build();
                hpResourceDTO = ResourceDTO.builder()
                        .books(Arrays.asList(value[10].split(",")))
                        .toys(Arrays.asList(value[11].split(",")))
                        .build();
                childDTO = ChildDTO.builder()
                        .childId(value[0])
                        .location(value[1])
                        .age(value[2])
                        .character(value[3])
                        .gender(value[4])
                        .hobby(new ArrayList<>(Arrays.asList(value[5].split(","))))
                        .hpGender(value[6])
                        .hopAges(value[7])
                        .resource(resourceDTO)
                        .hpResource(hpResourceDTO)
                        .build();
                childDTOMapBak.put(childDTO.getChildId(), childDTO);
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("initCity--over,num:{},time:{}", i,DateFormater.DateToString(new Date(),DateFormater.DATE_STYLE5));
    }

}
