package org.example.racekatteklubben.use_case;

import org.example.racekatteklubben.entity.interfaces.ICatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CatService {

    private ICatRepository catRepository;
    @Autowired
    public CatService(ICatRepository catRepository) {
        this.catRepository = catRepository;
    }
}
