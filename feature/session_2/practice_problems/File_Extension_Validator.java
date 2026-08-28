public class File_Extension_Validator {
    public static void main(String[] args) {
        System.out.println(validateFileExtension("Assignment.pdf"));
    }
    static String validateFileExtension(String filename)
    {
        String extension = filename.substring(filename.lastIndexOf('.'));
        if(extension.equalsIgnoreCase(".pdf") || extension.equalsIgnoreCase(".docx") || extension.equalsIgnoreCase(".zip"))
        {
            return "Accepted";
        }
        else return "Rejected - invalid filetype";    
    }
}