package org.openxava.xavacrm.model;

import java.time.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;

/**
 * 
 * @author Javier Paniza
 */

@Embeddable
public class Activity {
		
	@Required
	@Column(length=120)
	private String description;
	
	@Required
	@DefaultValueCalculator(CurrentLocalDateCalculator.class)
	private LocalDate date;


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
