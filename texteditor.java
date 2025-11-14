
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class texteditor {

    // Stack untuk menyimpan riwayat keadaan teks sebelumnya (Undo Stack)
    private Deque<String> undoStack = new LinkedList<>();

    // Stack untuk menyimpan riwayat keadaan teks yang dibatalkan (Redo Stack)
    private Deque<String> redoStack = new LinkedList<>();

    // Variabel yang menyimpan keadaan teks editor saat ini
    private String currentText = "";

    /**
     * Konstruktor: Menyimpan keadaan awal teks ke dalam undoStack
     */
    public texteditor() {
        // Simpan keadaan awal (string kosong) agar undo bisa dilakukan dari awal
        undoStack.push(currentText);
    }

    /**
     * 1. Menambahkan teks baru ke dalam editor (Write).
     *
     * @param newText Teks yang akan ditambahkan.
     */
    public void write(String newText) {
        // 1. Simpan keadaan teks saat ini ke Undo Stack sebelum diubah
        undoStack.push(currentText);

        // 2. Ubah keadaan teks saat ini
        currentText += newText;

        // 3. Hapus semua riwayat di Redo Stack (karena ada perubahan baru, riwayat redo hangus)
        redoStack.clear();

        System.out.println("\n[INFO] Teks berhasil ditambahkan.");
    }

    /**
     * 2. Menampilkan isi teks editor saat ini (Show).
     */
    public void show() {
        if (currentText.isEmpty()) {
            System.out.println("\n[SHOW] Editor kosong.");
        } else {
            System.out.println("\n=== ISI EDITOR SAAT INI ===");
            System.out.println(currentText);
            System.out.println("=========================");
        }
        System.out.println("Status Undo: " + (undoStack.size() - 1) + " langkah | Status Redo: " + redoStack.size() + " langkah.");
    }

    /**
     * 3. Mengembalikan isi teks ke keadaan sebelumnya (Undo).
     */
    public void undo() {
        if (undoStack.size() > 1) { // Harus ada setidaknya 1 state tersisa (keadaan awal)

            // 1. Simpan keadaan teks saat ini ke Redo Stack
            redoStack.push(currentText);

            // 2. Pop (ambil dan hapus) versi teks sebelumnya dari Undo Stack
            // (Kita Pop 2 kali karena versi teks currentText yang tersimpan di undoStack
            //  adalah versi sebelum perubahan terakhir)
            undoStack.pop();

            // 3. Tetapkan versi sebelumnya sebagai keadaan saat ini
            currentText = undoStack.peek();

            System.out.println("\n[UNDO] Berhasil mengembalikan ke keadaan sebelumnya.");
        } else {
            System.out.println("\n[UNDO] Tidak ada lagi aksi untuk dibatalkan. Sudah di keadaan awal.");
        }
    }

    /**
     * 4. Memulihkan pengembalian isi teks (Redo).
     */
    public void redo() {
        if (!redoStack.isEmpty()) {
            // 1. Simpan keadaan teks saat ini ke Undo Stack (untuk berjaga-jaga jika di-undo lagi)
            undoStack.push(currentText);

            // 2. Pop versi yang dibatalkan (yang lebih baru) dari Redo Stack
            String nextState = redoStack.pop();

            // 3. Tetapkan versi lebih baru sebagai keadaan saat ini
            currentText = nextState;

            System.out.println("\n[REDO] Berhasil memulihkan aksi.");
        } else {
            System.out.println("\n[REDO] Tidak ada aksi untuk dipulihkan.");
        }
    }

    // =========================================================================
    // MAIN PROGRAM & INTERAKSI PENGGUNA
    // =========================================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        texteditor editor = new texteditor();
        boolean running = true;

        System.out.println("=============================================");
        System.out.println(" SIMULASI TEXT EDITOR DENGAN UNDO/REDO (Java)");
        System.out.println("=============================================");

        while (running) {
            System.out.println("\n[Pilih Aksi]:");
            System.out.println("1. Tulis (write)");
            System.out.println("2. Tampilkan (show)");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. Keluar (exit)");
            System.out.print("Input: ");

            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":
                case "write":
                    System.out.print("Masukkan teks yang akan ditambahkan: ");
                    String text = scanner.nextLine();
                    editor.write(text);
                    break;
                case "2":
                case "show":
                    editor.show();
                    break;
                case "3":
                case "undo":
                    editor.undo();
                    break;
                case "4":
                case "redo":
                    editor.redo();
                    break;
                case "5":
                case "exit":
                    running = false;
                    System.out.println("\nProgram editor ditutup. Sampai jumpa!");
                    break;
                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }
}
