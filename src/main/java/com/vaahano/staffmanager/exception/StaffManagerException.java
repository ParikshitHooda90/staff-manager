package com.vaahano.staffmanager.exception;

public class StaffManagerException extends Exception{

	private static final long serialVersionUID = 9041773419465751512L;

	public StaffManagerException(ExceptionMessage exception) {
		super(exception.getCause());
	}
	
	public StaffManagerException(Exception e) {
		super(e);
	}
	
	public static enum ExceptionMessage{
		NO_STAFF_MEMBER_EXISTS("No staff member exists"),
		NO_BUS_ASSIGNED_TO_STAFF_MEMBER("No bus assigned to staff member"),
		ILLFORMATED_STAFF_ID("staff id is ill-formated");
		
		private String cause;
		
		ExceptionMessage(String cause){
			this.cause = cause;
		}
		public String getCause() {
			return this.cause;
		}
	}
	
}
