package contracts;

import domain.models.CatModel;
import domain.models.MasterModel;

import java.util.List;

public interface MasterService {
    List<MasterModel> checkAllMasters();

    MasterModel findMasterById(Long id);

    List<CatModel> checkAllCatsOfMaster(Long masterId);

    void addMaster(String name, String birthday);

    void deleteMaster(Long id);

    void update(Long id, MasterModel master);

    void deleteCat(Long catId, Long masterId);
}
