ALTER TABLE Songs
    ADD CONSTRAINT fk_artist_song
    FOREIGN KEY (artist_id)
    REFERENCES Artists(artist_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
    
ALTER TABLE Playlists
    ADD CONSTRAINT fk_user
    FOREIGN KEY (user_id)
    REFERENCES Users(user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;

ALTER TABLE Playlist_Songs
    ADD CONSTRAINT fk_playlist
    FOREIGN KEY (playlist_id)
    REFERENCES Playlists(playlist_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,

    ADD CONSTRAINT fk_song
    FOREIGN KEY (song_id)
    REFERENCES Songs(song_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE;
