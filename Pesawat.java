// Kelas Passenger merepresentasikan satu penumpang dalam daftar

class Passenger {

    String name;       // Nama penumpang
    Passenger next;    // Referensi ke penumpang berikutnya

    // Konstruktor untuk inisialisasi penumpang baru
    public Passenger(String name) {
        this.name = name;
        this.next = null;
    }
}

// Kelas Pesawat untuk mengelola daftar penumpang
public class Pesawat {

    private Passenger head; // Penumpang pertama dalam daftar

    // Menambahkan penumpang ke akhir daftar
    public void addPassenger(String name) {
        Passenger newPassenger = new Passenger(name);

        // Jika daftar kosong, tambahkan sebagai penumpang pertama
        if (head == null) {
            head = newPassenger;
            return;
        }

        // Jika tidak kosong, cari penumpang terakhir
        Passenger current = head;
        while (current.next != null) {
            current = current.next;
        }

        // Tambahkan penumpang baru di akhir
        current.next = newPassenger;
    }

    // Menghapus penumpang berdasarkan nama
    public void removePassenger(String name) {
        if (head == null) {
            System.out.println("Daftar penumpang kosong.");
            return;
        }

        // Jika penumpang pertama yang dihapus
        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println(name + " telah dihapus dari daftar.");
            return;
        }

        Passenger current = head;
        Passenger previous = null;

        // Cari penumpang yang sesuai dengan nama
        while (current != null && !current.name.equalsIgnoreCase(name)) {
            previous = current;
            current = current.next;
        }

        // Jika tidak ditemukan
        if (current == null) {
            System.out.println("Penumpang dengan nama " + name + " tidak ditemukan.");
            return;
        }

        // Hapus penumpang dari daftar
        previous.next = current.next;
        System.out.println(name + " telah dihapus dari daftar.");
    }

    // Menampilkan semua penumpang
    public void displayPassengers() {
        if (head == null) {
            System.out.println("Tidak ada penumpang dalam daftar.");
            return;
        }

        System.out.println("Daftar Penumpang:");
        Passenger current = head;
        while (current != null) {
            System.out.println("- " + current.name);
            current = current.next;
        }
    }

    // Main method untuk pengujian
    public static void main(String[] args) {
        Pesawat pesawat = new Pesawat();

        // Tambahkan beberapa penumpang
        pesawat.addPassenger("Reski");
        pesawat.addPassenger("Budi");
        pesawat.addPassenger("Citra");
        pesawat.addPassenger("Dewi");

        // Tampilkan daftar awal
        pesawat.displayPassengers();

        System.out.println("\nMenghapus penumpang 'Budi'...");
        pesawat.removePassenger("Budi");

        // Tampilkan daftar setelah penghapusan
        pesawat.displayPassengers();
    }
}
