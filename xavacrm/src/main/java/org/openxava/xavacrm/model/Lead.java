package org.openxava.xavacrm.model;

import java.time.*;
import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.model.*;
import org.openxava.util.*;

/**
 * @author Javier Paniza
 */

@Entity
@View(members=
	"name, status, email, lastTouch;" + 
	"description { description }" +
	"remarks { remarks }" + 
	"activities { activities }" +
	"attachments { attachments }" 
)
@Tab(
	properties= "name, email, lastTouch, status.description, status.finished, description, remarks",	
	defaultOrder = "${status.description}"
) 
public class Lead extends Identifiable {
	
	@Column(length=40) @Required
	private String name;
	
	@DescriptionsList
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private LeadStatus status;
	
	@Column(length=80) @DisplaySize(40) 
	private String email;
	
	@ReadOnly
	private LocalDate lastTouch; 
	
	@Stereotype("SIMPLE_HTML_TEXT") 
	@Column(columnDefinition="MEDIUMTEXT")
	private String description;

	@Stereotype("SIMPLE_HTML_TEXT") 
	@Column(columnDefinition="MEDIUMTEXT")
	private String remarks;
	
	@ElementCollection @OrderBy("date")
	private Collection<Activity> activities;
	
	@Stereotype("FILES")
	private String attachments; 

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
		Activity last = (Activity) XCollections.last(activities);
		if (last == null || last.getDate() == null) return;
		lastTouch = last.getDate();
	}

	public LocalDate getLastTouch() {
		return lastTouch;
	}

	public void setLastTouch(LocalDate lastTouch) {
	}

	public String getAttachments() {
		return attachments;
	}

	public void setAttachments(String attachments) {
		this.attachments = attachments;
	}
	
}
