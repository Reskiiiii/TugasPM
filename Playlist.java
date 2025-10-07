// Kelas Song merepresentasikan satu lagu dalam playlist

class Song {

    String title;  // Judul lagu
    Song next;     // Referensi ke lagu berikutnya

    // Konstruktor untuk inisialisasi lagu
    public Song(String title) {
        this.title = title;
        this.next = null;
    }
}

// Kelas Playlist untuk mengelola daftar lagu
public class Playlist {

    private Song head; // Lagu pertama dalam playlist

    // Menambahkan lagu ke akhir playlist
    public void addSong(String title) {
        Song newSong = new Song(title);

        // Jika playlist kosong, lagu baru jadi lagu pertama
        if (head == null) {
            head = newSong;
            return;
        }

        // Jika tidak kosong, cari lagu terakhir
        Song current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Tambahkan lagu baru di akhir
        current.next = newSong;
    }

    // Menampilkan semua lagu dalam playlist
    public void displayPlaylist() {
        if (head == null) {
            System.out.println("Playlist kosong.");
            return;
        }

        System.out.println("Daftar Lagu dalam Playlist:");
        Song current = head;
        while (current != null) {
            System.out.println("- " + current.title);
            current = current.next;
        }
    }

    // Main method untuk pengujian
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        playlist.addSong("Tarot - Feast");
        playlist.addSong("Bintang Di Surga - Noah");
        playlist.addSong("Komang - Raim Laode");

        playlist.displayPlaylist();
    }
}
