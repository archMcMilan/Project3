package parsers;

import model.Drug;
import model.versions.type.Certificate;
import model.versions.type.Dosage;
import model.versions.type.Package;
import model.versions.type.VersionsType;
import view.View;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * Class implements Parser - realized method parse() in with shows StAX parsing.
 */
public class StaxParser implements Parser{

    /**
     * Method parse xml file by the View.XML_PATH root by the StAX model. Method throws XMLStreamException,
     * FileNotFoundException,XMLStreamException. Firstly, clear drugs in  the Parser.
     * @return Drug List from interface Parser.
     */
    @Override
    public List<Drug> parse() {
        this.drugs.clear();
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
        try {
            reader = factory.createXMLStreamReader(new FileReader(View.XML_PATH));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String tagContent = "";
        Drug currentDrug = null;

        try {
            while (reader.hasNext()) {
                int type = reader.next();

                switch (type) {
                    case XMLStreamConstants.START_ELEMENT:
                        switch (DrugEnum.getEnumByString(reader.getLocalName())) {
                            case DRUG:
                                currentDrug = new Drug();
                                currentDrug.setId(reader.getAttributeValue(0));
                                break;
                            case VERSIONS:
                                currentDrug.setVersion(new VersionsType());
                                break;
                            case CERTIFICATE:
                                currentDrug.getVersion().setCertificate(new Certificate());
                                break;
                            case PACKAGE:
                                currentDrug.getVersion().setPackageType(new Package());
                                break;
                            case DOSAGE:
                                currentDrug.getVersion().setDosage(new Dosage());
                                currentDrug.getVersion().getDosage().setUnit(reader.getAttributeValue(0));
                                break;
                        }
                        break;
                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;
                    case XMLStreamConstants.END_ELEMENT:
                        DrugEnum drugEnum=DrugEnum.getEnumByString(reader.getLocalName());
                        if(drugEnum.equals(DrugEnum.DRUG)){
                            drugs.add(currentDrug);
                        }else{
                            currentDrug=SaxParser.setter(currentDrug,drugEnum,tagContent);
                        }
                        break;
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return drugs;
    }
}
