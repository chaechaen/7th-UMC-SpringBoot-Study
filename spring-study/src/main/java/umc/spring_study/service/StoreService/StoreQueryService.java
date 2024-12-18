package umc.spring_study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}