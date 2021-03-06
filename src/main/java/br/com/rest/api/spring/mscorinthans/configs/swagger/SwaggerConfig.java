package br.com.rest.api.spring.mscorinthans.configs.swagger;

import br.com.rest.api.spring.mscorinthans.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket msCorinthansApi(){

        return new Docket(DocumentationType.SWAGGER_2)

                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.rest.api.spring.mscorinthans"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(this.info())
                .ignoredParameterTypes(User.class)
                .globalOperationParameters(
                        Arrays.asList(
                                new ParameterBuilder()
                                        .name("Authorization")
                                        .description("Header para Token JWT")
                                        .modelRef(new ModelRef("string"))
                                        .parameterType("header")
                                        .required(false)
                                        .build()));
    }

    public ApiInfo info() {

        return new ApiInfoBuilder()
                .title("Corinthans")
                .description("API que busca simular o controle de informações sobre o elenco do Corinthans de 2020.")
                .version("1.0")
                .build();
    }
}
