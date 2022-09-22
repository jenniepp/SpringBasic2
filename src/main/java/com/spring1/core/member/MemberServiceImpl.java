package com.spring1.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
    //MemberServiceImpl : 의존관계에 대한 고민x > 실행만 집중
    private final MemberRepository memberRepository ;

    @Autowired //생성자에 @Autowired : 자동 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository) { //DI(dependency injection)
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);

    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //간단한 테스트 용도 : 인터페이스에는 x
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
