
import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

 /**
 This program demonstrates the item list builder. It prints the XML
 file corresponding to a DOM document containing a list of items.
 */
 public class ItemListBuilderDemo
 {
 public static void main(String[] args) throws Exception
 {
	 Contact contact1 = new ContactImpl("Mr Man", 1);
	 contact1.addNotes("Some notes");
	 Contact contact2 = new ContactImpl("Mr Men", 2);
	 contact2.addNotes("Some notes");
	 Set<Contacts> contacts = new LinkedHashSet<Contacts>();
	 contacts.add(new Contacts(contact1));
	 contacts.add(new Contacts(contact2));
	 
	 ItemListBuilder builder = new ItemListBuilder();
	 Document doc = builder.build(contacts);
	 DOMImplementation impl = doc.getImplementation();
	 DOMImplementationLS implLS = (DOMImplementationLS) impl.getFeature("LS", "3.0");
	 LSSerializer ser = implLS.createLSSerializer();
	 ser.getDomConfig().setParameter("format-pretty-print", true);
	 LSOutput lsOutput = implLS.createLSOutput();
	 lsOutput.setEncoding("UTF-8");
	 Writer stringWriter = new StringWriter();
	 lsOutput.setCharacterStream(stringWriter);
	 ser.write(doc, lsOutput);
	 
	 String out = stringWriter.toString();

	 
	 java.io.FileWriter fw = new java.io.FileWriter("my-file.xml");
	 fw.write(out);
	 fw.close();
		 
	System.out.println(out);
 }
 }
