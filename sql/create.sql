CREATE TABLE Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    user_name VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(20) NOT NULL,
    user_password VARCHAR(20) NOT NULL,
    playlist_count INT NOT NULL DEFAULT 0,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Artists (
    artist_id INT AUTO_INCREMENT PRIMARY KEY,
    artist_name VARCHAR(20) NOT NULL,
    genre VARCHAR(20),
    biography TEXT
);

-- CREATE TABLE Albums (
--     album_id INT AUTO_INCREMENT PRIMARY KEY,
--     album_name VARCHAR(20) NOT NULL,
--     artist_id INT,
--     release_date DATE,
--     genre VARCHAR(20)
-- );

CREATE TABLE Songs (
    song_id INT AUTO_INCREMENT PRIMARY KEY,
    song_name VARCHAR(20) NOT NULL,
    artist_id INT,
    -- album_id INT,
    genre VARCHAR(20)
);

CREATE TABLE Playlists (
    playlist_id INT AUTO_INCREMENT PRIMARY KEY,
    playlist_name VARCHAR(20) NOT NULL,
    user_id INT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    is_public BOOLEAN DEFAULT TRUE
);

CREATE TABLE Playlist_Songs (
    playlist_id INT,
    song_id INT,
    PRIMARY KEY (playlist_id, song_id)
);
