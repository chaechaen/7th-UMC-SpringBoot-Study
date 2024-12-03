package umc.spring_study.web.dto.MissionDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MissionResponseDTO {
    private Long missionId;
    private Integer reward;
    private LocalDate deadline;
    private String missionSpec;
}