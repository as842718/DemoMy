package application;

import java.awt.Button;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.tree.TreeCellEditor;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.ChoiceBoxTreeTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;

public class MainController implements Initializable {

	@FXML
	public ComboBox<String> combobox;

	@FXML
	public Label lablename;

	@FXML
	public ComboBox<String> comboboxLang;

	@FXML
	public ListView<String> listview;

	@FXML
	public TreeView<String> ViewTree;

	final MyNumber mynum = new MyNumber();

	@FXML
	private ProgressBar pi;

	@FXML
	private ProgressIndicator pb;
	
	@FXML
	public TextField searchText;
	
	@FXML
	public Button show;
	
	/*@FXML
	private TableView<Student> table;
	@FXML
	private TableColumn<Student,Integer>age;
	@FXML
	private TableColumn<Student, String>name;
	@FXML
	private  TableColumn<Student, String> sex;*/
	@FXML
	private ImageView chrome;

	@FXML
	public TreeTableView<Student> treetable;
	@FXML
	private TreeTableColumn<Student,Number>age;
	@FXML
	private TreeTableColumn<Student, String>name;
	@FXML
	private  TreeTableColumn<Student, String> sex;

	
	TreeItem<Student>perl = new TreeItem<Student>(new Student("asd",1,"m"));
	TreeItem<Student>per2 = new TreeItem<Student>(new Student("asd",2,"m"));
	TreeItem<Student>per3 = new TreeItem<Student>(new Student("asd",3,"m"));
	TreeItem<Student>per4 = new TreeItem<Student>(new Student(" ",3," "));	
	
	TreeItem<Student>treeroot = new TreeItem<Student>(new Student("Name",0,"Email"));	
	
	Image icone = new Image(getClass().getResourceAsStream("/img/4.png"));
	Image testcase_icon = new Image(getClass().getResourceAsStream("/img/edit-icon.png"));

	// creating list
	ObservableList<String> list = FXCollections.observableArrayList("CHROME", "FIREFOX", "IE", "SAFARI");
	

public ObservableList<Student> studentlist = FXCollections.observableArrayList(
	
	new Student("Ankur",1,"male"),new Student("Anku",2,"male")	
	,new Student("Ankr",1,"Female")	
);



	@SuppressWarnings("unchecked")
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	
		
		//DropDown
		combobox.setItems(list);
		listview.setItems(list);
		listview.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		
		// TreeView Folder
		TreeItem<String> root = new TreeItem<>("TestSuite",new javafx.scene.image.ImageView(icone));
		TreeItem<String> root2 = new TreeItem<>("Test11Suite",new javafx.scene.image.ImageView(icone));
		
		TreeItem<String> node_1 = new TreeItem<>("TestCase_1",new javafx.scene.image.ImageView(testcase_icon));
		TreeItem<String> node_2= new TreeItem<String>("TestCase_2",new javafx.scene.image.ImageView(testcase_icon));
		TreeItem<String> node_3= new TreeItem<String>("TestCase_3",new javafx.scene.image.ImageView(testcase_icon));
		
