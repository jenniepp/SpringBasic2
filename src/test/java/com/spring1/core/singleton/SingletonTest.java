package com.spring1.core.singleton;

import com.spring1.core.AppConfig;
import com.spring1.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI컨테이너의 문제점")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = appConfig.memberService();
        //2.조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인 : 객체가 새로 생성됨. > 메모리낭비가 큼. 이를 해결하기 위해 싱글톤패턴 이용
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        //참조값이 동일
        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
        //isEquals (equal) vs isSameAs (==)
    }

    //스프링 컨테이너는 기본적으로 싱글톤패턴을 이용한다. 그래서 bean으로 생성하면 싱글톤패턴이 포함된 상태.
    @Test
    @DisplayName("스프링컨테이너와 싱글톤")
    void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        //1.조회 : 호출할 때마다 객체 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        //2.조회 : 호출할 때마다 객체 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값 동일 : 스프링컨테이너에 bean으로 관리되는 객체는 싱글톤패턴!
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);

    }
}
