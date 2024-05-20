package org.example;
import java.sql.*;
import java.util.Scanner;

public class Main {
    private static final String url = "jdbc:mysql://127.0.0.1:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "Sanskar98@";

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // creating connection with db

        try {
            Connection connection = DriverManager.getConnection(url, username, password);

            while (true) {
                System.out.println("Please select from below");
                System.out.println("1. Book a room");
                System.out.println("2. View the room");
                System.out.println("3. Show all the bookings");
                System.out.println("4. Update Booking Details");
                System.out.println("5. Delete booking");
                System.out.println("6. Exit");

                Scanner sc = new Scanner(System.in);
                System.out.println("Enter your option");

                int option = sc.nextInt();
                System.out.println("Option selected by user:" + option);
                if (option == 1) {
                    System.out.println("Book a room");
                    book_a_room(connection);
                } else if (option == 2) {
                    System.out.println("View the room");
                    view_the_room(connection);
                } else if (option == 3) {
                    System.out.println("Show all bookings");
                    show_all_bookings(connection);

                } else if (option == 4) {
                    System.out.println("Update booking");
                    update_booking(connection);
                } else if (option == 5) {
                    System.out.println("Delete booking");
                    delete_booking(connection);
                } else if (option == 6) {
                    System.out.println("Program is terminated");
                    break;
                } else {
                    System.out.println("Please select a option");
                }
            }

            connection.close();

        } catch (Exception E) {
            System.out.println(E);
        }
    }

    private static void book_a_room(Connection connection) {
        String sql_query = "Insert into booking_table (room_no, name, phone) values (?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql_query);

            Scanner sc = new Scanner(System.in);

            System.out.println("Enter room no");
            int room_no = sc.nextInt();

            System.out.println("Enter Name");
            String name = sc.next();

            System.out.println("Enter phone number");
            String phone = sc.next();

            stmt.setInt(1, room_no);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            int row = stmt.executeUpdate();
            System.out.println(row);
            if (row > 0) {
                System.out.println("Booking Done");
            } else {
                System.out.println("Retry Again");
            }
            stmt.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Failed to book a room");
        }
    }

    private static void view_the_room(Connection connection) {
        String sql_query = "Select * from booking_table where id = ? and name = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql_query);
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter id");
            int uid = sc.nextInt();

            System.out.println("Enter name");
            String uname = sc.next();

            stmt.setInt(1, uid);
            stmt.setString(2, uname);

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int room = result.getInt("room_no");
                String name = result.getString("name");
                String phone = result.getString("phone");
                Timestamp timestamp = result.getTimestamp("booking_timestamp");

                System.out.println(id);
                System.out.println(room);
                System.out.println(name);
                System.out.println(phone);
                System.out.println(timestamp);

            }
            result.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void show_all_bookings(Connection connection) {
        String sql_query = "select * from booking_table";
        try {
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery(sql_query);
            while (result.next()) {
                int id = result.getInt("id");
                int room = result.getInt("room_no");
                String name = result.getString("name");
                String phone = result.getString("phone");
                Timestamp timestamp = result.getTimestamp("booking_timestamp");

                System.out.println(id);
                System.out.println(room);
                System.out.println(name);
                System.out.println(phone);
                System.out.println(timestamp);

            }
            result.close();
            stmt.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static void update_booking(Connection connection) {
        String sql_query = "update booking_table set phone = ? where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql_query);
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter id");
            int uid = sc.nextInt();

            System.out.println("Enter phone");
            String uphone = sc.next();

            stmt.setInt(1, uid);
            stmt.setString(2, uphone);

            ResultSet result = stmt.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                int room = result.getInt("room_no");
                String name = result.getString("name");
                String phone = result.getString("phone");
                Timestamp timestamp = result.getTimestamp("booking_timestamp");

                System.out.println(id);
                System.out.println(room);
                System.out.println(name);
                System.out.println(phone);
                System.out.println(timestamp);

            }
            result.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void delete_booking(Connection connection){
        String sql_query = "delete from booking_table where id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql_query);
            Scanner sc = new Scanner(System.in);

            System.out.println("Enter id");
            int uid = sc.nextInt();

            stmt.setInt(1, uid);

            int  result = stmt.executeUpdate();
            if(result>0){
                System.out.println("data deleted");
            }
            else{
                System.out.println();
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}