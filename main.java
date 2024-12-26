// Uso
public class Main {
    public static void main(String[] args) {
        SupportSystem support = new SupportSystem();
        support.processSupportRequest(new SupportRequest("Application crashing"));
    }
}

public class SupportSystem {
    public void processSupportRequest(SupportRequest request) {
        resolveIssue(request, SupportLevel.LEVEL_1);
    }

    private void resolveIssue(SupportRequest request, SupportLevel level) {
        switch (level) {
            case LEVEL_1:
                if (!resolveIssueAtLevel1(request)) {
                    resolveIssue(request, SupportLevel.LEVEL_2);
                }
                break;
            case LEVEL_2:
                if (!resolveIssueAtLevel2(request)) {
                    resolveIssue(request, SupportLevel.LEVEL_3);
                }
                break;
            case LEVEL_3:
                resolveIssueAtLevel3(request);
                break;
            default:
                System.out.println("Unknown support level.");
        }
    }


    private boolean resolveIssueAtLevel1(SupportRequest request) {
        System.out.println("Level 1 support handling request: " +
                request.getIssue());
            // Simulate problem-solving attempt
        return false; // Simulação: não resolvido.
    }

    private boolean resolveIssueAtLevel2(SupportRequest request) {
        System.out.println("Level 2 support handling request: " +
                request.getIssue());
        // Simulate problem-solving attempt
        return false; // Simulação: não resolvido.
    }

    private void resolveIssueAtLevel3(SupportRequest request) {
        System.out.println("Level 3 support handling request: " +
                request.getIssue());
            // Aqui a resolução é considerada garantida.
    }

    private enum SupportLevel {
        LEVEL_1,
        LEVEL_2,
        LEVEL_3;
    }
}

public class SupportRequest {
    private final String issue;

    public SupportRequest(String issue) {
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }
}