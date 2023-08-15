package dev.kkkkkksssssaaaa.springroadmap.advancedproxy;

import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.config.proxy.v6.AopConfig;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.LogTrace;
import dev.kkkkkksssssaaaa.springroadmap.advancedproxy.trace.logtarce.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import({
	AopConfig.class
})
@SpringBootApplication(
	scanBasePackages = "dev.kkkkkksssssaaaa.springroadmap.advancedproxy.app.v3"
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
