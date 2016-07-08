package parsers;

import model.Drug;
import model.GroupType;
import model.versions.type.*;
import model.versions.type.Package;
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
                        switch (DrugEnum.getEnumByString(reader.getLocalName())) {
                            case DRUG:
                                drugs.add(currentDrug);
                                break;
                            case NAME:
                                currentDrug.setName(tagContent);
                                break;
                            case PHARM:
                                currentDrug.setPharm(tagContent);
                                break;
                            case GROUP:
                                currentDrug.setGroup(GroupType.valueOf(tagContent.toUpperCase()));
                                break;
                            case ANALOGS:
                                currentDrug.addAnalog(tagContent);
                                break;
                            case CONSISTENCY:
                                currentDrug.getVersion().setConsistency(Consistency.valueOf(tagContent.toUpperCase()));
                                break;
                            case NUMBER:
                                currentDrug.getVersion().getCertificate().setNumber(Long.valueOf(tagContent));
                                break;
                            case ISSUE_DATE:
                                if(tagContent.matches(View.SAMPLE_DATE)){
                                    currentDrug.getVersion().getCertificate().setIssueDate(tagContent);
                                }
                                break;
                            case EXPIRY_DATE:
                                if(tagContent.matches(View.SAMPLE_DATE)){
                                    currentDrug.getVersion().getCertificate().setExpiryDate(tagContent);
                                }
                                break;
                            case ORGANIZATION:
                                currentDrug.getVersion().getCertificate().setOrganization(tagContent);
                                break;
                            case TYPE:
                                currentDrug.getVersion().getPackageType().setType(tagContent);
                                break;
                            case AMOUNT:
                                currentDrug.getVersion().getPackageType().setAmount(Integer.valueOf(tagContent));
                                break;
                            case PRICE:
                                currentDrug.getVersion().getPackageType().setPrice(Integer.valueOf(tagContent));
                                break;
                            case UNITS:
                                currentDrug.getVersion().getDosage().setUnit(tagContent.toUpperCase());
                                break;
                            case DRUG_DOSAGE:
                                currentDrug.getVersion().getDosage().setDrugDosage(Integer.valueOf(tagContent));
                                break;
                            case PERIOD:
                                currentDrug.getVersion().getDosage().setPeriod(Long.valueOf(tagContent));
                                break;
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
