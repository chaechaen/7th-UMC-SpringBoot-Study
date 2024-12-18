package umc.spring_study.service.StoreService;

import umc.spring_study.domain.Mission;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.Store;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.StoreDTO.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddStoreDTO request);

    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request);

    Mission addMissionToStore(Long storeId, MissionRequestDTO requestDTO);

    void challengeMission(Long memberId, Long missionId);
}