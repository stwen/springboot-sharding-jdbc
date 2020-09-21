package com.stwen.shardingjdbc.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisPlusConfig {

    /**
     * 物理分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * mybatis-plus SQL执行效率插件【生产环境可以关闭】
     */
    @Bean
//    @Profile({"dev"})// 设置 dev环境开启，线上不推荐
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor interceptor = new PerformanceInterceptor();
        interceptor.setMaxTime(5000);//sql 最大执行时长/毫秒ms,超过自动停止运行，有助于发现问题
        interceptor.setFormat(false);//SQL是否格式化 默认false
        return interceptor;
    }
}
