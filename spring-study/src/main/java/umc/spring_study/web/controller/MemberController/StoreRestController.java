package umc.spring_study.web.controller.MemberController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring_study.apiPayload.ApiResponse;
import umc.spring_study.converter.StoreConverter;
import umc.spring_study.domain.Member;
import umc.spring_study.domain.Mission;
import umc.spring_study.domain.Review;
import umc.spring_study.domain.mapping.MemberMission;
import umc.spring_study.service.StoreService.StoreCommandService;
import umc.spring_study.service.StoreService.StoreQueryService;
import umc.spring_study.validation.annotation.ExistMember;
import umc.spring_study.validation.annotation.ExistStore;
import umc.spring_study.web.dto.MissionDTO.MissionRequestDTO;
import umc.spring_study.web.dto.MissionDTO.MissionResponseDTO;
import umc.spring_study.web.dto.StoreDTO.StoreRequestDTO;
import umc.spring_study.web.dto.StoreDTO.StoreResponseDTO;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<StoreResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId,@RequestParam(name = "page") Integer page){
        storeQueryService.getReviewList(storeId,page);
        var reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(StoreConverter.reviewPreViewListDTO(reviewList));
    }

    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO> addMissionToStore(
            @RequestBody @Valid MissionRequestDTO requestDTO,
            @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = storeCommandService.addMissionToStore(storeId, requestDTO);
        return ApiResponse.onSuccess(StoreConverter.toMissionResponseDTO(mission));
    }

    @PostMapping("/{memberId}/missions/{missionId}/challenge")
    public ApiResponse<String> challengeMission(
            @ExistMember @PathVariable(name = "memberId") Long memberId,
            @PathVariable(name = "missionId") Long missionId) {
        storeCommandService.challengeMission(memberId, missionId);
        return ApiResponse.onSuccess("Mission successfully challenged!");
    }
}