package mapper;

import domain.models.CatModel;
import dto.CatDto;
import entity.Cat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class CatMapper {
    public static CatDto modelToDto(CatModel catModel) {
        CatDto catDto = CatDto.builder()
                .id(catModel.getId())
                .name(catModel.getName())
                .breed(catModel.getBreed())
                .color(catModel.getColor())
                .birthdayDate(catModel.getBirthdayDate().toString())
                .master(MasterMapper.modelToDto(catModel.getMaster()))
                .friends(new ArrayList<>())
                .build();

        for (var cat : catModel.getCatFriends()) {
            CatDto friendDto = CatDto.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .color(cat.getBreed())
                    .breed(cat.getBreed())
                    .birthdayDate(cat.getBirthdayDate().toString())
                    .master(MasterMapper.modelToDto(cat.getMaster()))
                    .friends(new ArrayList<>())
                    .build();

            catDto.friends().add(friendDto);
        }

        return catDto;
    }

    public static CatModel dtoToModel(CatDto catDto) {
        CatModel catModel = CatModel.builder()
                .id(catDto.id())
                .name(catDto.name())
                .breed(catDto.breed())
                .color(catDto.color())
                .birthdayDate(LocalDate.parse(catDto.birthdayDate()))
                .master(MasterMapper.dtoToModel(catDto.master()))
                .catFriends(new ArrayList<>())
                .build();

        for (var cat : catDto.friends()) {
            CatModel friendModel = CatModel.builder()
                    .id(cat.id())
                    .name(cat.name())
                    .breed(cat.breed())
                    .color(cat.color())
                    .birthdayDate(LocalDate.parse(cat.birthdayDate()))
                    .master(MasterMapper.dtoToModel(cat.master()))
                    .catFriends(new ArrayList<>())
                    .build();

            catModel.getCatFriends().add(friendModel);
        }

        return catModel;
    }

    public static CatModel entityToModel(Cat catEntity) {
        CatModel catModel = CatModel.builder()
                .id(catEntity.getId())
                .name(catEntity.getName())
                .breed(catEntity.getBreed())
                .color(catEntity.getColor())
                .birthdayDate(catEntity.getBirthdayDate())
                .master(MasterMapper.entityToModel(catEntity.getMaster()))
                .catFriends(new ArrayList<>())
                .build();

        for (var cat : catEntity.getCatFriends()) {
            CatModel friendModel = CatModel.builder()
                    .id(cat.getId())
                    .name(cat.getName())
                    .breed(cat.getBreed())
                    .color(cat.getColor())
                    .birthdayDate(cat.getBirthdayDate())
                    .master(MasterMapper.entityToModel(cat.getMaster()))
                    .catFriends(new ArrayList<>())
                    .build();

            catModel.getCatFriends().add(friendModel);
        }

        return catModel;
    }

    public static Cat modelToEntity(CatModel catModel) {
        Cat catEntity = fillEntity(catModel);
        Set<Cat> friends = new HashSet<>();

        for (var cat : catModel.getCatFriends()) {
            Cat friendEntity = fillEntity(cat);
            friendEntity.setCatFriends(new HashSet<>());
            friends.add(friendEntity);
        }
        catEntity.setCatFriends(friends);
        return catEntity;
    }

    private static Cat fillEntity(CatModel catModel) {
        Cat catEntity = new Cat();
        catEntity.setId(catModel.getId());
        catEntity.setName(catModel.getName());
        catEntity.setBreed(catModel.getBreed());
        catEntity.setColor(catModel.getColor());
        catEntity.setBirthdayDate(catModel.getBirthdayDate());
        catEntity.setMaster(MasterMapper.modelToEntity(catModel.getMaster()));

        return catEntity;
    }
}
