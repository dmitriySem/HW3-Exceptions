package macro;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader bfn = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;

        while (flag) {
            System.out.println("Введите ФИО, дату рождения (в формате dd.mm.yyyy), номер телефона (8xxxxxxxxxx) и пол (М или Ж) через пробел");
            System.out.println("Например: Иванов Иван Иванович 01.01.1999 89194441448 М");
            String str = bfn.readLine();

            String surname = "";
            String name = "";
            String patronymic = "";

            String bithDate = "";
            long phone = 0;
            String gender = "";


            try {
                //System.out.println(str);
                String[] s = str.split(" ");

                if (s.length< 6)
                    throw new QuantityDataExeption("Вы ввели меньше данных чем требуется," +
                        " данные не соответсвуют шаблону ФИО дата_рождения номер_телефона пол");
                else if (s.length > 6)
                    throw new QuantityDataExeption("Вы ввели излишние данные чем требуется," +
                        " данные не соответсвуют шаблону ФИО дата_рождения номер_телефона пол");

                surname = s[0];
                name = s[1];
                patronymic = s[2];

                bithDate = getBithDate(s[3]);

                phone = getPhone(s[4]);
                gender = getGender(s[5]);


            } catch (QuantityDataExeption quantityDataExeption) {
                quantityDataExeption.printStackTrace();
            }

            Path path = Path.of(surname.toLowerCase(Locale.ROOT) + ".txt");
            if (!Files.exists(path))
                Files.createFile(path);
            Files.writeString(path, surname + " " + name + " " + patronymic + " " +
                                        bithDate + " " + phone + " " + gender  + '\n', StandardOpenOption.APPEND);

            System.out.println("Внести еще данные? Да/Нет");
            try {
                if (bfn.readLine().toLowerCase(Locale.ROOT).equals("нет")) flag = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private static String getGender(String s) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag){
            try {
                if (!(s.toLowerCase(Locale.ROOT).equals("м") || s.toLowerCase(Locale.ROOT).equals("ж")))
                    throw new GenderFormatException("Не верный формат ввод пола, нужно М или Ж");
                flag = false;
            } catch (GenderFormatException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите пол:");
                s = reader.readLine();
//                e.printStackTrace();
            }

        }
        return s;
    }

    public static String getBithDate(String s) throws IOException{
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag){
            try {
                sdf.parse(s);
                flag = false;
            } catch (ParseException e) {
                System.out.println("Не верный формат ввод даты рождения, требуется ввести согласно шаблону dd.mm.yyyy");
                System.out.println("Введите дату рождения в виде dd.mm.yyyy:");
                s = reader.readLine();
//                e.printStackTrace();
            }

        }
        return s;
    }

    public static long getPhone(String s) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        long phone = 0;
        boolean flag = true;
        while (flag) {
            try {
                if (!s.matches("\\d{11}"))
                    throw new FormaPhoneException("Не верно введен номер телефона, необходимо ввиде 8xxxxxxxxxx");

                phone = Long.parseLong(s);
                flag = false;
            } catch (FormaPhoneException e) {
                System.out.println(e.getMessage());
                System.out.println("Введите номер телефона в виде 8хххххххххх:");
                s = reader.readLine();
//                e.printStackTrace();
            }
        }
            return phone;
    }



}




