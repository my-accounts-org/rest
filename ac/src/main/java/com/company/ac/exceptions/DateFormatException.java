/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.company.ac.exceptions;

/**
 *
 * @author Administrator
 */
public class DateFormatException extends Exception {
	private String message="";
	private String description="";

	public DateFormatException(){
		super();
		message="Please provide the date in dd mmm yyyy format. i.e date - month - year\nAcceptable delimiters are \n1) forward slash (/)\n2) dot (.)\n3) hipen (-)\n4) Space ( )\nTo know about other supported formats for a DateManager class use getDescription()\n method of DateFormatException class";
		description="Please specify the date in one of the following format\n" +
				"1)MM/dd/yyyy - This is format of date is obtained when a query is made with DB2 Database\n" +
				"2)yyyy-MM-dd - This format is also supported by DB2 database\n" +
				"3)dd-MMM-yyyy - This is a readable date format ex: 15-Aug-2008, can be used to display on the client side\n" +
				"4)dd-MM-yyyy - This is also used at client side to display the dates only in digits Ex: 15-08-2008";
	}

	public String getDescription() {
		return description;
	}

    @Override
	public String getMessage() {
		return message;
	}
}
