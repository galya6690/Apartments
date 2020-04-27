import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        DbProperties props = new DbProperties();
        try (Connection conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            try (Statement st = conn.createStatement()) {
                st.execute("DROP TABLE IF EXISTS APARTMENTS");
                st.execute("\n" +
                        "CREATE TABLE APARTMENTS (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,region TEXT Default NULL,adress TEXT DEFAULT NULL,area TEXT DEFAULT NULL, numberOfrooms INT DEFAULT NULL, price DOUBLE DEFAULT NULL\n" +
                        ")");
                st.execute("INSERT INTO APARTMENTS (region, adress, area,numberOfrooms, price) VALUES('Podol', 'street of Ivan Vyhovsky', '40', '2','30300')");
                st.execute("INSERT INTO APARTMENTS (region, adress, area,numberOfrooms, price) VALUES('Darnitsky', 'Street of Ivan Franko', '69', '3','60000')");
                st.execute("INSERT INTO APARTMENTS (region, adress, area,numberOfrooms, price) VALUES('Obolonsky', 'March 8th Street', '40', '1','44600')");
                st.execute("INSERT INTO APARTMENTS (region, adress, area,numberOfrooms, price) VALUES('Dnipro', 'Street of Ivan Boyko', '67', '3','78800')");
                st.execute("INSERT INTO APARTMENTS (region, adress, area,numberOfrooms, price) VALUES('Darnitsky', 'Anna Akhmatova Street', '28', '2','29000')");
            }}
    sample();
    }
        public static void sample(){
            DbProperties props = new DbProperties();
            try (Connection conn = DriverManager.getConnection(props.getUrl(), props.getUser(), props.getPassword())) {
            Scanner sc= new Scanner(System.in);
            Scanner sc1=new Scanner(System.in);
            System.out.println("Якщо ви бажаєте зробити вибірку квартир по районі натисніть 1 , по кількістю квартир натисніть 2 , по ціні натисніть 3" );
            int count=sc.nextInt();
             if (count==1){
             System.out.println("введіть район");
               String reg=sc1.nextLine();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM APARTMENTS where region='"+reg+"'");
            try (ResultSet rs = ps.executeQuery()){
               ResultSetMetaData md = rs.getMetaData();
                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.print(md.getColumnName(i) + "\t\t");
                    System.out.println();
                    while (rs.next()) {
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
            }
        }
            if (count==2){
                System.out.println("введіть кількість квартир");
                String rooms=sc1.nextLine();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM APARTMENTS where numberOfrooms='"+rooms+"'");
                try (ResultSet rs = ps.executeQuery()){
                    ResultSetMetaData md = rs.getMetaData();
                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.print(md.getColumnName(i) + "\t\t");
                    System.out.println();

                    while (rs.next()) {
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }
                }
            } if (count==3){
                System.out.println("введіть максимальну ціну");
                int pri=sc1.nextInt();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM APARTMENTS where  price<='"+pri+"'");
                try (ResultSet rs = ps.executeQuery()){
                    ResultSetMetaData md = rs.getMetaData();
                    for (int i = 1; i <= md.getColumnCount(); i++)
                        System.out.print(md.getColumnName(i) + "\t\t");
                    System.out.println();

                    while (rs.next()) {
                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            System.out.print(rs.getString(i) + "\t\t");
                        }
                        System.out.println();
                    }

                }
            }} catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

