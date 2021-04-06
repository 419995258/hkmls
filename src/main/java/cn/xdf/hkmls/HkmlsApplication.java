package cn.xdf.hkmls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
public class HkmlsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkmlsApplication.class, args);
    }


    /**
     * 跨域过滤器,解决ajax访问请求跨域
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1、允许任何域名使用
        corsConfiguration.addAllowedOrigin("*");
        // 2、允许任何头
        corsConfiguration.addAllowedHeader("*");
        // 3、允许任何方法（post、get等）
        corsConfiguration.addAllowedMethod("*");
        // 允许携带cookie跨域
        corsConfiguration.setAllowCredentials(true);
        //
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }


}
