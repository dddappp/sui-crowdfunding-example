package org.test.suicrowdfundingexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class
})
@EntityScan(basePackages = {
        "org.test.suicrowdfundingexample.sui.contract"
})
@EnableScheduling
//@EnableAutoConfiguration
public class SuiCrowdfundingExampleApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SuiCrowdfundingExampleApplication.class, args);
        //ApplicationContext.current = new SpringApplicationContext(ctx);
        ctx.publishEvent(new ContextStartedEvent(ctx));
    }

}
