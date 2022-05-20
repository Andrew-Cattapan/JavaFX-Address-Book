/*
 * Andrew Cattapan
 * Address Book Program
 * May 20th, 2022
 */

package addressbook;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddressBook extends Application {
        
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        
        //Creates a BorderPane, masterPane, that every other pane will go inside
        BorderPane masterPane = new BorderPane();
        
        /*Variable Insets, smallBuffer, is created to maintain continuity of
        spacing*/
        Insets smallBuffer = new Insets(5, 5, 5, 5);

        /*Creates an HBox, nameHbox, which stores all of the horizontal name
        elements in the first row*/
        HBox nameHbox = new HBox(4);
        nameHbox.setPadding(smallBuffer);        
        
        /*Creates a TextField, firstName, and Label for the first name and puts
        them all in a BorderPane, paneForFirstName
        
        Prompts the user to enter a first name*/
        BorderPane paneForFirstName = new BorderPane();
        paneForFirstName.setLeft(new Label("\tFirst Name:\t   "));
        TextField firstName = new TextField();
        firstName.setPromptText("(John or Mary)");
        firstName.setAlignment(Pos.CENTER_LEFT);
        paneForFirstName.setRight(firstName);
        
        /*Creates a TextField, lastName, and Label for the last name and puts
        them all in a BorderPane, paneForLastName
        
        Prompts the user to enter a last name*/
        BorderPane paneForLastName = new BorderPane();
        paneForLastName.setLeft(new Label("\tLast Name:   "));
        TextField lastName = new TextField();
        lastName.setPromptText("(Smith)");
        lastName.setAlignment(Pos.CENTER_LEFT);
        paneForLastName.setRight(lastName);

        /*Creates RadioButtons rbMale and rbFemale
        Prompts the user to select either male or female*/
        RadioButton rbMale = new RadioButton("Male");
        RadioButton rbFemale = new RadioButton("Female");
        
        //Puts all of the elements in the first row together
        nameHbox.getChildren().addAll(paneForFirstName, paneForLastName, rbMale,
                rbFemale);
                        
        /*Creates a TextField, spouseName, and a Label for the spouse's first
        name and puts them all in an HBox named spouseHbox
        
        Prompts the user to enter a spouse's first name if applicable*/
        HBox spouseHbox = new HBox();
        BorderPane spouse = new BorderPane();
        TextField spouseName = new TextField();
        spouseName.setPromptText("(Mary or John)");
        spouseName.setAlignment(Pos.CENTER_LEFT);
        spouse.setPadding(smallBuffer);
        spouse.setLeft(new Label("\tSpouse's Name:  "));
        spouse.setRight(spouseName);
        spouseHbox.getChildren().addAll(spouse);
        
        /*Gets all the panes that have to do with names and puts them in a
        BorderPane, called namesPane*/
        BorderPane namesPane = new BorderPane();
        namesPane.setPadding(smallBuffer);
        namesPane.setTop(new Label("Names:"));
        namesPane.setLeft(nameHbox);
        namesPane.setBottom(spouseHbox);        
        
        /*Creates an HBox, addressHbox, which stores all of the horizontal
        address elements*/
        HBox addressHbox = new HBox(4);
        addressHbox.setPadding(smallBuffer);       
        
        /*Creates a ComboBox, homeComboBox, and a Label and puts them on the
        right and left of the BorderPane, homePane.
        
        Prompts the user to choose either home or work address*/
        BorderPane homePane = new BorderPane();
        String[] homeWork = new String[]{"Home", "Work"};
        ComboBox<String> homeComboBox = new ComboBox<>();
        homePane.setLeft(new Label("\t"));
        homePane.setRight(homeComboBox);
        ObservableList<String> homeItems =
                FXCollections.observableArrayList( homeWork );
        homeComboBox.getItems().addAll( homeItems );
        homeComboBox.getSelectionModel().select( "Home" );
        
        /*Creates a TextField, streetTextPane, and Label and puts them on the
        right and left of the BorderPane, streetTextPane.
        
        Prompts the user to enter a street address*/
        BorderPane streetTextPane = new BorderPane();
        streetTextPane.setLeft(new Label("Street: "));
        TextField streetText = new TextField();
        streetText.setPromptText("(123 Sesame Street)"); 
        streetText.setAlignment(Pos.CENTER_LEFT);
        streetTextPane.setRight(streetText);
        
        /*Creates a TextField, cityPane, and Label and puts them in another
        BorderPane, cityPane.
        
        Prompts the user to enter the city name*/
        BorderPane cityPane = new BorderPane();
        cityPane.setLeft(new Label("City: "));
        TextField cityText = new TextField();
        cityText.setPromptText("(Chicago)");
        cityText.setAlignment(Pos.CENTER_LEFT);
        cityText.setMaxWidth(100);
        cityPane.setRight(cityText);

        /*Creates a ComboBox, stateComboBox, and a Label and puts them in
        another BorderPane, statePane.
        
        Prompts the user to choose a state.*/
        BorderPane statePane = new BorderPane();
        String[] stateList = new String[] {"AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"};
        ComboBox<String> stateComboBox = new ComboBox<>();
        statePane.setLeft(new Label("State: "));
        statePane.setRight(stateComboBox);
        ObservableList<String> stateItems = FXCollections.observableArrayList( stateList );
        stateComboBox.getItems().addAll( stateItems );
        
        /*Creates a TextField, zipText, and a Label and puts them in a
        BorderPane, zipPane.
        
        Prompts the user for a zip code*/
        BorderPane zipPane = new BorderPane();
        zipPane.setLeft(new Label("Zip: "));
        TextField zipText = new TextField();
        zipText.setPromptText("(43702)");
        zipText.setAlignment(Pos.CENTER_LEFT);
        zipText.setMaxWidth(55);
        zipPane.setRight(zipText);
        
        //Adds all of the horizontal elements of addressHbox together
        addressHbox.getChildren().addAll(homePane, streetTextPane, cityPane, statePane, zipPane);
        
        String[] home = {"", "", "", ""};
        String[] work = {"", "", "", ""};
        
        /*Saves and switches out the address values when selecting Home or Work
        in the ComboBox*/
        homeComboBox.setOnAction( e->
        {
            String userChoice = homeComboBox.getValue();
            
            if(userChoice == "Home")
            {
                work[0] = streetText.getText();
                work[1] = cityText.getText();
                work[2] = stateComboBox.getValue();
                work[3] = zipText.getText();
                streetText.clear();
                cityText.clear();
                zipText.clear();
                streetText.appendText(home[0]);
                cityText.appendText(home[1]);
                stateComboBox.getSelectionModel().select(home[2]);
                zipText.appendText(home[3]);
            }
            if(userChoice == "Work")
            {
                home[0] = streetText.getText();
                home[1] = cityText.getText();
                home[2] = stateComboBox.getValue();
                home[3] = zipText.getText();
                streetText.clear();
                cityText.clear();
                zipText.clear();
                streetText.appendText(work[0]);
                cityText.appendText(work[1]);
                stateComboBox.getSelectionModel().select(work[2]);
                zipText.appendText(work[3]); 
            }
        });
        
        //Puts all panes related to the address together in addressPane
        BorderPane addressPane = new BorderPane();
        addressPane.setPadding(smallBuffer);
        addressPane.setTop(new Label("Address:"));
        addressPane.setLeft(addressHbox);
        
        /*Creates an HBox, phoneHbox to contain all of the horizontal elements 
        related to the phone number*/
        HBox phoneHbox = new HBox();
        phoneHbox.setPadding(smallBuffer);
        
        /*Creates a TextField, phone1Text, and Label and puts them all in a
        BorderPane, phone1Pane.
        
        Prompts the user for the home phone number*/
        BorderPane phone1Pane = new BorderPane();
        TextField phone1Text = new TextField();
        phone1Text.setMaxWidth(100);
        phone1Text.setPromptText("(123-456-7890)");
        phone1Pane.setLeft(new Label("\tHome: "));
        phone1Pane.setRight(phone1Text);
        
        /*Creates a ComboBox, phone2ComboBox, and TextField, phone2Text, and
        puts them in a BorderPane, phone2Pane.
        
        Prompts the user for the second phone number and the type (Mobile or
        Work)*/
        BorderPane phone2Pane = new BorderPane();
        String[] phoneMobileWork = new String[]{"Mobile", "Work"};
        ComboBox<String> phone2ComboBox = new ComboBox<>();        
        ObservableList<String> phone2Items = FXCollections.observableArrayList( phoneMobileWork );
        phone2ComboBox.getItems().addAll( phone2Items );
        phone2ComboBox.setMaxWidth(80);
        phone2ComboBox.getSelectionModel().select( "Mobile" );
        TextField phone2Text = new TextField();
        phone2Text.setMaxWidth(100);
        phone2Text.setPromptText("(123-456-7890)");
        phone2Pane.setLeft(new Label("\t"));
        phone2Pane.setCenter(phone2ComboBox);
        phone2Pane.setRight(phone2Text);
        
        //Adds phone1Pane and phone2Pane to phoneHbox
        phoneHbox.getChildren().addAll(phone1Pane, phone2Pane);
        
        String[] phoneType = {"", ""};
        
        /*Saves and switches phone numbers when the user selects mobile or work
        phone from the combobox*/
        phone2ComboBox.setOnAction( e->
        {
            String userChoice = phone2ComboBox.getValue();
            
            if(userChoice == "Work")
            {
                phoneType[0] = phone2Text.getText();
                phone2Text.clear();
                phone2Text.appendText(phoneType[1]);
            }
            if(userChoice == "Mobile")
            {
                phoneType[1] = phone2Text.getText();
                phone2Text.clear();
                phone2Text.appendText(phoneType[0]);
            }
        });
        
        //Adds the phone panes together
        BorderPane phonePane = new BorderPane();
        phonePane.setPadding(smallBuffer);
        phonePane.setTop(new Label("Phone:"));
        phonePane.setLeft(phoneHbox);
        
        //Creates and adds the memo pane
        BorderPane memoPane = new BorderPane();
        memoPane.setPadding(smallBuffer);
        TextArea memoText = new TextArea();
        memoText.setMaxWidth(500);
        memoText.setMaxHeight(70);
        memoText.setPromptText("(Place any miscellaneous info here)");
        memoPane.setTop(new Label("Memo: "));
        HBox memoHbox = new HBox();
        memoHbox.setPadding(smallBuffer);
        memoHbox.getChildren().addAll(new Label("\t"), memoText);
        memoPane.setLeft(memoHbox);
        
        //Gathers all panes that are aligned to the left
        VBox leftAligned = new VBox();
        leftAligned.getChildren().addAll(namesPane, addressPane, phonePane, memoPane);
                
        BorderPane rightAligned = new BorderPane();
        
        //Buttons for save/load/exit
        VBox optionVbox = new VBox();
        Button save = new Button("Save");
        save.setMaxWidth(50);
        Button load = new Button("Load");
        load.setMaxWidth(50);
        Button cancel = new Button("Exit");
        cancel.setMaxWidth(50);
        optionVbox.getChildren().addAll(save, load, cancel);
        
        //Text fields for save/load and a button for clear
        VBox inputVbox = new VBox();
        TextField saveText = new TextField();
        saveText.setMaxWidth(80);
        saveText.setPromptText("(Filename)");
        TextField loadText = new TextField();        
        loadText.setMaxWidth(80);
        loadText.setPromptText("(Filename)");
        Button clear = new Button("Clear");
        clear.setMaxWidth(80);
        inputVbox.getChildren().addAll(saveText, loadText, clear);
        
        //Gathers all the save/load/clear buttons and their text fields
        HBox buttonHbox = new HBox();
        buttonHbox.setPadding(smallBuffer);
        buttonHbox.getChildren().addAll(optionVbox, inputVbox);
        
        //Gathers all properties of the image uploader
        BorderPane photoPane = new BorderPane();
        photoPane.setPadding(smallBuffer);
        String currentDirectory = System.getProperty("user.dir");
        ImageView photoView = new ImageView();
        Button loadPhoto = new Button("Load Photo");
        loadPhoto.setMaxWidth(125);
        photoView.setFitHeight(125);
        photoView.setFitWidth(125);
        TextField photoText = new TextField();
        photoText.setPromptText("(image.jpg)");
        photoText.setMaxWidth(125);
        photoPane.setTop(photoView);
        photoPane.setLeft(photoText);
        photoPane.setBottom(loadPhoto);
        
        rightAligned.setTop(photoPane);
        rightAligned.setBottom(buttonHbox);
        
        //Creates a TextArea that will provide messages to the user
        TextArea info = new TextArea();
        info.setMaxHeight(60);
        
        //Add right, left, and info TextArea to the masterPane
        masterPane.setLeft(leftAligned);
        masterPane.setRight(rightAligned);
        masterPane.setBottom(info);
        Scene scene = new Scene(masterPane, 800, 350);
        
        primaryStage.setTitle("Address Book");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Prevents male and female from being selected at the same time
        ToggleGroup toggleMaleFemale = new ToggleGroup();
        rbMale.setToggleGroup(toggleMaleFemale);
        rbFemale.setToggleGroup(toggleMaleFemale);
        
        //Resets all the data entered so the user can easily enter a new person
        clear.setOnAction(e ->
        {
            firstName.clear();
            lastName.clear();
            spouseName.clear();
            rbMale.setSelected(false);
            rbFemale.setSelected(false);
            for(int i = 0; i < work.length; i++)
            {
                home[i] = "";
                work[i] = "";
            }
            stateComboBox.getSelectionModel().select("");
            streetText.clear();
            cityText.clear();
            zipText.clear();
            phone1Text.clear();
            for(int i = 0; i < phoneType.length; i++)
            {
                phoneType[i] = "";
            }
            phone2Text.clear();
            memoText.clear();
            photoText.clear();
            photoView.setImage(null);
        });
        
        /*Fires button immediately to cause .getText() method to work with all
        TextFields even when empty*/
        clear.fire();

        //Closes the window
        cancel.setOnAction(e ->
        {
            info.appendText("Exiting...\n");
            System.exit(0);
        });
        
        //Saves all the fields to a file that is specified in the save TextField
        save.setOnAction( e->
        {
            String saveFile = saveText.getText();
            DataOutputStream outputFile;
            try
            {
                outputFile = new DataOutputStream( new FileOutputStream( saveFile ));
                
                outputFile.writeUTF(firstName.getText());
                outputFile.writeUTF(lastName.getText());
                outputFile.writeUTF(spouseName.getText());
                
                if(rbMale.isSelected())
                {
                    outputFile.writeChar('M');
                }
                else if(rbFemale.isSelected())
                {
                    outputFile.writeChar('F');
                }
                else
                {
                    outputFile.writeChar('N');
                }
                
                homeComboBox.getSelectionModel().select( "Home" );

                outputFile.writeUTF(streetText.getText());
                outputFile.writeUTF(cityText.getText());
                outputFile.writeUTF(stateComboBox.getValue());
                outputFile.writeUTF(zipText.getText());
                for(int i = 0; i < work.length; i++)
                {
                    outputFile.writeUTF(work[i]);
                }
                phone2ComboBox.getSelectionModel().select("Mobile");
                outputFile.writeUTF(phone1Text.getText());
                for(int i = 0; i < phoneType.length; i++)
                {
                    outputFile.writeUTF(phoneType[i]);
                }
                outputFile.writeUTF(memoText.getText());
                outputFile.writeUTF(photoText.getText());
                outputFile.close();
                info.appendText("Your file is saved as '" + saveFile + "'\n");
            }
            catch( IOException exception )
            {
                info.appendText("IOException error.\n");
            }
            catch( NullPointerException exception)
            {
                info.appendText("Tried referencing an empty field.\n");
            }
            
        });

        /*Reads the file specified by the load TextField (if there is one) and
        puts the data in their appropriate spots*/
        load.setOnAction( e->
        {
            String inputName = loadText.getText();
            
            info.appendText("Loading '" + inputName + "'...\n");
            try
            {
                clear.fire();
                DataInputStream inputStream = new DataInputStream(new FileInputStream(inputName));
                while(true)
                {                            
                    firstName.appendText(inputStream.readUTF());
                    lastName.appendText(inputStream.readUTF());
                    spouseName.appendText(inputStream.readUTF());
                    char gender = inputStream.readChar();
                    if(gender == 'M')
                    {
                        rbMale.setSelected(true);
                    }
                    else if(gender == 'F')
                    {
                        rbFemale.setSelected(true);
                    }
                    homeComboBox.getSelectionModel().select("Home");
                    streetText.appendText(inputStream.readUTF());
                    cityText.appendText(inputStream.readUTF());
                    stateComboBox.getSelectionModel().select(inputStream.readUTF());
                    zipText.appendText(inputStream.readUTF());
                    for(int i = 0; i < work.length; i++)
                    {
                        work[i] = inputStream.readUTF();
                    }
                    phone1Text.appendText(inputStream.readUTF());
                    phone2ComboBox.getSelectionModel().select("Mobile");
                    for(int i = 0; i < phoneType.length; i++)
                    {
                        phoneType[i] = inputStream.readUTF();
                    }
                    phone2Text.appendText(phoneType[0]);
                    memoText.appendText(inputStream.readUTF());
                    photoText.appendText(inputStream.readUTF());
                    loadPhoto.fire();
                }
            }
            catch(FileNotFoundException exception)
            {
                info.appendText("File Not Found. Please Try Again.\n");
            }
            catch(EOFException exception)
            {
                info.appendText("Finished Loading " + inputName +".\n");
            }
            catch(IOException exception)
            {
                info.appendText("I/O Exception occured.\n");
            }

        });

        //Loads the photo specified in the photo TextField if there is one
        loadPhoto.setOnAction( e->
        {
            if(photoText.getText() != "")
            {
                try
                {
                    Image newPhoto = new Image(currentDirectory + "\\" + photoText.getText());
                    photoView.setImage(newPhoto);
                }
                catch (IllegalArgumentException exception)
                {
                    info.appendText("Photo was not found.\n");
                }
            }
            else
            {
                photoView.setImage(null);
                info.appendText("Photo cleared.\n");
            }
        });
    }

    
    public static void main(String[] args) {
        launch(args);
    }
}

