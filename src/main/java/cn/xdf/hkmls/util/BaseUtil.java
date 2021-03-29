package cn.xdf.hkmls.util;

import com.google.common.hash.Hashing;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BaseUtil
 *
 * @author zhouxuan
 */
public class BaseUtil {

    private BaseUtil() {}

    public static boolean isNullOrEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean hasElements(List<?> list) {
        return !isNullOrEmpty(list);
    }

    public static boolean isNullOrEmpty(Object[] arr) {
        return arr == null || arr.length < 1;
    }

    public static boolean hasElements(Object[] arr) {
        return !isNullOrEmpty(arr);
    }

    public static boolean isNullOrEmpty(Set<?> set) {
        return set == null || set.isEmpty();
    }

    public static boolean hasElements(Set<?> set) {
        return !isNullOrEmpty(set);
    }

    public static boolean isNullOrEmpty(Map<?, ?> model) {
        return model == null || model.isEmpty();
    }

    public static boolean hasElements(Map<?, ?> model) {
        return !isNullOrEmpty(model);
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().length() < 1;
    }

    public static boolean hasContent(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isNullOrTrimEmpty(String str) {
        return str == null || str.trim().length() < 1;
    }

    public static String currentDate(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }

    public static final String PATTERN_DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_DEFAULT_DATE = "yyyy-MM-dd";

    private static final Pattern PATTERN_NUM = Pattern.compile("[0-9]+");


    private static final String[] CHINESE_NUM_CHAR = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private static final String[] CHINESE_UNIT_CHAR = {"", "十", "百", "千"};

    public static String genUUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

    public static String currentDateTime() {
        return new SimpleDateFormat(PATTERN_DEFAULT_DATETIME).format(new Date());
    }

    public static String date2string(Date date, String format) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * <pre>
     * 返回类的名称（非全名称）
     *
     * e.g. com.jd.seomap.Test.class 返回 Test
     * </pre>
     *
     * @param clazz
     * @return
     */
    public static String getSimpleClassName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return clazz.getSimpleName();
    }

    /**
     * <pre>
     * 返回类的全名称
     *
     * e.g. com.jd.seomap.Test.class 返回 com.jd.seomap.Test
     * </pre>
     *
     * @param clazz
     * @return
     */
    public static String getAllClassName(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        return clazz.getName();
    }

    public static boolean isNumberic(String str) {
        if (isNullOrEmpty(str)) {
            return false;
        }

        Matcher isNum = PATTERN_NUM.matcher(str);
        return isNum.matches();
    }

    public static <E> List<E> newList() {
        return new ArrayList<>();
    }

    public static <E> List<E> newList(int initCapacity) {
        return new ArrayList<>(initCapacity);
    }

    @SafeVarargs
    public static <E> List<E> newList(E... es) {
        if (es == null) {
            return null;
        }
        List<E> list = new ArrayList<>(es.length);
        Collections.addAll(list, es);
        return list;
    }

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> newMap(int initCapacity) {
        return new HashMap<>(initCapacity);
    }

    public static <E> Set<E> newSet() {
        return new HashSet<>();
    }

    public static <E> Set<E> newSet(E... es) {
        return new HashSet<>(Arrays.asList(es));
    }

    public static boolean notIn(int a, int... iArr) {
        return !in(a, iArr);
    }

    public static boolean in(int a, int... iArr) {
        for (int i : iArr) {
            if (a == i) {
                return true;
            }
        }
        return false;
    }

    public static String discardSensitive(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }

