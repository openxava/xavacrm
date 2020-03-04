package org.openxava.xavacrm.tests;

import java.time.*;
import java.time.format.*;
import java.util.*;

import org.openxava.tests.*;

/**
 * 
 * @author Javier Paniza
 */

public class LeadTest extends ModuleTestBase {

	public LeadTest(String nameTest) {
		super(nameTest, "XavaCRM", "Lead");
	}
	
	public void testCreateNewLead() throws Exception {
		login("admin", "admin"); 
		setValue("name", "JUnit Lead");
		String [][] status = {
			{ "", "" },	
			{ "402880466e662e7e016e66349fbb0002", "A Confirmed" },
			{ "402880466e662e7e016e6634d9c20003", "B Pending" },
			{ "402880466e662e7e016e66364e5f0004", "X Discarded" },
			{ "402880466e662e7e016e663666d40005", "Z Done" }
		};
		
		assertValidValues("status.id", status);
		setValue("status.id", "402880466e662e7e016e663666d40005"); 
		setValue("email", "junitlead@gmail.com");
		setValue("description", "This is a JUnit Lead");
		
		execute("Sections.change", "activeSection=1");
		setValue("remarks", "This is a remark");
		
		execute("Sections.change", "activeSection=2");
		setValueInCollection("activities", 0, "description", "The first activity with JUnit Lead");
		
		execute("CRUD.save");
		execute("Mode.list");
		
		assertListRowCount(1);
		assertValueInList(0, 0, "JUnit Lead");
		assertValueInList(0, 4, "Finished");
		execute("List.viewDetail", "row=0");
		
		assertValue("name", "JUnit Lead");
		assertValue("status.id", "402880466e662e7e016e663666d40005"); 
		assertValue("email", "junitlead@gmail.com");
		assertValue("lastTouch", getCurrentDate()); 
		execute("Sections.change", "activeSection=0");
		assertValue("description", "This is a JUnit Lead");
		execute("Sections.change", "activeSection=1");
		assertValue("remarks", "This is a remark");
		execute("Sections.change", "activeSection=2");
		assertCollectionColumnCount("activities", 1);
		assertValueInCollection("activities", 0, "description", "The first activity with JUnit Lead");
		assertValueInCollection("activities", 0, "date", getCurrentDate());
				
		execute("CRUD.delete");
		assertNoErrors();
	}
	
	private String getCurrentDate() {
		return LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).withLocale(Locale.ENGLISH));
	}

}