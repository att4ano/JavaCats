package dto;

import lombok.Builder;

import java.util.List;

@Builder
public record MasterDto(Long id, String name, String birthdayDate, List<CatDto> cats) { }
