public class BankTransactionReferenceGeneratorValidator {

    public static String normalizeReference(String raw) {

        String reference = raw.trim();


        String bankCode = reference.substring(0, 3).toUpperCase();

        String remainingPart = reference.substring(3);

        return bankCode + remainingPart;
    }

    public static String validateAndFormat(String reference) {

        if (reference.length() != 14) {
            return "Invalid: wrong length";
        }
        for (int i = 0; i < 3; i++) {

            if (!Character.isLetter(reference.charAt(i))) {
                return "Invalid: bank code must be 3 letters";
            }
        }

   
        for (int i = 3; i < reference.length(); i++) {

            if (!Character.isDigit(reference.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }


        String bankCode = reference.substring(0, 3);
        String date = reference.substring(3, 9);
        String sequence = reference.substring(9, 14);

        String formattedDate =
                date.substring(0, 2)
                + "/"
                + date.substring(2, 4)
                + "/"
                + date.substring(4, 6);

        StringBuilder result = new StringBuilder();

        result.append("[")
              .append(bankCode)
              .append("] DATE: ")
              .append(formattedDate)
              .append(" | SEQ: ")
              .append(sequence);

        return result.toString();
    }

    public static void main(String[] args) {

        String rawReference = " hdf03022600042 ";

        String normalizedReference =
                normalizeReference(rawReference);

        String result =
                validateAndFormat(normalizedReference);

        System.out.println(result);

        String invalidReference = "12F03022600042";

        String normalizedInvalidReference =
                normalizeReference(invalidReference);

        String invalidResult =
                validateAndFormat(normalizedInvalidReference);

        System.out.println(invalidResult);
    }
}