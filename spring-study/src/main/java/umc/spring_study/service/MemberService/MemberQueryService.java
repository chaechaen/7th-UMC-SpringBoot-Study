package umc.spring_study.service.MemberService;

import umc.spring_study.domain.Member;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);

}