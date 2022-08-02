package modelo.transporte;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.DAO;

public class TransporteDAO extends DAO<TransporteDTO> {

    public TransporteDAO(Connection conexion) {
        super(conexion, "transporte", "t_matricula", new String[] {
                "t_tripulantes", "t_pasajeros"
        });
    }

    @Override
    protected void fijarColumnas(PreparedStatement statement, TransporteDTO dto, boolean modificando)
            throws SQLException {
        if (!modificando) {
            statement.setInt(1, dto.getVehiculo().getMatricula());
            statement.setInt(2, dto.getTripulantes());
            statement.setInt(3, dto.getPasajeros());
        }
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
    public boolean actualizar(TransporteDTO dto) throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}
