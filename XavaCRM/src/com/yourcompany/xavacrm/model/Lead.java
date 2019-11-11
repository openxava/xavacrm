package com.yourcompany.xavacrm.model;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

/**
 * This is an example of an entity.
 * 
 * Feel free feel to rename, modify or remove at your taste.
 */

@Entity
public class Lead extends Identifiable {
	
	@Column(length=40) @Required
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
