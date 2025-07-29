package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RankingDB {
    private static final String DB_URL = "ranking.db";

    public RankingDB() {
        crearTablaSiNoExiste();
    }

    private void crearTablaSiNoExiste() {
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement()) {
            String sql = """
                CREATE TABLE IF NOT EXISTS ranking (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    nombre TEXT NOT NULL,
                    puntos INTEGER NOT NULL
                );
                """;
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + DB_URL);
    }

    public void insertarPuntaje(String nombre, int puntos) {
        String sql = "INSERT INTO ranking(nombre, puntos) VALUES(?, ?)";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, puntos);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> obtenerRanking() {
        List<String> lista = new ArrayList<>();
        String sql = "SELECT nombre, puntos FROM ranking ORDER BY puntos DESC LIMIT 10";
        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String entrada = rs.getString("nombre") + " - " + rs.getInt("puntos") + " pts";
                lista.add(entrada);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
