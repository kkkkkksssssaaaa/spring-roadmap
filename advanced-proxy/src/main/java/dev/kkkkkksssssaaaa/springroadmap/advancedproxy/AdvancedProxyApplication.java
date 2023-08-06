package dev.kkkkkksssssaaaa.springroadmap.advancedproxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV1Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV2Config;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.ExcludeComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;

@Import({
	AppV1Config.class,
	AppV2Config.class
})
@SpringBootApplication(
	scanBasePackages = "dev.kkkkkksssssaaaa.springroadmap.advancedproxy"
)
@ComponentScan(
	excludeFilters = @ComponentScan.Filter(
		type = FilterType.ANNOTATION,
		classes = {ExcludeComponent.class}
	)
)
public class AdvancedProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedProxyApplication.class, args);
	}

}
