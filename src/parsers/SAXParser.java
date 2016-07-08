package parsers;

import model.Drug;
import model.GroupType;
import model.versions.type.*;
import model.versions.type.Package;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.List;

/**
 * Class implements Parser - realized method parse() in with shows SAX parsing. Class also contains Drug currentDrug
 * and DrugEnum currentTag that act as temp object that transmitted between method that override method
 * from DefaultHandler
 */
public class SaxParser extends DefaultHandler implements Parser{
    private Drug currentDrug;
    private DrugEnum currentTag;

    /**
     * Method parse xml file by the View.XML_PATH root by the SAX model. Method throws ParserConfigurationException,
     * SAXException,IOException,FileNotFoundException. Firstly, clear drugs in  the Parser.
     * @return Drug List from interface Parser.
     */
    @Override
    public List<Drug> parse(){
        this.drugs.clear();
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        InputStream is = null;
        try {
            is = new FileInputStream(new File(View.XML_PATH));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SaxParser parserObject = new SaxParser();
        try {
            saxParser.parse(is, parserObject);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    /*
    @Override
    public void startDocument() throws SAXException {
        view.printMessage(View.START_DOCUMENT);
    }

    @Override
    public void endDocument() throws SAXException {
        view.printMessage(View.END_DOCUMENT);
    }
    */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals(DrugEnum.DRUG.getValue())) {
            currentDrug = new Drug();
            currentDrug.setId(attributes.getValue(0));
        } else if (qName.equals(DrugEnum.VERSIONS.getValue())) {
            currentDrug.setVersion(new VersionsType());
        } else if (qName.equals(DrugEnum.CERTIFICATE.getValue())) {
            currentDrug.getVersion().setCertificate(new Certificate());
        } else if (qName.equals(DrugEnum.PACKAGE.getValue())) {
            currentDrug.getVersion().setPackageType(new Package());
        } else if (qName.equals(DrugEnum.DOSAGE.getValue())) {
            currentDrug.getVersion().setDosage(new Dosage());
            currentDrug.getVersion().getDosage().setUnit(attributes.getValue(0));
        }
        currentTag = DrugEnum.getEnumByString(qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(DrugEnum.DRUG.getValue())) {
            drugs.add(currentDrug);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String tagContent = new String(ch, start, length).trim();
        if (currentTag != null) {
            switch (currentTag) {
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
        }
        currentTag = null;
    }
}
