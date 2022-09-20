package com.spring1.core;

import com.spring1.core.member.Grade;
import com.spring1.core.member.Member;
import com.spring1.core.member.MemberService;
import com.spring1.core.member.MemberServiceImpl;

public class MemberApp {

    //작동을 잘 하는지 간단히 확인하기 위한 TestClass : 순수한 자바코드, 스프링 관련된 코드 0
    //하지만 이렇게 확인하면 매번 콘솔에 나온 값으로 눈으로 확인해야하고 좋은 테스트가 아니다 > JUnit이용하자
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService(); //MemberServiceImpl
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
