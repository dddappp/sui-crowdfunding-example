package org.test.suicrowdfundingexample;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.test.suicrowdfundingexample.specialization.ApplicationContext;
import org.test.suicrowdfundingexample.specialization.spring.SpringApplicationContext;

@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext ctx) throws BeansException {
        ApplicationContext.current = new SpringApplicationContext(ctx);
    }
}
