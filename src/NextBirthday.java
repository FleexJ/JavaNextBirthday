import java.util.Calendar;
import java.util.Scanner;

public class NextBirthday {

    public static void calculateNextBirthday() {
        Calendar dateBirth = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        int day, month, year;

        Scanner scanner = new Scanner(System.in);
        //Пока не считаем корректную дату
        while (true) {
            //Считывание даты
            System.out.print("\nEnter your date of birth: ");
            String input = scanner.nextLine();

            //Если введенная дата не подходит под формат
            if (!input.matches("^[0-9]{1,2}\\.[0-9]{1,2}\\.[0-9]{1,4}$")) {
                System.out.println("Incorrect date!");
            }
            //Иначе парсим дату из строки и запоминаем
            else {
                String[] inputSplit = input.split("\\.");
                day = Integer.parseInt(inputSplit[0]);
                month = Integer.parseInt(inputSplit[1]) - 1;
                year = Integer.parseInt(inputSplit[2]);
                if (month > 11 || month < 0) {
                    System.out.println("Incorrect date!");
                    continue;
                }

                dateBirth.set(year, month, day, 0, 0, 0);
                //Устанавливаем текущий год
                dateBirth.set(Calendar.YEAR, now.get(Calendar.YEAR));
                //Если день уже прошел, то прибавляем год
                if (dateBirth.before(now)) {
                    dateBirth.add(Calendar.YEAR, 1);
                }
                System.out.println("\nNext birthday:\n" + dateBirth.getTime());
                break;
            }
        }

        //Вычисляем разницу между следующим днем рождения и текущей датой в миллисекундах, переводим миллисекунды в дни
        double nextDays = (dateBirth.getTimeInMillis() - now.getTimeInMillis()) / 86400000.0;
        System.out.println("\nDays left until birthday:\n" + nextDays);
    }

    public static void main(String[] args) {
        calculateNextBirthday();
    }
}
