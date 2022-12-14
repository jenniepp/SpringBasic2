package com.spring1.core.order;

import com.spring1.core.annotation.MainDiscountPolicy;
import com.spring1.core.discount.DiscountPolicy;
import com.spring1.core.member.Member;
import com.spring1.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final이 붙은 필드 생성자 생성(Lombok)
public class OrderServiceImpl implements OrderService{

   // private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // 선언과 객체생성까지 다하면 dip와 ocp위반. > 구현체와 추상인터페이스 모두에 의존하게 되기 때문에
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존(구체화 의존 x) + discountPolicy에 의존성 주입

    @Autowired //생성자 호출
    public OrderServiceImpl(MemberRepository memberRepository,
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //간단한 테스트 용도 : 인터페이스에는 x
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
