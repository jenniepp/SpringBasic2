package com.spring1.core.discount;

import com.spring1.core.member.Grade;
import com.spring1.core.member.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    //test내용 : 정말 10percent 할인이 되는지
    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10%할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertEquals(discount, 1000);
    }

    //test에서는 예외검사 중요
    @Test
    @DisplayName("vip가 아니면 할인이 적용되지 않아야한다.")
    void vip_x() {
        //given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        Assertions.assertEquals(discount, 0);
    }
}