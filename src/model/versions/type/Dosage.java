package model.versions.type;

/**
 * Class shows <dosage> tag children and attribute units. Class contains inner enum - Units that shows available
 * units values.
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

    public void setUnit(String unit) {
        this.unit = Units.valueOf(unit.toUpperCase());
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
