--
-- File generated with SQLiteStudio v3.1.1 on mer. mai 9 17:42:38 2018
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: AnswerPoll
DROP TABLE IF EXISTS AnswerPoll;
CREATE TABLE AnswerPoll (User INT NOT NULL REFERENCES User (ID), Author INT NOT NULL, Date INT NOT NULL, Choice INT NOT NULL, Score INT NOT NULL, FOREIGN KEY (Author, Date, Choice) REFERENCES ChoicePoll (Author, Date, Position));
INSERT INTO AnswerPoll (User, Author, Date, Choice, Score) VALUES (3, 4, 1, 4, 1);
INSERT INTO AnswerPoll (User, Author, Date, Choice, Score) VALUES (3, 4, 1, 6, 2);
INSERT INTO AnswerPoll (User, Author, Date, Choice, Score) VALUES (3, 4, 1, 2, 3);
INSERT INTO AnswerPoll (User, Author, Date, Choice, Score) VALUES (3, 5, 13, 4, 1);

-- Table: AnswerQuestion
DROP TABLE IF EXISTS AnswerQuestion;
CREATE TABLE AnswerQuestion (User INT NOT NULL REFERENCES User (ID), Author INT NOT NULL, Date INT NOT NULL, Position INT NOT NULL, QPos INT NOT NULL, FOREIGN KEY (Author, Date, Position, QPos) REFERENCES ChoiceQuestion (Author, Date, Position, QPos));
INSERT INTO AnswerQuestion (User, Author, Date, Position, QPos) VALUES (3, 2, 1000, 2, 4);
INSERT INTO AnswerQuestion (User, Author, Date, Position, QPos) VALUES (3, 2, 1000, 1, 3);
INSERT INTO AnswerQuestion (User, Author, Date, Position, QPos) VALUES (4, 1, 42, 2, 4);
INSERT INTO AnswerQuestion (User, Author, Date, Position, QPos) VALUES (4, 1, 42, 1, 2);

-- Table: ChoicePoll
DROP TABLE IF EXISTS ChoicePoll;
CREATE TABLE ChoicePoll (Author INT NOT NULL, Date INT NOT NULL, Position INT NOT NULL, Text TEXT NOT NULL, PRIMARY KEY (Author, Date, Position), FOREIGN KEY (Author, Date) REFERENCES Poll (Author, Date));
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 4, 'Carapace verte');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 2, 'Bill Ball');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 1, 'Champignon');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 3, 'Banane');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 6, 'Carapace bleue');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (4, 1, 5, 'Carapace rouge');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (3, 7, 2, 'Non');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (3, 7, 1, 'Oui');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (5, 13, 4, 'Les 3 autres');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (5, 13, 3, 'Bah Samus, oblig�');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (5, 13, 2, 'Samus Zero Suit');
INSERT INTO ChoicePoll (Author, Date, Position, Text) VALUES (5, 13, 1, 'Samus');

-- Table: ChoiceQuestion
DROP TABLE IF EXISTS ChoiceQuestion;
CREATE TABLE ChoiceQuestion (Author INT NOT NULL, Date INT NOT NULL, Position INT NOT NULL, Text TEXT NOT NULL, QPos INT NOT NULL, PRIMARY KEY (Author, Date, Position, QPos), FOREIGN KEY (Author, Date, Position) REFERENCES Question (Author, Date, Position));
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 2, 'Montagne Goron', 1);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 1, 'Gris', 4);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 1, 'Noir', 3);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 1, 'Rouge', 2);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 1, 'Rose', 1);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 2, 'Cimeti�re Cocorico', 4);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 2, 'D�sert Gerudo', 3);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (2, 1000, 2, 'Lac Hylia', 2);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 2, 'La r�ponse D', 4);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 2, 'The Sacred Stones', 3);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 2, 'Blazing Sword', 2);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 2, 'Binding Sword', 1);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 1, 'Awakening', 3);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 1, 'Radiant Dawn', 2);
INSERT INTO ChoiceQuestion (Author, Date, Position, Text, QPos) VALUES (1, 42, 1, 'Path of Radiance', 1);

