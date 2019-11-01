import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class DOMExample {
    // Список для сотрудников из XML файла


    public static void main(String[] args) throws ParserConfigurationException, IOException, org.xml.sax.SAXException, TransformerException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Запарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("src/main/resources/University.xml"));



        // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList facultyElements = document.getDocumentElement().getElementsByTagName("faculty");
        NodeList departmentElements = document.getDocumentElement().getElementsByTagName("department");
        NodeList studentElements = document.getDocumentElement().getElementsByTagName("student");

        Node node = document.createElement("name");
        node.setTextContent("Саша");
        Node node2 = document.createElement("student");
        node2.appendChild(node);
        document.getDocumentElement().getElementsByTagName("group").item(0).appendChild(node2);

        // Перебор всех элементов employee
        for (int i = 0; i < facultyElements.getLength(); i++) {
            Node faculties = facultyElements.item(i);

            if (faculties.getNodeType() == Node.ELEMENT_NODE) {
                Element faculty = (Element) faculties;
                System.out.println("Факультет : "
                        + faculty.getElementsByTagName("name")
                        .item(0).getTextContent()
                        + faculty.getElementsByTagName("fullname")
                        .item(0).getTextContent());

            }
            for (int j = 0; j < departmentElements.getLength(); j++) {
                Node departments = departmentElements.item(j);

                if (faculties.getNodeType() == Node.ELEMENT_NODE) {
                    Element department = (Element) departments;

                    System.out.println("Кафедры:"
                            + department.getElementsByTagName("name")
                            .item(0).getTextContent()
                            + department.getElementsByTagName("fullname")
                            .item(0).getTextContent())
                    ;
                }



            }
            for (int k = 0; k < studentElements.getLength(); k++) {
                Node students = studentElements.item(k);

                if (students.getNodeType() == Node.ELEMENT_NODE) {
                    Element student = (Element) students;

                    System.out.println("Студенты:"
                            + student.getElementsByTagName("name")
                            .item(0).getTextContent())
                    ;
                }

            }


        }

        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        tf.transform(new DOMSource(document), new StreamResult(new File("src/main/resources/test1.xml")));



    }

}