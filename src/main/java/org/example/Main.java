package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        Scanner scanner  = new Scanner(System.in);
        int i = -1;
        while (i != 0){
            System.out.println("0=>Exit, 1=>New User, 2=>Edit User, 3=>Delete User, 4=>List User");
            i = scanner.nextInt();
            scanner = new Scanner(System.in);
            switch (i){
                case 1:
                    System.out.println("Enter firstname");
                    String firstname = scanner.nextLine();
                    System.out.println("Enter lastname");
                    String lastname = scanner.nextLine();
                    System.out.println("Enter username");
                    String username = scanner.nextLine();
                    System.out.println("Enter password");
                    String password = scanner.nextLine();
                    User user = new User(firstname,lastname,username,password);
//                    databaseService.saveUser(user);
                    databaseService.saveUserPreparedStatement(user);
                    break;
                case 2:
                    System.out.println("Enter user's id");
                    // int stringga o'zgaradi shuning un scannerni yangila
                    int id = scanner.nextInt();
                    scanner = new Scanner(System.in);
                    System.out.println("Enter user's firstname");
                    firstname= scanner.nextLine();
                    System.out.println("Enter user's lastname");
                    lastname= scanner.nextLine();
                    System.out.println("Enter user's username");
                    username= scanner.nextLine();
                    System.out.println("Enter user's password");
                    password= scanner.nextLine();
                    user = new User(id,firstname,lastname,username,password);
                    databaseService.editUser(user);
                    break;
                case 3:
                    System.out.println("Enter user's id: ");
                    id = scanner.nextInt();
//                    databaseService.deleteUser(id);
                    databaseService.deleteUserPreparedStatement(id);
                    break;
                case 4:
//                   databaseService.getUser();
                    databaseService.getUserPreparedStatement();
                    System.out.println("List User");
                    break;
            }
        }
        System.out.println("Hello world!");
    }
}