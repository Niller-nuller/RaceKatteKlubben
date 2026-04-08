package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class CatService {

    private ICatRepository catRepository;

    @Autowired
    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> handleGetFullListOfCats(){
        return catRepository.requestFullCatList();
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
}
