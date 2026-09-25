class PatientProfile {

    private String patientId;
    private String name;
    private boolean discharged;

    // Stored transformed PIN
    private String lockerPinHash;

    // No-argument constructor
    public PatientProfile() {
        this(null, null);
    }

    // Name-only constructor
    public PatientProfile(String name) {
        this(null, name);
    }

    // Main constructor
    public PatientProfile(
            String patientId,
            String name) {

        this.patientId = patientId;
        this.name = name;
        this.discharged = false;
    }

    // Getter
    public String getPatientId() {
        return patientId;
    }

    // Write-once setter
    public void setPatientId(String id) {

        if (this.patientId == null) {
            this.patientId = id;
        }
    }

    // Name getter
    public String getName() {
        return name;
    }

    // Name setter
    public void setName(String name) {
        this.name = name;
    }

    // Boolean getter
    public boolean isDischarged() {
        return discharged;
    }

    // Boolean setter
    public void setDischarged(boolean discharged) {
        this.discharged = discharged;
    }

    // Write-only PIN
    public void setLockerPin(String pin) {

        if (pin == null) {
            return;
        }

        if (pin.matches("\\d{4,6}")) {

            // Simple one-way transformation
            lockerPinHash =
                Integer.toHexString(pin.hashCode());
        }
    }
}


public class PatientProfileJavaBean {

    public static void main(String[] args) {

        PatientProfile p =
            new PatientProfile();

        p.setPatientId("MT2026-0142");

        // This will be ignored
        p.setPatientId("HACKED-0000");

        System.out.println(
            p.getPatientId());

        PatientProfile p2 =
            new PatientProfile("Arjun Iyer");

        System.out.println(
            p2.getPatientId());

        PatientProfile p3 =
            new PatientProfile(
                "MT2026-0142",
                "Arjun Iyer");

        System.out.println(
            p3.getPatientId());

        p3.setDischarged(true);

        System.out.println(
            p3.isDischarged());

        p3.setLockerPin("1234");
    }
}