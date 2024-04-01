package repositories;

import abstractions.repositories.MasterRepository;
import entity.Cat;
import entity.Master;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.List;

public class MasterRepositoryImpl implements MasterRepository {
    private final EntityManagerFactory entityManagerFactory;

    public MasterRepositoryImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<Master> getAll() {
        return entityManagerFactory
                .createEntityManager()
                .createQuery("FROM Master", Master.class)
                .getResultList();
    }

    @Override
    public Master getMasterById(Long id) {
        return entityManagerFactory
                .createEntityManager()
                .find(Master.class, id);

    }

    @Override
    public void add(String name, String birthday) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
            Master master = new Master();
            master.setName(name);
            master.setBirthdayDate(LocalDate.parse(birthday));
            em.persist(master);
        });
    }

    @Override
    public void update(Long id, Master master) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
            Master currentMaster = em.find(Master.class, id);
            currentMaster.setName(master.getName());
            currentMaster.setBirthdayDate(master.getBirthdayDate());
            em.merge(currentMaster);
        });
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> em.remove(em.find(Master.class, id)));
    }

    @Override
    public void deleteCat(Long catId, Long masterId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TryTransaction(entityManager, em -> {
            Master master = em.find(Master.class, masterId);
            Cat cat = em.find(Cat.class, catId);
            master.getCats().remove(cat);
            cat.setMaster(null);
            em.merge(master);
            em.merge(cat);
        });
    }
}
