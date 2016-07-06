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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Artem on 06.07.16.
 */
public class SaxParser extends DefaultHandler {
    private List<Drug> drugs;
    private Drug currentDrug;
    private DrugEnum currentTag;
    private View view;

    public SaxParser() {
        drugs = new ArrayList<>();
        view=new View();
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public List<Drug> parse() {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            SAXParser saxParser = saxParserFactory.newSAXParser();
            InputStream is = new FileInputStream(new File(View.XML_PATH));
            SaxParser parserObject = new SaxParser();
            saxParser.parse(is, parserObject);
            drugs = parserObject.getDrugs();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }
        return drugs;
    }

    @Override
    public void startDocument() throws SAXException {
        view.printMessage(View.START_DOCUMENT);
    }

    @Override
    public void endDocument() throws SAXException {
        view.printMessage(View.END_DOCUMENT);
    }

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
        String text = new String(ch, start, length).trim();
        if (currentTag != null) {
            switch (currentTag) {
                case NAME:
                    currentDrug.setName(text);
                    break;
                case PHARM:
                    currentDrug.setPharm(text);
                    break;
                case GROUP:
                    currentDrug.setGroup(GroupType.valueOf(text.toUpperCase()));
                    break;
                case ANALOGS:
                    currentDrug.addAnalog(text);
                    break;
                case CONSISTENCY:
                    currentDrug.getVersion().setConsistency(Consistency.valueOf(text.toUpperCase()));
                    break;
                case NUMBER:
                    currentDrug.getVersion().getCertificate().setNumber(Long.valueOf(text));
                    break;
                case ISSUE_DATE:
                    currentDrug.getVersion().getCertificate().setIssueDate(text);
                    break;
                case EXPIRY_DATE:
                    currentDrug.getVersion().getCertificate().setExpiryDate(text);
                    break;
                case ORGANIZATION:
                    currentDrug.getVersion().getCertificate().setOrganization(text);
                    break;
                case TYPE:
                    currentDrug.getVersion().getPackageType().setType(text);
                    break;
                case AMOUNT:
                    currentDrug.getVersion().getPackageType().setAmount(Integer.valueOf(text));
                    break;
                case PRICE:
                    currentDrug.getVersion().getPackageType().setPrice(Integer.valueOf(text));
                    break;
                case UNITS:
                    currentDrug.getVersion().getDosage().setUnit(Dosage.Units.valueOf(text.toUpperCase()));
                    break;
                case DRUG_DOSAGE:
                    currentDrug.getVersion().getDosage().setDrugDosage(Integer.valueOf(text));
                    break;
                case PERIOD:
                    currentDrug.getVersion().getDosage().setPeriod(Long.valueOf(text));
                    break;
            }
        }
        currentTag = null;
    }
}
