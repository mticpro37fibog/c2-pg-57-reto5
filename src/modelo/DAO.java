package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<DTO> {

    private Connection conexion;
    private final String sentenciaBorrar;
    private final String sentenciaSeleccionar;
    private final String sentenciaSeleccionarTodo;
    private final String sentenciaInsertar;

    protected DAO(Connection conexion, String nombreTabla, String nombrePK, String[] columnas) {
        this.conexion = conexion;
        sentenciaSeleccionar = "SELECT * FROM " + nombreTabla + " WHERE " + nombrePK + "= ?;";
        sentenciaSeleccionarTodo = "SELECT * FROM " + nombreTabla + ";";
        sentenciaBorrar = "DELETE FROM " + nombreTabla + " WHERE " + nombrePK + "= ?;";

        String aux = "INSERT INTO " + nombreTabla + "(" + nombrePK;
        for (String columna : columnas) {
            aux += "," + columna;
        }
        aux += ") VALUES (?";
        for (int i = 0; i < columnas.length; i++) {
            aux += ",?";
        }
        aux += ");";
        sentenciaInsertar = aux;
    }

    /**
     * CREATE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean crear(DTO dto) throws SQLException {
        PreparedStatement statement = conexion.prepareStatement(sentenciaInsertar);
        fijarColumnas(statement, dto, false);
        return statement.executeUpdate() > 0;
    }

    protected abstract void fijarColumnas(PreparedStatement statement, DTO dto, boolean modificando)
            throws SQLException;

    /**
     * RETRIEVE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public DTO recuperar(Integer pk) throws SQLException {
        PreparedStatement statement = conexion.prepareStatement(sentenciaSeleccionar);
        statement.setInt(1, pk);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return ensamblar(result);
        }
        return null;
    }

    protected abstract DTO ensamblar(ResultSet result) throws SQLException;

    public List<DTO> recuperarTodo() throws SQLException {
        List<DTO> resultado = new ArrayList<>();
        Statement statement = conexion.createStatement();
        ResultSet result = statement.executeQuery(sentenciaSeleccionarTodo);
        while (result.next()) {
            resultado.add(ensamblar(result));
        }
        return resultado;
    };

    /**
     * UPDATE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public abstract boolean actualizar(DTO dto) throws SQLException;

    /**
     * DELETE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean remover(Integer pk) throws SQLException {
        PreparedStatement statement = conexion.prepareStatement(sentenciaBorrar);
        statement.setInt(1, pk);
        return statement.executeUpdate() > 0;
    };
}
