package umc.spring_study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.enums.MissionStatus;
import umc.spring_study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {

    Optional<Member> findMember(Long id);
    Page<Review> getMemberReviews(Long memberId, Integer page);
    Page<MemberMission> getMemberMissions(Long memberId, MissionStatus status, Integer page);
}