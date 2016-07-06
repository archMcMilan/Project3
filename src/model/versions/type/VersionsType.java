package model.versions.type;

/**
 * Created by Artem on 05.07.16.
 */
public class VersionsType {
    private Consistency consistency;
    private Certificate certificate;
    private Package packageType;
    private Dosage dosage;

    public VersionsType() {
        certificate=new Certificate();
        packageType=new Package();
        dosage=new Dosage();
    }

    public Consistency getConsistency() {
        return consistency;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public Package getPackageType() {
        return packageType;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public void setConsistency(Consistency consistency) {
        this.consistency = consistency;
    }

    public void setCertificate(Certificate certificate) {
        this.certificate = certificate;
    }

    public void setPackageType(Package packageType) {
        this.packageType = packageType;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    @Override
    public String toString() {
        return "VersionsType{" +
                "consistency=" + consistency +
                ", certificate=" + certificate +
                ", packageType=" + packageType +
                ", dosage=" + dosage +
                '}';
    }
}
