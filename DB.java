package personal.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {

    private final String DB_URL = "jdbc:h2:/" + System.getProperty("user.dir");
    private final String DB_Driver = "org.h2.Driver";
    public Connection conn;

    public DB() {
        try {
            Class.forName(DB_Driver);
            conn = DriverManager.getConnection(DB_URL + "/db");//подключение бд
            try (Statement state = conn.createStatement()) {
                state.execute(
                        "CREATE TABLE IF NOT EXISTS `employee` ("
                                + "  `id_employee` integer PRIMARY KEY AUTO_INCREMENT,"
                                + "  `id_filial` integer,"
                                + "  `id_position` integer,"
                                + "  `fio` text,"
                                + "  `mobile` varchar(20),"
                                + "  `email` text"
                                + ");"
                                + "CREATE TABLE IF NOT EXISTS `filial` ("
                                + "  `id_filial` integer PRIMARY KEY AUTO_INCREMENT,"
                                + "  `name` text"
                                + ");"
                                + "CREATE TABLE IF NOT EXISTS `position` ("
                                + "  `id_position` integer PRIMARY KEY AUTO_INCREMENT,"
                                + "  `name` text"
                                + ");"
                                + "ALTER TABLE `employee` ADD FOREIGN KEY (`id_filial`) REFERENCES `filial` (`id_filial`) ON DELETE CASCADE;"
                                + "ALTER TABLE `employee` ADD FOREIGN KEY (`id_position`) REFERENCES `position` (`id_position`) ON DELETE CASCADE;");
                System.out.println("БД Подключена");
            }
        } catch (ClassNotFoundException | SQLException e) {

                System.out.println("БД Подключена");

            
        } catch(Exception e){
            System.out.println("Не удалось подключиться к базе данных");
        }
    }
    
///////////////////////////////////////////////
//Филиалы
///////////////////////////////////////////////  
    
    public ArrayList<Filial> getFilials(){
        
        ArrayList<Filial> filial = new ArrayList();
    

            try (PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM filial")) {
                statement.execute();
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    filial.add(new Filial(rs.getString(2), rs.getInt(1)));
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return filial;
    }
    
    public int addFilial(String filial){
        int result = -1;
        String generatedColumns[] = { "id_filial" };
        try (PreparedStatement statement = this.conn.prepareStatement(
            "INSERT INTO filial(`name`) VALUES(?)", generatedColumns)) {
            statement.setString(1, filial);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                result = rs.getInt(1);
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return result;
    }
    
    public void removeFilial(int id){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "DELETE FROM filial WHERE id_filial=?")) {
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    
    public void updateFilial(int id, String name){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "UPDATE filial SET name=?  WHERE id_filial=?")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
///////////////////////////////////////////////
//Работники
///////////////////////////////////////////////      
    
    
    public ArrayList<Part> getEmployee(){
        ArrayList<Part> part = new ArrayList();
        
        try (PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM employee LEFT JOIN position ON employee.id_position=position.id_position")) {
                statement.execute();
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    part.add(new Part(rs.getString(4), rs.getString(6), rs.getString(5), rs.getInt(3), rs.getString(8), rs.getInt(2), rs.getInt(1)));
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return part;
        
    }
    
    public int addEmployee(int filial, int position, String fio, String number, String email){
        int result = -1;
        String generatedColumns[] = { "id_employee" };
        try (PreparedStatement statement = this.conn.prepareStatement(
            "INSERT INTO employee(`id_filial`, `id_position`, `fio`, `mobile`, `email`) VALUES(?,?,?,?,?)", generatedColumns)) {
            statement.setInt(1, filial);
            statement.setInt(2, position);
            statement.setString(3, fio);
            statement.setString(4, number);
            statement.setString(5, email);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                result = rs.getInt(1);
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return result;
    }
    
        public void removeEmployee(int id){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "DELETE FROM employee WHERE id_employee=?")) {
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    
    public void updateEmployee(Part empl){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "UPDATE employee SET id_filial=?, id_position=?, fio=?, mobile=?, email=?  WHERE id_employee=?")) {
            statement.setInt(1, empl.getFilial_id());
            statement.setInt(2, empl.getPosition_id());
            statement.setString(3, empl.getFio());
            statement.setString(4, empl.getMobile());
            statement.setString(5, empl.getEmail());
            statement.setInt(6, empl.getEmployee_id());
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    
    
    
///////////////////////////////////////////////
//Должности
///////////////////////////////////////////////      

    public ArrayList<Position> getPosition() {
        ArrayList<Position> position = new ArrayList();
        
        try (PreparedStatement statement = this.conn.prepareStatement("SELECT * FROM position")) {
                statement.execute();
                ResultSet rs = statement.getResultSet();
                while (rs.next()){
                    position.add(new Position(rs.getString(2), rs.getInt(1)));
                }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return position;
    }

    public int addPosition(String position) {
        int result = -1;
        String generatedColumns[] = { "id_position" };
        try (PreparedStatement statement = this.conn.prepareStatement(
            "INSERT INTO position(`name`) VALUES(?)", generatedColumns)) {
            statement.setString(1, position);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()){
                result = rs.getInt(1);
            }
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
        
        return result;
    }
    
    public void removePosition(int id){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "DELETE FROM position WHERE id_position=?")) {
            statement.setInt(1, id);
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }
    
    public void updatePosition(int id, String name){
        try (PreparedStatement statement = this.conn.prepareStatement(
            "UPDATE position SET name=?  WHERE id_position=?")) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.execute();
            
        } catch (SQLException e) {
                e.printStackTrace();
        }
    }

}
