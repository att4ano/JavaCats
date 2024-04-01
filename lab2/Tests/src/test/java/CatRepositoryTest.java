import abstractions.repositories.CatRepository;
import domain.models.CatModel;
import dto.CatDto;
import dto.MasterDto;
import entity.Cat;
import entity.Master;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.CatRepositoryImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CatRepositoryTest {

    private final EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
    private final EntityManager entityManager = Mockito.mock(EntityManager.class);
    private final CatRepository catRepository = new CatRepositoryImpl(entityManagerFactory);
    @Test
    public void getAllReturnsListOfCats() {
        Cat cat1 = new Cat();
        Cat cat2 = new Cat();
        List<Cat> expectedCats = Arrays.asList(cat1, cat2);
        Query<Cat> query = Mockito.mock(Query.class);
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.createQuery("FROM Cat", Cat.class)).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(expectedCats);
        List<Cat> actualCats = catRepository.getAll();
        assertEquals(expectedCats, actualCats);
    }

    @Test
    public void getCatByIdTest() {
        long catId = 1L;
        Cat expectedCat = new Cat();
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Cat.class, catId)).thenReturn(expectedCat);
        Cat actualCat = catRepository.getCatById(catId);
        assertEquals(expectedCat, actualCat);
    }

    @Test
    void getAllFriendsOfCatTest() {
        long catId = 1L;
        Cat cat = new Cat();
        Set<Cat> expectedFriends = new HashSet<>();
        expectedFriends.add(new Cat());
        expectedFriends.add(new Cat());
        cat.setCatFriends(expectedFriends);
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Cat.class, catId)).thenReturn(cat);
        Set<Cat> actualFriends = catRepository.getAllFriendsOfCat(catId);
        assertEquals(expectedFriends, actualFriends);
    }

    @Test
    void getAllCatsOfMasterTest() {
        long masterId = 1L;
        Master master = new Master();
        List<Cat> expectedCats = Arrays.asList(new Cat(), new Cat());
        master.setCats(expectedCats);
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Master.class, masterId)).thenReturn(master);
        List<Cat> actualCats = catRepository.getAllCatsOfMaster(masterId);
        assertEquals(expectedCats, actualCats);
    }

    @Test
    void addCreatesNewCat() {
        long masterId = 1L;
        Master master = new Master();
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Master.class, masterId)).thenReturn(master);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        catRepository.add("cat", "2004-07-21", "sad", "white");
        Mockito.verify(entityManager).persist(Mockito.any(Cat.class));
    }

    @Test
    void updateExistingCat() {
        long catId = 1L;
        Cat catToUpdate = new Cat();
        catToUpdate.setName("Tom");
        catToUpdate.setBirthdayDate(LocalDate.parse("2004-01-01"));
        catToUpdate.setBreed("breed");
        Master master = new Master();
        catToUpdate.setMaster(master);

        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        Mockito.when(entityManager.find(Cat.class, catId)).thenReturn(catToUpdate);
        Cat updatedCat = new Cat();
        updatedCat.setName("nekit");
        updatedCat.setBirthdayDate(LocalDate.parse("2004-01-01"));
        updatedCat.setBreed("sigma");
        Mockito.when(entityManager.merge(Mockito.any(Cat.class))).thenReturn(updatedCat);
        catRepository.updateName(catId, "leha");
        Mockito.verify(entityManager).merge(catToUpdate);
    }


    @Test
    void addFriendToCat() {
        long firstCatId = 1L;
        long secondCatId = 2L;
        Cat firstCat = new Cat();
        Cat secondCat = new Cat();
        firstCat.setCatFriends(new HashSet<>());
        secondCat.setCatFriends(new HashSet<>());
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Cat.class, firstCatId)).thenReturn(firstCat);
        Mockito.when(entityManager.find(Cat.class, secondCatId)).thenReturn(secondCat);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        catRepository.addFriendToCat(firstCatId, secondCatId);
        Assertions.assertTrue(firstCat.getCatFriends().contains(secondCat));
        Assertions.assertTrue(secondCat.getCatFriends().contains(firstCat));
    }

    @Test
    void deleteRemovesCat() {
        long catId = 1L;
        Cat catToDelete = new Cat();
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Cat.class, catId)).thenReturn(catToDelete);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        catRepository.delete(catId);
        Mockito.verify(entityManager).remove(catToDelete);
    }


}
