package com.spring1.core.beanfind;

import com.spring1.core.discount.DiscountPolicy;
import com.spring1.core.discount.FixDiscountPolicy;
import com.spring1.core.discount.RateDiscountPolicy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
 스프링 빈 조회 - 상속관계
 : 부모타입으로 조회하면 자식 타입도 함께 조회된다.

*/
public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

    @Test
    @DisplayName("부모 타입으로 조회시, 자식이 둘 이상 있으면 중복 오류 발생")
    void findBeanByParentTypeDulicate() {
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }

    //해결방법1 : 빈 이름 지정
    @Test
    @DisplayName("빈 이름 지정 : 부모 타입으로 조회시, 자식이 둘 이상 있다면")
    void findBeanByParentTypeBeanName() {
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    //해결방법2 : 특정 하위 타입으로 조회 (좋은 방법x 하지만 쓰이는 경우가 있음)
    @Test
    @DisplayName("특정 하위 타입으로 조회 : 부모 타입으로 조회시, 자식이 둘 이상 있다면 ")
    void findBeanBySubType() {
        RateDiscountPolicy rateDiscountPolicy = ac.getBean(RateDiscountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회")
    void findAllBeanByParentType() {
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        assertThat(beansOfType.size()).isEqualTo(2);
        for (String s : beansOfType.keySet()) {
            System.out.println("s = " + s + ", value = " + beansOfType.get(s));
        }
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회 : Object")
    void findAllBeanByObject() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String s : beansOfType.keySet()) {
            System.out.println("s = " + s + ", value = " + beansOfType.get(s));
        }
    }




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
