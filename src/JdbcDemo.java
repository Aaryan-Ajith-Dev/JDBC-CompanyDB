//STEP 1. Import required packages

import java.sql.*;
import java.io.*;
import java.util.*;

public class JdbcDemo {

   // Set JDBC driver name and database URL
   static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
   static final String DB_URL = "jdbc:mysql://localhost:3306/spotify?useSSL=false";

   // Database credentials
   static final String USER = "root";// add your user
   static final String PASSWORD = "sqlis#BA3";// add password

   public static void executeFile(Statement stmt, String filePath) throws IOException, SQLException {
      BufferedReader br = new BufferedReader(new FileReader(filePath));
      String line;
      String queryLine = "";
      while((line = br.readLine()) != null) {
         if (line.trim().startsWith("--"))
            continue;
         queryLine = queryLine + line + " ";
         if(line.trim().endsWith(";")) {
            stmt.execute(queryLine.trim());
            queryLine = "";
         }
      }
      br.close();
   }


   public static void main(String[] args) {
      Connection conn = null;
      Statement stmt = null;
      Scanner scanner = new Scanner(System.in);

      // STEP 2. Connecting to the Database
      try {
         // STEP 2a: Register JDBC driver
         Class.forName(JDBC_DRIVER);
         // STEP 2b: Open a connection
         System.out.println("Connecting to database...");
         conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
         // STEP 2c: Execute a query
         System.out.println("Creating statement...");
         stmt = conn.createStatement();

         
         // INSERT, UPDATE, DELETE

         // creating tables.
         DatabaseMetaData dbm = conn.getMetaData();
         ResultSet tables = dbm.getTables("spotify", null, null, null);
         if (!tables.next()) {
            executeFile(stmt, "./sql/create.sql");
            executeFile(stmt, "./sql/alter.sql");
            executeFile(stmt, "./sql/insert.sql");
            executeFile(stmt, "./sql/update.sql");
         }

         
         // STEP 3: Query to database
         conn.setAutoCommit(false);
         boolean flag = true;
         // int userId;
         // System.out.println("1. Login");
         // System.out.println("2. Signup");
         // while (flag) {
         //    System.out.print("Enter choice: ");
         //    int choice = scanner.nextInt();
         //    if (choice == 1) {
         //       System.out.println("Login: ");
         //       System.out.print("Enter username: ");
         //       String userName = scanner.next();
         //       System.out.print("Enter password: ");
         //       String passoword = scanner.next();
         //       ResultSet rs = stmt.executeQuery(String.format("select * from Users where user_name = '%s' and user_password = '%s'", userName, passoword));
         //       if (!rs.next()) {
         //          System.out.println("Please enter valid credentials.");
         //       }
         //       else {
         //          userId = rs.getInt("user_id");
         //          System.out.println("Welcome " + userName );
         //          break;
         //       }
         //    }
         //    else if (choice == 2) {
         //       System.out.println("Signup: ");
         //       System.out.print("Enter username: ");
         //       String userName = scanner.next();
         //       System.out.print("Enter email: ");
         //       String userEmail = scanner.next();
         //       System.out.print("Enter password: ");
         //       String passoword = scanner.next();
         //       stmt.executeUpdate(String.format("update Users set user_name = '%s', set email = '%s', set user_password = '' ", userName, userEmail, passoword))
         //    }
         // }
         
         // flag = true;
         
         while (flag) {
            System.out.println("\n******************MENU******************");
            // System.out.println("********************************");
            System.out.println("1.  Show all users");
            System.out.println("2.  Show all playlists");
            System.out.println("3.  Search songs by name");
            System.out.println("4.  Search all artists");
            System.out.println("5.  Get Artist of a song");
            System.out.println("6.  Get all public playlists");
            System.out.println("7.  Create playlist");
            System.out.println("8.  Update Playlist");
            System.out.println("9.  Delete Playlist");
            System.out.println("10. Get all playlists a song belongs to");
            System.out.println("11. Add songs to playlist");
            System.out.println("12. Delete songs from playlist");
            System.out.println("13. Get details of a user");
            System.out.println("14. Filter songs by genre");
            System.out.println("15. Get ids and names of all songs");
            System.out.println("16. Get all songs in playlists");
            System.out.println("17. Show all songs by an artist");
            System.out.println("18. Songs that are there in most playlists");
            System.out.println("19. Artists that have the most number of songs");
            System.out.println("20. Close");
            System.out.println();
            // Get songs in playlists
            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // taking the '\n'
            ResultSet rs;
            switch (choice) {
               case 1:
                  rs = stmt.executeQuery("select user_id, user_name from Users");
                  while (rs.next()) {
                     // // Retrieve by column name
                     int user_id = rs.getInt("user_id");
                     String user_name = rs.getString("user_name");
         
                     // // Display values
                     System.out.print("user id: " + user_id);
                     System.out.println(", user name: " + user_name);
                  }
                  break;
               case 2:
                  rs = stmt.executeQuery("select playlist_id, playlist_name, user_id from Playlists");
                  while (rs.next()) {
                     // // Retrieve by column name
                     int playlist_id = rs.getInt("playlist_id");
                     String playlist_name = rs.getString("playlist_name");
                     int user_id = rs.getInt("user_id");
         
                     // // Display values
                     System.out.print("playlist id: " + playlist_id);
                     System.out.print(", user id: " + user_id);
                     System.out.println(", playlist name: " + playlist_name);
                  }
                  break;
               case 3:
                  System.out.print("Enter name of the song: ");
                  String song_name = scanner.nextLine();
                  // System.out.println("select song_id, song_name, Songs.genre from Songs where song_name = '"+ song_name + "'");
                  rs = stmt.executeQuery("select song_id, song_name, Songs.genre from Songs where song_name = '"+ song_name + "'");
                  while (rs.next()) {
                     // // Retrieve by column name
                     int song_id = rs.getInt("song_id");
                     song_name = rs.getString("song_name");
                     String genre = rs.getString("Songs.genre");
         
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.print(", song name: " + song_name);
                     System.out.println(", genre: " + genre);
                  }
                  break;
               case 4:
                  rs = stmt.executeQuery("select artist_id, artist_name, biography from Artists");
                  while (rs.next()) {
                     // // Retrieve by column name
                     int artist_id = rs.getInt("artist_id");
                     String artist_name = rs.getString("artist_name");
                     String bio = rs.getString("biography");
         
                     // // Display values
                     System.out.print("artist id: " + artist_id);
                     System.out.print(", artist name: " + artist_name);
                     System.out.println(", artist bio: " + bio);
                  }
                  break;
               case 5:
                  System.out.print("Enter song id: ");
                  int song_id = scanner.nextInt();
                  rs = stmt.executeQuery("select artist_name from Songs inner join Artists on Songs.artist_id = Artists.artist_id and song_id = '" + song_id + "'");
                  if (rs.next())
                     System.out.println("Artist name: " + rs.getString("artist_name"));   
                  break;
               case 6:
                  rs = stmt.executeQuery("select playlist_id, playlist_name from Playlists where is_public = TRUE");
                  while (rs.next()) {
                     // // Retrieve by column name
                     int playlist_id = rs.getInt("playlist_id");
                     String playlist_name = rs.getString("playlist_name");
         
                     // // Display values
                     System.out.print("playlist id: " + playlist_id);
                     System.out.println(", playlist name: " + playlist_name);
                  }
                  break;
               case 7:
                  System.out.print("Enter playlist name: ");
                  String playlist_name = scanner.nextLine();
                  System.out.print("Enter user id: ");
                  int user_id = scanner.nextInt();
                  System.out.print("Should it be public? (True / False) ");
                  boolean is_public = scanner.nextBoolean();
                  // delete record in transaction
                  int status = 0;
                  int status1 = 0;
                  status = stmt.executeUpdate(String.format("insert into Playlists (playlist_name, user_id, is_public) values ('%s', %d, %b)",playlist_name, user_id, is_public));
                  if (status > 0)
                     status1 = stmt.executeUpdate("update Users set playlist_count = playlist_count + 1 where user_id = " + user_id); 
                  System.out.println(status > 0 && status1 > 0 ? "Successfully added" : "Failed to add");
                  break;
               case 8:
                  System.out.print("Enter playlist id to be updated: ");
                  int playlist_id = scanner.nextInt();
                  System.out.print("Enter playlist name: ");
                  scanner.nextLine();
                  playlist_name = scanner.nextLine();
                  System.out.print("Enter user id: ");
                  user_id = scanner.nextInt();
                  System.out.print("Should it be public? (True / False) ");
                  is_public = scanner.nextBoolean();
                  status = stmt.executeUpdate(String.format("update Playlists set playlist_name = '%s', user_id = %d, is_public = %b where playlist_id = %d",playlist_name, user_id, is_public, playlist_id));
                  System.out.println(status > 0 ? "Successfully updated playlist with id: " + playlist_id : "Record not found");
                  break;
               case 9:
                  System.out.print("Enter playlist id to be deleted: ");
                  playlist_id = scanner.nextInt();
                  rs = stmt.executeQuery("select user_id from Playlists where playlist_id = " + playlist_id);
                  status1 = 0;
                  status = 0;
                  if (rs.next()) {
                        user_id = rs.getInt("user_id");
                     status = stmt.executeUpdate(String.format("delete from Playlists where playlist_id = %d", playlist_id));
                     if (status > 0)
                        status1 = stmt.executeUpdate("update Users set playlist_count = playlist_count - 1 where user_id = " + user_id);
                  }
                  System.out.println(status > 0 && status1 > 0 ? "Successfully deleted playlist with id: " + playlist_id : "Record not found");
                  break;
               case 10:
                  System.out.print("Enter song id: ");
                  song_id = scanner.nextInt();
                  rs = stmt.executeQuery(String.format("select Playlists.playlist_id, playlist_name, Songs.song_id from Songs inner join Playlist_Songs on Songs.song_id = Playlist_Songs.song_id inner join Playlists on Playlist_Songs.playlist_id = Playlists.playlist_id where Playlist_Songs.song_id = %d;", song_id));
                  while (rs.next()) {
                     // // Retrieve by column name
                     playlist_id = rs.getInt("playlist_id");
                     playlist_name = rs.getString("playlist_name");
                     song_id = rs.getInt("song_id");
         
                     // // Display values
                     System.out.print("playlist id: " + playlist_id);
                     System.out.print(", playlist name: " + playlist_name);
                     System.out.println(", song id: " + song_id);
                  }
                  break;
               case 11:
                  System.out.print("Enter Song ID: ");
                  song_id = scanner.nextInt();
                  System.out.print("Enter Playlist ID: ");
                  playlist_id = scanner.nextInt();
                  status = stmt.executeUpdate(String.format("insert into Playlist_Songs values (%d, %d)", playlist_id, song_id));
                  System.out.println(status > 0 ? "Inserted Successfully" : "Failed to Add");
                  break;
               case 12:
                  System.out.print("Enter Song ID: ");
                  song_id = scanner.nextInt();
                  System.out.print("Enter Playlist ID: ");
                  playlist_id = scanner.nextInt();
                  status = stmt.executeUpdate(String.format("delete from Playlist_Songs where playlist_id = %d and song_id = %d", playlist_id, song_id));
                  System.out.println(status > 0 ? "Deleted Successfully" : "Record not found");
                  break;
               case 13:
                  System.out.print("Enter User ID: ");
                  user_id = scanner.nextInt();
                  rs = stmt.executeQuery("select user_name, email, registration_date, playlist_count from Users where user_id = " + user_id);
                  
                  if (rs.next()) {

                     String user_name = rs.getString("user_name");
                     String email = rs.getString("email");
                     int playlist_count = rs.getInt("playlist_count");
         
                     // // Display values
                     System.out.print("user id: " + user_id);
                     System.out.print(", user name: " + user_name);
                     System.out.print(", email: " + email);
                     System.out.print(", number of playlists " + playlist_count);
                     System.out.println(", registration date: " + rs.getDate("registration_date"));
                  }
                  break;
               case 14:
                  System.out.print("Enter genre: ");
                  String genre = scanner.nextLine();
                  rs = stmt.executeQuery("select song_id, song_name, artist_name, Songs.genre from Songs inner join Artists on Songs.artist_id = Artists.artist_id where Songs.genre = '" + genre + "'");
                  while (rs.next()) {
                     // // Retrieve by column name
                     song_id = rs.getInt("song_id");
                     song_name = rs.getString("song_name");
                     String artist_name = rs.getString("artist_name");
                     genre = rs.getString("genre");
         
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.print(", song name: " + song_name);
                     System.out.println(", artist name: " + artist_name);
                     System.out.println("genre: " + genre);
                  }
                  break;
               case 15:
                  rs = stmt.executeQuery("select song_id, song_name from Songs");
                  while (rs.next()) {
                     // // Retrieve by column name
                     song_id = rs.getInt("song_id");
                     song_name = rs.getString("song_name");
         
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.println(", song name: " + song_name);
                  }
                  break;
               case 16:
                  System.out.print("Enter playlist id: ");
                  playlist_id = scanner.nextInt();
                  rs = stmt.executeQuery("select S.song_id, S.song_name, P.playlist_id, P.playlist_name from Playlist_Songs as PS inner join Playlists as P on PS.playlist_id = P.playlist_id inner join Songs as S on PS.song_id = S.song_id where P.playlist_id = " + playlist_id);
                  while (rs.next()) {
                     // // Retrieve by column name
                     song_id = rs.getInt("song_id");
                     song_name = rs.getString("song_name");
                     playlist_id = rs.getInt("playlist_id");
                     playlist_name = rs.getString("playlist_name");
         
                     // // Display values
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.print(", song name: " + song_name);
                     System.out.print(", playlist id: " + playlist_id);
                     System.out.println(", playlist name: " + playlist_name);
                  }
                  break;
               case 17:
                  System.out.print("Enter artist id: ");
                  int artist_id = scanner.nextInt();
                  rs = stmt.executeQuery("select song_id, song_name, Songs.artist_id, artist_name from Songs inner join Artists on Songs.artist_id = Artists.artist_id where Artists.artist_id = " + artist_id);
                  while (rs.next()) {
                     // // Retrieve by column name
                     song_id = rs.getInt("song_id");
                     song_name = rs.getString("song_name");
                     artist_id = rs.getInt("artist_id");
                     String artist_name = rs.getString("artist_name");
         
                     // // Display values
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.print(", song name: " + song_name);
                     System.out.print(", artist id: " + artist_id);
                     System.out.println(", artist name: " + artist_name);
                  }
                  break;
               case 18:

                  rs = stmt.executeQuery("select PS.song_id, song_name,count(S.song_id) from Playlist_Songs as PS inner join Songs as S on PS.song_id = S.song_id group by S.song_id order by count(S.song_id) desc");
                  while (rs.next()) {
                     // // Retrieve by column name
                     song_id = rs.getInt("PS.song_id");
                     song_name = rs.getString("song_name");
                     int playlistCount = rs.getInt("count(S.song_id)");
         
                     // // Display values
                     // // Display values
                     System.out.print("song id: " + song_id);
                     System.out.print(", song name: " + song_name);
                     System.out.println(", playlist count: " + playlistCount);
                  }
                  break;
               case 19:
                  rs = stmt.executeQuery("select A.artist_id, A.artist_name, count(A.artist_id) from Songs as S inner join Artists as A on A.artist_id = S.artist_id group by A.artist_id order by count(A.artist_id)");
                  while (rs.next()) {
                     // // Retrieve by column name
                     artist_id = rs.getInt("A.artist_id");
                     String artist_name = rs.getString("A.artist_name");
                     int artistCount = rs.getInt("count(A.artist_id)");
                     
                     // // Display values
                     // // Display values
                     System.out.print("artist id: " + artist_id);
                     System.out.print(", artist name: " + artist_name);
                     System.out.println(", artist count: " + artistCount);
                  }
                  break;
               default:
                  flag = false;
                  break;
            }  

            conn.commit();
         }


         // STEP 5: Clean-up environment
         scanner.close();
         stmt.close();
         conn.close();
      } catch (SQLException se) { // Handle errors for JDBC
         se.printStackTrace();
         System.out.println("Rolling back data here....");
         try{
            if(conn!=null)
               conn.rollback();
         }catch(SQLException se2){
            System.out.println("Rollback failed....");
               se2.printStackTrace();
         }
      } catch (Exception e) { // Handle errors for Class.forName
         e.printStackTrace();
      } finally { // finally block used to close resources regardless of whether an exception was
                  // thrown or not
         try {
            if (stmt != null)
               stmt.close();
         } catch (SQLException se2) {
         }
         try {
            if (conn != null)
               conn.close();
         } catch (SQLException se) {
            se.printStackTrace();
         } // end finally try
      } // end try
      System.out.println("End of Code");
   } // end main
} // end class

// Note : By default autocommit is on. you can set to false using
// con.setAutoCommit(false)