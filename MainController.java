package application;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

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
	@FXML
	public TextField filter;
	@FXML
	public TableView<ObjectReop> tableObj;
	@FXML
	public TableColumn<ObjectReop, String> objectName;
	
	@FXML
	public TableColumn<ObjectReop, String> locator;
	
	
	public ObservableList<ActionClass> Actionlist = FXCollections.observableArrayList(
			
			new ActionClass("loadURL"," ","https://google.com"," "),
			new ActionClass("EnterText","searchfield","Googi","text field"),
			new ActionClass("click","searchBox"," ","click event"),
			new ActionClass("Close Browser"," ",""," ")
				);
	
	
	public ObservableList<ActionClass> newAciont = FXCollections.observableArrayList(
			
			new ActionClass(" ", " ", " ", " "),new ActionClass(" ", " ", " ", " "),
			new ActionClass(" ", " ", " ", " "),new ActionClass(" ", " ", " ", " ")
			
			
			);
	
	public ObservableList<String> browserlist = FXCollections.observableArrayList("CHROME","FIREFOX","IE");
	
	Image icone = new Image(getClass().getResourceAsStream("/img/4.png"));
	Image testcase_icon = new Image(getClass().getResourceAsStream("/img/edit-icon.png"));
	
	TreeItem<String> root;
	
	
	public void setTableView() {
		step.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("UserAction"));
		object.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("Locators"));
		value.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("value"));
		comment.setCellValueFactory(new PropertyValueFactory<ActionClass,String>("comment"));
		
		// editable
		step.setCellFactory(TextFieldTableCell.forTableColumn());
		object.setCellFactory(TextFieldTableCell.forTableColumn());
		value.setCellFactory(TextFieldTableCell.forTableColumn());
		comment.setCellFactory(TextFieldTableCell.forTableColumn());
		
		tableview.setItems(Actionlist);	
		tableview.setEditable(true);	
		
		// Edit Update
		step.setOnEditCommit((CellEditEvent<ActionClass, String> t) ->
		t.getRowValue().setUserAction(t.getNewValue()));
		
		value.setOnEditCommit((CellEditEvent<ActionClass, String> t) ->
		t.getRowValue().setValue(t.getNewValue()));
		
		object.setOnEditCommit((CellEditEvent<ActionClass, String> t) ->
		t.getRowValue().setLocators(t.getNewValue()));
		
		comment.setOnEditCommit((CellEditEvent<ActionClass, String> t) ->
		t.getRowValue().setComment(t.getNewValue()));
		
		
		
		
		/*
		
		object.setCellFactory(new Callback<ActionClass,String> {
	           
			public TableCell call(TableColumn String) {
	               TableCell cell = new TableCell() {
	               // The updateItem method is what is called when setting the cell's text.  You can customize formatting here
	               @Override 
	               protected void updateItem(Object item, boolean empty) {
	                  // calling super here is very important - don't skip this!
	                  super.updateItem(item, empty);
	                  if(item != null) {
	                      setText(item.toString());
	                  }
	               }
	           };
	           
	           ContextMenu menu = new ContextMenu();
	           
	           
	           MenuItem item = new MenuItem("View Person");
	           item.setOnAction(new EventHandler() {
	               public void handle(ActionEvent t) {
	                   Person person = tableView.getSelectionModel().getSelectedItem();
	                   System.out.println("person" + person.getId().intValue());
	               }
	           });
	           menu.getItems().addAll(item);
	           
	           cell.setOnClick(new EventHandler() {
	               public void handle(MouseEvent t) {
	                   if(t.getClickCount() == 2) {
	                       Person person = (Person) tableView.getItems().get(((TableCell)t.getSource()).getIndex());
	                       System.out.println("person" + person.getId().intValue());
	                   }
	               }
	           };
	           
	           return cell;
	       }
	    });
		
		*/
	}
		/*
		 public void startEdi1t() {
		        super.startEdit();
		        if (textField == null) {
		            createTextField();
		        }
		        setGraphic(textField);
		        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		    }*/
	
	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		setTableView();
		
		comboboxBrowser.getItems().addAll(browserlist);
		
		addImage.setImage(new Image("/img/document-add-icon.png"));
		removeItem.setImage(new Image("/img/document-remove-icon.png"));
		play.setImage(new Image("/img/Play-1-Hot-icon.png"));
		
		// TreeView Folder
			root = new TreeItem<>("Folder",new javafx.scene.image.ImageView(icone));
		TreeItem<String> node_1 = new TreeItem<>("TestCase",new javafx.scene.image.ImageView(icone));
		TreeItem<String> node_2= new TreeItem<String>("ObjectRepo",new javafx.scene.image.ImageView(icone));

		root.getChildren().addAll(node_1,node_2);
		
		
		node_1.getChildren().addAll(new TreeItem<String>("LogIn",new javafx.scene.image.ImageView(testcase_icon)), new TreeItem<>("VerifyUser",new javafx.scene.image.ImageView(testcase_icon)));
		ViewTree.setEditable(true);
		ViewTree.setCellFactory(TextFieldTreeCell.forTreeView());
		ViewTree.setRoot(root);
		ViewTree.setShowRoot(false);
	
		
		ViewTree.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
			
	        @Override
	        public void changed(ObservableValue observable, Object oldValue,
	                Object newValue) {
	        	
	            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
	            System.out.println("Selected Text : " + selectedItem.getValue());
	            
	            if(selectedItem.getValue()!="TestCase") {
	            	 FXCollections.copy(Actionlist, newAciont);
	 	            tableview.refresh();   
	            }   
	        }

	      });
	
	
	}
	
	
	
	
	
	
		
