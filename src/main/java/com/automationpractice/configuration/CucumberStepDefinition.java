package com.automationpractice.configuration;


import com.automationpractice.AutomationPracticeApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@SpringBootTest
@ContextConfiguration(classes = AutomationPracticeApplication.class)
public @interface CucumberStepDefinition {
}
