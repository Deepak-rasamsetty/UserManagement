package com.example.UserManagement.Model;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
	  private Date timestamp;
	  private List<String> details;

	  public ErrorDetails(Date timestamp,List<String> details) {
	    super();
	    this.timestamp = timestamp;
	    this.details = details;
	  }

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", details=" + details + "]";
	}
	  
	  
}