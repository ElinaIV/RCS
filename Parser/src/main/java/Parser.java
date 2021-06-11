import org.w3c.dom.*;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

public class Parser {
    private static NodeList nodeList;
    public static boolean parse(String path) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            FileInputStream fis = new FileInputStream(path);
            InputSource is = new InputSource(fis);
            Document doc = builder.parse(is);
            Element element = doc.getDocumentElement();
            nodeList = element.getChildNodes();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public static NodeList getNodeList() {
        return nodeList;
    }
}
