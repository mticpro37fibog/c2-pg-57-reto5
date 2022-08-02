package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehiculoDAO extends DAO<VehiculoDTO> {

    public VehiculoDAO(Connection conexion) {
        super(conexion, "vehiculo", "v_matricula", new String[] {
                "v_nombre", "v_velocidad", "v_longtitud"
        });
    }

    @Override
    protected void fijarColumnas(PreparedStatement statement, VehiculoDTO dto, boolean modificando)
            throws SQLException {
        if (!modificando) {
            statement.setInt(1, dto.getMatricula());
            statement.setString(2, dto.getNombre());
            statement.setFloat(3, dto.getVelocidad());
            statement.setDouble(4, dto.getLongtitud());
        }
    }

    @Override
    protected VehiculoDTO ensamblar(ResultSet result) throws SQLException {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setMatricula(result.getInt(1));
        dto.setNombre(result.getString(2));
        dto.setVelocidad(result.getFloat(3));
        dto.setLongtitud(result.getDouble(4));
        return dto;
    }

    @Override
    public boolean actualizar(VehiculoDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}