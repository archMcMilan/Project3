package model.versions.type;

/**
 * Class shows <certificate> tag children
 */
public class Certificate {
    private long number;
    private String issueDate;
    private String expiryDate;
    private String organization;

    public long getNumber() {
        return number;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getOrganization() {
        return organization;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return "Certificate{" +
                "number=" + number +
                ", issueDate='" + issueDate + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", organization='" + organization + '\'' +
                '}';
    }
}
