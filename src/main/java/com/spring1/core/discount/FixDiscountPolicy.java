package com.spring1.core.discount;

import com.spring1.core.member.Grade;
import com.spring1.core.member.Member;
import org.springframework.stereotype.Component;

//vip에게 정액할인
@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000; //정액할인 : 1000원

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
