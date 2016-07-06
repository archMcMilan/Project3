package view;

/**
 * Created by Artem on 05.07.16.
 */
public class View {
    public static final String SAMPLE_DATE = "[\\d]{2}[\\.][\\d]{2}[\\.][\\d]{4}";
    public static final String START_DOCUMENT="Start parsing...";
    public static final String END_DOCUMENT="End parsing...";

    public static final String XML_PATH = System.getProperty("user.dir")+"/src/xml/Medicine.xml";
    public static final String XSD_PATH = System.getProperty("user.dir")+"/src/xml/MedicineScheme.xsd";
    public static final String SAX_PARSER = "SAX Parser:";
    public static final String STAX_PARSER = "STAX Parser:";
    public static final String DOM_PARSER = "DOM Parser:";
    public static final String DOCUMENT_VALIDATION_FAIL = "Document is invalid. Please, try again";

    /**
     * print simple message
     * @param message
     */
    public void printMessage(String message) {
        System.out.println(message);
    }
}
