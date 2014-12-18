import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCExample {

    public static void main(String[] args) {

        Connection connection = null;
        //URL к базе состоит из протокола:подпротокола://[хоста]:[порта_СУБД]/[БД] и других_сведений
		// String user = "imbot";
		// String password = "P@ssw0rd";
		// String url = "jdbc:mysql://217.146.253.39/imbot";
		String user = "root";
		String password = "potem358669";
		String url = "jdbc:mysql://192.168.0.81/imbot";
		String driver = "com.mysql.jdbc.Driver";
        try {
            //Загружаем драйвер
            Class.forName(driver);
            Logger.getLogger(JDBCExample.class.getName()).log(Level.INFO, "Драйвер подключен");
            //Создаём соединение
            connection = DriverManager.getConnection(url, user, password);
            Logger.getLogger(JDBCExample.class.getName()).log(Level.INFO, "Соединение установлено");
            Statement statement = null;
            statement = connection.createStatement();       
    		ResultSet rs = statement.executeQuery("Show tables");
			
			 while (rs.next()) {
			 System.out.println(rs.getString("Tables_in_imbot"));
			 }
            
				// INSERT
				PreparedStatement preparedStatement = null; 
				
				preparedStatement = connection
						.prepareStatement("INSERT INTO users(FirstName,LastName,MiddleName,Login) values(?,?,?,?)");
	
				String param[][] = { { "Ivan", "Ivanov", "Ivanovich", "Vano" },
						{ "Petr", "Petrov", "Petrovich", "Pit" },
						{ "Sidor", "Sidorov", "Sidorovich", "Sid" },
						{ "Mark", "Marakulin", "markovich", "Mak" } };
	
				for (String[] par : param) {
					for (int ind = 0; ind < 4; ind++) {
						preparedStatement.setString(ind + 1, par[ind]);
					}
					preparedStatement.executeUpdate();
					Logger.getLogger(JDBCExample.class.getName()).log(Level.INFO, "В таблицу users добавлен кортеж");
				}

				//UPDATE

				preparedStatement = connection
						.prepareStatement("UPDATE users SET DateOfBirth = '1964.02.01' where id = 29");
				preparedStatement.executeUpdate(); 
				Logger.getLogger(JDBCExample.class.getName()).log(Level.INFO, "В таблице users обновлен кортеж");
				
				//Delete

				preparedStatement = connection
						.prepareStatement("Delete FROM users where id >1");
				preparedStatement.executeUpdate();
				Logger.getLogger(JDBCExample.class.getName()).log(Level.INFO, "Из таблицы users удалены все кортежи, где id >1  ");
					
			   // SELECT
				ResultSet result1 = preparedStatement.executeQuery("SELECT * FROM users");

				while (result1.next()) {
					System.out.println(result1.getInt("ID") + " "
							+ result1.getString("FirstName") + " "
							+ result1.getString("LastName") + " "
							+ result1.getString("MiddleName") + " "
							+ result1.getDate("DateOfBirth") + " "
							+ result1.getString("Login"));
				}
        
      
        
        } catch (Exception ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }
}