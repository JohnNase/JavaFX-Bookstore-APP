package com.example.bookstorepro;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.ArrayList;

public class UserController implements java.io.Serializable{
    @Serial
    private static final long serialVersionUID = -155396823076863L;
    private static ArrayList<User> users;
    public UserController() {
        users = new ArrayList<>();
    }
    public boolean signUp(String firstName, String lastName, String email, String username, String role, String password, String verifyPassword) {
        if (isNullOrEmpty(firstName) || isNullOrEmpty(lastName) || isNullOrEmpty(email) ||
                isNullOrEmpty(username) || isNullOrEmpty(role) || isNullOrEmpty(password) ||
                isNullOrEmpty(verifyPassword)) {
            System.out.println("Null or empty field(s)");
            return false;
        }
        if (!password.equals(verifyPassword)) {
            System.out.println("Passwords do not match");
            return false;
        }
        if (containsOnlySpaces(firstName) || containsOnlySpaces(lastName) || containsOnlySpaces(username) || containsOnlySpaces(role) || containsOnlySpaces(password) || containsOnlySpaces(verifyPassword)) {
            System.out.println("Field(s) contain only spaces");
            return false;
        }
        if (containsSymbol(firstName) || containsSymbol(lastName) || containsSymbol(username) || containsSymbol(role)) {
            System.out.println("First Name, Last Name, username, roles should not contain symbol(s)");
            return false;
        }
        if (!isValidEmail(email)) {
            System.out.println("Invalid email");
            return false;
        }
        if (!isValidRole(role)) {
            System.out.println("Invalid role");
            return false;
        }
        User newUser = new User(firstName, lastName, email, username, role, password);
        users.add(newUser);
        return true;
    }

    // Helper methods
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidRole(String role) {
        String normalizedRole = role.toLowerCase(); // Convert role to lowercase

        return normalizedRole.equals("administrator") || normalizedRole.equals("manager") || normalizedRole.equals("librarian");  // Check if role is valid
    }

    public static boolean containsOnlySpaces(String str) {
        return str.trim().isEmpty();
    }

    public static boolean containsSymbol(String str) {
        return !str.matches("[a-zA-Z0-9 ]+");
    }

    public static boolean isValidEmail(String email) {
        // Regex pattern to validate email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }


    public void printUsers(){
        for(int i = 0; i < users.size(); i ++){
            System.out.println(users.get(i));
        }
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public void setUsers(ArrayList<User> user){
        users = user;
    }
    public void deleteUser(User user) {
        users.remove(user);
    }
    public void updateRole(User user, String role) {
        int index = users.indexOf(user);
        user.setRole(role);
        users.set(index, user);
    }
    public void saveData() {
        try {
            ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("History.dat"));
            output.writeObject(users);
            output.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e);
        }
    }
}
