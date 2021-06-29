package com.xmeme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmemeApplication {

	public static void main(String[] args) {

		SpringApplication.run(XmemeApplication.class, args);
	}

	/*@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.xmeme"))
				.paths(PathSelectors.ant("/memes/*"))
				.build();
	}*/

}


