import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество дата_рождения номер_телефона пол):");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split(" ");
            if (parts.length != 6) {
                throw new IllegalArgumentException("Неправильное количество данных");
            }

            // Проверка Фамилии, Имени, Отчества на строки
            if (!isString(parts[0]) || !isString(parts[1]) || !isString(parts[2])) {
                throw new IllegalArgumentException("Фамилия, Имя и Отчество должны быть строками");
            }

            String surname = parts[0];
            String name = parts[1];
            String fathername = parts[2];

            // Проверка даты рождения на формат dd.mm.yyyy
            String birthDate = parts[3];
            if (!isValidDate(birthDate)) {
                throw new IllegalArgumentException("Неправильный формат даты рождения (ожидается dd.mm.yyyy)");
            }

            // Проверка номера телефона на цифры
            long phoneNumber = Long.parseLong(parts[4]);

            // Проверка пола
            char gender = parts[5].charAt(0);
            if (gender != 'f' && gender != 'm') {
                throw new IllegalArgumentException("Неправильный формат пола (ожидается f или m)");
            }

            // Запись в файл
            String filename = surname + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.write(surname + " " + name + " " + fathername + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
            writer.close();

            System.out.println("Данные успешно записаны в файл " + filename);
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат номера телефона (ожидается только цифры)");
            System.err.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл");
            e.printStackTrace();
        }
    }

    // Проверка строки на то, что она содержит только буквы
    private static boolean isString(String s) {
        return s.matches("[a-zA-Z]+");
    }

    // Проверка строки на формат даты dd.mm.yyyy
    private static boolean isValidDate(String date) {
        return date.matches("\\d{2}\\.\\d{2}\\.\\d{4}");
    }
}
