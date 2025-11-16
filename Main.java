
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main { // Nama class diubah menjadi Main

    // Menggunakan Stack untuk menyimpan history
    private Stack<String> historyStack;

    public Main() {
        this.historyStack = new Stack<>();
    }

    /**
     * Fungsi untuk menambahkan website baru ke dalam daftar history. Mirip
     * dengan operasi 'push' pada Stack.
     *
     * @param url Website URL yang akan ditambahkan.
     */
    public void browse(String url) {
        if (url != null && !url.trim().isEmpty()) {
            historyStack.push(url);
            System.out.println("✅ Berhasil menambahkan: " + url);
        } else {
            System.out.println("❌ URL tidak boleh kosong.");
        }
    }

    /**
     * Fungsi untuk kembali ke website sebelumnya dan menghapus history
     * terakhir. Mirip dengan operasi 'pop' pada Stack.
     */
    public void back() {
        if (historyStack.isEmpty()) {
            System.out.println("⚠️ History kosong. Tidak bisa kembali.");
            return;
        }

        // URL terakhir dihapus (pop)
        String lastUrl = historyStack.pop();
        System.out.println("⬅️ Kembali dari: " + lastUrl);

        if (historyStack.isEmpty()) {
            System.out.println("Anda sekarang berada di halaman awal (History kosong).");
        } else {
            // URL saat ini adalah elemen di puncak Stack setelah pop
            String previousUrl = historyStack.peek();
            System.out.println("➡️ Anda sekarang berada di: " + previousUrl);
        }
    }

    /**
     * Fungsi untuk menampilkan semua history browser yang diurutkan dari yang
     * paling baru.
     */
    public void view() {
        if (historyStack.isEmpty()) {
            System.out.println("📜 History browser saat ini kosong.");
            return;
        }

        System.out.println("\n--- 🌐 HISTORY BROWSER (Terbaru ke Terlama) ---");

        // Membuat salinan sementara untuk ditampilkan agar Stack asli tidak berubah
        Stack<String> tempStack = (Stack<String>) historyStack.clone();

        // Membalik urutan elemen dalam Stack sementara
        // Ini memastikan elemen terbaru (yang berada di puncak) ditampilkan di awal daftar.
        Collections.reverse(tempStack);

        int index = tempStack.size();

        for (String url : tempStack) {
            String indicator = (index == historyStack.size()) ? " [CURRENT]" : "";
            System.out.println(index + ". " + url + indicator);
            index--;
        }

        System.out.println("----------------------------------------------\n");
    }

    // --- MAIN METHOD UNTUK SIMULASI ---
    public static void main(String[] args) {
        // Objek sekarang dibuat dari class Main
        Main browser = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Simulasi History Browser (Java) ===");
        System.out.println("Pilihan Perintah:");
        System.out.println("  1. browse [url]  : Tambah URL baru (Contoh: browse google.com)");
        System.out.println("  2. back          : Kembali ke URL sebelumnya (Pop history terakhir)");
        System.out.println("  3. view          : Tampilkan semua history (Terbaru ke Terlama)");
        System.out.println("  4. exit          : Keluar dari program");
        System.out.println("---------------------------------------\n");

        while (true) {
            System.out.print("Masukkan Perintah > ");
            String inputLine = scanner.nextLine().trim();

            if (inputLine.equalsIgnoreCase("exit")) {
                System.out.println("👋 Program selesai. Sampai jumpa!");
                break;
            }

            if (inputLine.startsWith("browse")) {
                String url = inputLine.substring(7).trim();
                browser.browse(url);
            } else if (inputLine.equalsIgnoreCase("back")) {
                browser.back();
            } else if (inputLine.equalsIgnoreCase("view")) {
                browser.view();
            } else {
                System.out.println("Perintah tidak valid. Silakan coba lagi.");
            }
        }

        scanner.close();
    }
}