        int len = str.length();
        switch (len) {
            case 1:
                return "*";
            case 2:
                return "**";
            default:
                return str.charAt(0) + "****" + str.charAt(len - 1);
        }
    }

    public static String discardTelSensitive(String str) {
        if (isNullOrEmpty(str)) {
            return "";
        }

        int len = str.length();
        if (len < 11) {
            return "*****";
        }
        return str.substring(0, 3) + "****" + str.substring(7, len);
    }

    public static String safeMobile(String mobile) {
        if (isNullOrEmpty(mobile)) {
            return "";
        }

        int len = mobile.length();
        if (len < 11) {
            return "*******";
        }
        return mobile.substring(0, 3) + "****" + mobile.substring(7, len);
    }

    public static String bigDecimal2string(BigDecimal money, int scale) {
        if (money == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("0.");
            for (int i = 0; i < scale; i++) {
                sb.append("0");
            }
            return sb.toString();
        }
        return money.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static Long default2long(String id, long defaultVal) {
        try {
            return Long.valueOf(id);
        } catch (Exception e) {
        }
        return defaultVal;
    }

    public static String base64Decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    public static String deleteHttpPrefix(String url) {
        if (!isNullOrTrimEmpty(url) && url.startsWith("http:")) {
            return url.replaceFirst("http:", "");
        }
        return url;
    }

    public static BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) < 0 ? a : b;
    }

    public static String join(String[] skuArr, String splitor) {
        if (isNullOrEmpty(skuArr)) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String s : skuArr) {
            sb.append(s).append(splitor);
        }
        return sb.length() < 1 ? sb.toString() : sb.substring(0, sb.length() - 1);
    }

    public static int string2int(String str, int defaultVal) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static BigDecimal string2bigDecimal(String str) {
        try {
            return new BigDecimal(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean in(String s, String... in) {
        for (String i : in) {
            if (i == null) {
                continue;
            }
            if (i.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean notIn(String s, String... in) {
        return !in(s, in);
    }

    public static boolean isBetween(int num, int low, int high) {
        return num >= low && num <= high;
    }

    public static long string2long(String str, long defaultVal) {
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            return defaultVal;
        }
    }

    public static boolean isLetterDigit(String str) {
        boolean result = false;
        try {
            String regex = "^[a-z0-9A-Z]+$";
            result = str.matches(regex);
        } catch (Exception e) {
            return result;
        }
        return result;
    }

    public static boolean equals(Object a, Object b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return a.equals(b);
    }

    //生成随机字符串，默认长度为16
    public static String getRandomTraceId(){
        return RandomStringUtils.randomAlphanumeric(16);
    }

    //根据需要生成指字长度的随机字符串
    public static String getRandomTraceId(int len){
        if(len > 0){
            return RandomStringUtils.randomAlphanumeric(len);
        } else {
            return "";
        }
    }

//    /**
//     * 获取traceId，每个线程唯一
//     * @return
//     */
//    public static String getUniqueTraceId(){
//        //从本地线程中取对象
//        LocalCache localCache = LocalHolder.get();
//        //存在则直接取值
//        if(localCache != null){
//            String traceId = localCache.getTraceId();
//            if(BaseUtil.isNullOrEmpty(traceId)){
//                String newTraceId = getRandomTraceId();
//                localCache.setTraceId(newTraceId);
//                return newTraceId;
//            }
//            return traceId;
//        } else { //不存在，在第一次取时新建对象并存入
//            localCache = new LocalCache();
//            String newTraceId = getRandomTraceId();
//            localCache.setTraceId(newTraceId);
//            LocalHolder.add(localCache);
//            return newTraceId;
//        }
//    }


    public static String num2Chinese(int section) {
        if (section >= 10 && section < 20){
            return "十" + num2Chinese(section % 10);
        }
        StringBuilder chnStr = new StringBuilder();
        StringBuilder strIns = new StringBuilder();
        int unitPos = 0;
        boolean zero = true;
        while (section > 0) {
            int v = section % 10;
            if (v == 0) {
                if (!zero) {
                    zero = true;
                    chnStr.append(CHINESE_NUM_CHAR[v]).append(chnStr);
                }
            } else {
                zero = false;
                strIns.delete(0, strIns.length());
                strIns.append(CHINESE_NUM_CHAR[v]);
                strIns.append(CHINESE_UNIT_CHAR[unitPos]);
                chnStr.insert(0, strIns);
            }
            unitPos++;
            section = (int) Math.floor(section / 10f);
        }
        return chnStr.toString();
    }

    /**
     * 获取 HashCode
     * @param formula 字符串
     * @return HashCode
     */
    public static String getHashCode(String formula){
        return Hashing.sha256().hashBytes(formula.getBytes()).toString();
    }

    /**
     * 将日期字符串转化成时间戳，到秒
     * @param date_str
     * @param format
     * @return
     */
    public static long date2TimeStamp(String date_str,String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将日期字符串转化为时间戳，使用默认的时间格式
     * @param date_str
     * @return
     */
    public static long date2TimeStamp(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_DEFAULT_DATETIME);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 将一组数据固定分组，每组n个元素
     *
     * @param source 要分组的数据源
     * @param n      每组n个元素
     * @param <T> 类型范型
     * @return 固定分组数据
     */
    public static <T> List<List<T>> fixedGrouping(List<T> source, int n) {
        if (null == source || source.size() == 0 || n <= 0){
            return Collections.emptyList();
        }

        int sourceSize = source.size();
        int size = (source.size() / n) + 1;
        List<List<T>> result = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            List<T> subset = new ArrayList<T>();
            for (int j = i * n; j < (i + 1) * n; j++) {
                if (j < sourceSize) {
                    subset.add(source.get(j));
                }
            }
            result.add(subset);
        }
        return result;
    }

    /**
     * 将日期对象转化为时间戳，到秒
     * @param date
     * @return
     */
    public static long date2TimeStamp(Date date) {
        try {
            return date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
