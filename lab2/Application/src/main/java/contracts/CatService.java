package contracts;

import domain.models.CatModel;

import java.util.List;

public interface CatService {
    List<CatModel> checkAllCats();

    List<CatModel> checkAllFriendsOfCat(Long catId);

    CatModel findCat(Long id);

    void addCat(String name, String birthday, String breed, String color);

    void deleteCat(Long id);

    void updateCatName(Long id, String name);

    void makeFriends(Long firstCatId, Long secondCatId);

    void endFriendship(Long firstCatId, Long secondCatId);
}
