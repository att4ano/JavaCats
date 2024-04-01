package dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CatDto(Long id, String name, String birthdayDate, String breed, String color, MasterDto master, List<CatDto> friends) { }
