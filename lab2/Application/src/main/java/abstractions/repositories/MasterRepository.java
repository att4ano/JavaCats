package abstractions.repositories;

import entity.Master;

import java.util.List;

public interface MasterRepository extends Repository {
    List<Master> getAll();

    Master getMasterById(Long id);

    void add(String name, String birthday);

    void update(Long id, Master master);

    void delete(Long id);

    void deleteCat(Long catId, Long masterId);
}
