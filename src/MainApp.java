import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * Aplikasi Todo sederhana yang memungkinkan pengguna untuk menambahkan, menandai selesai, menghapus,
 * dan menyimpan daftar todo.
 */
class TodoApp {
    /** Konstanta integer untuk menghitung nilai rating. */
    public static final int INT = 2;
    private static final String FILE_NAME = "todos.bin";
    private static Todo[] todos = new Todo[20];
    private static int tLength = 0;
    public static int rate;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Kelas internal untuk representasi todo item.
     */
    static class Todo implements Serializable {
        String title;
        String createdAt; // Refactor Rename (2)
        boolean isCompleted;

        /**
         * Konstruktor untuk membuat todo item baru.
         *
         * @param title judul dari todo
         * @param createdAt waktu pembuatan todo dalam format string
         */
        public Todo(String title, String createdAt) {
            this.title = title;
            this.createdAt = createdAt;
            this.isCompleted = false;

        }
    }

    /**
     * Menghitung rating berdasarkan konstanta INT.
     *
     * @return hasil perkalian antara rate dan INT
     */
    public static double hitung() {
        return rate * INT; // Refactor Introduce Constant (4)
    }

    /**
     * Menyimpan todo ke dalam file binari.
     */
    private static void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            for (int i = 0; i < tLength; i++) {
                oos.writeObject(todos[i]);
            }
        } catch (IOException e) {
            System.out.println("Can't save your todo");
        }
    }

    /**
     * Membaca todo dari file binari.
     */
    private static void readFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            while (true) {
                todos[tLength] = (Todo) ois.readObject();
                tLength++;
            }
        } catch (EOFException e) {
            // End of file reached
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file");
        }
    }

    /**
     * Menambahkan todo baru ke daftar.
     */
    private static void addTodo() {
        System.out.print("Type your todo >> ");
        String userInput = scanner.nextLine();
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM HH:mm"));
        todos[tLength] = new Todo(userInput, currentTime);
        tLength++;
    }

    /**
     * Menampilkan semua todo dalam daftar.
     */
    private static void printAllTodo() {
        System.out.println("+----+-------------------------------------+--------------+-------------+");
        System.out.println("| ID |            Todo Title               |  Created at  |  Completed  |");
        System.out.println("+----+-------------------------------------+--------------+-------------+");

        for (int i = 0; i < tLength; i++) {
            String completed = todos[i].isCompleted ? " ✅  Yes" : " ❌  No";
            System.out.printf("|%3d | %-35s | %-12s | %-11s|\n", i + 1, todos[i].title, todos[i].createdAt, completed);
            System.out.println("+----+-------------------------------------+--------------+-------------+");
        }
    }

    /**
     * Menandai todo sebagai selesai berdasarkan ID.
     */
    private static void markAsComplete() {
        System.out.print("Enter the ID of todo \n>> ");
        int todoId = Integer.parseInt(scanner.nextLine()) - 1;
        if (todoId < 0 || todoId >= tLength) {
            System.out.println("Invalid todo id 😑");
        } else {
            todos[todoId].isCompleted = true;
            System.out.println("Todo marked as completed");
        }
    }

    /**
     * Menghapus todo dari daftar berdasarkan ID.
     */
    private static void deleteTodo() {
        System.out.print("Enter the ID of todo \n>> ");
        int todoId = Integer.parseInt(scanner.nextLine()) - 1;
        if (todoId < 0 || todoId >= tLength) {
            System.out.println("Invalid todo id 😑");
        } else {
            System.arraycopy(todos, todoId + 1, todos, todoId, tLength - todoId - 1);
            tLength--;
            System.out.println("Your todo has been deleted 😵");
        }
    }

    /**
     * Menampilkan opsi kepada pengguna dan menerima input.
     */
    private static void showOptions() {
        System.out.print("Type 'A' to add, 'D' to delete, 'C' to mark complete, or 'Q' to quit\n>> ");
        char userChoice = scanner.nextLine().toUpperCase().charAt(0);
        switch (userChoice) {
            case 'A':
                addTodo();
                break;
            case 'D':
                deleteTodo();
                break;
            case 'C':
                markAsComplete();
                break;
            case 'Q':
                System.exit(0);
                break;
            default:
                System.out.println("Command not found 😓");
        }
        performPostAction();
    }

    /**
     * Melakukan tindakan setelah operasi selesai, yaitu menyimpan dan menampilkan daftar todo.
     */
    private static void performPostAction() { // Refactor Extract Method (1)
        saveToFile();
        printAllTodo();
        showOptions();
    }

    /**
     * Mengecek apakah ini pertama kali aplikasi dijalankan dan memuat data jika ada.
     */
    public static void isThisFirstTime() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            readFromFile();
            printAllTodo();
            showOptions();
        } else {
            System.out.println("Welcome to the Great Todo App");
            addTodo();
            saveToFile();
            printAllTodo();
            showOptions();
        }
    }

    /**
     * Membersihkan layar terminal.
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
