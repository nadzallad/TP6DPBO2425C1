//Janji

Saya Nadzalla Diva Asmara Sutedja dengan Nim 2408095 mengerjakan TP6 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak akan melakukan kecurangan seperti yang telah di spesifikasikan

//Desain Program
Membuat permainan Flappy Bird menggunakan bahasa pemrograman Java dengan pustaka swing sebagai GUI. Tujuan dari permainan ini adalah mengendalikan seekor burung agar dapat terbang melewati celah-celah antara dua pipa tanpa menabrak atau jatuh ke tanah. Bagian utama dari permainan dikendalikan oleh kelas Logic, yang berfungsi sebagai pusat pengatur jalannya game. Kelas ini bertanggung jawab terhadap semua proses logika, seperti pergerakan burung, pengaturan gravitasi, pergerakan pipa, deteksi tabrakan, dan sistem skor. Permainan dijalankan secara terus-menerus menggunakan timer dengan kecepatan sekitar 60 frame per detik (FPS). Setiap frame, posisi burung akan berubah akibat gaya gravitasi. Pemain dapat menekan tombol spasi untuk membuat burung terbang ke atas, sementara pipa bergerak dari kanan ke kiri. Setiap kali burung berhasil melewati satu set pipa, skor akan bertambah satu poin.

Untuk mengulang permainan (Restart), pemain dapat menekan tombol R. Saat tombol ditekan, restartGame() pada kelas Logic akan dijalankan. Berfungsi untuk menghapus seluruh objek pipa yang tersisa, mengembalikan posisi burung ke posisi awal, mengatur ulang skor menjadi nol, dan mengaktifkan kembali timer agar permainan dimulai dari awal.  

Tampilan visual permainan diatur oleh kelas View. Kelas ini menggambar seluruh elemen di layar yaitu latar belakang, burung, pipa, skor, dan pesan Game Over. Penggambaran dilakukan melalui metode paintComponent(Graphics g) yang dipanggil secara otomatis setiap kali layar diperbarui (repaint()). Metode ini juga memastikan tampilan tidak menumpuk dengan selalu memanggil super.paintComponent(g) di awal.

Untuk bonus dibuat menu di awal permainan. Menu ini dibuat menggunakan kelas MainMenu, yang menampilkan judul permainan dan tombol Start Game dan tombol Exit. Ketika tombol  Start Game ditekan , menu akan tertutup dan permainan dimulai melalui pembuatan objek Logic dan View baru. Jika Tombol Exit ditekan maka akan keluar dari permainan.

//Alur Program  
1. Program dimulai dengan menampilkan menu utama yang dibuat menggunakan kelas MainMenu. Terdapat dua tombol yaitu Play Game dan Exit. Tombol Play Game berfungsi untuk memulai permainan, sedangkan tombol Exit digunakan untuk keluar permainan. Ketika pemain menekan tombol Play Game, jendela menu utama akan ditutup dengan perintah dispose(), lalu program menjalankan metode openGame() untuk membuka jendela permainan utama.
2. Setelah menu utama ditutup, jendela baru dibuka yang berisi permainan inti. Di tahap ini, kelas Logic dan View saling terhubung untuk mengatur jalannya permainan dan menampilkan elemen grafis di layar. Latar belakang game digambar ulang, burung pemain ditampilkan di tengah layar, dan pipa-pipa akan muncul dari sisi kanan. Program menggunakan dua buah timer untuk mengatur pergerakan objek. Timer pertama (gameLoop) berjalan sebanyak 60 kali per detik, berfungsi untuk memperbarui posisi burung dan pipa secara terus-menerus. Timer kedua (pipesCooldown) aktif setiap 1,5 detik untuk menambahkan sepasang pipa baru ke layar. Burung akan jatuh karena pengaruh gravitasi, dan pemain harus menekan tombol spasi (space) untuk membuat burung terbang ke atas. Setiap kali burung berhasil melewati pipa tanpa menabrak, skor pemain akan bertambah satu poin.
3. Selama permainan berlangsung, program selalu memeriksa apakah burung menyentuh pipa atau jatuh ke tanah. Jika salah satu kondisi tersebut terjadi, maka permainan dinyatakan Game Over dan akan muncul teks “GAME OVER” berwarna merah di tengah layar. Di bawahnya juga muncul instruksi “Press R to Restart” sebagai petunjuk untuk mengulang permainan bagi pemain. Ketika pemain menekan tombol R, metode restartGame() akan dijalankan.

//Dokumentasi 

Dokumentasi skor akan bertambah jika pipa terlewati dan akan game over jika burung menabrak pipa atau burung terjatuh

https://github.com/user-attachments/assets/f2452af9-7ec2-4c25-a565-b5f573dc64bd

Dokumentasi tampilan menu untuk masuk dan keluar permainan Flappy Bird d

https://github.com/user-attachments/assets/36bdfd6a-b539-4f21-a3c0-de0d731efbbe

