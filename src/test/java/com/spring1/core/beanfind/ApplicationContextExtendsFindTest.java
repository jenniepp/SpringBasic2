package com.spring1.core.beanfind;

import com.spring1.core.discount.DiscountPolicy;
import com.spring1.core.discount.FixDiscountPolicy;
import com.spring1.core.discount.RateDiscountPolicy;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 스프링 빈 조회 - 상속관계
 : 부모타입으로 조회하면 자식 타입도 함께 조회된다.

* */
public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);



    @Configuration
    static class TestConfig{
        @Bean
        public DiscountPolicy rateDiscountPolicy() {
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy() {
            return new FixDiscountPolicy();
        }

    }


}
