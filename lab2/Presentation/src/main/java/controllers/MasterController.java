package controllers;

import contracts.MasterService;
import dto.CatDto;
import dto.MasterDto;
import mapper.CatMapper;
import mapper.MasterMapper;

import java.util.List;

public class MasterController {
    private final MasterService masterService;

    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    public List<MasterDto> checkAllMasters() {
        return masterService.checkAllMasters()
                .stream()
                .map(MasterMapper::modelToDto)
                .toList();
    }

    public MasterDto findMaster(Long id) {
        return MasterMapper.modelToDto(masterService.findMasterById(id));
    }

    public List<CatDto> checkAllCatsOfMaster(Long id) {
        return masterService.checkAllCatsOfMaster(id)
                .stream()
                .map(CatMapper::modelToDto)
                .toList();
    }

    public void addMaster(String name, String birthday) {
        masterService.addMaster(name, birthday);
    }

    public void deleteMaster(Long id) {
        masterService.deleteMaster(id);
    }

    public void updateMaster(Long id, MasterDto masterDto) {
        masterService.update(id, MasterMapper.dtoToModel(masterDto));
    }

    public void deleteCat(Long catId, Long masterId) {
        masterService.deleteCat(catId, masterId);
    }
}