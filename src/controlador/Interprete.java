package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fasterxml.jackson.databind.ObjectMapper;

import comun.Entidad;
import comun.Operacion;
import comun.RetoException;
import modelo.capitan.CapitanDTO;
import modelo.capitan.GestorCapitanes;
import modelo.ligero.GestorLigeros;
import modelo.ligero.LigeroDTO;
import modelo.transporte.GestorTransporte;
import modelo.transporte.TransporteDTO;

public class Interprete {

    private static String RESPUESTA_EXITOSA = "{\"respuesta\": \"Operación Exitosa\"}";

    private static String RESPUESTA_FALLIDA = "{\"respuesta\": \"Operación Fallida\"}";

    private Connection conexion;

    private ObjectMapper mapeador;

    private GestorTransporte gestorTransporte;

    private GestorLigeros gestorLigeros;

    private GestorCapitanes gestorCapitanes;

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
            gestorCapitanes = new GestorCapitanes(conexion, gestorTransporte);
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
            case CAPITAN:
                return procesarCapitan(operacion, json);
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
        try {
            LigeroDTO dto = mapeador.readValue(json, LigeroDTO.class);
            switch (operacion) {
                case CREAR:
                    return gestorLigeros.registrarLigero(dto) ? RESPUESTA_EXITOSA : RESPUESTA_FALLIDA;

                case ACTUALIZAR:
                    return gestorLigeros.actualizarLigero(dto) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;

                case ELIMINAR:
                    return gestorLigeros.eliminarLigero(dto.getMatricula()) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;
                default:
                    // Si tiene ID
                    if (dto.getMatricula() != null) {
                        LigeroDTO ligeroDTO = gestorLigeros.recuperarLigero(dto.getMatricula());
                        if (ligeroDTO != null) {
                            return mapeador.writeValueAsString(ligeroDTO);
                        }
                        return RESPUESTA_FALLIDA;
                    } else {
                        return mapeador.writeValueAsString(gestorLigeros.recuperarLigeros());
                    }
            }
        } catch (Exception ex) {
            throw new RetoException("No es posible procesar la petición", ex);
        }
    }

    private String procesarCapitan(Operacion operacion, String json) throws RetoException {
        try {
            CapitanDTO dto = mapeador.readValue(json, CapitanDTO.class);
            switch (operacion) {
                case CREAR:
                    return gestorCapitanes.registrarCapitan(dto) ? RESPUESTA_EXITOSA : RESPUESTA_FALLIDA;

                case ACTUALIZAR:
                    return gestorCapitanes.actualizarCapitan(dto) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;

                case ELIMINAR:
                    return gestorCapitanes.eliminarCapitan(dto.getLicencia()) ? RESPUESTA_EXITOSA
                            : RESPUESTA_FALLIDA;
                default:
                    // Si tiene ID
                    if (dto.getLicencia() != null) {
                        return mapeador.writeValueAsString(
                                gestorCapitanes.recuperarCapitan(dto.getLicencia()));
                    } else {
                        return mapeador.writeValueAsString(gestorCapitanes.recuperarCapitanes());
                    }
            }
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
