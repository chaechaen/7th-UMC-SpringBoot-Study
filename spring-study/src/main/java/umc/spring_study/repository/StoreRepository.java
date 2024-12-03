package umc.spring_study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring_study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
