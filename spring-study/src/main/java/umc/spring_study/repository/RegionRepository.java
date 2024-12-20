package umc.spring_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByName(String name);
}