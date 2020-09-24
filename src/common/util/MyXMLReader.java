package common.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class MyXMLReader {
    public Map<String, String> readXML() {
        try {
            File f = new File("config/database_server_config.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(f);
            String address = doc.getElementsByTagName("address").item(0).getTextContent();
            String dbname = doc.getElementsByTagName("dbname").item(0).getTextContent();
            String dbusername = doc.getElementsByTagName("dbusername").item(0).getTextContent();
            String dbpassword = doc.getElementsByTagName("dbpassword").item(0).getTextContent();
            Map<String, String> map = new HashMap<>();
            map.put("address", address);
            map.put("dbname", dbname);
            map.put("dbusername", dbusername);
            map.put("dbpassword", dbpassword);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}