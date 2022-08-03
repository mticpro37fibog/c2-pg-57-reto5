package modelo.ligero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        // TODO Auto-generated method stub
        
    }

    @Override
    protected LigeroDTO ensamblar(ResultSet result) throws SQLException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected List<String> camposActualizacion(LigeroDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
