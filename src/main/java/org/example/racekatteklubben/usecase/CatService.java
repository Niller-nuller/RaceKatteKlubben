package org.example.racekatteklubben.usecase;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class CatService {
    private final ICatRepository catRepository;
    private final ValidationService validationService;
    private final List<Cat> fullServerCatList;

    @Autowired
    public CatService(ICatRepository catRepository, ValidationService validationService,List<Cat> fullServerCatList) {
        this.catRepository = catRepository;
        this.validationService = validationService;
        this.fullServerCatList = fullServerCatList;
    }

    public List<Cat> handleReturnCatList(String criteria){
        if(fullServerCatList.isEmpty()){
            fullServerCatList.addAll(catRepository.requestCatListPopulate());
            return fullServerCatList;
        }
        if(!criteria.isEmpty()){
            return fullServerCatList.stream().filter(cat -> cat.getName().contains(criteria)).toList();
        }
        return fullServerCatList;
    }
    public void createCat(Cat cat, long ownerId) {
        cat.setOwnerId(ownerId);


        if (cat.getDateOfBirth() != null) {
            cat.setAge((int) ChronoUnit.YEARS.between(
                    cat.getDateOfBirth(), LocalDate.now()));
        }


        if (!cat.isDead()) {
            cat.setDateOfDeath(null);
        }

        cat.setFullCode(buildFullCode(cat));

        catRepository.createCat(cat);
    }

    private String buildFullCode(Cat cat) {
        return String.join("-",
                safe(cat.getFurColorCode()),
                safe(cat.getPatternCode()),
                safe(cat.getBreedCode()),
                safe(cat.getEyeCode()));
    }

    private String safe(String s) {
        return s == null ? "" : s;
    }

    public List<Cat> listCatById (long id){
        return catRepository.createListOfCatsById(id);
    }

    public Cat getCatById(long id) {
        return catRepository.findById(id);
    }

    public void updateCat(Cat cat) {
        if (cat.getDateOfBirth() != null) {
            cat.setAge((int) ChronoUnit.YEARS.between(
                    cat.getDateOfBirth(), LocalDate.now()));
        }

        if (!cat.isDead()) {
            cat.setDateOfDeath(null);
        }

        cat.setFullCode(buildFullCode(cat));

        catRepository.updateCat(cat);
    }
    public void annihilateCat(long id, long ownerId) {
        catRepository.annihilateCat(id, ownerId);

    }
}
