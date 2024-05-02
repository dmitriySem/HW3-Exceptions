package macro;

public class QuantityDataExeption extends Exception{

        private String name;
        private String surname;
        private String patronymic;
        private String birthDate;
        private String phone;
        private String gender;


        public QuantityDataExeption(String ErrorMessage, String str) {
                super(ErrorMessage);

                String[] s = str.split(" ");


        }
}
