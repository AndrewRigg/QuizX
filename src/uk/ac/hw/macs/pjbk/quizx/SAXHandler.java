package uk.ac.hw.macs.pjbk.quizx;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    private List<MCQuestion> multipleChoiceQuestions;
    private String tempVal;
    private MCQuestion tempQuestion;

    public SAXHandler() {
        multipleChoiceQuestions = new ArrayList<MCQuestion>();
    }

    public List<MCQuestion> getMCQuestions() {
        return multipleChoiceQuestions;
    }

    public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
        tempVal = "";
        if (qName.equalsIgnoreCase("question")) {
            tempQuestion = new MCQuestion(tempVal);
        }else if(qName.equalsIgnoreCase("questions")){
            tempQuestion = new MCQuestion(tempVal);
        }
    }
    
    public void characters(char[] ch, int start, int length) throws SAXException {
        tempVal = new String(ch, start, length);
    }
  
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("question")) {
            multipleChoiceQuestions.add(tempQuestion);
        }
        }
}