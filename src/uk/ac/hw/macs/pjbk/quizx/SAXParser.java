package uk.ac.hw.macs.pjbk.quizx;

import android.util.Log;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class SAXParser {

    public SAXParser(){
    	
    }

    public static List<MCQuestion> parse(InputStream stream) {
        XMLReader xmlReader =  null;
        List<MCQuestion> init  = null;
        try {

            xmlReader = SAXParserFactory.newInstance().newSAXParser().getXMLReader();
        }catch(ParserConfigurationException e){
            Log.e("SAXParser", "Error 1");
        }catch(SAXException e){
            Log.e("SAXParser", "Error 2");
        }
            SAXHandler saxHandler = new SAXHandler();
            xmlReader.setContentHandler(saxHandler);
            InputSource in = new InputSource(stream);
        try {
            xmlReader.parse(in);
        }catch(IOException e){
            Log.e("Parsing", "Error 3");
        }catch(SAXException e){
            Log.e("Parsing", "Error 4");
        }
        init = saxHandler.getMCQuestions();
        return init;
    }

    public static void main(String[] args){
        try {
            InputStream xmlFile = new FileInputStream("C:\\Users\\Andrew\\Desktop\\example.xml");
            
            System.out.println(xmlFile.toString());
            xmlFile.close();
            SAXParser.parse(xmlFile);
        }catch(Exception e){
            System.err.println(e);
        }

    }
}