


import java.util.Set;
 import javax.xml.parsers.DocumentBuilder;
 import javax.xml.parsers.DocumentBuilderFactory;
 import javax.xml.parsers.ParserConfigurationException;
 import org.w3c.dom.Document;
 import org.w3c.dom.Element;
 import org.w3c.dom.Text;

 /**
Builds a DOM document for an array list of items.
*/
public class ItemListBuilder
{
private DocumentBuilder builder;
 private Document doc;
 
 public ItemListBuilder()
  throws ParserConfigurationException
  {
  DocumentBuilderFactory factory
  = DocumentBuilderFactory.newInstance();
  builder = factory.newDocumentBuilder();
  }

 /**
 Builds a DOM document for an array list of items.
  @param items the items
  @return a DOM document describing the items
  */
  public Document build(Set<Contacts> contacts)
  {
  doc = builder.newDocument();
  doc.appendChild(createContacts(contacts));
  return doc;
  }
 
  /**
  Builds a DOM element for an array list of items.
  @param items the items
  @return a DOM element describing the items
  */
  private Element createContacts(Set<Contacts> contacts)
  {
  Element e = doc.createElement("contacts");
 
  for (Contacts aContact : contacts)
  e.appendChild(createContact(aContact));
 
  return e;
  }
 
  /**
  Builds a DOM element for an item.
  @param anItem the item
  @return a DOM element describing the item
  */
  private Element createContact(Contacts aContact)
  {
  Element e = doc.createElement("Contact");
 
  e.appendChild(createContact(aContact.getContact()));
 
  return e;
  }
 
  /**
  Builds a DOM element for a product.
  @param p the product
  @return a DOM element describing the product
  */
  private Element createContact(Contact c)
  {
  Element e = doc.createElement("Contact");
 
  e.appendChild(createTextElement("ID", String.valueOf(c.getId())));
  e.appendChild(createTextElement("Name", c.getName()));
  e.appendChild(createTextElement("Notes", c.getNotes()));
 
  return e;
  }
  
  private Element createTextElement(String name, String text)
   {
   Text t = doc.createTextNode(text);
   Element e = doc.createElement(name);
   e.appendChild(t);
   return e;
   }
  }
 