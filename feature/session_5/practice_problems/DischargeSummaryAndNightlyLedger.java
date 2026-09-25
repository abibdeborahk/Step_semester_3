final class DischargeSummary {

    private final String patientId;
    private final String[] medicationCodes;

    // Static block
    static {
        System.out.println(
            "Discharge Summary System Initialized");
    }

    public DischargeSummary(
            String patientId,
            String[] medicationCodes) {

        this.patientId = patientId;

        // Validate the complete array
        for (int i = 0; i < medicationCodes.length; i++) {

            String code = medicationCodes[i];

            if (code == null ||
                code.length() != 5 ||
                code.charAt(0) != 'M' ||
                code.charAt(1) != 'E' ||
                code.charAt(2) != 'D' ||
                code.charAt(3) != '-' ||
                !Character.isUpperCase(code.charAt(4))) {

                throw new IllegalArgumentException(
                    "Invalid medication code: " + code);
            }
        }

        // Defensive copy
        this.medicationCodes =
            medicationCodes.clone();
    }

    public String[] getMedicationCodes() {

        // Defensive copy
        return medicationCodes.clone();
    }

    public DischargeSummary withCorrectedMedication(
            int index,
            String newCode) {

        if (index < 0 ||
            index >= medicationCodes.length) {

            throw new IndexOutOfBoundsException(
                "Invalid medication index");
        }

        // Validate new code
        if (newCode == null ||
            newCode.length() != 5 ||
            newCode.charAt(0) != 'M' ||
            newCode.charAt(1) != 'E' ||
            newCode.charAt(2) != 'D' ||
            newCode.charAt(3) != '-' ||
            !Character.isUpperCase(newCode.charAt(4))) {

            throw new IllegalArgumentException(
                "Invalid medication code");
        }

        // Make a copy
        String[] newCodes =
            medicationCodes.clone();

        newCodes[index] = newCode;

        // Return a NEW object
        return new DischargeSummary(
            patientId,
            newCodes);
    }
}


class CriticalCareDischargeSummary
        extends DischargeSummary {

    private final int icuDays;

    public CriticalCareDischargeSummary(
            String patientId,
            String[] medicationCodes,
            int icuDays) {

        super(patientId, medicationCodes);

        this.icuDays = icuDays;
    }

    public int getIcuDays() {
        return icuDays;
    }
}


class NightlyProcessor {

    public static String processNightlyBatch(
            DischargeSummary[] summaries) {

        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (int i = 0; i < summaries.length; i++) {

            // Null safety
            if (summaries[i] == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            // instanceof dispatch
            if (summaries[i]
                    instanceof CriticalCareDischargeSummary) {

                critical++;

            } else {

                routine++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + critical + " critical-care | "
                + routine + " routine";
    }
}


public class DischargeSummaryAndNightlyLedger {

    public static void main(String[] args) {

        DischargeSummary d =
            new DischargeSummary(
                "MT2026-0142",
                new String[]{
                    "MED-A",
                    "MED-B"
                });

        String[] codes =
            d.getMedicationCodes();

        codes[0] = "TAMPERED";

        System.out.println(
            d.getMedicationCodes()[0]);

        DischargeSummary corrected =
            d.withCorrectedMedication(
                0,
                "MED-C");

        System.out.println(
            corrected.getMedicationCodes()[0]);

        CriticalCareDischargeSummary c =
            new CriticalCareDischargeSummary(
                "MT001",
                new String[]{"MED-X"},
                4);

        DischargeSummary[] summaries = {
            c,
            null,
            new DischargeSummary(
                "MT002",
                new String[]{"MED-Y"})
        };

        System.out.println(
            NightlyProcessor.processNightlyBatch(
                summaries));
    }
}