
/**
 Describes a quantity of an article to purchase.
 */
 public class Contacts
 {
 
 private Contact theContact;

 /**
 Constructs an item from the product and quantity.
 @param aProduct the product
 @param aQuantity the item quantity
 */
 public Contacts(Contact aContact) {
	 theContact = aContact;
 }

 /**
 Formats this item.
 @return a formatted string of this line item
 */
 public String format()
 {
	 return String.format("%-30s%8.2f%5d%8.2f", theContact.getName(), theContact.getId(), theContact.getNotes());
 }
 
 public Contact getContact(){
	 return theContact;
 }
 
 }
