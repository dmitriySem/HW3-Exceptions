package macro;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {

        BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;

        while (flag) {
            try {
                System.out.println("Введите ФИО, дату рождения (в формате dd.mm.yyyy), номер телефона (8xxxxxxxxxx) и пол (М или Ж) через пробел");
                System.out.println("Например: Иванов Иван Иванович 01.01.1999 89194441448 М");
                String str = bfn.readLine();
                //System.out.println(str);
                String[] s = str.split(" ");

                if (s.length< 6)
                    throw new QuantityDataExeption("Вы ввели меньше данных чем требуется," +
                        " данные не соответсвуют шаблону ФИО дата_рождения номер_телефона пол", str);
                else if (s.length > 6)
                    throw new QuantityDataExeption("Вы ввели излишние данные чем требуется," +
                        " данные не соответсвуют шаблону ФИО дата_рождения номер_телефона пол", str);

                Path path = Path.of(s[0].toLowerCase(Locale.ROOT) + ".txt");
                if (!Files.exists(path))
                    Files.createFile(path);
                Files.writeString(path, str + '\n', StandardOpenOption.APPEND);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (QuantityDataExeption quantityDataExeption) {
                quantityDataExeption.printStackTrace();
            }

            System.out.println("Внести еще данные? Да/Нет");
            try {
                if (bfn.readLine().toLowerCase(Locale.ROOT).equals("нет")) flag = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}




