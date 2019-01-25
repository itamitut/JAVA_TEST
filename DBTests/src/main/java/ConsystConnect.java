import java.sql.*;

public class ConsystConnect {
    public static void main(String args[]){
        try{
//step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

//step2 create  the connection object
//            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@(description=(address=(host=ucb-test-db1.tengry.com)(protocol=tcp)(port=1521))(connect_data=(service_name=ucbtst1.tengry.com)(server=SHARED)))","DATAMART","Un45hJaw8j");
            Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@ucb-test-db1.tengry.com:1521/ucbtst1.tengry.com","DATAMART","Un45hJaw8j");

//step3 create the statement object
            Statement stmt=con.createStatement();

//step4 execute query
            ResultSet rs=stmt.executeQuery("SELECT SURNAME FROM DWAR_SUBSCRIBER_PERSON WHERE MONITORING_FLAG = 1");
            while(rs.next())
                System.out.println(rs.getString(1));

//step5 close the connection object
            con.close();

        }catch(Exception e){ System.out.println(e);}

    }
}
