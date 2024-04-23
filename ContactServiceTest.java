
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContactServiceTest {

	//create a contact and test values
	// this creates contacts with a unique ID using the ContactService addContact method
	@Test
	void testContactServiceClass() {
		ContactService.addContact("Farrik", "Barnard", "6096657878", 
				"4011 Great Ln. Party, NJ 08754");
		//System.out.println(ContactService.contactList.get(0).getId());	used for debugging
	//	assertTrue(ContactService.contactList.get(0).getContactId().equals("1003"));
		assertTrue(ContactService.contactList.get(0).getFirstName().equals("Farrik"));
		assertTrue(ContactService.contactList.get(0).getLastName().equals("Barnard"));
		assertTrue(ContactService.contactList.get(0).getPhone().equals("6096657878"));
		assertTrue(ContactService.contactList.get(0).getAddress().equals("4011 Great Ln. Party, NJ 08754"));
	}
	// confirm deletion of a contact
	@Test
	void testContactServiceDelete() {
		ContactService.addContact("Farrik", "Barnard", "6096657878", 
				"4011 Great Ln. Party, NJ 08754");
		int size = ContactService.contactList.size();
		ContactService.deleteContact("1000000003");
		assertTrue(ContactService.searchContact("1000000003") == 2);
	}
	// update first name test
	@Test
	void testContactServiceUpdateFirstName() {
		ContactService.addContact("Jack", "Skellingto", "6618886118", "Halloween Ave.");
		int size = ContactService.contactList.size();
		System.out.println(ContactService.contactList.get(size - 1).getContactId());
		System.out.println(ContactService.contactList.get(size - 1).getFirstName());
		ContactService.updateFirstName("1000000003", "Kyie");
		System.out.println(ContactService.contactList.get(size - 1).getFirstName());
		assertTrue(ContactService.contactList.get(size - 1).getFirstName().equals("Kyie"));
	}
	// test confirming update to last name
	@Test
	void testContactServiceUpdateLastName() {
		int size = ContactService.contactList.size();
		ContactService.updateLastName("1000000003", "Kyle");
		assertTrue(ContactService.contactList.get(size - 1).getLastName().equals("Kyle"));
	}
	// test confirming update to phone number
	@Test
	void testContactServiceUpdatePhone() {
		int target = 0;
		target = ContactService.findIndex("1000000003");
		ContactService.updatePhoneNum("1000000003", "6096651212");
		assertTrue(ContactService.contactList.get(target).getPhone().equals("6096651212"));
	}
	// test confirming update to address
	@Test
	void testContactServiceUpdateAddress() {
		int target = 0;
		target = ContactService.findIndex("1000000003");
		ContactService.updateAddress("1000000003", "34 OK St. Looky, NH 00089");
		assertTrue(ContactService.contactList.get(target).getAddress().equals("34 OK St. Looky, NH 00089"));
	}
	
	// test to confirm unique ID
	@Test
	void testContactServiceUniqueId() {
		Contact newContact = new Contact("17", "New", "Name", "6097876545", "1 Man Way TN 23253");
		ContactService.addContact(newContact);
		Contact duplicateId = new Contact("17", "New", "Name", "6097876545", "1 Man Way TN 23253");
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ContactService.addContact(duplicateId);
		});
	}

}