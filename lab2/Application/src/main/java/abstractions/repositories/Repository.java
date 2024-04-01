package abstractions.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.function.Consumer;

public interface Repository {
    default void TryTransaction(EntityManager entityManager, Consumer<EntityManager> operation) {
        EntityTransaction transaction = entityManager.getTransaction();
        try (entityManager) {
            transaction.begin();
            operation.accept(entityManager);
            transaction.commit();
        } catch (Exception transactionException) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw transactionException;
        }
    }
}
