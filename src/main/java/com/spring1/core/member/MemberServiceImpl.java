package com.spring1.core.member;

public class MemberServiceImpl implements MemberService{
    //MemberServiceImpl : 의존관계에 대한 고민x > 실행만 집중
    private final MemberRepository memberRepository ;

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
