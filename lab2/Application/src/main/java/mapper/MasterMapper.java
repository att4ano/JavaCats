package mapper;

import domain.models.CatModel;
import domain.models.MasterModel;
import dto.CatDto;
import dto.MasterDto;
import entity.Cat;
import entity.Master;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@UtilityClass
public class MasterMapper {
    public static MasterDto modelToDto(MasterModel masterModel) {

        if (masterModel == null) {
            return null;
        }

        MasterDto masterDto = MasterDto.builder()
                .id(masterModel.getId())
                .name(masterModel.getName())
                .birthdayDate(masterModel.getBirthdayDate().toString())
                .cats(new ArrayList<>())
                .build();

        for (var cat : masterModel.getCats()) {
            CatDto catDto = CatDto.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .breed(cat.getBreed())
                    .birthdayDate(cat.getBirthdayDate().toString())
                    .color(cat.getColor())
                    .master(masterDto)
                    .friends(new ArrayList<>())
                    .build();

            masterDto.cats().add(catDto);
        }

        return masterDto;
    }

    public static MasterModel dtoToModel(MasterDto masterDto) {
        MasterModel masterModel = MasterModel.builder()
                .id(masterDto.id())
                .name(masterDto.name())
                .birthdayDate(LocalDate.parse(masterDto.birthdayDate()))
                .cats(new ArrayList<>())
                .build();

        for (var cat : masterDto.cats()) {
            CatModel catModel = CatModel.builder()
                    .id(cat.id())
                    .name(cat.name())
                    .breed(cat.breed())
                    .color(cat.color())
                    .birthdayDate(LocalDate.parse(cat.birthdayDate()))
                    .catFriends(new ArrayList<>())
                    .master(masterModel)
                    .build();

            masterModel.getCats().add(catModel);
        }

        return masterModel;
    }

    public static MasterModel entityToModel(Master masterEntity) {

        if (masterEntity == null) {
            return null;
        }

        MasterModel masterModel = MasterModel.builder()
                .id(masterEntity.getId())
                .name(masterEntity.getName())
                .birthdayDate(masterEntity.getBirthdayDate())
                .cats(new ArrayList<>())
                .build();

        for (var cat : masterEntity.getCats()) {
            CatModel catModel = CatModel.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .breed(cat.getBreed())
                    .color(cat.getColor())
                    .birthdayDate(cat.getBirthdayDate())
                    .master(masterModel)
                    .catFriends(new ArrayList<>())
                    .build();

            masterModel.getCats().add(catModel);
        }

        return masterModel;
    }

    public static Master modelToEntity(MasterModel masterModel) {
        Master masterEntity = new Master();
        masterEntity.setId(masterEntity.getId());
        masterEntity.setName(masterEntity.getName());
        masterEntity.setBirthdayDate(masterModel.getBirthdayDate());
        List<Cat> catList = new ArrayList<>();

        for (var cat : masterModel.getCats()) {
            Cat catEntity = new Cat();
            catEntity.setId(cat.getId());
            catEntity.setName(cat.getName());
            catEntity.setBreed(cat.getBreed());
            catEntity.setColor(cat.getColor());
            catEntity.setBirthdayDate(cat.getBirthdayDate());
            catEntity.setMaster(masterEntity);
            catEntity.setCatFriends(new HashSet<>());

            catList.add(catEntity);
        }

        masterEntity.setCats(catList);
        return masterEntity;
    }
}
