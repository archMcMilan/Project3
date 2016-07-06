package controller;

import model.Drug;
import org.xml.sax.SAXException;
import parsers.SaxParser;
import view.View;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.List;

/**
 * Created by Artem on 05.07.16.
 */
public class Controller {
    View view = new View();

    public boolean validate(String xmlPath, String xsdPath) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));
            return true;
        } catch (SAXException | IOException e) {
            return false;
        }
    }

    public void processUser() throws ParserConfigurationException, SAXException, IOException {
        if (validate(View.XML_PATH, View.XSD_PATH)) {
            SaxParser saxParser = new SaxParser();
            view.printMessage(View.SAX_PARSER);

            List<Drug> drugs = saxParser.parse();
            for (Drug d : drugs) {
                view.printMessage(d.toString());
            }


        } else {
            view.printMessage(View.DOCUMENT_VALIDATION_FAIL);
        }
    }
}
