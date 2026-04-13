DROP TABLE IF EXISTS Cats
DROP TABLE IF EXISTS Users
DROP TABLE IF EXISTS Credentials

Create Table Credentials(

                            CredentialsId BIGINT auto_increment primary key,
                            Email varchar(100) not null unique,
                            Password varchar(255) not null
);

Create Table Users(
                      UserId BIGINT auto_increment primary key,
                      Name varchar(100),
                      LastName varchar(100),
                      Gender varchar(100),
                      CredentialsId BIGINT not null,
                      FOREIGN KEY (CredentialsId) REFERENCES Credentials(CredentialsId)
);

Create Table Cats(
                     CatId BIGINT auto_increment primary key,
                     Name varchar(100) not null,
                     Gender varchar(100) not null,
                     Age int not null,
                     DateOfBirth TimeStamp,
                     IsDead bool,
                     DateOfDeath TimeStamp,
                     FurColorCode varchar(100),
                     PatternCode varchar(100),
                     BreedCode varchar(100),
                     EyeCode varchar(100),
                     FullCode varchar(100),
                     OwnerId BIGINT,
                     BreederId BIGINT,
                     FatherId BIGINT,
                     MotherId BIGINT,
                     FOREIGN KEY (OwnerId)   REFERENCES Users(UserId),
                     FOREIGN KEY (BreederId) REFERENCES Users(UserId),
                     FOREIGN KEY (FatherId)  REFERENCES Cats(CatId),
                     FOREIGN KEY (MotherId)  REFERENCES Cats(CatId)
);
