package org.example;

import java.sql.*;

/**
 * JDBC Java DataBase Connectivity -> Ma'lumotlar omboriga bog'lovchi deb tarjima qilinadi.
 */

/**
 * Callable statement  -> MO funksiya va proseduralar chaqirish uchun muljallangan statement
 */


public class DatabaseService {
    private String url = "jdbc:postgresql://localhost:5432/app-api-spring-advanced-one";
    private String dbUser = "postgres";
    private String dbPassword = "1234";

    /**
     * <h1>
     *     1.added to dataBase
     * </h1>
     * @param user variables object
     */

    public void saveUser(User user){
        /**
         * Connection -> Java applacetionimizni MOBT bn bog'laydi
         */
        try {
            Connection boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            /**
             * Statement -> Statemant objecti orqali MOBT ga SQL so'rovlarini amalga oshiradi
             */
            Statement statement = boglovchi.createStatement();
            String query = "insert into users(first_name,last_name,username,password) " +
                    "values('"+user.getFirstname()+"'," +
                    "'"+user.getLastname()+"'," +
                    "'"+user.getUsername()+"'," +
                    "'"+user.getPassword()+"')";
            statement.execute(query);
            System.out.println("User added");
            statement.close();
            boglovchi.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>2.getUser ma'lumotlarni database dan olish</h1>
     */
    public void getUser(){
        Connection boglovchi = null;
        try {
            boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = boglovchi.createStatement();
            String query = "select * from users";
            /**
             * ResultSet -> so'rov natijasi bilan ishlashga mo'ljallangan interface
             */
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int id =  resultSet.getInt("id");
                String firstname = resultSet.getString(2);
                String lastname= resultSet.getString(3);
                String username =  resultSet.getString(4);
                String password =  resultSet.getString(5);
                User user  = new User(id,firstname,lastname,username,password);
                System.out.println(user);

            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * <h1>3.deleteUser userni o'chirish</h1>
     */
    public void deleteUser(int userId){
        Connection boglovchi = null;
        try {
            boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = boglovchi.createStatement();
            String query = "delete from users where id = "+userId;
            boolean execute = statement.execute(query);
            System.out.println(execute + "User deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>4.editUser userni o'zgartirish</h1>
     *
     */
    public void editUser(User user){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,dbUser,dbPassword);
            Statement statement = connection.createStatement();
            String query = "update users set ";
            if (!user.getFirstname().isEmpty()){
                query = query + "first_name='"+user.getFirstname() + "',";
            }
            if (!user.getLastname().isEmpty()){
                query = query + "last_name='"+user.getLastname() + "',";
            }
            if (!user.getUsername().isEmpty()){
                query = query + "username='"+user.getUsername() + "',";
            }
            if (!user.getPassword().isEmpty()){
                query = query + "password="+user.getPassword();
            }
            if (!query.equals("update users set ")){
                if (query.endsWith("',")){
                    query = query.substring(0,query.length()-1);
                }
                query = query + "where id="+user.getId();
                statement.execute(query);
                System.out.println("User edited");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *<h1> 1.added to dataBase PreparedStatement</h1>
     */

    /**
     * PreparedStatement -> statement + parametirlari bilan
     */
    public void saveUserPreparedStatement(User user){
        try {
            Connection boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "insert into users(first_name,last_name,username,password) values(?,?,?,?)";
            PreparedStatement preparedStatement = boglovchi.prepareStatement(query);
            preparedStatement.setString(1,user.getFirstname());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setString(3,user.getUsername());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("User added from preparedStatement");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>2.getUser ma'lumotlarni database dan olish PreparedStatement</h1>
     */
    public void getUserPreparedStatement(){
        Connection boglovchi = null;
        try {
            boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "select * from users";
            PreparedStatement preparedStatement =  boglovchi.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id =  resultSet.getInt("id");
                String firstname = resultSet.getString(2);
                String lastname= resultSet.getString(3);
                String username =  resultSet.getString(4);
                String password =  resultSet.getString(5);
                User user  = new User(id,firstname,lastname,username,password);
                System.out.println(user);

            }
            System.out.println("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h1>3.deleteUser userni o'chirish PreparedStatement</h1>
     * @param userId
     */
    public void deleteUserPreparedStatement(int userId){
        Connection boglovchi = null;
        try {
            boglovchi = DriverManager.getConnection(url,dbUser,dbPassword);
            String query = "delete from users where id = "+userId;
            PreparedStatement preparedStatement = boglovchi.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("User deleted");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
