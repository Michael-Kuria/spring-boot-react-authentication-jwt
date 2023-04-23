package com.michael.springbootreactauthenticationjwt;

import com.michael.springbootreactauthenticationjwt.config.RsaKeys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeys.class)
@SpringBootApplication
public class SpringBootReactAuthenticationJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootReactAuthenticationJwtApplication.class, args);
	}

}
