package controllers;

import contracts.CatService;
import dto.CatDto;
import mapper.CatMapper;

import java.util.List;

public class CatController {
    private final CatService catService;

    public CatController(CatService catService) {
        this.catService = catService;
    }

    public void makeFriends(Long firstId, Long secondId) {
        catService.makeFriends(firstId, secondId);
    }

    public List<CatDto> checkAllCats() {
        return catService
                .checkAllCats()
                .stream()
                .map(CatMapper::modelToDto)
                .toList();
    }

    public List<CatDto> checkFriendsOfCat(Long id) {
        return catService
                .checkAllFriendsOfCat(id)
                .stream()
                .map(CatMapper::modelToDto)
                .toList();
    }

    public CatDto findCat(Long id) {
        return CatMapper.modelToDto(catService.findCat(id));
    }

    public void addCat(String name, String birthday, String breed, String color) {
        catService.addCat(name, birthday, breed, color);
    }

    public void deleteCat(Long id) {
        catService.deleteCat(id);
    }

    public void updateCatName(Long id, String name) {
        catService.updateCatName(id, name);
    }

    public void endFriendship(Long firstCatId, Long secondCatId) {
        catService.endFriendship(firstCatId, secondCatId);
    }
}
