import abstractions.repositories.MasterRepository;
import entity.Master;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import repositories.MasterRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class MasterRepositoryTest {
    private final EntityManagerFactory entityManagerFactory = Mockito.mock(EntityManagerFactory.class);
    private final EntityManager entityManager = Mockito.mock(EntityManager.class);
    private final MasterRepository masterRepository = new MasterRepositoryImpl(entityManagerFactory);

    @Test
    public void getAllTest() {
        Master master1 = new Master();
        Master master2 = new Master();
        List<Master> expectedMasters = List.of(master1, master2);
        Query<Master> query = Mockito.mock(Query.class);
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.createQuery("FROM Master", Master.class)).thenReturn(query);
        Mockito.when(entityManager.createQuery("FROM Master", Master.class).getResultList()).thenReturn(expectedMasters);
        List<Master> actualMasters = masterRepository.getAll();
        Assertions.assertEquals(expectedMasters, actualMasters);
    }

    @Test
    public void getMasterByIdTest() {
        long masterId = 1L;
        Master expectedMaster = new Master();
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        Mockito.when(entityManager.find(Master.class, masterId)).thenReturn(expectedMaster);
        Master actualMaster = masterRepository.getMasterById(masterId);
        Assertions.assertEquals(expectedMaster, actualMaster);
    }

    @Test
    public void addMasterTest() {
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        masterRepository.add("leha", "2004-07-21");
        Mockito.verify(entityManager).persist(Mockito.any(Master.class));
    }

    @Test
    void updateMasterTest() {
        long masterId = 1L;
        Master masterToUpdate = new Master();
        masterToUpdate.setName("leha name");
        masterToUpdate.setBirthdayDate(LocalDate.parse("1337-01-01"));

        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);

        Mockito.when(entityManager.find(Master.class, masterId)).thenReturn(masterToUpdate);

        Master updatedMaster = new Master();
        updatedMaster.setName("nekit name");
        updatedMaster.setBirthdayDate(LocalDate.parse("2004-01-01"));

        masterRepository.update(masterId, updatedMaster);
        Mockito.verify(entityManager).merge(masterToUpdate);
    }

    @Test
    public void deleteMasterTest() {
        long masterId = 1L;
        Master masterToDelete = new Master();
        Mockito.when(entityManagerFactory.createEntityManager()).thenReturn(entityManager);
        EntityTransaction transaction = Mockito.mock(EntityTransaction.class);
        Mockito.when(entityManager.getTransaction()).thenReturn(transaction);
        Mockito.when(entityManager.find(Master.class, masterId)).thenReturn(masterToDelete);
        masterRepository.delete(masterId);
        Mockito.verify(entityManager).remove(masterToDelete);
    }
}
