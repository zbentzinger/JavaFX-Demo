package javafxdemo;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Zach
 */
public class ExampleTableViewController implements Initializable {

    // Handles change of scene back to first one.
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }


    //TableView Items
    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private TableColumn<Person, String> lastNameColumn;

    @FXML
    private TableColumn<Person, LocalDate> birthDateColumn;

    public ObservableList<Person> getPeople() {

        ObservableList<Person> people = FXCollections.observableArrayList();
        people.add(new Person("Frank", "Sinatra", LocalDate.of(1926, Month.DECEMBER, 12)));
        people.add(new Person("Rebecca", "Fergusson", LocalDate.of(1986, Month.JULY, 21)));
        people.add(new Person("Mr.", "T", LocalDate.of(1952, Month.MAY, 21)));

        return people;

    }


    // Update firstName of person in table cell.
    public void changeFirstNameCellEvent(CellEditEvent editedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setFirstName(editedCell.getNewValue().toString());

    }


    // Update lastName of person in table cell.
    public void changeLastNameCellEvent(CellEditEvent editedCell) {

        Person personSelected = tableView.getSelectionModel().getSelectedItem();
        personSelected.setLastName(editedCell.getNewValue().toString());

    }


    // Add new person
    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private DatePicker birthdayDatePicker;

    public void newPersonButtonPushed() {

        Person newPerson = new Person(
                firstNameTextField.getText(),
                lastNameTextField.getText(),
                birthdayDatePicker.getValue()
        );

        tableView.getItems().add(newPerson);

    }


    // Delete person from TableView
    public void deletePersonsButtonPushed() {

        ObservableList<Person> selectedRows, allPeople;
        allPeople = tableView.getItems();

        selectedRows = tableView.getSelectionModel().getSelectedItems();

        for (Person person: selectedRows) {

            allPeople.remove(person);

        }

    }


    // Exchange data between scenes
    public void changeSceneToDetailPerson(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("PersonView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        PersonViewController controller = loader.getController();
        controller.initData(tableView.getSelectionModel().getSelectedItem());

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }

    // Main Controller Execution
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //TableView Columns
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        birthDateColumn.setCellValueFactory(new PropertyValueFactory<Person, LocalDate>("birthday"));

        // Populate TableView
        tableView.setItems(getPeople());

        //Update table to allow first and last names to be editable.
        tableView.setEditable(true);
        firstNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        // Allow Table to select multiple rows at once.
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

}
