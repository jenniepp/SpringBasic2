package com.spring1.core.beanfind;

import com.spring1.core.AppConfig;
import com.spring1.core.member.MemberService;
import com.spring1.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스니?
    }

    @Test
    @DisplayName("빈 이름없이 타입으로 조회")
    void findBeanByType() { //장점: 편리하다 단점:타입이 여러개일때 불편하다
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스니?
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByName2() { //인터페이스가 아니라 구현체로 써도된다. 하지만 별로 좋은 방법 아님.(유연성이 떨어짐) 가끔필요할때가 있음
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스니?
    }

    //예외 테스트
    @Test
    @DisplayName("빈 이름으로 조회x")
    void findBeanByName_x() {
       // MemberService memberService = ac.getBean("xxxxx", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("xxxxx", MemberService.class)
        );
        //오른쪽 로직을 실행하면 오른쪽 NoSuchBeanDefinitionException가 만족되어야한다.(=예외가 발생해야한다.)
    }
}
