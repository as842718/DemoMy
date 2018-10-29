package application;

import com.sun.javafx.runtime.SystemProperties;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
	
	private SimpleStringProperty name;
	private SimpleIntegerProperty age;
	private SimpleStringProperty sex;
	
	
	public Student(String name, int age, String sex) {
		super();
		this.name = new SimpleStringProperty(name) ;
		this.age = new SimpleIntegerProperty(age);
		this.sex = new SimpleStringProperty(sex) ;
	}


	public SimpleStringProperty getName() {
		return name;
	}


	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}


	public SimpleIntegerProperty getAge() {
		return age;
	}


	public void setAge(SimpleIntegerProperty age) {
		this.age = age;
	}


	public SimpleStringProperty getSex() {
		return sex;
	}


	public void setSex(SimpleStringProperty sex) {
		this.sex = sex;
	}
	
	

}
