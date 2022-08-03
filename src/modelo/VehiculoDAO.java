package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO extends AbstractDAO<VehiculoDTO> {

    public VehiculoDAO(Connection conexion) {
        super(conexion, "vehiculo", "v_matricula", new String[] { "v_nombre", "v_velocidad", "v_longitud" });
    }

    protected void fijarColumnas(PreparedStatement statement, VehiculoDTO dto)
            throws SQLException {
        statement.setInt(1, dto.getMatricula());
        statement.setString(2, dto.getNombre());
        statement.setFloat(3, dto.getVelocidad());
        statement.setDouble(4, dto.getLongitud());
    }

    @Override
    protected VehiculoDTO ensamblar(ResultSet result) throws SQLException {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setMatricula(result.getInt(1));
        dto.setNombre(result.getString(2));
        dto.setVelocidad(result.getFloat(3));
        dto.setLongitud(result.getDouble(4));
        return dto;
    }

    @Override
    protected List<String> camposActualizacion(VehiculoDTO dto) {
        List<String> campos = new ArrayList<>();

        if (dto.getNombre() != null) {
            campos.add("v_nombre = '" + dto.getNombre() + "'");
        }

        if (dto.getVelocidad() != null) {
            campos.add("v_velocidad = " + dto.getVelocidad());
        }

        if (dto.getLongitud() != null) {
            campos.add("v_longitud = " + dto.getLongitud());
        }
        return campos;
    }
}