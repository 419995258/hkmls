package cn.xdf.hkmls.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.xdf.hkmls.enums.ErrorsEnum;
import cn.xdf.hkmls.enums.LocationEnum;
import cn.xdf.hkmls.model.Builder.ChildDTOBuilder;
import cn.xdf.hkmls.model.ChildDTO;
import cn.xdf.hkmls.model.MatchDTO;
import cn.xdf.hkmls.model.ResponseEntity;
import cn.xdf.hkmls.model.SimDTO;
import cn.xdf.hkmls.model.SimList;
import cn.xdf.hkmls.model.ViewVO;
import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("childC")
@Slf4j
public class childController {

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> childDTOMap;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> countryMatchMap;

    @Autowired
    private ConcurrentHashMap<Integer, Map<String,String>> cityMatchMap;

    @Autowired
    private ConcurrentHashMap<Integer, Double> countryMatchMapMax;
    @Autowired
    private ConcurrentHashMap<Integer, Double> cityMatchMapMax;

    @GetMapping("/get")
    public ResponseEntity<ViewVO> getChild(@RequestParam("childId")String childId){
        log.info("getChild,id={}",childId);
        if(StringUtils.isBlank(childId)){
            return ResponseEntity.fail(ErrorsEnum.ILLEGAL_CHARACTER);
        }
        String txt = childId + "";
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if (!m.matches()) {// 输入的是数字
           return ResponseEntity.fail(ErrorsEnum.ILLEGAL_CHARACTER);
        }
        ViewVO viewVO = new ViewVO();
        // 获取孩子基础信息
        int mo = Integer.valueOf(childId) % 16;
        Map<String,String> map = childDTOMap.get(mo);
        String jsonStr = map.get(childId);
        if(StringUtils.isBlank(jsonStr)){
            return ResponseEntity.fail(ErrorsEnum.NONE_CHILD);
        }
        ChildDTO childDTO = JSONObject.parseObject(jsonStr,ChildDTO.class);
        childDTO = ChildDTOBuilder.of(childDTO);
        viewVO.setChildObj(childDTO);
        // 计算孩子匹配信息
        List<MatchDTO> matchDTOList = new ArrayList<>();
        if(LocationEnum.CITY.getCode().equals(childDTO.getLocation())){
            // 城市
            Map<String,String> cityMap = cityMatchMap.get(mo);
            String jsonMatchStr = cityMap.get(childId);
            SimDTO simDTO = JSONObject.parseObject(jsonMatchStr,SimDTO.class);
            // 匹配孩子的基础信息
            for (SimList sim : simDTO.getSimList()) {
                String simChild = sim.getChildId();
                int simMo = Integer.valueOf(simChild) % 16;
                map = childDTOMap.get(simMo);
                jsonStr = map.get(simChild);
                childDTO = JSONObject.parseObject(jsonStr,ChildDTO.class);
                childDTO = ChildDTOBuilder.of(childDTO);
                // 展示数据转换
                MatchDTO matchDTO = MatchDTO.builder()
                        .childId(simChild)
                        .simvalues(sim.getSimvalues())
                        .childObj(childDTO)
                        .build();
                matchDTOList.add(matchDTO);
            }
            viewVO.setCountrysideList(matchDTOList);
        }else{
            // 乡村
            Map<String,String> countryMap = countryMatchMap.get(mo);
            String jsonMatchStr = countryMap.get(childId);
            SimDTO simDTO = JSONObject.parseObject(jsonMatchStr,SimDTO.class);
            // 匹配孩子的基础信息
            for (SimList sim : simDTO.getSimList()) {
                String simChild = sim.getChildId();
                int simMo = Integer.valueOf(simChild) % 16;
                map = childDTOMap.get(simMo);
                jsonStr = map.get(simChild);
                childDTO = JSONObject.parseObject(jsonStr,ChildDTO.class);
                childDTO = ChildDTOBuilder.of(childDTO);
                // 展示数据转换
                MatchDTO matchDTO = MatchDTO.builder()
                        .childId(simChild)
                        .simvalues(sim.getSimvalues())
                        .childObj(childDTO)
                        .build();
                matchDTOList.add(matchDTO);
            }
            viewVO.setCityList(matchDTOList);
        }

        log.info("getChild,viewVO={}",viewVO);
        return ResponseEntity.success(viewVO);
    }


}
