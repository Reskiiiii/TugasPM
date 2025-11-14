
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class split {

    /**
     * Membagi file input menjadi beberapa file output, masing-masing berisi
     * sejumlah baris yang ditentukan, menggunakan Queue (FIFO).
     *
     * @param inputFileName Nama file teks yang akan dibagi.
     * @param linesPerPart Jumlah baris dalam setiap file bagian.
     */
    public static void splitFile(String inputFileName, int linesPerPart) {
        if (linesPerPart <= 0) {
            System.err.println("Jumlah baris per bagian harus lebih dari nol.");
            return;
        }

        // Menggunakan LinkedList sebagai implementasi dari interface Queue
        Queue<String> lineQueue = new LinkedList<>();
        int partCount = 1;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFileName))) {
            String currentLine;

            System.out.println("\nMemproses file: " + inputFileName);

            // 1. Membaca file baris demi baris
            while ((currentLine = reader.readLine()) != null) {

                // 2. Menambahkan baris ke Queue (Enqueue)
                // .offer() adalah metode Queue yang disukai untuk menambahkan elemen
                lineQueue.offer(currentLine);

                // 3. Memeriksa apakah Queue sudah mencapai batas baris untuk satu bagian
                if (lineQueue.size() == linesPerPart) {
                    // Jika sudah mencapai batas, tulis isi Queue ke file baru
                    writePartToFile(lineQueue, partCount, inputFileName);
                    partCount++;
                    // Queue sudah dikosongkan di dalam metode writePartToFile
                }
            }

            // Menangani sisa baris yang mungkin ada di Queue setelah file selesai dibaca
            if (!lineQueue.isEmpty()) {
                writePartToFile(lineQueue, partCount, inputFileName);
            }

            System.out.println("\nProses pemotongan file selesai.");

        } catch (IOException e) {
            System.err.println("Terjadi kesalahan I/O: Pastikan file '" + inputFileName + "' ada.");
        }
    }

    /**
     * Menulis semua baris dari Queue ke file bagian.
     *
     * * @param queue Queue yang berisi baris-baris untuk bagian saat ini.
     * @param partNumber Nomor bagian file.
     * @param baseFileName Nama dasar file input untuk membuat nama file output.
     */
    private static void writePartToFile(Queue<String> queue, int partNumber, String baseFileName) throws IOException {
        // Membuat nama file output, e.g., 'data.txt' -> 'data_part_1.txt'
        String outputFileName = baseFileName.replaceFirst("[.][^.]+$", "")
                + "_part_" + partNumber + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            System.out.println("Menulis bagian " + partNumber + " ke: " + outputFileName);

            // Dequeue dan tulis setiap baris ke file
            while (!queue.isEmpty()) {
                // Mengambil dan menghapus baris dari Queue (Dequeue)
                // .poll() adalah metode Queue yang disukai untuk mengambil dan menghapus elemen
                String line = queue.poll();
                writer.write(line);
                writer.newLine(); // Menambahkan baris baru setelah setiap baris teks
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Meminta input dari pengguna
        System.out.print("Masukkan nama file teks yang akan dibagi: ");
        String fileName = scanner.nextLine();

        System.out.print("Masukkan jumlah baris per bagian: ");
        int linesPerPart = 0;
        try {
            linesPerPart = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Input tidak valid. Harap masukkan angka.");
            scanner.close();
            return;
        }

        scanner.close();

        // Panggil metode untuk membagi file
        splitFile(fileName, linesPerPart);
    }
}
