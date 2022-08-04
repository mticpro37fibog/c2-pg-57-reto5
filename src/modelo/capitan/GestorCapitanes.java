package modelo.capitan;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import modelo.transporte.GestorTransporte;
import modelo.transporte.TransporteDTO;

public class GestorCapitanes {

    private CapitanDAO capitanDAO;

    private GestorTransporte gestorTransporte;

    public GestorCapitanes(Connection conexion, GestorTransporte gestorTransporte) {
        this.gestorTransporte = gestorTransporte;
        capitanDAO = new CapitanDAO(conexion);
    }

    public boolean registrarCapitan(CapitanDTO dto) throws SQLException {
        return capitanDAO.crear(dto);
    }

    public boolean eliminarCapitan(Integer licencia) throws SQLException {
        return capitanDAO.remover(licencia);
    }

    public boolean actualizarCapitan(CapitanDTO dto) throws SQLException {

        if (dto.getEspecie() != null ||
                dto.getTransporte() != null ||
                dto.getNombre() != null) {
            return capitanDAO.actualizar(dto.getLicencia(), dto);
        }
        return false;
    }

    public CapitanDTO recuperarCapitan(Integer licencia) throws SQLException {
        CapitanDTO respuesta = capitanDAO.recuperar(licencia);
        if (respuesta != null && respuesta.getTransporte() != null) {
            TransporteDTO dto = gestorTransporte.recuperarTransporte(respuesta.getTransporte().getMatricula());
            respuesta.setTransporte(dto);
        }
        return respuesta;
    }

    public List<CapitanDTO> recuperarCapitanes() throws SQLException {
        List<CapitanDTO> respuesta = capitanDAO.recuperarTodo();
        for (CapitanDTO dto : respuesta) {
            if (dto != null && dto.getTransporte() != null) {
                dto.setTransporte(gestorTransporte.recuperarTransporte(dto.getTransporte().getMatricula()));
            }
        }
        return respuesta;
    }
}