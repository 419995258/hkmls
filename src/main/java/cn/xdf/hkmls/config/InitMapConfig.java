package cn.xdf.hkmls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import cn.xdf.hkmls.model.ChildDTO;

@Configuration
public class InitMapConfig {

    @Bean
    public ConcurrentHashMap<String, ChildDTO> childDTOMapBak(){
        return new ConcurrentHashMap<>(21);
    }

    @Bean
    public ConcurrentHashMap<Integer, Map<String, String>> childDTOMap(){
        return new ConcurrentHashMap<Integer, Map<String, String>>(4);
    }

    @Bean
    public ConcurrentHashMap<Integer, Map<String, String>> cityMatchMap(){
        return new ConcurrentHashMap<Integer, Map<String, String>>(4);
    }

    @Bean
    public ConcurrentHashMap<Integer, Map<String, String>> countryMatchMap(){
        return new ConcurrentHashMap<Integer, Map<String, String>>(4);
    }

    @Bean
    public ConcurrentHashMap<Integer, Double> cityMatchMapMax(){
        return new ConcurrentHashMap<Integer,Double>(4);
    }

    @Bean
    public ConcurrentHashMap<Integer, Double> countryMatchMapMax(){
        return new ConcurrentHashMap<Integer,Double>(4);
    }
}
