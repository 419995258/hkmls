package cn.xdf.hkmls.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: k12-paper-sdk
 * @description: 用于针对字符串的布尔类型兼容
 * @author: huyaocheng@xdf.cn
 * @create: 2021-01-11 13:52
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {

    TRUE(1,"表示 是 类型的枚举"),
    FALSE(0,"表示 否 类型的枚举");

    private Integer code;

    private String desc;


}
