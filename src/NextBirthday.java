import java.util.Calendar;
import java.util.Scanner;

public class NextBirthday {

    public static void calculateNextBirthday() {
        Calendar dateBirth = Calendar.getInstance();
        Calendar nextDateBirth = Calendar.getInstance();
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
                //Копируем введенную дату и меняем год на текущий
                nextDateBirth = (Calendar) dateBirth.clone();
                nextDateBirth.set(
                        Calendar.YEAR,
                        Calendar.getInstance().get(Calendar.YEAR)
                );
                //Если день уже прошел, то прибавляем год
                if (nextDateBirth.before(Calendar.getInstance())) {
                    nextDateBirth.add(Calendar.YEAR, 1);
                }
                System.out.println("\nNext birthday:\n" + nextDateBirth.getTime());
                break;
            }
        }

        //Вычисляем разницу между следующим днем рождения и текущей датой в миллисекундах, переводим миллисекунды в дни
        double nextDays = (nextDateBirth.getTimeInMillis() - Calendar.getInstance().getTimeInMillis()) / 86400000.0;
        System.out.println("\nDays left until birthday:\n" + nextDays);
    }

    public static void main(String[] args) {
        calculateNextBirthday();
    }
}
