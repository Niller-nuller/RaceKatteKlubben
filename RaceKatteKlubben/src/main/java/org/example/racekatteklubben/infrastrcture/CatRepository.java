package org.example.racekatteklubben.infrastrcture;

import org.example.racekatteklubben.entity.Cat;
import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CatRepository implements ICatRepository {

    @Override
    public void createCat(Cat cat) {
        System.out.println("Kat oprettet (stub): " + cat.getName());
    }
}
