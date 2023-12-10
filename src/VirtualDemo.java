import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VirtualDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean inputValid = false;

        while (!inputValid) {
            try {
                // Menampilkan informasi waktu saat ini menggunakan fungsi getCurrentDate dan getCurrentTime
                System.out.println("          Data            ");
                System.out.println("Tanggal: " + getCurrentDate());
                System.out.println("Waktu  : " + getCurrentTime());

                // Input data untuk log in
                System.out.println("-------------------------------------");
                System.out.println("   SILAHKAN LOG IN TERLEBIH DAHULU");
                System.out.println("-------------------------------------");
                System.out.print("Username: ");
                String username = scanner.nextLine();

                System.out.print("Password: ");
                String password = scanner.nextLine();

                // Input dan verifikasi captcha
                String captcha = generateCaptcha();
                System.out.println("Captcha: " + captcha);

                System.out.print("Masukkan Captcha (case insensitive): ");
                String userCaptcha = scanner.nextLine();

                // Verifikasi log in dan captcha
                if (isValidLogin(username, password) && isValidCaptcha(captcha, userCaptcha)) {
                    System.out.println("Log in berhasil!");

                    // Input data pelanggan
                    System.out.println("DATA PELANGGAN");
                    System.out.println("-------------------------------------");
                    System.out.print("Input Nama Pelanggan   : ");
                    String namaPelanggan = scanner.nextLine();

                    System.out.print("Input No. HP Pelanggan : ");
                    String noHpPelanggan = scanner.nextLine();

                    System.out.print("Input Alamat Pelanggan : ");
                    String alamatPelanggan = scanner.nextLine();

                    // Input data pembelian barang
                    System.out.println("Input DATA PEMBELIAN BARANG");
                    System.out.println("-------------------------------------");
                    System.out.print("No. Faktur              : ");
                    String noFaktur = scanner.nextLine();

                    System.out.print("Input Kode Barang       : ");
                    String kodeBarang = scanner.nextLine();

                    System.out.print("Input Nama Barang       : ");
                    String namaBarang = scanner.nextLine();

                    System.out.print("Input Harga Barang      : ");
                    double hargaBarang = Double.parseDouble(scanner.nextLine());

                    System.out.print("Input Jumlah Beli       : ");
                    int jumlahBeli = Integer.parseInt(scanner.nextLine());

                    System.out.print("Input Nama Kasir        : ");
                    String kasir = scanner.nextLine();

                    // Membuat objek Pemesanan dengan data yang telah diinput
                    Pemesanan pesanan = new Pemesanan(noFaktur, new Pelanggan(namaPelanggan, noHpPelanggan, alamatPelanggan), new Barang(kodeBarang, namaBarang, hargaBarang), jumlahBeli, kasir);

                    // Menampilkan informasi pemesanan
                    pesanan.tampilkanInformasi();

                    System.out.println("=====================================");

                    inputValid = true;
                } else {
                    System.out.println("Log in gagal. Pastikan username, password, dan captcha benar.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Pastikan input sesuai dengan format yang benar.");
                System.out.println("Silahkan ulangi pengisian sesuai dengan ketentuan.");
                scanner.nextLine();
            } catch (NumberFormatException e) {
                System.out.println("Error: Harga Barang dan Jumlah Beli harus berupa angka.");
                System.out.println("Silahkan ulangi pengisian sesuai dengan ketentuan.");
                scanner.nextLine();
            } catch (JumlahBeliException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    // Fungsi untuk mendapatkan tanggal saat ini.
    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd/MM/yyyy");
        return dateFormat.format(Calendar.getInstance().getTime());
    }

    // Fungsi untuk mendapatkan waktu saat ini.
    private static String getCurrentTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a zzz");
        return timeFormat.format(Calendar.getInstance().getTime());
    }

    // Fungsi untuk generate captcha
    private static String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        StringBuilder captcha = new StringBuilder();

        for (int i = 0; i < length; i++) {
            captcha.append(characters.charAt((int) (Math.random() * characters.length())));
        }

        return captcha.toString();
    }

    // Fungsi untuk verifikasi login
    private static boolean isValidLogin(String username, String password) {
        // Implementasi validasi login sesuai kebutuhan Anda
        // Misalnya, username dan password tertentu yang diizinkan.
        return "diva".equals(username) && "diva123".equals(password);
    }

    // Fungsi untuk verifikasi captcha
    private static boolean isValidCaptcha(String generatedCaptcha, String userCaptcha) {
        // Menggunakan equalsIgnoreCase() untuk membandingkan captcha tanpa memperdulikan case
        return generatedCaptcha.equalsIgnoreCase(userCaptcha);
    }
}
