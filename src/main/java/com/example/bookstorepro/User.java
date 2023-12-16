package com.example.bookstorepro;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -81230957409593984L;
    private transient SimpleStringProperty firstName, lastName, email, username, password, role;
    private String firstName1, lastName1, email1, username1, password1, role1;

    public User(String firstName, String lastName, String email, String username, String role, String password){
        this.firstName1 = firstName;
        setFirstName(firstName1);
        this.lastName1 = lastName;
        setLastName(lastName);
        this.email1 = email;
        setEmail(email);
        this.username1 = username;
        setUsername(username);
        this.role1 = role;
        setRole(role);
        this.password1 = password;
    }

    public String getFirstName() {
        if(firstName == null){
            setFirstName(firstName1);
            setPassword(password1);
        }
        return firstName.get();
    }

    public String getLastName() {
        if(lastName == null) setLastName(lastName1);
        return lastName.get();
    }

    public String getEmail() {
        if(email == null) setEmail(email1);
        return email.get();
    }

    public String getUsername(){
        if(username == null) setUsername(username1);
        return username.get();
    }

    public String getRole(){
        if(role == null) setRole(role1);
        return role.get();
    }

    public String getPassword(){
        if(password == null) setPassword(password1);
        return password.get();
    }
    //UPDATE: added the firstName NULL check

    public void setFirstName(String firstName) {
        if(firstName==null){
            throw new NullPointerException("First name cannot be null");
        }
        this.firstName = new SimpleStringProperty(firstName);
        this.password = new SimpleStringProperty(password1);
    }
    //UPDATE: added the lastName NULL check
    public void setLastName(String lastName) {
        if(lastName==null){
            throw new NullPointerException("Last name cannot be null");
        }
        this.lastName = new SimpleStringProperty(lastName);
    }
    //UPDATE: added the email NULL check
    public void setEmail(String email) {
        if(email==null){
            throw new NullPointerException("Email cannot be null");
        }
        this.email = new SimpleStringProperty(email);
    }
    //UPDATE: added the username NULL check
    public void setUsername(String username){
        if(username==null){
            throw new NullPointerException("Username cannot be null");
        }
        this.username = new SimpleStringProperty(username);
    }
    //UPDATE: added the role NULL check
    public void setRole(String role){
        if(role==null){
            throw new NullPointerException("Role cannot be null");
        }
        this.role = new SimpleStringProperty(role);
    }
    //UPDATE: added the password NULL check
    public void setPassword(String password) {
        if(password==null){
            throw new NullPointerException("Password cannot be null");
        }
        this.password = new SimpleStringProperty(password);
    }

    //UPDATE: removed the password from the toString method
    @Override
    public String toString() {
        return "Name: " + getFirstName() + " Last Name: " + getLastName() + " Email: " + getEmail() + " Username: " + getUsername() + " Role: " + getRole();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User other = (User) obj;
        return Objects.equals(getFirstName(), other.getFirstName()) &&
                Objects.equals(getLastName(), other.getLastName()) &&
                Objects.equals(getEmail(), other.getEmail()) &&
                Objects.equals(getUsername(), other.getUsername()) &&
                Objects.equals(getRole(), other.getRole());
    }


}

