package modelo.ligero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.AbstractDAO;

public class LigeroDAO extends AbstractDAO<LigeroDTO> {

    public LigeroDAO(Connection conexion) {
        super(conexion, "ligero", "l_matricula", new String[] {
                "l_color", "l_andoride"
        });
    }

    @Override
    protected void fijarColumnas(PreparedStatement statement, LigeroDTO dto) throws SQLException {
        statement.setInt(1, dto.getMatricula());
        statement.setString(2, dto.getColor());
        statement.setString(3, dto.getAndroide());
    }

    @Override
    protected LigeroDTO ensamblar(ResultSet result) throws SQLException {
        LigeroDTO dto = new LigeroDTO();
        dto.setMatricula(result.getInt(1));
        dto.setColor(result.getString(2));
        dto.setAndroide(result.getString(3));
        return dto;
    }

    @Override
    protected List<String> camposActualizacion(LigeroDTO dto) {
        List<String> campos = new ArrayList<>();

        if (dto.getColor() != null) {
            campos.add("l_color = \"" + dto.getColor() + "\"");
        }

        if (dto.getAndroide() != null) {
            campos.add("l_androide = \"" + dto.getAndroide() + "\"");
        }
        return campos;
    }

}
