package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import comun.Entidad;
import comun.Operacion;
import comun.RetoException;
import modelo.transporte.GestorTransporte;
import modelo.transporte.TransporteDTO;

public class Interprete {

    private static String RESPUESTA_EXITOSA = "{\"respuesta\": \"Operación Exitosa\"}";

    private static String RESPUESTA_FALLIDA = "{\"respuesta\": \"Operación Fallida\"}";

    private Connection conexion;

    private ObjectMapper mapeador;

    private GestorTransporte gestorTransporte;

    public Interprete(String nombre) throws RetoException {
        try {
            conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre);
            mapeador = new ObjectMapper();
            gestorTransporte = new GestorTransporte(conexion);
        } catch (SQLException ex) {
            throw new RetoException("No se puede establecer conexión", ex);
        }
    }

    public String procesarComando(Operacion operacion, Entidad entidad, String json) throws RetoException {
        switch (entidad) {
            case TRANSPORTE:
                return procesarTransporte(operacion, json);
            default:
                throw new RetoException("No Existe la tabla",
                        new UnsupportedOperationException("Operación No Implementada"));
        }
    }

    private String procesarTransporte(Operacion operacion, String json) throws RetoException {
        try {
            TransporteDTO dto = mapeador.readValue(json, TransporteDTO.class);
            switch (operacion) {
                case CREAR:
                    return gestorTransporte.registrarTransporte(dto) ? RESPUESTA_EXITOSA : RESPUESTA_FALLIDA;
                case ELIMINAR:
                    return gestorTransporte.eliminarTransporte(dto.getMatricula()) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;
                case RECUPERAR:
                    // Si tiene ID
                    if (dto.getMatricula() != null) {
                        TransporteDTO transporteDTO = gestorTransporte
                                .recuperarTransporte(dto.getMatricula());
                        if (transporteDTO != null) {
                            return mapeador.writeValueAsString(transporteDTO);
                        }
                    } else {
                        TransporteDTO transporteDTO = gestorTransporte
                                .recuperarTransporte(dto.getMatricula());
                        if (transporteDTO != null) {
                            return mapeador.writeValueAsString(transporteDTO);
                        }
                    }

                    return RESPUESTA_FALLIDA;
            }
            return RESPUESTA_FALLIDA;
        } catch (Exception ex) {
            throw new RetoException("No es posible procesar la petición", ex);
        }
    }

    public void listo() {
        try {
            if (conexion != null)
                conexion.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
