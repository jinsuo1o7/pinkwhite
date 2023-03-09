package com.project.pinkwhite.repository;

import com.project.pinkwhite.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
