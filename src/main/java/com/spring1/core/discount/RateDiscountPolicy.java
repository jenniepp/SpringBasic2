package com.spring1.core.discount;

import com.spring1.core.annotation.MainDiscountPolicy;
import com.spring1.core.member.Grade;
import com.spring1.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy //내가 만든 어노테이션
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10; //할인율

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price / discountPercent;
        } else {
            return 0;
        }


    }
}
