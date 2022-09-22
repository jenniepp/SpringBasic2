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
    //스프링컨테이너는 기본적으로 빈객체를 싱글톤으로 관리
    //메소드 호출 순서는 보장하지 않음

    //@Bean : memberService -> new MemoryMemberRepository()
    //@Bean : orderService -> new MemoryMemberRepository(), new RateDiscountPolicy()
    //MemoryMemberRepository() 두개 만들어지니까 싱글톤패턴이 지켜지지 않는건가?

    @Bean
    public MemberService memberService() {
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(getMemberRepository());
    }

    @Bean
    public MemoryMemberRepository getMemberRepository() {
        System.out.println("AppConfig.getMemberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    @Bean
    public DiscountPolicy getDiscountPolicy() {
        System.out.println("AppConfig.getDiscountPolicy");
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

/*bean = com.spring1.core.AppConfig$$EnhancerBySpringCGLIB$$2b7a619f@4a83a74a
bean = com.spring1.core.AppConfig 이렇게 출력되어야 맞다.
사실 이는 우리가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해 등록한 것이다.
즉, AppConfig 클래스를 상속받은 다른 조작 라이브러리 클래스 등록 여기서 싱글톤이 보장된다.
*/

//@Bean : 메서드마다 스프링 빈이 존재하면 존재하는 빈을 반환/없으면 새로 등록. 코드가 동적으로 만들어진다.
//단, @Configuration없이 @Bean쓰면 싱글톤 패턴 보장x


}
