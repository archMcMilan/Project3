package parsers;

/**
 * Created by Artem on 06.07.16.
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

    public static DrugEnum getEnumByString(String value){
        for(DrugEnum d:DrugEnum.values()){
            if(value.equals(d.getValue())){
                return d;
            }
        }
        return null;
    }

}
