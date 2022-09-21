package com.spring1.core;

import com.spring1.core.discount.DiscountPolicy;
import com.spring1.core.discount.FixDiscountPolicy;
import com.spring1.core.discount.RateDiscountPolicy;
import com.spring1.core.member.MemberService;
import com.spring1.core.member.MemberServiceImpl;
import com.spring1.core.member.MemoryMemberRepository;
import com.spring1.core.order.OrderService;
import com.spring1.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//appConfig : 중복 선언 없어야하며 역할에 따른 구현이 한눈에 파악가능해야함
@Configuration
public class AppConfig {
    //애플리케이션 전체 동작 방식을 구성하기 위해 객체 생성 및 연결
    //책임을 가지는 설정 클래스 >생성자를 통해 주입(=injection,생성자주입)

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public MemoryMemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    @Bean
    public DiscountPolicy getDiscountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


}
