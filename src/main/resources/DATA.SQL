spring.sql.init.mode=always

INSERT INTO Credentials (CredentialsId, Email, Password) VALUES (1, 'john@email.com', 'hashed_password_1');
INSERT INTO Credentials (CredentialsId, Email, Password) VALUES (2, 'jane@email.com', 'hashed_password_2');
INSERT INTO Credentials (CredentialsId, Email, Password) VALUES (3, 'bob@email.com', 'hashed_password_3');
INSERT INTO Credentials (CredentialsId, Email, Password) VALUES (4, 'alice@email.com', 'hashed_password_4');
INSERT INTO Credentials (CredentialsId, Email, Password) VALUES (5, 'charlie@email.com', 'hashed_password_5');

INSERT INTO Users (UserId, Name, LastName, Gender, CredentialsId) VALUES (1, 'John', 'Doe', 'Male', 1);
INSERT INTO Users (UserId, Name, LastName, Gender, CredentialsId) VALUES (2, 'Jane', 'Doe', 'Female', 2);
INSERT INTO Users (UserId, Name, LastName, Gender, CredentialsId) VALUES (3, 'Bob', 'Smith', 'Male', 3);
INSERT INTO Users (UserId, Name, LastName, Gender, CredentialsId) VALUES (4, 'Alice', 'Johnson', 'Female', 4);
INSERT INTO Users (UserId, Name, LastName, Gender, CredentialsId) VALUES (5, 'Charlie', 'Brown', 'Male', 5);

INSERT INTO Cats (CatId, Name, Gender, Age, DateOfBirth, IsDead, FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, OwnerId, BreederId, FatherId, MotherId)
VALUES (1, 'Whiskers', 'Male', 5, '2019-03-15 00:00:00', false, 'd', '12', 'PER', '64', 'PER d 12 64', 1, 2, NULL, NULL);

INSERT INTO Cats (CatId, Name, Gender, Age, DateOfBirth, IsDead, FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, OwnerId, BreederId, FatherId, MotherId)
VALUES (2, 'Luna', 'Female', 4, '2020-06-20 00:00:00', false, 'n', NULL, 'SIA', '61', 'SIA n 61', 2, 1, NULL, NULL);

INSERT INTO Cats (CatId, Name, Gender, Age, DateOfBirth, IsDead, FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, OwnerId, BreederId, FatherId, MotherId)
VALUES (3, 'Mittens', 'Female', 2, '2022-01-10 00:00:00', false, 'w', '03', 'PER', '64', 'PER w 03 64', 3, 2, 1, 2);

INSERT INTO Cats (CatId, Name, Gender, Age, DateOfBirth, IsDead, FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, OwnerId, BreederId, FatherId, MotherId)
VALUES (4, 'Shadow', 'Male', 3, '2021-09-05 00:00:00', false, 'a', NULL, 'BRI', '65', 'BRI a 65', 4, 3, 1, 2);

INSERT INTO Cats (CatId, Name, Gender, Age, DateOfBirth, IsDead, DateOfDeath, FurColorCode, PatternCode, BreedCode, EyeCode, FullCode, OwnerId, BreederId, FatherId, MotherId)
VALUES (5, 'Bella', 'Female', 6, '2018-11-25 00:00:00', true, '2024-08-01 00:00:00', 'e', '13', 'MCO', '62', 'MCO e 13 62', 5, 1, 1, 2);'2018-11-25 00:00:00', true, '2024-08-01 00:00:00', 'CREAM', 'SPOTTED', 'MAINE_COON', 'BROWN', 5, 1, 1, 2);