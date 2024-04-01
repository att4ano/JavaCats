package domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MasterModel {

    private final Long id;

    private final String name;

    private final LocalDate birthdayDate;

    private final List<CatModel> cats;
}
