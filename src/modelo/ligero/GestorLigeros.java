package modelo.ligero;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import modelo.VehiculoDAO;
import modelo.VehiculoDTO;

public class GestorLigeros {

    private LigeroDAO ligeroDAO;
    private VehiculoDAO vehiculoDAO;

    public GestorLigeros(Connection conexion) {
        vehiculoDAO = new VehiculoDAO(conexion);
        ligeroDAO = new LigeroDAO(conexion);
    }

    public boolean registrarLigero(LigeroDTO dto) throws SQLException {
       throw new UnsupportedOperationException("Metodo no implementado");
    }

    public boolean eliminarLigero(Integer matricula) throws SQLException {
        throw new UnsupportedOperationException("Metodo no implementado");
    }

    public boolean actualizarLigero(LigeroDTO dto) throws SQLException {
        throw new UnsupportedOperationException("Metodo no implementado");
    }

    public LigeroDTO recuperarLigero(Integer matricula) throws SQLException {
        throw new UnsupportedOperationException("Metodo no implementado");
    }

    public List<LigeroDTO> recuperarLigeros() throws SQLException {
        throw new UnsupportedOperationException("Metodo no implementado");
    }
}