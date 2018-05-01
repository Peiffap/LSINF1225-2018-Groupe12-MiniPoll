--
-- File generated with SQLiteStudio v3.1.1 on ven. févr. 2 20:54:25 2018
--
-- Text encoding used: UTF-8
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: owns
DROP TABLE IF EXISTS owns;
CREATE TABLE [owns] ( [u_id] INTEGER NOT NULL , [s_id] INTEGER NOT NULL , [s_rating] float , FOREIGN KEY ( [u_id] ) REFERENCES [users] ( [u_id] ) , FOREIGN KEY ( [s_id] ) REFERENCES [songs] ( [s_id] ) );
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 1, 5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 2, 2);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 3, 5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 4, 1);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 4, 5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 5, NULL);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 5, 3);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 6, 4);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 6, 3);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 7, 2.5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 8, 2);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 9, 3);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 10, NULL);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 11, 4);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 12, 4);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 13, 4);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 14, 2);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 15, 5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (2, 16, 4.5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 17, 3.5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 18, 2);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 19, 1.5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 20, 5);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 21, 0);
INSERT INTO owns (u_id, s_id, s_rating) VALUES (1, 22, NULL);

-- Table: songs
DROP TABLE IF EXISTS songs;
CREATE TABLE [songs] ( [s_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , [s_title] TEXT NOT NULL , [s_artist] TEXT , [s_album] TEXT , [s_filename] TEXT NOT NULL );
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (1, 'Today', '10 Code', 'Transmitter single', '10_Code_-_Today.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (2, 'Ally Calvine Wonderful Day', 'Ally Calvine', 'Royalty free Background Music', 'Ally_Calvine_-_Ally_Calvine_-_Wonderful_Day.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (3, 'Dire News', 'Celestial Aeon Project', 'Aeon', 'Celestial_Aeon_Project_-_Dire_News.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (4, 'As We Await Tomorrow', 'Colin Newcomer', 'Quiescent Contemplation', 'Colin_Newcomer_-_As_We_Await_Tomorrow.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (5, 'Fur Elise Ludwig van Beethoven', 'Colin Newcomer', 'Piano Classics', 'Colin_Newcomer_-_Fur_Elise,_Ludwig_van_Beethoven.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (6, 'L''adieu Waltz Frederic Chopin', 'Colin Newcomer', 'Piano Classics', 'Colin_Newcomer_-__L_adieu__Waltz,_Frederic_Chopin.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (7, 'Nocturn in F Minor Frederic Ch', 'Colin Newcomer', 'Piano Classics', 'Colin_Newcomer_-_Nocturn_in_F_Minor,_Frederic_Chopin.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (8, 'Halloween night', 'Efrem Scacco', 'Haloween night', 'Efrem_Scacco_-_Halloween_night.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (9, 'Conversations', 'Eracilon', 'Claviano', 'Eracilon_-_Conversations.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (10, 'Eloges', 'Eracilon', 'Claviano', 'Eracilon_-_Eloges.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (11, 'Notturno', 'Even death dies', 'Ieri oggi domani mai', 'even_death_dies_-_Notturno.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (12, 'Home Improvement', 'Josh Woodward', 'Here Today', 'Josh_Woodward_-_Home_Improvement.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (13, 'Morning Blue', 'Josh Woodward', 'The Simple Life', 'Josh_Woodward_-_Morning_Blue.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (14, 'Sundown', 'Josh Woodward', 'Crawford Street', 'Josh_Woodward_-_Sundown.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (15, 'Darkness', 'Mister M', 'Intermedium', 'Mister_M_-_Darkness.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (16, 'Closing', 'Peter Rudenko', '15 Etudes', 'Peter_Rudenko_-_Closing.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (17, 'I wanna Be', 'Quentin Hannappe', 'I Wanna Be', 'Quentin_Hannappe_-_I_wanna_Be.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (18, 'THE LIMIT', 'Robert Avellanet', 'Heart Soul', 'Robert_Avellanet_-_The_Limit.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (19, 'Who Cares', 'Sam Brown', '37 Reasons', 'Sam_Brown_-_Who_Cares.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (20, 'Broken Stereo Acoustic Version', 'Sean Fournier', 'Oh My', 'Sean_Fournier_-_Broken_Stereo__Acoustic_Version_.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (21, 'Green Island Intro', 'Terraws', 'Train To Nowhere', 'Terraws_-_Green_Island__Intro_.mp3');
INSERT INTO songs (s_id, s_title, s_artist, s_album, s_filename) VALUES (22, 'Xenia Nen Gotta find a reason', 'Xenia Nen', 'Just The Way I m Feeling Part', 'Xenia_Nen_-_Xenia_Nen_-_Gotta_find_a_reason.mp3');

-- Table: users
DROP TABLE IF EXISTS users;
CREATE TABLE [users] ( [u_id] INTEGER PRIMARY KEY NOT NULL , [u_name] TEXT NOT NULL DEFAULT ( NULL ) , [u_password] TEXT NOT NULL DEFAULT ( NULL ) );
INSERT INTO users (u_id, u_name, u_password) VALUES (1, 'Utilisateur 1', 'password');
INSERT INTO users (u_id, u_name, u_password) VALUES (2, 'Kim Mens', 'lsinf1225');

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
