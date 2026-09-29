package org.openxava.xavacrm;

import org.openxava.spring.OpenXavaApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Execute this class to start the application.
 * With Maven: mvn spring-boot:run
 */
@SpringBootApplication
public class XavaCrmApplication extends OpenXavaApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(XavaCrmApplication.class, args);
	}

}
