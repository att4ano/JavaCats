package repositories;


import abstractions.repositories.CatRepository;
import entity.Cat;
import entity.Master;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class CatRepositoryImpl implements CatRepository {
    private final EntityManagerFactory entityManagerFactory;

    public CatRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Cat> getAll() {
        List<Cat> cats = entityManagerFactory
                .createEntityManager()
                .createQuery("FROM Cat", Cat.class)
                .getResultList();

        return cats;
    }

    @Override
    public Cat getCatById(Long id) {
        return entityManagerFactory
                .createEntityManager()
                .find(Cat.class, id);
    }

    @Override
    public Set<Cat> getAllFriendsOfCat(Long catId) {
        return entityManagerFactory
                .createEntityManager()
                .find(Cat.class, catId)
                .getCatFriends();
    }

    @Override
    public List<Cat> getAllCatsOfMaster(Long masterId) {
        return entityManagerFactory.createEntityManager()
                .find(Master.class, masterId)
                .getCats();
    }

    @Override
    public void endFriendship(Long firstCatID, Long secondCatId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        TryTransaction(entityManager, em -> {
            Cat firstCat = em
                    .find(Cat.class, firstCatID);

            Cat secondCat = em
                    .find(Cat.class, secondCatId);
            firstCat.getCatFriends().remove(secondCat);
            secondCat.getCatFriends().remove(firstCat);
            em.merge(firstCat);
            em.merge(secondCat);
        });

    }

    @Override
    public void add(String name, String birthday, String breed, String color) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
            Cat cat = new Cat();
            cat.setName(name);
            cat.setBirthdayDate(LocalDate.parse(birthday));
            cat.setBreed(breed);
            cat.setColor(color);
            em.persist(cat);
        });
    }

    @Override
    public void updateName(Long id, String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
           Cat curentCat = em.find(Cat.class, id);
           curentCat.setName(name);
           em.merge(curentCat);
        });
    }

    @Override
    public void addFriendToCat(Long firstCatId, Long secondCatId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
            Cat firstCat = em.find(Cat.class, firstCatId);
            Cat secondCat = em.find(Cat.class, secondCatId);
            firstCat.getCatFriends().add(secondCat);
            secondCat.getCatFriends().add(firstCat);
            em.merge(firstCat);
            em.merge(secondCat);
        });
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> em.remove(em.find(Cat.class, id)));
    }

}
