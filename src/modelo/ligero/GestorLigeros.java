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
        if (vehiculoDAO.crear(dto.getVehiculo())) {
            return ligeroDAO.crear(dto);
        }
        return false;
    }

    public boolean eliminarLigero(Integer matricula) throws SQLException {
        if (ligeroDAO.remover(matricula)) {
            return vehiculoDAO.remover(matricula);
        }
        return false;
    }

    public boolean actualizarLigero(LigeroDTO dto) throws SQLException {
        boolean respuesta = false;

        if (dto.getVehiculo().getNombre() != null ||
                dto.getVehiculo().getLongitud() != null ||
                dto.getVehiculo().getVelocidad() != null) {
            respuesta = vehiculoDAO.actualizar(dto.getMatricula(), dto.getVehiculo());
        }

        if (dto.getAndroide() != null ||
                dto.getColor() != null) {
            respuesta = ligeroDAO.actualizar(dto.getMatricula(), dto);
        }

        return respuesta;
    }

    public LigeroDTO recuperarLigero(Integer matricula) throws SQLException {
        LigeroDTO ligeroDTO = null;
        VehiculoDTO vehiculoDTO = vehiculoDAO.recuperar(matricula);
        if (vehiculoDTO != null) {
            ligeroDTO = ligeroDAO.recuperar(matricula);
            ligeroDTO.setVehiculo(vehiculoDTO);
        }
        return ligeroDTO;
    }

    public List<LigeroDTO> recuperarLigeros() throws SQLException {
        List<LigeroDTO> respuesta = ligeroDAO.recuperarTodo();
        for (LigeroDTO ligeroDTO : respuesta) {
            ligeroDTO.setVehiculo(
                    vehiculoDAO.recuperar(ligeroDTO.getMatricula()));
        }
        return respuesta;
    }
}