package cn.xdf.hkmls.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author daiyafei3@xdf.cn
 * @desc 公共异常枚举类
 * @classname ErrorsEnum
 * @date 2020/9/1
 */
@Getter
@AllArgsConstructor
public enum ErrorsEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 数据已存在
     */
    DUPLICATED_DATA(300, "数据已存在"),

    /**
     * 参数错误
     */
    ERROR_PARAM(400, "参数错误"),

    /**
     * 权限校验失败
     */
    AUTH_FAIL(403, "权限校验失败"),

    /**
     * 资源未找到
     */
    NOT_FOUND(404, "资源未找到"),

    /**
     * 请求方法不支持
     */
    REQUEST_METHOD_NOT_SUPPORTED(406, "请求方法不支持"),

    /**
     * 请求类型不支持请求类型不支持
     */
    REQUEST_TYPES_NOT_SUPPORTED(415, "请求类型不支持"),

    /**
     * 系统错误
     */
    ERROR_SYSTEM(500, "系统错误"),

    /**
     * 执行任务超时
     */
    THREAD_POOL_TIMEOUT(501, "执行任务超时"),

    /**
     * 执行任务异常
     */
    THREAD_POOL_ERROR(502, "执行任务异常"),


    /**
     * 分布式锁获取失败
     */
    RETRY_AGAIN(506, "系统忙,请重试！"),

    /**
     * 三方服务异常
     */
    THIRD_ERROR(507, "三方服务异常"),

    THIRD_DATA_ERROR(508, "三方服务数据异常"),

    APP_BUSINESS_ERROR(509,"应用或业务权限异常"),



    /**
     * 试卷信息相关异常
     */
    ILLEGAL_CHARACTER(600,"非法字符"),
    NONE_CHILD(601,"未找到匹配学生"),

    CREATE_PAPER_ERROR(601, "创建试卷异常"),
    INSERT_PAPER_ERROR(602, "新增试卷异常"),
    REMOVE_PAPER_ERROR(603, "删除试卷异常"),
    CURL_PAPER_FAILED(699,"组卷中心出错啦~"),
    ADD_QUESTION_ERROR(1003,"试卷加入试题异常"),
    SET_PAPER_STYLE_PROPERTY_ERROR(1004,"设置试卷卷型异常"),
    EDIT_PAPER_ERROR(1005,"修改试卷异常"),
    PAPER_OWN_ERROR(1006,"试卷归属权限异常"),
    PAPER_REUSE_ERROR(1007,"试卷复用异常"),

    PAPER_SNAPSHOT_NOT_FIND(610,"当前版本试卷快照不存在"),


    PAPER_DOWNLOAD_ERROR(620,"文件下载失败"),

    PAPER_SCORE_UPDATE_NOT_FIND(1999,"未修改到指定试卷的分数"),
    PAPER_USAGE_PROPERTY_CONFIG_NOT_FIND(2000,"试卷用途属性配置未获取到"),
    PAPER_USAGE_PROPERTY_DATA_NOT_FIND(2001,"数据库试卷用途属性数据未查询到"),
    PAPER_USAGE_PROPERTY_UPDATE_NOT_FIND(2002,"未修改到指定试卷用途属性"),
    PAPER_QUESTION_SCORE_UPDATE_NOT_FIND(2003,"未修改到指定试卷试题的分数"),
    PAPER_USAGE_EQUALS_CURR_ERROR(2004,"指定切换试卷用途与当前试卷用途相同"),
    PAGER_RECORD_DELETE_DATA_NOT_FIND(2005,"未删除指定数据"),
    QUESTION_PID_INDEX_MATCH_ERROR(2006,"题目与编号不是唯一对应"),

    QUESTION_NOT_FOUND(2007,"未查询到试题信息"),

    QUESTION_STRUCTURE_TYPE_UNKNOW(2008, "未知的试题结构编码"),

    /**
     * 个人中心相关异常
     */
    TIME_OPTION_NOT_FOUND(2050,"未获取到时间筛选枚举数据"),
    ADD_DOWNLOAD_RECORD_ERROR(2051, "添加下载记录失败"),

    /**
     * 试题篮异常，以后可能会被清除
     */
    QUESTION_BASKET_SAVE_ERROR(3001,"试题篮加入异常"),
    QUESTION_BASKET_DELETE_ERROR(3002,"试题篮删除异常"),
    QUESTION_BASKET_CLEAN_ERROR(3003,"试题篮清空异常"),
    QUESTION_BASKET_QUERY_ERROR(3004,"试题篮查询异常"),
    QUESTION_BASKET_OVERVIEW_ERROR(3005,"试题篮总览异常"),


    ;
    private Integer code;

    private String message;
}
