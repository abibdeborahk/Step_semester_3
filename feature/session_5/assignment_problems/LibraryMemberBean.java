
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LibraryMemberBean {

    private String membershipId;
    private String name;
    private boolean premiumMember;

    private String securityAnswerHash;

    private boolean membershipIdSet = false;

    // No-argument constructor
    public LibraryMemberBean() {
        this(null, null);
    }

    // Name-only constructor
    public LibraryMemberBean(String name) {
        this(null, name);
    }

    // ID + name constructor
    public LibraryMemberBean(String membershipId, String name) {

        this.name = name;

        if (membershipId != null) {
            this.membershipId = membershipId;
            this.membershipIdSet = true;
        }
    }

    // Membership ID getter
    public String getMembershipId() {
        return membershipId;
    }

    // Write-once membership ID setter
    public void setMembershipId(String id) {

        if (!membershipIdSet) {
            membershipId = id;
            membershipIdSet = true;
        }
    }

    // Name getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Premium member getter and setter
    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        premiumMember = premium;
    }

    // Write-only security answer
    public void setSecurityAnswer(String answer) {

        try {
            MessageDigest md =
                MessageDigest.getInstance("SHA-256");

            byte[] hash = md.digest(
                answer.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder result = new StringBuilder();

            for (byte b : hash) {
                result.append(String.format("%02x", b));
            }

            securityAnswerHash = result.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        LibraryMemberBean m1 =
            new LibraryMemberBean("Priya Nair");

        System.out.println(m1.getMembershipId());

        LibraryMemberBean m2 =
            new LibraryMemberBean("LIB-8841", "Priya Nair");

        System.out.println(m2.getMembershipId());

        m2.setMembershipId("FAKE-0000");

        System.out.println(m2.getMembershipId());

        m2.setPremiumMember(true);

        System.out.println(m2.isPremiumMember());

        m2.setSecurityAnswer("Blue");
    }
}
