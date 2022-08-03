package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import comun.Entidad;
import comun.Operacion;
import comun.RetoException;
import modelo.ligero.GestorLigeros;
import modelo.transporte.GestorTransporte;
import modelo.transporte.TransporteDTO;

public class Interprete {

    private static String RESPUESTA_EXITOSA = "{\"respuesta\": \"Operación Exitosa\"}";

    private static String RESPUESTA_FALLIDA = "{\"respuesta\": \"Operación Fallida\"}";

    private Connection conexion;

    private ObjectMapper mapeador;

    private GestorTransporte gestorTransporte;
    private GestorLigeros gestorLigeros;

    public Interprete(String nombre) throws RetoException {
        try {
            // conexion = DriverManager.getConnection("jdbc:sqlite:" + nombre);
            String dbURL = "jdbc:mysql://127.0.0.1:3306/" + nombre;
            String username = "test";
            String password = "test";
            conexion = DriverManager.getConnection(dbURL, username, password);
            mapeador = new ObjectMapper();
            gestorTransporte = new GestorTransporte(conexion);
            gestorLigeros = new GestorLigeros(conexion);
        } catch (SQLException ex) {
            throw new RetoException("No se puede establecer conexión", ex);
        }
    }

    public String procesarComando(Operacion operacion, Entidad entidad, String json) throws RetoException {
        switch (entidad) {
            case TRANSPORTE:
                return procesarTransporte(operacion, json);
            case LIGERO:
                return procesarLigeros(operacion, json);
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

                case ACTUALIZAR:
                    return gestorTransporte.actualizarTransporte(dto) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;

                case ELIMINAR:
                    return gestorTransporte.eliminarTransporte(dto.getMatricula()) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;
                default:
                    // Si tiene ID
                    if (dto.getMatricula() != null) {
                        TransporteDTO transporteDTO = gestorTransporte
                                .recuperarTransporte(dto.getMatricula());
                        if (transporteDTO != null) {
                            return mapeador.writeValueAsString(transporteDTO);
                        }
                        return RESPUESTA_FALLIDA;
                    } else {
                        return mapeador.writeValueAsString(gestorTransporte.recuperarTransportes());
                    }
            }
        } catch (Exception ex) {
            throw new RetoException("No es posible procesar la petición", ex);
        }
    }

    private String procesarLigeros(Operacion operacion, String json) throws RetoException {
        throw new UnsupportedOperationException("Metodo no implementado");
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
