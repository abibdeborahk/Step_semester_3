interface Exportable {
    String exportData();
}

class ExportCounter {
    static int count = 0;

    public static int getTotalExports() {
        return count;
    }
}

class ReportGenerator implements Exportable {
    String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public String exportData() {
        ExportCounter.count++;
        return "Exported report: " + reportName;
    }
}

class UserProfile implements Exportable {
    String username;

    public UserProfile(String username) {
        this.username = username;
    }

    @Override
    public String exportData() {
        ExportCounter.count++;
        return "Exported profile: " + username;
    }
}

public class OneClickDataExport {

    static void exportAll(Exportable[] items) {
        for (Exportable item : items) {
            System.out.println(item.exportData());
        }
    }

    static int getTotalExports() {
        return ExportCounter.getTotalExports();
    }

    public static void main(String[] args) {

        ReportGenerator r = new ReportGenerator("Sales Q1");
        UserProfile u = new UserProfile("jane_doe");

        System.out.println(r.exportData());
        System.out.println(u.exportData());

        // Upcasting
        Exportable ref = r;

        Exportable[] items = {ref, u};

        System.out.println("\nExporting All:");

        exportAll(items);

        System.out.println("Total Exports: " + getTotalExports());
    }
}