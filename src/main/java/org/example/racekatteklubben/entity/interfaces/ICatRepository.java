package org.example.racekatteklubben.entity.interfaces;

import org.example.racekatteklubben.entity.Cat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICatRepository {

    List<Cat> requestFullFilteredCatList(String criteria);
    void createCat(Cat cat);
    List<Cat> createListOfCatsById(long id);
    Cat findById(long id);
    void updateCat(Cat cat);
    void annihilateCat(long id, long ownerId);
    List<Cat> requestCatListPopulate();
}
