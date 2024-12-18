package umc.spring_study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring_study.apiPayload.code.status.ErrorStatus;
import umc.spring_study.apiPayload.exception.handler.GeneralException;
import umc.spring_study.converter.StoreConverter;
import umc.spring_study.domain.*;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.repository.MemberRepository.MemberRepository;
import umc.spring_study.repository.MissionRepository.MemberMissionRepository;
import umc.spring_study.repository.MissionRepository.MissionRepository;
import umc.spring_study.repository.RegionRepository;
import umc.spring_study.repository.ReviewRepository;
import umc.spring_study.repository.StoreRepository;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.StoreDTO.StoreRequestDTO;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    private final RegionRepository regionRepository;

    @Override
    public Store addStore(StoreRequestDTO.AddStoreDTO request) {
        Region region = regionRepository.findByName(request.getRegionName())
                .orElseThrow(() -> new GeneralException(ErrorStatus.REGION_NOT_FOUND));

        Store store = Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .score(0.0f)
                .region(region)
                .build();

        return storeRepository.save(store);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReveiwDTO request) {

        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }

    @Override
    public Mission addMissionToStore(Long storeId, MissionRequestDTO requestDTO) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Mission mission = Mission.builder()
                .reward(requestDTO.getReward())
                .deadline(requestDTO.getDeadline())
                .missionSpec(requestDTO.getMissionSpec())
                .store(store)
                .build();

        return missionRepository.save(mission);
    }

    @Override
    public void challengeMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found"));

        MemberMission memberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();

        memberMissionRepository.save(memberMission);
    }
}