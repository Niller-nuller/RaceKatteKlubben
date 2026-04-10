package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository {

    List<Cat> requestFullCatList();
    void createCat(Cat cat);
}
