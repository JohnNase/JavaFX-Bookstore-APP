package com.example.bookstorepro;

import java.io.*;
import java.util.ArrayList;

public class ReadData {
    //This class takes the history.dat file and turns its contents, the users' data, into an arraylist

    public static ArrayList<String> usernames = new ArrayList<>();
    public static ArrayList<String> passwords = new ArrayList<>();
    public static ArrayList<String> roles = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();


    public static void main(String[] args) throws FileNotFoundException {

        read();

        System.out.println(usernames);
        System.out.println(passwords);
        System.out.println(roles);

    }
    public static void read() throws FileNotFoundException {
        usernames = new ArrayList<>();
        passwords = new ArrayList<>();
        roles = new ArrayList<>();
        users = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream("history.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            users = (ArrayList<User>) ois.readObject();
            ois.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        PrintWriter writer = new PrintWriter("UserData.txt");
        for (User user : users) {
            writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "\n");
            usernames.add(user.getUsername());
            passwords.add(user.getPassword());
            roles.add(user.getRole());
        }
        writer.close();
    }

    public static void updateUserRole(String username, String newRole) throws FileNotFoundException {
        int index = usernames.indexOf(username);
        if (index != -1) {
            roles.set(index, newRole);

            PrintWriter writer = new PrintWriter("UserData.txt");
            for (User user : users) {
                writer.write(user.getUsername() + "," + user.getPassword() + "," + user.getRole() + "\n");
                usernames.add(user.getUsername());
                passwords.add(user.getPassword());
                roles.add(user.getRole());
            }
            writer.close();
        }
        }
    }

