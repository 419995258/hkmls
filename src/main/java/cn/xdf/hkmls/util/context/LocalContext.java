package cn.xdf.hkmls.util.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author daiyafei3@xdf.cn
 * @desc 请求上下文
 * @classname LocalContext
 * @date 2020/9/1
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LocalContext {
    /**
     * 请求唯一标识
     */
    private String traceId;

    /**
     * 缓存穿透标识
     */
    private String cachePenetration;

    /**
     * 强制清除缓存标识
     */
    private Boolean cacheForceCleanByRoute;

    /**
     * 当前请求用户email
     */
    private String userEmail;

    /**
     * 当前用户是否为管理员
     */
    private Boolean userAdminFlag;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 业务id
     */
    private String businessId;

}
