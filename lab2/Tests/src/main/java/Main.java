import abstractions.repositories.CatRepository;
import abstractions.repositories.MasterRepository;
import application.CatServiceImpl;
import application.MasterServiceImpl;
import contracts.CatService;
import contracts.MasterService;
import controllers.CatController;
import controllers.MasterController;
import dto.CatDto;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositories.CatRepositoryImpl;
import repositories.MasterRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("postgres@localhost");
        CatRepository catRepository = new CatRepositoryImpl(entityManagerFactory);
        MasterRepository masterRepository = new MasterRepositoryImpl(entityManagerFactory);

        CatService catService = new CatServiceImpl(catRepository);
        MasterService masterService = new MasterServiceImpl(masterRepository, catRepository);

        CatController catController = new CatController(catService);
        MasterController masterController = new MasterController(masterService);

        masterController.deleteCat(2L, 2L);

        List<CatDto> cats = catController.checkAllCats();
        for (var cat : cats) {
            System.out.println(cat.name());
        }
    }
}
