public class library_ISBN_formatter_and_validator {

    public static void main(String args[]) {

        String code = normalizeCode(" pen2026004251 ");

        System.out.println(validateAndFormat(code));
    }

    static String normalizeCode(String raw) {

        raw = raw.trim();

        String word = raw.substring(0, 3).toUpperCase();

        String code = word + raw.substring(3);

        return code;
    }

    static String validateAndFormat(String code) {

        if (code.length() != 13) {
            return "Invalid: publisher code must contain 13 characters";
        }

        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < 13; i++) {

            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: publisher code must contain 10 digits";
            }
        }

        String publisher = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(publisher);
        sb.append("] YEAR: ");
        sb.append(year);
        sb.append(" | CATALOG: ");
        sb.append(catalog);

        return sb.toString();
    }
}