package ru.mantis.test;

import org.testng.annotations.Test;
import ru.mantis.model.Users;
import ru.mantis.model.UsersData;
import java.sql.*;

public class DBTestMantis {
    @Test
    public static Users users() {
        Users users = new Users();
        Connection conn = null;
        try {
            conn =
                    DriverManager.getConnection( "jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&newPassword=" );
            // Do something with the Connection
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery( "select id, username, email from mantis_user_table" );

            while (rs.next()) {
                users.add( new UsersData().withId( rs.getInt( "id" ) ).withName( rs.getString( "username" ))
                            .withEmail(rs.getString( "email")));
                rs.next();
            }
            //Закрыть все
            rs.close();
            st.close();
            conn.close();
            //Вывести на консоль данные
            System.out.println(users);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println( "SQLException: " + ex.getMessage() );
            System.out.println( "SQLState: " + ex.getSQLState() );
            System.out.println( "VendorError: " + ex.getErrorCode() );
        }
        return users;
    }

}