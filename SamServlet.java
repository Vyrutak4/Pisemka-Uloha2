import java.sql.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws SQLException {
        String query0 = "SELECT DISTINCT pomucka FROM ochrane_pomucky";
        String query1 = "SELECT pocet FROM  ochrane_pomucky WHERE pomucka=?";
        LinkedList<String> list = new LinkedList<>();

        Connection conn = DriverManager.getConnection("jdbc:sqlite:data.db");
        PreparedStatement ps = conn.prepareStatement(query0);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String polozka = rs.getString("pomucka");
            list.add(polozka);
        }

        ps = conn.prepareStatement(query1);
        for (String s : list) {
            ps.setString(1, s);
            rs = ps.executeQuery();

            int suma = 0;
            while (rs.next()) {
                int r = rs.getInt("pocet");
                suma += r;
            }

            System.out.println(s + " " + suma);
        }
    }
}