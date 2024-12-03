package umc.spring_study.service.MemberService;

import umc.spring_study.domain.Member;
import umc.spring_study.web.dto.MemberDTO.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
