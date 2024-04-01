package application;


import abstractions.repositories.CatRepository;
import contracts.CatService;
import domain.models.CatModel;
import entity.Cat;
import mapper.CatMapper;

import java.util.List;

public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;
    public CatServiceImpl(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    @Override
    public void makeFriends(Long firstCatId, Long secondCatId) {
        catRepository.addFriendToCat(firstCatId, secondCatId);
    }

    @Override
    public void endFriendship(Long firstCatId, Long secondCatId) {
        catRepository.endFriendship(firstCatId, secondCatId);
    }

    @Override
    public List<CatModel> checkAllFriendsOfCat(Long catId) {
        return catRepository
                .getAllFriendsOfCat(catId)
                .stream()
                .map(CatMapper::entityToModel)
                .toList();
    }

    @Override
    public List<CatModel> checkAllCats() {
        List<Cat> cats = catRepository.getAll();
        return cats
                .stream()
                .map(CatMapper::entityToModel)
                .toList();
    }

    @Override
    public CatModel findCat(Long id) {
        return CatMapper.entityToModel(catRepository.getCatById(id));
    }

    @Override
    public void addCat(String name, String birthday, String breed, String color) {
        catRepository.add(name, birthday, breed, color);
    }

    @Override
    public void deleteCat(Long id) {
        catRepository.delete(id);
    }

    @Override
    public void updateCatName(Long id, String name) {
        catRepository.updateName(id, name);
    }
}
