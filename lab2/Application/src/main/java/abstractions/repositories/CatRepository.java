package abstractions.repositories;

import entity.Cat;

import java.util.List;
import java.util.Set;

public interface CatRepository extends Repository {
    List<Cat> getAll();

    Cat getCatById(Long id);

    void add(String name, String birthday, String breed, String color);

    void updateName(Long id, String name);

    Set<Cat> getAllFriendsOfCat(Long catId);

    void addFriendToCat(Long firstCatId, Long secondCatId);

    void delete(Long id);

    List<Cat> getAllCatsOfMaster(Long masterId);

    void endFriendship(Long firstCatID, Long secondCatId);
}
