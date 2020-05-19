package com.junle.exam.Repository;

import com.junle.exam.Entity.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberGroupRepository extends JpaRepository<MemberGroup,Integer> {
    MemberGroup findByGroupName(String groupName);
}
