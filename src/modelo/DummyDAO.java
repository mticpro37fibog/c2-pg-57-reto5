package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DummyDAO implements DAO<VehiculoDTO> {

    @Override
    public boolean crear(VehiculoDTO dto) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:reto5.db");

            String sql = "INSERT INTO vehiculo(v_matricula,v_nombre,v_velocidad,v_longitud) VALUES(?,?,?,?,?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, dto.getMatricula());
            statement.setString(2, dto.getNombre());
            statement.setFloat(3, dto.getVelocidad());
            statement.setDouble(4, dto.getLongitud());
            int response = statement.executeUpdate();
            if (response > 0) {
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
                System.out.println("Cerrando");
            }
        }

        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public VehiculoDTO recuperar(Integer pk) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:reto5.db");

            String sql = "SELECT * FROM vehiculo WHERE v_matricula = ?;";

            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                VehiculoDTO respuesta = new VehiculoDTO();
                // respuesta.setMatricula(pk);
                respuesta.setMatricula(result.getInt(1));
                respuesta.setNombre(result.getString(2));
                respuesta.setVelocidad(result.getFloat(3));
                respuesta.setLongitud(result.getDouble(4));
                return respuesta;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
                System.out.println("Cerrando");
            }
        }

        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean actualizar(Integer pk, VehiculoDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remover(Integer pk) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}