		root.getChildren().addAll(node_1,node_2,node_3);
		node_1.getChildren().addAll(new TreeItem<String>("TestStep_1"), new TreeItem<>("TestStep_2"));
		ViewTree.setRoot(root);
		ViewTree.setRoot(root2);
		
		
		// Progress bar
		mynum.setNumber(0);
		mynum.numberProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
			pb.progressProperty().bind(mynum.numberProperty());
			pi.progressProperty().bind(mynum.numberProperty());	
			}
		});
	

		treeroot.getChildren().setAll(perl,per2,per3,per4);
		
		name.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Student, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().getValue().getName();
			}
		});

		sex.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student,String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Student, String> param) {
				// TODO Auto-generated method stub
				return param.getValue().getValue().getSex();
			}
		});
		
		age.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Student,Number>, ObservableValue<Number>>() {

			@Override
			public ObservableValue<Number> call(CellDataFeatures<Student, Number> param) {
				
				return param.getValue().getValue().getAge();
			}
		});
		
		ObservableList<String> lust = FXCollections.observableArrayList();
		lust.add("As");
		lust.add("asd");
		lust.add("sd");
		
		name.setCellFactory(ComboBoxTreeTableCell.forTreeTableColumn(lust));
		
		
		//age.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		sex.setCellFactory(TextFieldTreeTableCell.forTreeTableColumn());
		
		name.setOnEditCommit( new EventHandler<TreeTableColumn.CellEditEvent<Student, String>>(){

			@Override
			public void handle(javafx.scene.control.TreeTableColumn.CellEditEvent<Student, String> event) {
				TreeItem<Student> cuurrent = treetable.getTreeItem(event.getTreeTablePosition().getRow());
				
				cuurrent.getValue().setName(event.getNewValue());
			}

		});
		treetable.setEditable(true);
		treetable.setShowRoot(false);
		
		treetable.setRoot(treeroot);
		
		/*searchText.textProperty().addListener(new ChangeListener<String>() {
			public <T> void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				tv.setPredicate(new Predicate<T>() {

					@Override
					public boolean test(T t) {
						// TODO Auto-generated method stub
						return false;
					}
				})
			
				
			}
		}*/
		
	//	age.setCellValueFactory(TreeTableColumn.CellDataFeatures<Student,String>para->para.);
		
		
	
	}
		
public void MouseClick(MouseEvent event) {

		if (event.getClickCount() == 2) {
			TreeItem<String> items = ViewTree.getSelectionModel().getSelectedItem();
			System.out.println(items.getValue());	
		}
	}

public void changeName(ActionEvent event) {
	
		lablename.setText(comboboxLang.getValue());
	}

public void AddLangToUser(ActionEvent event) {
	
if(combobox.getValue().equals("CHROME")){
		comboboxLang.getItems().clear();
	comboboxLang.getItems().addAll("Chrome70","Chrome69","Chrome68");
	chrome.setImage(new Image(getClass().getResourceAsStream("/img/chrome.png")));
}
else if (combobox.getValue().equals("FIREFOX")) {
		comboboxLang.getItems().clear();
		comboboxLang.getItems().addAll("FF56","FF40","FF41");
		chrome.setImage(new Image(getClass().getResourceAsStream("/img/ff.png")));
	}
else if (combobox.getValue().equals("IE")) {
		comboboxLang.getItems().clear();
		comboboxLang.getItems().addAll("IE11","IE10","IE12");
		chrome.setImage(new Image(getClass().getResourceAsStream("/img/IE.png")));
	}
else if (combobox.getValue().equals("SAFARI")) {
	comboboxLang.getItems().clear();
	comboboxLang.getItems().addAll("SF12","SF2","SF3");

	chrome.setImage(new Image(getClass().getResourceAsStream("/img/SF.png")));
	
}
else {comboboxLang.getItems().clear();}
}


public void addListView(ActionEvent event) {
	listview.setItems(list);	
}

public void BtnClickInc(ActionEvent event) {	
	mynum.setNumber(mynum.getNumber()+.1);
}

public void BtnClickDec(ActionEvent event) {
	mynum.setNumber(mynum.getNumber()-.1);
}

public void closeApp(ActionEvent event) {
	Platform.exit();
	System.exit(0);
}

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


public void clickshow(ActionEvent event) {
	

	List <List<String>> arrlist = new ArrayList<>();
	
	// Getting Browser value
	
	for(int i=0;i<treetable.getItems().size();i++) {	
		student =treetable.getItems().get(i);
		arrlist.add(new ArrayList<>());
		arrlist.get(i).add(Student);
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
	
}
/*
public void chnagename(TableColumn.CellEditEvent<Student, String>test) {
	
	Student product= table.getSelectionModel().getSelectedItem();
	
	product.setName(test.getNewValue().toString());
	
	System.out.println(test.getNewValue().toString());
	
}

*/

}
