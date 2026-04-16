package org.example.racekatteklubben.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Cat {

    private long id;
    private String name;
    private Gender gender;
    private int age;
    private LocalDate dateOfBirth;
    private boolean isDead;
    private LocalDate dateOfDeath;
    private String furColorCode;
    private String patternCode;
    private String breedCode;
    private String eyeCode;
    private String fullCode;
    private long ownerId;
    private long breedId;
    private long fatherId;
    private long motherId;
    public Cat() {}
    public Cat(long id,
               String name,
               Gender gender,
               int age,
               LocalDate dateOfBirth,
               boolean isDead,
               LocalDate dateOfDeath,
               String furColorCode,
               String patternCode,
               String breedCode,
               String eyeCode,
               String fullCode,
               long ownerId,
               long breedId,
               long fatherId,
               long motherId
    ) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.isDead = isDead;
        this.dateOfDeath = dateOfDeath;
        this.furColorCode = furColorCode;
        this.patternCode = patternCode;
        this.breedCode = breedCode;
        this.eyeCode = eyeCode;
        this.fullCode = fullCode;
        this.ownerId = ownerId;
        this.breedId = breedId;
        this.fatherId = fatherId;
        this.motherId = motherId;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public Gender getGender() { return gender; }
    public int getAge() { return age; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public boolean isDead() { return isDead; }
    public LocalDate getDateOfDeath() { return dateOfDeath; }
    public String getFurColorCode() { return furColorCode; }
    public String getPatternCode() { return patternCode; }
    public String getBreedCode() { return breedCode; }
    public String getEyeCode() { return eyeCode; }
    public String getFullCode() { return fullCode; }
    public long getOwnerId() { return ownerId; }
    public long getBreedId() { return breedId; }
    public long getFatherId() { return fatherId; }
    public long getMotherId() { return motherId; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setGender(Gender gender) { this.gender = gender; }
    public void setAge(int age) { this.age = age; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public void setDead(boolean dead) { isDead = dead; }
    public void setDateOfDeath(LocalDate dateOfDeath) { this.dateOfDeath = dateOfDeath; }
    public void setFurColorCode(String furColorCode) { this.furColorCode = furColorCode; }
    public void setPatternCode(String patternCode) { this.patternCode = patternCode; }
    public void setBreedCode(String breedCode) { this.breedCode = breedCode; }
    public void setEyeCode(String eyeCode) { this.eyeCode = eyeCode; }
    public void setFullCode(String fullCode) { this.fullCode = fullCode; }
    public void setOwnerId(long ownerId) { this.ownerId = ownerId; }
    public void setBreedId(long breedId) { this.breedId = breedId; }
    public void setFatherId(long fatherId) { this.fatherId = fatherId; }
    public void setMotherId(long motherId) { this.motherId = motherId; }

}
