# Music-App
- Thêm vào dependencies trong file build.gradle.kts (Module :app) như sau
  dependencies {
  . . .
  implementation(files("C:\\...\\...\\AndroidStudioProjects\\MusicApp\\app\\libs\\jtds-1.3.1.jar"))
  . . .
  }
  rồi bấm Sync Now


- Trong file ConClass.java chỉnh biến ip,port thành địa chỉ IP và cổng máy đang dùng:
  String ip = "192.168.0.114", port = "1433", db = "AccountMusicApp", username = "sa", password = "123";


- Tạo 1 database trong sql có tên AccountMusicApp
- Tạo các bảng trong sql server:
  CREATE TABLE ACCOUNT(USERNAME VARCHAR(255),PASSWORD VARCHAR(255),PRIMARY KEY(USERNAME));
  GO
  CREATE TABLE MUSIC(MUSIC_ID VARCHAR(255),LIKE_COUNT INT,PRIMARY KEY(MUSIC_ID));
  GO
  CREATE TABLE FAVOURITE(USERNAME VARCHAR(255),MUSIC_ID VARCHAR(255), FOREIGN KEY(USERNAME) REFERENCES ACCOUNT(USERNAME), FOREIGN KEY(MUSIC_ID) REFERENCES MUSIC(MUSIC_ID));
  GO
  CREATE TABLE HISTORY(USERNAME VARCHAR(255),MUSIC_ID VARCHAR(255),PLAY_HISTORY DATETIME, FOREIGN KEY(USERNAME) REFERENCES ACCOUNT(USERNAME), FOREIGN KEY(MUSIC_ID) REFERENCES MUSIC(MUSIC_ID));
- Trên Android Studio và File > Project Structure > Moudles > Đặt Compile Sdk Version = 34
- Mong là chạy được =))