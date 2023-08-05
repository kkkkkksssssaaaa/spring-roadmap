package dev.kkkkkksssssaaaa.springroadmap.advancedproxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.AppV1Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(AppV1Config.class)
@SpringBootApplication(scanBasePackages = "dev.kkkkkksssssaaaa.springroadmap.advancedproxy")
public class AdvancedProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedProxyApplication.class, args);
	}

}
