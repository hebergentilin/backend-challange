package com.invillia.acme.swagger;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Api
public class SwaggerConfig {
    @Bean
    public Docket dockerApi(){
        AuthorizationScope[] authScopes = new AuthorizationScope[1];
        authScopes[0] = new AuthorizationScopeBuilder()
                .scope("")
                .build();

        SecurityReference securityReference = SecurityReference.builder()
                .reference("basicAuth")
                .scopes(authScopes)
                .build();

        ArrayList<SecurityReference> reference = new ArrayList<>(1);
        reference.add(securityReference);


        ArrayList<SecurityContext> securityContexts = new ArrayList<>(1);
        securityContexts.add(SecurityContext.builder().securityReferences(reference).build());

        ArrayList<SecurityScheme> auth = new ArrayList<>(1);
        auth.add(new BasicAuth("basicAuth"));


        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .securitySchemes(auth)
                .securityContexts(securityContexts)
                .select()
                    .paths(regex("(/v1/sellers.*|" +
                            "/v1/orders.*|" +
                            "/v1/transfers.*|" +
                            "/v1/providers.*|" +
                            "/v1/financial_transaction.*|" +
                            "/v1/bank_account.*|" +
                            "/v1/bank_account_intermediary.*|" +
                            "/v1/moviments.*|" +
                            "/v1/seller-transaction.*|" +
                            "/v1/split.*){1}$|" +
                            "/seller/billing/aggregate/.*"))

                .build();
    }


    @Bean
    public UiConfiguration uiConfig() {
        return new UiConfiguration(null);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Backend Challenge API")
                .version("1.0")
                .build();
    }
}
