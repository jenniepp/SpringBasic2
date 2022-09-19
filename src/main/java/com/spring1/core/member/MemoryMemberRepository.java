package com.spring1.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{
    //구현체
    //사실은 동시성 이슈때문에 concurruntHashmap()을 쓰는게 좋다. 하지만 기본개념 배우는 중이니까 우린 넘어가자
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);

    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }

}
