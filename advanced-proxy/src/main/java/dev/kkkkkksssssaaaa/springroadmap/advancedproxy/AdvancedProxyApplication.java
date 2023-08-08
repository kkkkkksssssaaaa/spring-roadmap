package dev.kkkkkksssssaaaa.springroadmap.advancedproxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.ExcludeComponent;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v1.dynamic.DynamicProxyBasicConfig;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.ThreadLocalLogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v1.OrderControllerV1Impl;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.v2.OrderControllerV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Import(DynamicProxyBasicConfig.class)
@SpringBootApplication(
	scanBasePackages = "dev.kkkkkksssssaaaa.springroadmap.advancedproxy"
)
@ComponentScan(
	excludeFilters = {
		@ComponentScan.Filter(
			type = FilterType.ASSIGNABLE_TYPE,
			classes = {OrderControllerV1.class, OrderControllerV1Impl.class, OrderControllerV2.class}
		),
		@ComponentScan.Filter(
			type = FilterType.ANNOTATION,
			classes = {ExcludeComponent.class}
		)
	}
)
public class AdvancedProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedProxyApplication.class, args);
	}

	@Bean
	public LogTrace trace() {
		return new ThreadLocalLogTrace();
	}
}
