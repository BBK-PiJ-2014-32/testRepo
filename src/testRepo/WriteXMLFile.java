package testRepo;
import contactManager.*;

import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 



import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
 
public class WriteXMLFile {
 
	public static void main(String argv[]) {
 
	  try {
 
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
 
		Contact contact1 = new ContactImpl("Mr Man", 1);
		contact1.addNotes("Some notes");
		Contact contact2 = new ContactImpl("Mr Men", 2);
		contact1.addNotes("Some notes");
		Set<Contact> contactSet = new LinkedHashSet();
		contactSet.add(contact1);
		contactSet.add(contact2);
		
		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("CONTACTMANAGER");
		doc.appendChild(rootElement);
 
		Element contact = doc.createElement("CONTACT");
		rootElement.appendChild(contact);
 
		Attr attr = doc.createAttribute("id");
		attr.setValue(String.valueOf(contact1.getId()));
		contact.setAttributeNode(attr);

 
		Element name = doc.createElement("Name");
		name.appendChild(doc.createTextNode(contact1.getName()));
		contact.appendChild(name);

		Element notes = doc.createElement("notes");
		notes.appendChild(doc.createTextNode(contact1.getNotes()));
		contact.appendChild(notes);

		contact = doc.createElement("CONTACT");
		rootElement.appendChild(contact);

		attr = doc.createAttribute("id");
		attr.setValue(String.valueOf(contact2.getId()));
		contact.

 
		//name = doc.createElement("Name");
	//	name.appendChild(doc.createTextNode(contact2.getName()));
	//	contact.appendChild(name);

		//notes = doc.createElement("notes");
	//	notes.appendChild(doc.createTextNode(contact2.getNotes()));
	//	contact.appendChild(notes);

			
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("file.xml"));
 
		// Output to console for testing
		// StreamResult result = new StreamResult(System.out);
 
		transformer.transform(source, result);
 
		System.out.println("File saved!");
 
	  } catch (ParserConfigurationException pce) {
		pce.printStackTrace();
	  } catch (TransformerException tfe) {
		tfe.printStackTrace();
	  }
	}
}
