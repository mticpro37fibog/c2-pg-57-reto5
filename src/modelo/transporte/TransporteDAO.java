package modelo.transporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AbstractDAO;

public class TransporteDAO extends AbstractDAO<TransporteDTO> {

    public TransporteDAO(Connection conexion) {
        super(conexion, "transporte", "t_matricula", new String[] {
                "t_tripulantes", "t_pasajeros"
        });
    }

    @Override
    protected TransporteDTO ensamblar(ResultSet result) throws SQLException {
        TransporteDTO dto = new TransporteDTO();
        dto.setMatricula(result.getInt(1));
        dto.setTripulantes(result.getInt(2));
        dto.setPasajeros(result.getInt(3));
        return dto;
    }

    @Override
    protected void fijarColumnas(PreparedStatement statement, TransporteDTO dto) throws SQLException {
        statement.setInt(1, dto.getVehiculo().getMatricula());
        statement.setInt(2, dto.getTripulantes());
        statement.setInt(3, dto.getPasajeros());
    }

    @Override
    protected List<String> camposActualizacion(TransporteDTO dto) {
        List<String> campos = new ArrayList<>();

        if (dto.getPasajeros() != null) {
            campos.add("t_pasajeros = " + dto.getPasajeros());
        }

        if (dto.getTripulantes() != null) {
            campos.add("t_tripulantes = " + dto.getTripulantes());
        }

        return campos;
    }
}
