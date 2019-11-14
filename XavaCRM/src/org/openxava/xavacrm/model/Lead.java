package org.openxava.xavacrm.model;

import java.util.*;

import javax.persistence.*;
import org.openxava.annotations.*;
import org.openxava.model.*;

/**
 * @author Javier Paniza
 */

@Entity
@View(members=
	"name, status, email;" +
	"description { description }" +
	"remarks { remarks }" + 
	"activities { activities }"
)
@Tab(defaultOrder = "${status.description}") // tmp
public class Lead extends Identifiable {
	
	@Column(length=40) @Required
	private String name;
	
	@DescriptionsList
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private LeadStatus status;
	
	@Column(length=40) 
	private String email;
	
	@Stereotype("SIMPLE_HTML_TEXT") 
	@Column(columnDefinition="MEDIUMTEXT")
	private String description;

	@Stereotype("SIMPLE_HTML_TEXT") 
	@Column(columnDefinition="MEDIUMTEXT")
	private String remarks;
	
	@ElementCollection @OrderBy("date")
	private Collection<Activity> activities;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LeadStatus getStatus() {
		return status;
	}

	public void setStatus(LeadStatus status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Collection<Activity> getActivities() {
		return activities;
	}

	public void setActivities(Collection<Activity> activities) {
		this.activities = activities;
	}
	
}
