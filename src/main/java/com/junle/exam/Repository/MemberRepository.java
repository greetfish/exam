package com.junle.exam.Repository;

import com.junle.exam.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Iterable<Member> findMembersByMemberGroupId(Integer memberGroupId);
}
