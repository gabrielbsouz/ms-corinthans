package br.com.rest.api.spring.mscorinthans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MsCorinthansApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCorinthansApplication.class, args);
	}

}
