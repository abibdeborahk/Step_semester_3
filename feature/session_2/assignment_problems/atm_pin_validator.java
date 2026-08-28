public class atm_pin_validator {
    public static void main(String args[])
    {
        checkPinLength("4820");
    }

    static void checkPinLength(String pin) {
        pin = pin.trim();
        int length = pin.length();

        if(length != 4)
        {
            System.out.println("Invalid PIN — must be exactly 4 digits");
        }

        else 
        {
            System.out.println("PIN length OK");
        }
    }
}
