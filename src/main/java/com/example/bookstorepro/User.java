package com.example.bookstorepro;

import javafx.beans.property.SimpleStringProperty;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = -81230957409593984L;
    private String firstName, lastName, email, username, password, role;

    public User(String firstName, String lastName, String email, String username, String role, String password) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setUsername(username);
        setRole(role);
        setPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        if (firstName == null) {
            throw new NullPointerException("First name cannot be null");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null) {
            throw new NullPointerException("Last name cannot be null");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new NullPointerException("Email cannot be null");
        }
        this.email = email;
    }

    public void setUsername(String username) {
        if (username == null) {
            throw new NullPointerException("Username cannot be null");
        }
        this.username = username;
    }

    public void setRole(String role) {
        if (role == null) {
            throw new NullPointerException("Role cannot be null");
        }
        this.role = role;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new NullPointerException("Password cannot be null");
        }
        this.password = password;
    }

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
