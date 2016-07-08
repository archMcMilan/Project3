package parsers;

import model.Drug;
import model.GroupType;
import model.versions.type.*;
import model.versions.type.Package;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import view.View;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class implements Parser - realized method parse() in with shows DOM parsing
 */
public class DomParser implements Parser{

    /**
     * Method parse xml file by the View.XML_PATH root by the DOM model. Method throws ParserConfigurationException,
     * SAXException,IOException. Firstly, clear drugs in  the Parser.
     * @return Drug List from interface Parser.
     */
    @Override
    public List<Drug> parse() {
        this.drugs.clear();
        File xmlFile = new File(View.XML_PATH);
        Drug drug;
        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = null;
        try {
            doc = builder.parse(xmlFile);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement();

        NodeList nodeList = doc.getElementsByTagName(DrugEnum.DRUG.getValue());
        for (int i = 0; i < nodeList.getLength(); i++) {
            drug = new Drug();
            Node drugNode = nodeList.item(i);
            drug.setId(String.valueOf(drugNode.getAttributes().item(0).getTextContent()));

            Element drugElement = (Element) drugNode;

            drug.setName(drugElement.getElementsByTagName(DrugEnum.NAME.getValue()).item(0).getTextContent());
            drug.setPharm(drugElement.getElementsByTagName(DrugEnum.PHARM.getValue()).item(0).getTextContent());
            drug.setGroup(GroupType.valueOf(drugElement.getElementsByTagName(
                    DrugEnum.GROUP.getValue()).item(0).getTextContent().toUpperCase()));

            NodeList analogs=drugElement.getElementsByTagName(DrugEnum.ANALOGS.getValue());
            for(int j=0;j<analogs.getLength();j++){
                drug.addAnalog(analogs.item(j).getTextContent());
            }

            Element versionElement = (Element)drugNode.getChildNodes();
            drug.setVersion(new VersionsType());
            drug.getVersion().setConsistency(Consistency.valueOf(versionElement.getElementsByTagName(
                    DrugEnum.CONSISTENCY.getValue()).item(0).getTextContent().toUpperCase()));

            Element versionChildren= (Element)versionElement.getChildNodes();

            drug.getVersion().setCertificate(new Certificate());
            drug.getVersion().getCertificate().setNumber(Long.valueOf((versionChildren).
                    getElementsByTagName(DrugEnum.NUMBER.getValue()).item(0).getTextContent()));
            if((versionChildren).getElementsByTagName(DrugEnum.ISSUE_DATE.getValue()).item(0).
                    getTextContent().matches(View.SAMPLE_DATE)){
                drug.getVersion().getCertificate().setIssueDate((versionChildren).
                        getElementsByTagName(DrugEnum.ISSUE_DATE.getValue()).item(0).getTextContent());
            }
            if((versionChildren).getElementsByTagName(DrugEnum.EXPIRY_DATE.getValue()).item(0).
                    getTextContent().matches(View.SAMPLE_DATE)){
                drug.getVersion().getCertificate().setExpiryDate((versionChildren).
                        getElementsByTagName(DrugEnum.EXPIRY_DATE.getValue()).item(0).getTextContent());
            }
            drug.getVersion().getCertificate().setOrganization((versionChildren).
                    getElementsByTagName(DrugEnum.ORGANIZATION.getValue()).item(0).getTextContent());

            drug.getVersion().setPackageType(new Package());
            drug.getVersion().getPackageType().setType((versionChildren).
                    getElementsByTagName(DrugEnum.TYPE.getValue()).item(0).getTextContent());
            drug.getVersion().getPackageType().setAmount(Integer.valueOf(versionChildren.
                    getElementsByTagName(DrugEnum.AMOUNT.getValue()).item(0).getTextContent()));
            drug.getVersion().getPackageType().setPrice(Integer.valueOf(versionChildren.
                    getElementsByTagName(DrugEnum.PRICE.getValue()).item(0).getTextContent()));

            drug.getVersion().setDosage(new Dosage());

            //find dosage attribute
            NodeList drugChild=versionElement.getChildNodes();
            NodeList versionChild;
            for(int j=0; j<drugChild.getLength();j++){
                versionChild=drugChild.item(j).getChildNodes();
                for(int k=0;k<versionChild.getLength();k++){
                    if(versionChild.item(k).hasAttributes()){
                        drug.getVersion().getDosage().setUnit(versionChild.item(k).getAttributes().item(0).getTextContent());
                    }
                }

            }
            drug.getVersion().getDosage().setDrugDosage(Integer.valueOf(versionChildren.
                    getElementsByTagName(DrugEnum.DRUG_DOSAGE.getValue()).item(0).getTextContent()));
            drug.getVersion().getDosage().setPeriod(Long.valueOf(versionChildren.
                    getElementsByTagName(DrugEnum.PERIOD.getValue()).item(0).getTextContent()));


            drugs.add(drug);
        }

        return drugs;
    }
}
