package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {
	
	
	@FXML
	public TableView<ActionClass> tableview;

	@FXML
	public TableColumn<ActionClass, String> step;

	@FXML
	private TableColumn<ActionClass, String> object;

	@FXML
	private TableColumn<ActionClass, String> value;

	@FXML
	private TableColumn<ActionClass, String> comment;
	
	@FXML
	public ComboBox<String > comboboxBrowser;
	@FXML
	public Label browserlabel;
	@FXML
	public ImageView imageView;
	
	
	public ObservableList<ActionClass> Actionlist = FXCollections.observableArrayList(
			
			new ActionClass("loadURL"," ","https://google.com"," "),
			new ActionClass("EnterText","searchfield","Googi","text field"),
			new ActionClass("click","searchBox"," ","click event"),
			new ActionClass("Close Browser"," ",""," ")
				);
	
	public ObservableList<String> browserlist = FXCollections.observableArrayList("CHROME","FIREFOX","IE");
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		step.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("UserAction"));
		object.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("Locators"));
		value.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("value"));
		comment.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("comment"));
		step.setCellFactory(TextFieldTableCell.forTableColumn());
		object.setCellFactory(TextFieldTableCell.forTableColumn());
		value.setCellFactory(TextFieldTableCell.forTableColumn());
		comment.setCellFactory(TextFieldTableCell.forTableColumn());
		tableview.setItems(Actionlist);	
		tableview.setEditable(true);
		
		comboboxBrowser.getItems().addAll(browserlist);
		
		
		
		
	}
	
@FXML
public void ChangeStep(TableColumn.CellEditEvent<ActionClass, String>test) {
	
	ActionClass product= tableview.getSelectionModel().getSelectedItem();
	
	product.setUserAction(test.getNewValue().toString());
	
	System.out.println(test.getNewValue().toString());
	
}

@FXML
public void RunEvent(ActionEvent event) {
	
	ActionClass action = new ActionClass();
	
	List <List<String>> arrlist = new ArrayList<>();
	
	for(int i=0;i<tableview.getItems().size();i++) {	
		action=tableview.getItems().get(i);
		arrlist.add(new ArrayList<>());
		arrlist.get(i).add(action.UserAction.get());
		arrlist.get(i).add(action.Locators.get());
		arrlist.get(i).add(action.Comment.get());
		arrlist.get(i).add(action.value.get());
		
	}
	
	for(int i=0;i<arrlist.size();i++) {
		for(int j=0;j<arrlist.get(i).size();j++) {			
			System.out.print(arrlist.get(i).get(j)+"--->");
			
			//take data from this 
		}
		
	}
	
}

@FXML
public void changeBrowser(ActionEvent event) {
	browserlabel.setText(comboboxBrowser.getValue());
	
	//String value =
	if(browserlabel.getText().equalsIgnoreCase("chrome")) {
		browserlabel.setText(comboboxBrowser.getValue());
		imageView.setImage( new Image("/img/Chrome-icon.png"));
		//w=
		
	}else if(browserlabel.getText().equalsIgnoreCase("firefox")) {
		browserlabel.setText(comboboxBrowser.getValue());
		imageView.setImage( new Image("/img/firefox-icon.png"));
		
	}else if(browserlabel.getText().equalsIgnoreCase("ie")) {
		
		browserlabel.setText(comboboxBrowser.getValue());
		imageView.setImage( new Image("/img/IE-icon.png"));
	}
	
	
}



}
