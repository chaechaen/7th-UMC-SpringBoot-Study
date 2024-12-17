package umc.spring_study.repository.MemberRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);
}
