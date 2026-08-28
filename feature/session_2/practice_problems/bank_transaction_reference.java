public class bank_transaction_reference {
    public static void main(String args[])
    {
        String raw = " hdf03022600042 ";
        String normalized = normalizeReference(raw);
        System.out.println(validateAndFormat(normalized));
        
    }

    static String normalizeReference(String raw) {
        raw = raw.trim();
        String bankcode = raw.substring(0,3).toUpperCase();
        String remaining = raw.substring(3,14);
        return bankcode + remaining;
    }

    static String validateAndFormat(String reference) {
        if(reference.length()!=14)
        {
            return "Invalid: wrong length";
        }

        for(int i=0; i<3; i++)
        {
            if(!Character.isLetter(reference.charAt(i)))
            {
                return "Invalid: bank code must be 3 letters";
            }
        }

        for(int i=3; i<reference.length();i++)
        {
            if(!Character.isDigit(reference.charAt(i)))
            {
                return "Invalid: body must only contain digits";
            }
        }

        String bankCode = reference.substring(0,3);
        String date = reference.substring(3,9);
        String sequence = reference.substring(9,14);

        String formattedDate = date.substring(0,2)+"/"+date.substring(2,4)+"/"+date.substring(4,6);

        StringBuilder sb = new StringBuilder();

        sb.append("[");
        sb.append(bankCode);
        sb.append("] Date: ");
        sb.append(formattedDate);
        sb.append(" SEQ: ");
        sb.append(sequence);

        return sb.toString();
    }
}