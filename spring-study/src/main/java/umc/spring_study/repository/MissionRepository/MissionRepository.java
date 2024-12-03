package umc.spring_study.repository.MissionRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}