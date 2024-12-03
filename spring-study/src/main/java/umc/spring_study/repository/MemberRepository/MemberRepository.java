package umc.spring_study.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
