import java.util.ArrayList;
import java.util.Iterator;

public class ItemListParserDemo	{

	public static void main(String[] args) throws Exception {
		ItemListParser parser = new ItemListParser();
		ArrayList<Contacts> contacts = parser.parse("my-file.xml");	
		Iterator<Contacts> it = contacts.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getContact().getId());
		}
		
			
		}
 }
