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
        if (vehiculoDAO.remover(matricula)) {
            return transporteDAO.remover(matricula);
        }
        return false;
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
                    vehiculoDAO.recuperar(transporteDTO.getVehiculo().getMatricula()));
        }

        return respuesta;
    }
}
