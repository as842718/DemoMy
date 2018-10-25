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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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
	@FXML
	public ImageView addImage,removeItem,play;
	@FXML
	public TreeView<String>ViewTree;
	
	
	public ObservableList<ActionClass> Actionlist = FXCollections.observableArrayList(
			
			new ActionClass("loadURL"," ","https://google.com"," "),
			new ActionClass("EnterText","searchfield","Googi","text field"),
			new ActionClass("click","searchBox"," ","click event"),
			new ActionClass("Close Browser"," ",""," ")
				);
	
	public ObservableList<String> browserlist = FXCollections.observableArrayList("CHROME","FIREFOX","IE");
	
	Image icone = new Image(getClass().getResourceAsStream("/img/4.png"));
	Image testcase_icon = new Image(getClass().getResourceAsStream("/img/edit-icon.png"));
	
	@SuppressWarnings("unchecked")
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
		
		addImage.setImage(new Image("/img/document-add-icon.png"));
		removeItem.setImage(new Image("/img/document-remove-icon.png"));
		play.setImage(new Image("/img/Play-1-Hot-icon.png"));
		
		// TreeView Folder
		TreeItem<String> root = new TreeItem<>("TestCase",new javafx.scene.image.ImageView(icone));
		TreeItem<String> node_1 = new TreeItem<>("Step",new javafx.scene.image.ImageView(testcase_icon));
		TreeItem<String> node_2= new TreeItem<String>("Step_2",new javafx.scene.image.ImageView(testcase_icon));
		TreeItem<String> node_3= new TreeItem<String>("Step_3",new javafx.scene.image.ImageView(testcase_icon));
		
		root.getChildren().addAll(node_1,node_2,node_3);
		node_1.getChildren().addAll(new TreeItem<String>("TestStep_1"), new TreeItem<>("TestStep_2"));
		ViewTree.setEditable(true);
		ViewTree.setCellFactory(TextFieldTreeCell.forTreeView());
		ViewTree.setRoot(root);
		
		
	}
		
		
	
@FXML
public void ChangeStep(TableColumn.CellEditEvent<ActionClass, String>test) {
	
	ActionClass product= tableview.getSelectionModel().getSelectedItem();
	
	product.setUserAction(test.getNewValue().toString());
	
	System.out.println(test.getNewValue().toString());
	
}


public void RunEvent(MouseEvent event) {
	
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
	//	browserlabel.setText(comboboxBrowser.getValue());

		// String value =
		if (comboboxBrowser.getValue().equalsIgnoreCase("chrome")) {
			//browserlabel.setText(comboboxBrowser.getValue());
			imageView.setImage(new Image("/img/Chrome-icon.png"));
			// w=

		} else if (comboboxBrowser.getValue().equalsIgnoreCase("firefox")) {
			//browserlabel.setText(comboboxBrowser.getValue());
			imageView.setImage(new Image("/img/firefox-icon.png"));

		} else if (comboboxBrowser.getValue().equalsIgnoreCase("ie")) {

			//browserlabel.setText(comboboxBrowser.getValue());
			imageView.setImage(new Image("/img/IE-icon.png"));
		}

	}

@FXML
public void addItems(MouseEvent event) {
		addNewitems("TestCase");
	}
	@SuppressWarnings("unused")
	public void addNewitems(String value) {
		
		if (value == null || value.trim().equals(""))
		{
			System.out.println("Item cannot be empty.");
			return;
		}

		TreeItem<String> parent = ViewTree.getSelectionModel().getSelectedItem();
		
	//	String getvalue = ViewTree.getSelectionModel().getSelectedItem().getValue();

		if(parent==null) {
			System.out.println("Select node to add this ites");
			return;
			
		}
		 TreeItem<String> newItem = new TreeItem<String>(value,new javafx.scene.image.ImageView(testcase_icon));

		 parent.getChildren().add(newItem);
		 if (!parent.isExpanded())
			     {  parent.setExpanded(true);}	}

	
	public void removeItems() {
		 removeItem();
	}
	private void removeItem()
	{
		TreeItem<String> item = ViewTree.getSelectionModel().getSelectedItem();

		if (item == null)
		{
			System.out.println("Select a node to remove.");
			return;
		}

		TreeItem<String> parent = item.getParent();
		if (parent == null )
		{
			System.out.println("Cannot remove the root node.");
		}
		else
		{
			parent.getChildren().remove(item);
		}
	}
}