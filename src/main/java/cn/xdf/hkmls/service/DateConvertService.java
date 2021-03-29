package cn.xdf.hkmls.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import cn.xdf.hkmls.model.ChildDTO;

@Component
public class DateConvertService {
    /**
     * 切克城市孩子匹配
     */
    public void convertCityMatch() {
        // 读取文件
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "city_match_result_two_way_5.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                String childId = value[0];
                int mo = Integer.valueOf(childId) % 16;
                FileWriter fw = new FileWriter("H:\\tempFile" + File.separator + "city_out_" + mo + ".txt", true);
                // 数据处理json
                JSONObject json = new JSONObject();
                json.put("childId",childId);

                JSONArray array = new JSONArray();
                for (int i = 1; i < 6; i++) {
                    String str = value[i];
                    String[] strList = str.split(",");
                    JSONObject j = new JSONObject();
                    j.put("childId",strList[0]);
                    j.put("simvalues",strList[1]);
                    array.add(j);
                }
                json.put("simList",array);


                fw.write(childId + "\t" + json.toJSONString() + "\n");
                fw.close();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void convertCountyMatch() {
        // 读取文件
        try {
            File file = ResourceUtils.getFile("H:\\tempFile" + File.separator + "country_match_result_two_way_5.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] value = line.split("\\\t", -1);
                String childId = value[0];
                int mo = Integer.valueOf(childId) % 16;
                FileWriter fw = new FileWriter("H:\\tempFile" + File.separator + "country_out_" + mo + ".txt", true);
                // 数据处理json
                JSONObject json = new JSONObject();
                json.put("childId",childId);

                JSONArray array = new JSONArray();
                for (int i = 1; i < 6; i++) {
                    String str = value[i];
                    String[] strList = str.split(",");
                    JSONObject j = new JSONObject();
                    j.put("childId",strList[0]);
                    j.put("simvalues",strList[1]);
                    array.add(j);
                }
                json.put("simList",array);


                fw.write(childId + "\t" + json.toJSONString() + "\n");
                fw.close();
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
