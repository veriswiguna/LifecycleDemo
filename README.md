# Tugas 1 - Merancang Antarmuka (UI) Dasar Aplikasi Mobile

Aplikasi **Kartu Profil & Detail Portofolio** berbasis Android yang dikembangkan menggunakan Kotlin dan XML di Android Studio.

## 📱 Fitur & Layar Aplikasi
1. **Layar 1 (MainActivity)**: Kartu Profil Modern (Cyber/Dark Theme) berisi Foto Profil, Nama, Bio, Form Input (Nama & Email), dan Tombol Aksi.
2. **Layar 2 (SecondActivity)**: Detail Portofolio & Skill Set Mahasiswa.

## 🛠️ Komponen UI Dasar yang Digunakan
- **ViewGroup**: LinearLayout (Vertical & Horizontal), ScrollView
- **View Widgets**: TextView, EditText, ImageView, Button
- **Resource Management**: strings.xml, colors.xml

## 🚀 Cara Menjalankan Aplikasi
1. Build & Install ke HP via Terminal:
   ./gradlew installDebug
2. Buka Layar Kedua via ADB:
   adb shell am start -n com.example.lifecycledemo/.SecondActivity

## 📸 Tangkapan Layar (Screenshot)
- Layar 1 (Profil): screen1.png
- Layar 2 (Portofolio): screen2.png