-- Table: FriendRelation
DROP TABLE IF EXISTS FriendRelation;
CREATE TABLE FriendRelation (Sender INT REFERENCES User (ID) NOT NULL, Receiver INT REFERENCES User (ID) NOT NULL, Status TEXT NOT NULL);
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (2, 3, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (2, 5, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (1, 2, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (4, 1, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (5, 3, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (5, 1, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (3, 1, 'Friend');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (4, 5, 'Pending');
INSERT INTO FriendRelation (Sender, Receiver, Status) VALUES (3, 4, 'Pending');

-- Table: MCQ
DROP TABLE IF EXISTS MCQ;
CREATE TABLE MCQ (Author INT REFERENCES User (ID) NOT NULL, Date INT NOT NULL, Title TEXT NOT NULL, Format TEXT NOT NULL, isClosed BOOLEAN NOT NULL, NbrQ INT NOT NULL, PRIMARY KEY (Author, Date));
INSERT INTO MCQ (Author, Date, Title, Format, isClosed, NbrQ) VALUES (2, 1000, 'Culture TLOZ', 'Texte', 'false', 2);
INSERT INTO MCQ (Author, Date, Title, Format, isClosed, NbrQ) VALUES (1, 42, 'Culture FE', 'Texte', 'false', 2);

-- Table: ParticipationMCQ
DROP TABLE IF EXISTS ParticipationMCQ;
CREATE TABLE ParticipationMCQ (User INT NOT NULL REFERENCES User (ID), Author INT NOT NULL, Date INT NOT NULL, FOREIGN KEY (Author, Date) REFERENCES MCQ (Author, Date));
INSERT INTO ParticipationMCQ (User, Author, Date) VALUES (3, 2, 1000);
INSERT INTO ParticipationMCQ (User, Author, Date) VALUES (4, 1, 42);
INSERT INTO ParticipationMCQ (User, Author, Date) VALUES (2, 1, 42);

-- Table: ParticipationPoll
DROP TABLE IF EXISTS ParticipationPoll;
CREATE TABLE ParticipationPoll (User INT NOT NULL REFERENCES User (ID), Author INT NOT NULL, Date INT NOT NULL, FOREIGN KEY (Author, Date) REFERENCES Poll (Author, Date));
INSERT INTO ParticipationPoll (User, Author, Date) VALUES (3, 4, 1);
INSERT INTO ParticipationPoll (User, Author, Date) VALUES (4, 3, 7);
INSERT INTO ParticipationPoll (User, Author, Date) VALUES (3, 5, 13);
INSERT INTO ParticipationPoll (User, Author, Date) VALUES (1, 5, 13);

-- Table: Poll
DROP TABLE IF EXISTS Poll;
CREATE TABLE Poll (Author INT REFERENCES User (ID) NOT NULL, Date INT NOT NULL, Title TEXT NOT NULL, Question TEXT NOT NULL, Format TEXT NOT NULL, NbrTop INT NOT NULL, NbrChoice INT NOT NULL, isPoll BOOLEAN NOT NULL, isClosed BOOLEAN NOT NULL, PRIMARY KEY (Author, Date));
INSERT INTO Poll (Author, Date, Title, Question, Format, NbrTop, NbrChoice, isPoll, isClosed) VALUES (4, 1, 'Objet Mario Kart', 'Top 3 de vos objets pr�f�r�s?', 'Texte', 3, 6, 'true', 'false');
INSERT INTO Poll (Author, Date, Title, Question, Format, NbrTop, NbrChoice, isPoll, isClosed) VALUES (3, 7, 'La salle', 'Devrais-je rentrer dans cette pi�ce lugubre?', 'Texte', 1, 2, 'false', 'false');
INSERT INTO Poll (Author, Date, Title, Question, Format, NbrTop, NbrChoice, isPoll, isClosed) VALUES (5, 13, 'Perso SSBB', 'Quel ets votre perso de SSBB pr�f�r�?', 'Texte', 1, 4, 'true', 'false');

-- Table: Question
DROP TABLE IF EXISTS Question;
CREATE TABLE Question (Author INT NOT NULL, Date INT NOT NULL, Position INT NOT NULL, Description TEXT NOT NULL, RightAnswer INT NOT NULL, PRIMARY KEY (Author, Date, Position), FOREIGN KEY (Author, Date) REFERENCES MCQ (Author, Date));
INSERT INTO Question (Author, Date, Position, Description, RightAnswer) VALUES (2, 1000, 2, 'O� trouve-t-on le grappin dans OOT?', 4);
INSERT INTO Question (Author, Date, Position, Description, RightAnswer) VALUES (2, 1000, 1, 'Quel rubis n''existe pas?', 1);
INSERT INTO Question (Author, Date, Position, Description, RightAnswer) VALUES (1, 42, 2, 'Quel est le premier Fire Emblem sorti hors du Japon?', 2);
INSERT INTO Question (Author, Date, Position, Description, RightAnswer) VALUES (1, 42, 1, 'Comment s''appelle le seul Fire Emblem sorti sur WII?', 2);

-- Table: User
DROP TABLE IF EXISTS User;
CREATE TABLE User (ID INT NOT NULL UNIQUE PRIMARY KEY, Login TEXT NOT NULL, FirstName TEXT NOT NULL, Surname TEXT NOT NULL, Mail TEXT NOT NULL, Password TEXT NOT NULL, ProfilePicture TEXT, BF INT REFERENCES User (ID));
INSERT INTO User (ID, Login, FirstName, Surname, Mail, Password, ProfilePicture, BF) VALUES (1, 'Radiant', 'Ike', 'Greil', 'IkeRagnell@FE.com', 'Aether', NULL, NULL);
INSERT INTO User (ID, Login, FirstName, Surname, Mail, Password, ProfilePicture, BF) VALUES (2, 'I''mNotZelda', 'Link', 'Heyaaaah', 'LinkOcarina@TLOZ.com', 'Rupee', NULL, 5);
INSERT INTO User (ID, Login, FirstName, Surname, Mail, Password, ProfilePicture, BF) VALUES (3, 'Poltergeist', 'Luigi', 'Bros', 'LuigiMansion@MB.com', 'Daisy', NULL, 4);
INSERT INTO User (ID, Login, FirstName, Surname, Mail, Password, ProfilePicture, BF) VALUES (4, 'ItsMe', 'Mario', 'Bros', 'MarioKart8@MB.com', 'Peach', NULL, 3);
INSERT INTO User (ID, Login, FirstName, Surname, Mail, Password, ProfilePicture, BF) VALUES (5, 'ZeroSuitIsBetter', 'Samus', 'Aran', 'SamusPrime@Metroid.com', 'Ridley', NULL, NULL);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
