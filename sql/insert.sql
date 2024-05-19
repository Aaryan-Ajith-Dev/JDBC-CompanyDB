insert into Users (user_name, email, user_password, playlist_count) values
    ("user1","user1@gmail.com","pass1", 1),
    ("user2","user2@gmail.com","pass2", 1),
    ("user3","user3@gmail.com","pass3", 1);

insert into Artists (artist_name, genre, biography) values
    ("artist1", "rock", "biography1"),
    ("artist2", "pop", "biography2"),
    ("artist3", "classical", "biography3");

insert into Playlists (playlist_name, is_public) values
    ("playlist1", TRUE),
    ("playlist2", FALSE),
    ("playlist3", TRUE);

insert into Songs (song_name, genre) values
    ("song1", "rock"),
    ("song2", "rock"),
    ("song3", "classical"),
    ("song4", "classical"),
    ("song5", "pop"),
    ("song6", "pop");

insert into Playlist_Songs (playlist_id, song_id) values
    (1, 1),
    (1, 2),
    (1, 3),
    (1, 4),
    (2, 3),
    (2, 4),
    (2, 5),
    (3, 6);

