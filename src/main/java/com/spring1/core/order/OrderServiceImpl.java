package com.spring1.core.order;

import com.spring1.core.discount.DiscountPolicy;
import com.spring1.core.discount.FixDiscountPolicy;
import com.spring1.core.member.Member;
import com.spring1.core.member.MemberRepository;
import com.spring1.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
