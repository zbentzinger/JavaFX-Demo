package javafxdemo;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author Zach
 */
public class FXMLDocumentController implements Initializable {

    //CheckBox Items
    @FXML
    private Label pizzaOrderLabel;

    @FXML
    private CheckBox pepperoniCheckBox;

    @FXML
    private CheckBox pineappleCheckBox;

    @FXML
    private CheckBox baconCheckBox;

    public void pizzaOrderButtonPushed() {

        String order = "Toppings are:\n";

        if (pineappleCheckBox.isSelected()) {
            order += "\nPineapple";
        }

        if (pepperoniCheckBox.isSelected()) {
            order += "\nPepperoni";
        }

        if (baconCheckBox.isSelected()) {
            order += "\nBacon";
        }

        this.pizzaOrderLabel.setText(order);

    }


    // ChoiceBox Items
    @FXML
    private ChoiceBox choiceBox;

    @FXML
    private Label choiceBoxLabel;

    public void choiceBoxButtonPushed() {

        String temp = "My favorite fruit is:\n\n";
        choiceBoxLabel.setText(temp + choiceBox.getValue().toString());

    }


    // ComboBox Items
    @FXML
    private ComboBox comboBox;

    @FXML
    private Label comboBoxLabel;

    public void comboBoxWasUpdated() {

        String temp = "Course selected:\n\n";
        this.comboBoxLabel.setText(temp + comboBox.getValue().toString());

    }


    // RadioButton Items
    @FXML
    private RadioButton phpRadioButton;

    @FXML
    private RadioButton javaRadioButton;

    @FXML
    private RadioButton cSharpRadioButton;

    @FXML
    private RadioButton cPlusPlusRadioButton;

    @FXML
    private Label radioButtonLabel;

    private ToggleGroup favLangToggleGroup;

    public void radioButtonWasSelected() {

        String lang = "My favorite language is:\n\n";

        if (this.favLangToggleGroup.getSelectedToggle().equals(this.cPlusPlusRadioButton)) {
            this.radioButtonLabel.setText(lang + "C++");
        }

        if (this.favLangToggleGroup.getSelectedToggle().equals(this.cSharpRadioButton)) {
            this.radioButtonLabel.setText(lang + "C#");
        }

        if (this.favLangToggleGroup.getSelectedToggle().equals(this.javaRadioButton)) {
            this.radioButtonLabel.setText(lang + "Java");
        }

        if (this.favLangToggleGroup.getSelectedToggle().equals(this.phpRadioButton)) {
            this.radioButtonLabel.setText(lang + "PHP");
        }

    }


    // ListView and TextArea Items
    @FXML
    private ListView listView;

    @FXML
    private TextArea golfTextArea;

    public void listViewButtonPushed() {

        String textAreaString = "";

        ObservableList listOfItems = listView.getSelectionModel().getSelectedItems();

        for (Object item : listOfItems) {
            textAreaString += String.format("%s%n", (String) item);
        }

        this.golfTextArea.setText(textAreaString);

    }


    // Handles change of scene
    public void changeSceneButtonPushed(ActionEvent event) throws IOException {

        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ExampleTableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();

    }


    // Main Initialization method
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // CheckBox Items
        pizzaOrderLabel.setText("");

        //ChoiceBox Items
        choiceBoxLabel.setText("");
        choiceBox.getItems().add("Apples");
        choiceBox.getItems().add("Bananas");

        choiceBox.getItems().addAll(
                "Oranges",
                "Pears",
                "Strawberries"
        );

        choiceBox.setValue("Apples");

        //ComboBox Items
        comboBoxLabel.setText("");

        comboBox.getItems().addAll(
                "C192",
                "C193",
                "C484",
                "C195"
        );

        //RadioButton Items
        radioButtonLabel.setText("");
        favLangToggleGroup = new ToggleGroup();
        this.cPlusPlusRadioButton.setToggleGroup(favLangToggleGroup);
        this.cSharpRadioButton.setToggleGroup(favLangToggleGroup);
        this.javaRadioButton.setToggleGroup(favLangToggleGroup);
        this.phpRadioButton.setToggleGroup(favLangToggleGroup);

        //ListView Items
        listView.getItems().addAll(
                "Golf Balls",
                "Wedges",
                "Irons",
                "Tees",
                "Driver",
                "Putter"
        );

        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

}
