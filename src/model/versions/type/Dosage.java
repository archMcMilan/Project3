package model.versions.type;

/**
 * Created by Artem on 05.07.16.
 */
public class Dosage {
    private Units unit;
    private int drugDosage;
    private long period;

    public enum Units{
        ML("ml"),PCS("pcs");

        private String value;

        Units(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public Units getUnit() {
        return unit;
    }

    public int getDrugDosage() {
        return drugDosage;
    }

    public long getPeriod() {
        return period;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public void setDrugDosage(int drugDosage) {
        this.drugDosage = drugDosage;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "Dosage{" +
                "unit=" + unit +
                ", drugDosage=" + drugDosage +
                ", period=" + period +
                '}';
    }
}
