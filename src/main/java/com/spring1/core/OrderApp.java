package com.spring1.core;

import com.spring1.core.member.Grade;
import com.spring1.core.member.Member;
import com.spring1.core.member.MemberService;
import com.spring1.core.member.MemberServiceImpl;
import com.spring1.core.order.Order;
import com.spring1.core.order.OrderService;
import com.spring1.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    //자바코드 메인메소드로 테스트하기
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext ap = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ap.getBean("memberService", MemberService.class);
        OrderService orderService = ap.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 30000);
        System.out.println("order = " + order);
        System.out.println("order.calculatePrice() = " + order.calculatePrice());

    }
}
