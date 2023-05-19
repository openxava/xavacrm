package org.openxava.xavacrm.tests;

import java.time.*;
import java.time.format.*;

import org.openxava.tests.*;

/**
 * 
 * @author Javier Paniza
 */

public class LeadTest extends ModuleTestBase {

	public LeadTest(String nameTest) {
		super(nameTest, "xavacrm", "Lead");
	}
	
	public void testCreateNewLead() throws Exception {
		login("admin", "admin"); 
		setValue("name", "JUnit Lead");
		String [][] status = {
			{ "", "" },	
			{ "4028808d7ef9e67a017ef9e9b8080001", "A Confirmed" },
			{ "4028808d7ef9e67a017ef9e9d65e0002", "B Pending" },
			{ "4028808d7ef9e67a017ef9e9f3b30003", "X Discarded" },
			{ "4028808d7ef9e67a017ef9ea138b0004", "Z Done" }
		};
		
		assertValidValues("status.id", status);
		setValue("status.id", "4028808d7ef9e67a017ef9ea138b0004"); // Done 
		setValue("email", "antonio.rodolfo.valentino.smith@thelargestcompanyinworld.com");
		setValue("description", "This is a JUnit Lead");
		
		execute("Sections.change", "activeSection=1");
		setValue("remarks", "This is a remark");
		
		execute("Sections.change", "activeSection=2");
		setValueInCollection("activities", 0, "description", "The first activity with JUnit Lead");
		
		execute("Sections.change", "activeSection=3");
		uploadFile("attachments", "test-files/notes.txt");
		
		execute("CRUD.save");
		assertNoErrors(); 
		execute("Mode.list");
		
		assertListRowCount(1);
		assertValueInList(0, 0, "JUnit Lead");
		assertValueInList(0, 4, "Finished");
		execute("List.viewDetail", "row=0");
		
		assertValue("name", "JUnit Lead");
		assertValue("status.id", "4028808d7ef9e67a017ef9ea138b0004"); // Done 
		assertValue("email", "antonio.rodolfo.valentino.smith@thelargestcompanyinworld.com");
		assertValue("lastTouch", getCurrentDate()); // If fails change the GMT+x in serverTimezone MySQL URL
		execute("Sections.change", "activeSection=0");
		assertValue("description", "<p>This is a JUnit Lead</p>");
		execute("Sections.change", "activeSection=1");
		assertValue("remarks", "<p>This is a remark</p>");
		execute("Sections.change", "activeSection=2");
		assertCollectionColumnCount("activities", 1);
		assertValueInCollection("activities", 0, "description", "The first activity with JUnit Lead");
		assertValueInCollection("activities", 0, "date", getCurrentDate());
		execute("Sections.change", "activeSection=3");
		assertFile("attachments", 0, "text/plain");
				
		execute("CRUD.delete");
		assertNoErrors();
	}
	
	private String getCurrentDate() {
		return LocalDate.now().format(DateTimeFormatter.ofPattern("M/d/yyyy")); 
	}

}