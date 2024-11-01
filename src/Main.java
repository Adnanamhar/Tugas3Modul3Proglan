/**
 * Kelas utama yang menjalankan aplikasi Todo.
 */
public class Main { // Refactor Move Member (3)

    /**
     * Metode utama untuk memulai aplikasi Todo.
     *
     * @param args argumen baris perintah
     */
    public static void main(String[] args) {
        TodoApp.clearScreen();
        TodoApp.isThisFirstTime();
        System.out.println("Selamat Datang di Todos Version" + 10.5); // Refactor Inline Variable
    }
}
