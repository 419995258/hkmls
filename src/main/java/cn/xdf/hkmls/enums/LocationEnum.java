package cn.xdf.hkmls.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
/**
 * 展示的题目类型枚举
 * @Title:
 * @Description:
 * @author pengbin1 <pengbin>
 * @date 2020/12/23 18:27
 * @param
 * @return
 * @throws
 */
public enum LocationEnum {
    COUNTRYSIDE("countryside","乡村"),
    CITY("city","城镇"),
    ;


    /**
     * 题目code
     */
    private final String code;

    /**
     * 题目名称
     */
    private final String name;


}
