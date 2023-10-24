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

- Tạo 1 database trong sql server có tên AccountMusicApp

- Trên Android Studio và File > Project Structure > Moudles > Đặt Compile Sdk Version = 34
- Chúc chạy thành công