# TodoApp

TodoApp adalah aplikasi terminal sederhana untuk mengelola daftar tugas (to-do list) yang memungkinkan pengguna untuk menambahkan, menandai selesai, menghapus, dan menyimpan daftar todo secara lokal dalam file biner. Aplikasi ini juga dapat memuat daftar todo sebelumnya saat dijalankan kembali.

## Fitur

- **Menambahkan Todo**: Pengguna dapat menambahkan tugas baru.
- **Menandai Todo sebagai Selesai**: Tugas dapat ditandai sebagai selesai.
- **Menghapus Todo**: Pengguna dapat menghapus tugas dari daftar.
- **Simpan & Muat Otomatis**: Daftar tugas akan disimpan secara otomatis ke dalam file lokal dan dimuat kembali saat aplikasi dijalankan.
- **Antarmuka Terminal**: Semua interaksi dilakukan melalui terminal atau command line interface.

## Teknologi yang Digunakan

- **Java**: Aplikasi ini dibangun menggunakan bahasa pemrograman Java.
- **Serializable Interface**: Untuk menyimpan objek Todo dalam file biner.
- **File I/O**: Menggunakan `ObjectOutputStream` dan `ObjectInputStream` untuk menyimpan dan membaca file.

## Prasyarat

Pastikan Java Development Kit (JDK) sudah terinstall di komputer Anda. Anda bisa memeriksa apakah Java sudah terinstall dengan perintah berikut:

```bash
java -version
