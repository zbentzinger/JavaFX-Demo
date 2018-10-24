package javafxdemo;

import java.time.LocalDate;
import java.time.Period;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author Zach
 */
public class Person {

    private SimpleStringProperty firstName, lastName;
    private LocalDate birthday;
    private Image photo;

    public Person(String firstName, String lastName, LocalDate birthday) {

        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        photo = new Image("images/defaultimage.jpg");

    }

    public Person(String firstName, String lastName, LocalDate birthday, Image photo) {

        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.birthday = birthday;
        this.photo = photo;

    }

    public int getAge() {

        return Period.between(this.birthday, LocalDate.now()).getYears();

    }

    public Image getImage() {

        return photo;

    }

    public void setImage(Image newPhoto) {

        this.photo = newPhoto;

    }

    public String getFirstName() {

        return firstName.get();

    }

    public void setFirstName(String firstName) {

        this.firstName = new SimpleStringProperty(firstName);

    }

    public String getLastName() {

        return lastName.get();

    }

    public void setLastName(String lastName) {

        this.lastName = new SimpleStringProperty(lastName);

    }

    public LocalDate getBirthday() {

        return birthday;

    }

    public void setBirthday(LocalDate birthday) {

        this.birthday = birthday;

    }

}
