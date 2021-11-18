package com.amazing2j.amz.amz_client;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.amazing2j.amz.amz_client.config.AMZClientConfigure;
import com.amazing2j.amz.amz_client.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@SpringBootApplication
@EnableJms    //启动消息队列
public class AmzClientApplication extends WebMvcConfigurationSupport {
    private final static Logger logger = LoggerFactory.getLogger(AmzClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AmzClientApplication.class, args);

        AMZClientConfigure amzConfigure = SpringContextUtil.getBean(AMZClientConfigure.class);
        logger.info("[AmzClientApplication] start server on: http://{}:{}", amzConfigure.getServerAddress(), amzConfigure.getServerPort());
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        //4、将convert添加到converters中
        converters.add(fastConverter);
        //5、追加默认转换器
        super.addDefaultHttpMessageConverters(converters);
    }


}
