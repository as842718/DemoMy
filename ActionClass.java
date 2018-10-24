package application;

import javafx.beans.property.SimpleStringProperty;

public class ActionClass {
	
	private SimpleStringProperty UserAction;
	
	private SimpleStringProperty Locators;

	private SimpleStringProperty value;
	
	private SimpleStringProperty Comment;

	public ActionClass(String userAction, String locators, String value, String comment) {
		super();
		this.UserAction = new SimpleStringProperty(userAction);
		this.Locators = new SimpleStringProperty(locators);;
		this.value = new SimpleStringProperty(value);
		this.Comment = new  SimpleStringProperty(comment);
	}
	


	public String getUserAction() {
		return UserAction.get();
	}



	public void setUserAction(String userAction) {
		UserAction = new SimpleStringProperty(userAction);
	}



	public String getLocators() {
		return Locators.get();
	}



	public void setLocators(SimpleStringProperty locators) {
		this.Locators = locators;
	}



	public String getValue() {
		return value.get();
	}



	public void setValue(SimpleStringProperty value) {
		this.value = value;
	}



	public String getComment() {
		return Comment.get();
	}



	public void setComment(SimpleStringProperty comment) {
		Comment = comment;
	}



	
	
	

}
