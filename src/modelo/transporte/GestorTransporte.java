package modelo.transporte;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import modelo.VehiculoDAO;
import modelo.VehiculoDTO;

public class GestorTransporte {

    private TransporteDAO transporteDAO;
    private VehiculoDAO vehiculoDAO;

    public GestorTransporte(Connection conexion) {
        vehiculoDAO = new VehiculoDAO(conexion);
        transporteDAO = new TransporteDAO(conexion);
    }

    public boolean registrarTransporte(TransporteDTO dto) throws SQLException {
        if (vehiculoDAO.crear(dto.getVehiculo())) {
            return transporteDAO.crear(dto);
        }
        return false;
    }

    public boolean eliminarTransporte(Integer matricula) throws SQLException {
        if (transporteDAO.remover(matricula)) {
            return vehiculoDAO.remover(matricula);
        }
        return false;
    }

    public boolean actualizarTransporte(TransporteDTO dto) throws SQLException {

        boolean respuesta = false;

        if (dto.getVehiculo().getNombre() != null ||
                dto.getVehiculo().getLongitud() != null ||
                dto.getVehiculo().getVelocidad() != null) {
            respuesta = vehiculoDAO.actualizar(dto.getMatricula(), dto.getVehiculo());
        }

        if (dto.getPasajeros() != null ||
                dto.getTripulantes() != null) {
            respuesta = transporteDAO.actualizar(dto.getMatricula(), dto);
        }

        return respuesta;
    }

    public TransporteDTO recuperarTransporte(Integer matricula) throws SQLException {
        TransporteDTO transporteDTO = null;
        VehiculoDTO vehiculoDTO = vehiculoDAO.recuperar(matricula);
        if (vehiculoDTO != null) {
            transporteDTO = transporteDAO.recuperar(matricula);
            transporteDTO.setVehiculo(vehiculoDTO);
        }
        return transporteDTO;
    }

    public List<TransporteDTO> recuperarTransportes() throws SQLException {
        List<TransporteDTO> respuesta = transporteDAO.recuperarTodo();
        for (TransporteDTO transporteDTO : respuesta) {
            transporteDTO.setVehiculo(
                    vehiculoDAO.recuperar(transporteDTO.getMatricula()));
        }

        return respuesta;
    }
}