/*	@FXML
	private void fillTreeView() {
	    // The tree needs a root, and it needs to be a DocumentObject
	    // so we create an empty folder and hide it
	    TreeItem<String> treeRoot = new TreeItem<>("Test");

	    ObservableList<TreeItem<String>> firstLevel = FXCollections.observableArrayList();

	    for (Folder folder : logic.getFolderList()) {
	        TreeItem<String> folderNode = new TreeItem<>(folder);

	        for (FileReference file : folder.getFileList()) {
	            TreeItem<String> fileNode = new TreeItem<>(file);
	            folderNode.getChildren().add(fileNode);
	        }

	        firstLevel.add(folderNode);
	    }

	    treeRoot.setExpanded(true);


	    FilteredList<TreeItem<String>> filteredList = new FilteredList<>(firstLevel, item -> true);

	    filteredList.predicateProperty().bind(Bindings.createObjectBinding(() -> {
	        String filter = textField.getText();
	        if (filter.isEmpty()) return item -> true ;
	        return item -> item.getValue().getName().startsWith(filter) ; // your implementation may vary...
	    }, textField.textProperty());

	    Bindings.bindContent(treeRoot.getChildren(), filteredList);

	    treeNav.setRoot(treeRoot);
	    treeNav.setShowRoot(false);
	}	
	*/
@FXML
public void ChangeStep(TableColumn.CellEditEvent<ActionClass, String>test) {
	
	ActionClass product= tableview.getSelectionModel().getSelectedItem();
	
	product.setUserAction(test.getNewValue().toString());
	
	System.out.println(test.getNewValue().toString()+"test1");
	
}


public void RunEvent(MouseEvent event) {
	
	ActionClass action = new ActionClass();
	
	List <List<String>> arrlist = new ArrayList<>();
	
	// Getting Browser value
	String Browser =comboboxBrowser.getValue();
	
	if(Browser!=null) {
	System.out.println(Browser);
	
	for(int i=0;i<tableview.getItems().size();i++) {	
		action=tableview.getItems().get(i);
		arrlist.add(new ArrayList<>());
		arrlist.get(i).add(action.UserAction.get());
		arrlist.get(i).add(action.Locators.get());
		arrlist.get(i).add(action.value.get());
		arrlist.get(i).add(action.Comment.get());
	}
	
	System.out.println(arrlist);
	
	
	
	for(int i=0;i<arrlist.size();i++) {
		for(int j=0;j<arrlist.get(i).size();j++) {			
			System.out.print(arrlist.get(i).get(j)+"--->"+"\n");
		}
		
	}
	
	Thread t1 = new Thread(new UserAction ());
	t1.start();
	
	}else
		showAlertWithoutHeaderText();
	
}

	@FXML
	public void changeBrowser(ActionEvent event) {
	
		if (comboboxBrowser.getValue().equalsIgnoreCase("chrome")) {
			//browserlabel.setText(comboboxBrowser.getValue());
			imageView.setImage(new Image("/img/Chrome-icon.png"));
		
		} else if (comboboxBrowser.getValue().equalsIgnoreCase("firefox")) {
			
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
	

	
	public void closeApp(ActionEvent event) {
		Platform.exit();
		System.exit(0);
	}

	
@FXML
public void OpenApp(ActionEvent event) {
	
	FileChooser fc  = new FileChooser();
	fc.getExtensionFilters().add(
		new ExtensionFilter("Text File", "*.txt")
			);
	File selectFile= fc.showOpenDialog(null);
	
	if(selectFile!=null) {
		System.out.println("File Found");
	}else {
		
		System.out.println("File not found");
	}
}
	

public void addnewSuiteFolder() {
	TreeItem<String> newSuite = new TreeItem<>("TestSuite",new javafx.scene.image.ImageView(icone));
	root.getChildren().add(newSuite);
	ViewTree.setEditable(true);
	ViewTree.setCellFactory(TextFieldTreeCell.forTreeView());	
}

public void addnewTestDatafolder() {
	TreeItem<String> newSuite = new TreeItem<>("TestData",new javafx.scene.image.ImageView(icone));
	root.getChildren().add(newSuite);
	ViewTree.setEditable(true);
	ViewTree.setCellFactory(TextFieldTreeCell.forTreeView());	
}

public void addnewTestCasefolder() {
	TreeItem<String> newTestcase = new TreeItem<>("TestCase",new javafx.scene.image.ImageView(icone));
	root.getChildren().add(newTestcase);
	ViewTree.setEditable(true);
	ViewTree.setCellFactory(TextFieldTreeCell.forTreeView());	
}

public void showAlertWithoutHeaderText() {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Test Connection");

    // Header Text: null
    alert.setHeaderText(null);
    alert.setContentText("Please select browser type");

    alert.showAndWait();
}

public void resultview() throws IOException {
	
Stage primaryStage = new Stage();
	FXMLLoader loader  = new FXMLLoader(getClass().getResource("/Result.fxml"));
	Parent root = (Parent)loader.load();
	
	//screen
	
	Scene scene = new Scene(root);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	primaryStage.setTitle("Report");
	primaryStage.setScene(scene);
	primaryStage.show();

	
}
	
}
