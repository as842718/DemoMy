package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MainController implements Initializable {
	
	
	@FXML
	private TableView<ActionClass> tableview;

	@FXML
	private TableColumn<ActionClass, String> step;

	@FXML
	private TableColumn<ActionClass, String> object;

	@FXML
	private TableColumn<ActionClass, String> value;

	@FXML
	private TableColumn<ActionClass, String> comment;
	
	
	public ObservableList<ActionClass> Actionlist = FXCollections.observableArrayList(
			
			new ActionClass("loadURL"," ","https://google.com"," "),
			new ActionClass("EnterText","searchfield","Googi","text field"),
			new ActionClass("click","searchBox"," ","click event"),
			new ActionClass("Close Browser","",""," ")
				);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		step.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("UserAction"));
		object.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("Locators"));
		value.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("value"));
		comment.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("comment"));
		step.setCellFactory(TextFieldTableCell.forTableColumn());
		value.setCellFactory(TextFieldTableCell.forTableColumn());
		tableview.setItems(Actionlist);	
		tableview.setEditable(true);
		
	}
	

public void ChangeStep(TableColumn.CellEditEvent<ActionClass, String>test) {
	
	ActionClass product= tableview.getSelectionModel().getSelectedItem();
	
	product.setUserAction(test.getNewValue().toString());
	
	System.out.println(test.getNewValue().toString());
	
}

	

}
