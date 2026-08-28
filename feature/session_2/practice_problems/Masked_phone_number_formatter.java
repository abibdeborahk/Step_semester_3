public class Masked_phone_number_formatter {
    public static void main (String[] args) {
        System.out.println(maskPhoneNumber("9876543210"));
        
    } 
   static String maskPhoneNumber(String phone) { 
    if(phone.length() != 10)
    {
        return ("Invalid phone number");
    }
    for(int i=0; i< phone.length(); i++)
    {
        if(!Character.isDigit(phone.charAt(i)))
        {
            return ("Invalid phone number");
        }
    }
    String lastfour = phone.substring(6);
    StringBuilder sb = new StringBuilder("XXXXXX");
    sb.insert(6,"-");
    sb.insert(7,lastfour);
    return sb.toString();
   }
}
