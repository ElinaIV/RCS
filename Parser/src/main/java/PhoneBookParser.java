import data.Person;
import data.PhoneBook;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

public class PhoneBookParser {
    public static PhoneBook parse(String path) {
        if (Parser.parse(path)) {
            NodeList personNodes = Parser.getNodeList();
            List<Person> personList = new ArrayList<>();
            for (int i = 0; i < personNodes.getLength(); i++) {
                var personNode = personNodes.item(i);

                if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element personElem = (Element) personNode;

                    var phonesNodes = personElem.getElementsByTagName("phones").item(0);
                    List<String> phonesList = new ArrayList<>();
                    if (phonesNodes.getNodeType() == Node.ELEMENT_NODE) {
                        Element phonesElem = (Element) phonesNodes;
                        var nodesList = phonesElem.getElementsByTagName("phone");
                        for (int j = 0; j < nodesList.getLength(); ++j) {
                            phonesList.add(nodesList.item(j).getTextContent());
                        }
                    }

                    Person person = new Person(
                            personElem.getElementsByTagName("name").item(0).getTextContent(),
                            personElem.getElementsByTagName("email").item(0).getTextContent(),
                            phonesList
                    );
                    personList.add(person);
                }
            }

            return new PhoneBook(personList);
        }

        return null;
    }

    public static void main(String[] args) {
        var phoneBook = parse("src/main/resources/PhoneBook.xml");
        System.out.println("");
    }
}