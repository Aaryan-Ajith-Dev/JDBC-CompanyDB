UPDATE Songs
SET artist_id = 1
WHERE genre = "rock";

UPDATE Songs
SET artist_id = 2
WHERE genre = "pop";

UPDATE Songs
SET artist_id = 3
WHERE genre = "classical";  

UPDATE Playlists, Users
SET Playlists.user_id = playlist_id
WHERE Playlists.playlist_id = Users.user_id;


