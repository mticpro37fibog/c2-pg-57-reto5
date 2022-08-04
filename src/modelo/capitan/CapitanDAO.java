package modelo.capitan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AbstractDAO;
import modelo.transporte.TransporteDTO;

public class CapitanDAO extends AbstractDAO<CapitanDTO> {

    public CapitanDAO(Connection conn) {
        super(conn, "capitan", "c_licencia", new String[] { "c_nombre", "c_especie", "c_transporte" });
    }

    @Override
    protected void fijarColumnas(PreparedStatement statement, CapitanDTO dto) throws SQLException {
        statement.setInt(1, dto.getLicencia());
        statement.setString(2, dto.getNombre());
        statement.setString(3, dto.getEspecie());
        statement.setInt(4, dto.getTransporte().getMatricula());
    }

    @Override
    protected CapitanDTO ensamblar(ResultSet result) throws SQLException {
        CapitanDTO respuesta = new CapitanDTO();
        respuesta.setLicencia(result.getInt(1));
        respuesta.setNombre(result.getString(2));
        respuesta.setEspecie(result.getString(3));

        TransporteDTO transporte = new TransporteDTO();
        transporte.setMatricula(result.getInt(4));
        respuesta.setTransporte(transporte);
        return respuesta;
    }

    @Override
    protected List<String> camposActualizacion(CapitanDTO dto) {
        List<String> campos = new ArrayList<>();

        if (dto.getNombre() != null) {
            campos.add("c_nombre = \"" + dto.getNombre() + "\"");
        }

        if (dto.getEspecie() != null) {
            campos.add("c_especie = \"" + dto.getEspecie() + "\"");
        }

        if (dto.getTransporte() != null) {
            campos.add("c_transporte = " + dto.getTransporte());
        }
        return campos;
    }

}
