package wang.miansen.roothub.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 以 SpringBoot 的方式启动项目
 * 
 * @author miansen.wang
 * @date 2020-01-15
 * @since 3.0
 */
@SpringBootApplication
@ImportResource({"classpath*:spring/spring-*.xml"})
public class RoothubApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoothubApplication.class, args);
	}
	
}
