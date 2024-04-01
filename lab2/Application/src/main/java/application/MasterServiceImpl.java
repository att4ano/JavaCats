package application;


import abstractions.repositories.CatRepository;
import abstractions.repositories.MasterRepository;
import contracts.MasterService;
import domain.models.CatModel;
import domain.models.MasterModel;
import mapper.CatMapper;
import mapper.MasterMapper;

import java.util.List;

public class MasterServiceImpl implements MasterService {
    private final MasterRepository masterRepository;
    private final CatRepository catRepository;

    public MasterServiceImpl(MasterRepository masterRepository, CatRepository catRepository) {
        this.masterRepository = masterRepository;
        this.catRepository = catRepository;
    }

    @Override
    public List<MasterModel> checkAllMasters() {
        return masterRepository
                .getAll()
                .stream()
                .map(MasterMapper::entityToModel)
                .toList();
    }

    @Override
    public MasterModel findMasterById(Long id) {
        return MasterMapper.entityToModel(masterRepository.getMasterById(id));
    }

    @Override
    public List<CatModel> checkAllCatsOfMaster(Long id) {
        return catRepository
                .getAllCatsOfMaster(id)
                .stream()
                .map(CatMapper::entityToModel)
                .toList();
    }

    @Override
    public void addMaster(String name, String birthday) {
        masterRepository.add(name, birthday);
    }

    @Override
    public void deleteMaster(Long id) {
        masterRepository.delete(id);
    }

    @Override
    public void update(Long id, MasterModel masterModel) {
        masterRepository.update(id, MasterMapper.modelToEntity(masterModel));
    }

    @Override
    public void deleteCat(Long catId, Long masterId) {
        masterRepository.deleteCat(catId, masterId);
    }

}