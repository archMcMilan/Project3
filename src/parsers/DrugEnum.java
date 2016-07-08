package parsers;

/**
 * Enum contains all tag name and specified method that return enum element by value field.
 */
public enum  DrugEnum {
    MEDICINE("medicine"),
    DRUG("drug"),
    NAME("name"),
    PHARM("pharm"),
    GROUP("group"),
    ANALOGS("analogs"),
    VERSIONS("versions"),
    CONSISTENCY("consistency"),
    CERTIFICATE("certificate"),
    NUMBER("number"),
    ISSUE_DATE("issueDate"),
    EXPIRY_DATE("expiryDate"),
    ORGANIZATION("organization"),
    PACKAGE("package"),
    TYPE("type"),
    AMOUNT("amount"),
    PRICE("price"),
    DOSAGE("dosage"),
    UNITS("inits"),
    DRUG_DOSAGE("drugDosage"),
    PERIOD("period");

    private String value;

    DrugEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /**
     * Method find from all enums, enum with the same value field value.
     * @param value
     * @return DrugEnum if found, otherwise - null
     */
    public static DrugEnum getEnumByString(String value){
        for(DrugEnum d:DrugEnum.values()){
            if(value.equals(d.getValue())){
                return d;
            }
        }
        return null;
    }

}
