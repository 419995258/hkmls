package cn.xdf.hkmls.util.context;

import com.google.common.collect.Maps;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import cn.xdf.hkmls.enums.BooleanEnum;


/**
 * @author daiyafei3@xdf.cn
 * @desc 线程上下文工具类
 * @classname ContextUtil
 * @date 2020/9/2
 */
public class ContextUtil {

    /**
     * 获取traceId
     *
     * @param
     * @return string
     * @throw 
     * @author daiyafei3@xdf.cn
     * @date 
     */
    public static String getTraceId(){
        //从本地线程中取对象
        LocalContext localContext = LocalHolder.get();
        //存在则直接取值
        if(localContext != null){
            String traceId = localContext.getTraceId();
            if(StringUtils.isBlank(traceId)){
                String newTraceId = getRandomTraceId();
                localContext.setTraceId(newTraceId);
                return newTraceId;
            }
            return traceId;
        } else { //不存在，在第一次取时新建对象并存入
            localContext = new LocalContext();
            String newTraceId = getRandomTraceId();
            localContext.setTraceId(newTraceId);
            LocalHolder.add(localContext);
            return newTraceId;
        }
    }
    
    /**
     * 设置traceId
     *
     * @param traceId
     * @return 
     * @throw 
     * @author daiyafei3@xdf.cn
     * @date 
     */
    public static void setTraceId(String traceId){
        LocalContext localContext = LocalHolder.get();
        if (Objects.isNull(localContext)) {
            localContext = new LocalContext();
            localContext.setTraceId(traceId);
            LocalHolder.add(localContext);
        } else if (StringUtils.isBlank(localContext.getTraceId())) {
            localContext.setTraceId(traceId);
        }
    }

    public static void releaseThreadLocals(){
        LocalHolder.remove();
    }

    /**
     * 生成16位随机字符串
     *
     * @return
     */
    public static String getRandomTraceId(){
        return RandomStringUtils.randomAlphanumeric(16);
    }

    public static void setCachePenetration(String cachePenetration) {
        LocalContext localContext = LocalHolder.get();
        if (Objects.nonNull(localContext)) {
            localContext.setCachePenetration(cachePenetration);
        } else {
            localContext = LocalContext.builder().cachePenetration(cachePenetration).build();
            LocalHolder.add(localContext);
        }
    }

    public static void setCacheForceCleanByRoute(String forceCleanCacheByRouteVal) {
        LocalContext localContext = LocalHolder.get();
        boolean val = StringUtils.isNotBlank(forceCleanCacheByRouteVal)
                && "true".equals(forceCleanCacheByRouteVal.toLowerCase());
        if (Objects.nonNull(localContext)) {
            localContext.setCacheForceCleanByRoute(val);
        } else {
            localContext = LocalContext.builder().cacheForceCleanByRoute(val).build();
            LocalHolder.add(localContext);
        }
    }

    public static String getUserEmail(){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            return localContext.getUserEmail();
        } else {
            return null;
        }
    }

    public static void setUserEmail(String userEmail){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            localContext.setUserEmail(userEmail);
        } else {
            localContext = LocalContext.builder().userEmail(userEmail).build();
            LocalHolder.add(localContext);
        }
    }

    public static Boolean getUserAdminFlag(){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            return null==localContext.getUserAdminFlag()?false:localContext.getUserAdminFlag();
        } else {
            return false;
        }
    }

    public static void setUserAdminFlag(String userAdminFlag){
        Boolean adminFlag = false;

        if(BooleanEnum.TRUE.getCode().equals(userAdminFlag)){
            adminFlag = true;
        }

        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            localContext.setUserAdminFlag(adminFlag);
        } else {
            localContext = LocalContext.builder().userAdminFlag(adminFlag).build();
            LocalHolder.add(localContext);
        }
    }

    public static String getAppId(){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            return localContext.getAppId();
        } else {
            return null;
        }
    }

    public static void setAppId(String appId){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            localContext.setAppId(appId);
        } else {
            localContext = LocalContext.builder().appId(appId).build();
            LocalHolder.add(localContext);
        }
    }

    public static String getBusinessId(){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            return localContext.getBusinessId();
        } else {
            return null;
        }
    }

    public static void setBusinessId(String businessId){
        LocalContext localContext = LocalHolder.get();
        if(Objects.nonNull(localContext)){
            localContext.setBusinessId(businessId);
        } else {
            localContext = LocalContext.builder().businessId(businessId).build();
            LocalHolder.add(localContext);
        }
    }

    public static Map<String, String> copyContext() {
        return Optional.ofNullable(MDC.getCopyOfContextMap()).orElse(Maps.newHashMap());
    }

    public static LocalContext copyLocalContent() {
        return Optional.ofNullable(LocalHolder.get()).orElse(LocalContext.builder().build());
    }

}
