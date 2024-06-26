import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате: Фамилия Имя Отчество дата_рождения номер_телефона пол");

        String input = scanner.nextLine();
        String[] data = input.split(" ");

        try {
            if (data.length != 6) {
                throw new InvalidInputException("Неверное количество данных. Ожидалось 6 элементов, получено " + data.length);
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            validateBirthDate(birthDate);
            validatePhoneNumber(phoneNumber);
            validateGender(gender);

            String fileName = lastName + ".txt";
            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate + " " + phoneNumber + " " + gender + "\n");
                System.out.println("Данные успешно записаны в файл " + fileName);
            } catch (IOException e) {
                System.err.println("Ошибка записи в файл: ");
                e.printStackTrace();
            }

        } catch (InvalidInputException | InvalidDateFormatException | InvalidPhoneNumberFormatException | InvalidGenderFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void validateBirthDate(String birthDate) throws InvalidDateFormatException {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new InvalidDateFormatException("Неверный формат даты рождения. Ожидалось dd.mm.yyyy, получено " + birthDate);
        }
    }

    private static void validatePhoneNumber(String phoneNumber) throws InvalidPhoneNumberFormatException {
        if (!phoneNumber.matches("\\d+")) {
            throw new InvalidPhoneNumberFormatException("Неверный формат номера телефона. Ожидалось целое беззнаковое число, получено " + phoneNumber);
        }
    }

    private static void validateGender(String gender) throws InvalidGenderFormatException {
        if (!(gender.equals("f") || gender.equals("m"))) {
            throw new InvalidGenderFormatException("Неверный формат пола. Ожидалось 'f' или 'm', получено " + gender);
        }
    }
}

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

class InvalidDateFormatException extends Exception {
    public InvalidDateFormatException(String message) {
        super(message);
    }
}

class InvalidPhoneNumberFormatException extends Exception {
    public InvalidPhoneNumberFormatException(String message) {
        super(message);
    }
}

class InvalidGenderFormatException extends Exception {
    public InvalidGenderFormatException(String message) {
        super(message);
    }
}
