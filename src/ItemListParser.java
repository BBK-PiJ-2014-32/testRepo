import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
	
public class ItemListParser {
	
	private DocumentBuilder builder;
	private XPath path;

	public ItemListParser(){
		 try{
			 DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance(); 
			 builder = dbfactory.newDocumentBuilder(); 
			 XPathFactory xpfactory = XPathFactory.newInstance(); 
			 path = xpfactory.newXPath();
		 } catch (ParserConfigurationException ex){
			ex.printStackTrace(); 
		 }
	}
	
	public ArrayList<Contacts> parse(String fileName){
			try{
				File f = new File(fileName);
				Document doc = builder.parse(f);
				 ArrayList<Contacts> items = new ArrayList<Contacts>(); 
				 int itemCount = Integer.parseInt(path.evaluate(
				 "count(/items/item)", doc)); 
				 for (int i = 1; i <= itemCount; i++) {
					 String description = path.evaluate("/items/item[" + i + "]/product/description", doc);
					 int price = Double.parseDouble(path.evaluate( "/items/item[" + i + "]/product/price", doc));
					 Contact c = new ContactImpl(description, price); 
					 int quantity = Integer.parseInt(path.evaluate("/items/item[" + i + "]/quantity", doc)); 
					 Contacts it = new Contacts(c); 
					 items.add(it);
				 	}
				 return items;
				
			} catch (SAXException ex){
				ex.printStackTrace();
			} catch (IOException ex){
				ex.printStackTrace();
			} catch (XPathExpressionException ex){
				ex.printStackTrace();
			}
		}

	
}
