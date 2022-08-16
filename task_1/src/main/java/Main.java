import com.sun.xml.bind.api.impl.NameConverter;

import java.sql.*;

public class Main {
    static final String url = "jdbc:mysql://localhost:3306/skillbox";
    static final String user = "root";
    static final  String password = "Ybrbnf";
    static private String sql ="SELECT course_name, COUNT(*) / (MAX(month(subscription_date)) - MIN(month(subscription_date)) + 1) AS avg "+ "FROM PurchaseList "+ "WHERE YEAR (subscription_date) = ? " + "GROUP BY course_name;";
    public static void main(String[] args) {



        try (Connection connection = DriverManager.getConnection(url, user,password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, 2018);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {


                String courseName = resultSet.getString("course_name");
                String purchases = resultSet.getString("avg");
                System.out.println(courseName + " - " + purchases);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
    }
}

