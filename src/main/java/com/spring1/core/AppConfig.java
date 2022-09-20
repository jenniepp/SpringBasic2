package com.spring1.core;

import com.spring1.core.discount.FixDiscountPolicy;
import com.spring1.core.member.MemberService;
import com.spring1.core.member.MemberServiceImpl;
import com.spring1.core.member.MemoryMemberRepository;
import com.spring1.core.order.OrderService;
import com.spring1.core.order.OrderServiceImpl;

public class AppConfig {
    //애플리케이션 전체 동작 방식을 구성하기 위해 객체 생성 및 연결
    //책임을 가지는 설정 클래스 >생성자를 통해 주입(=injection,생성자주입)

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
