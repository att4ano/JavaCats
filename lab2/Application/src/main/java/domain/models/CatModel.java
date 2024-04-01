package domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CatModel {

    private final Long id;

    private String name;

    private final LocalDate birthdayDate;

    private final String breed;

    private final String color;

    private final List<CatModel> catFriends;

    private MasterModel master;
}
