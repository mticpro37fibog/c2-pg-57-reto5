package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<DTO> implements DAO<DTO> {

    protected Connection conexion;

    private final String sentenciaInsertar;
    private final String sentenciaBorrar;
    private final String sentenciaSeleccionar;
    private final String sentenciaSeleccionarTodo;

    private final String nombreTabla;
    private final String nombrePK;

    protected AbstractDAO(Connection conexion, String nombreTabla, String nombrePK, String[] columnas) {
        this.conexion = conexion;
        this.nombreTabla = nombreTabla;
        this.nombrePK = nombrePK;
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

        System.out.println("<<< Sentencias SQL para la Tabla [" + nombreTabla + "] >>>");
        System.out.println("Insertar: [" + sentenciaInsertar + "]");
        System.out.println("Selecionar: [" + sentenciaSeleccionar + "]");
        System.out.println("Selecionar Todo: [" + sentenciaSeleccionarTodo + "]");
        System.out.println("Borrar: [" + sentenciaBorrar + "]");
        System.out.println("<<< FIN >>>\n");
    }

    protected abstract void fijarColumnas(PreparedStatement statement, DTO dto) throws SQLException;

    public boolean crear(DTO dto) throws SQLException {
        PreparedStatement statement = conexion.prepareStatement(sentenciaInsertar);
        fijarColumnas(statement, dto);
        return statement.executeUpdate() > 0;
    }

    protected abstract DTO ensamblar(ResultSet result) throws SQLException;

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

    public List<DTO> recuperarTodo() throws SQLException {
        List<DTO> resultado = new ArrayList<>();
        Statement statement = conexion.createStatement();
        ResultSet result = statement.executeQuery(sentenciaSeleccionarTodo);
        while (result.next()) {
            resultado.add(ensamblar(result));
        }
        return resultado;
    };

    protected abstract List<String> camposActualizacion(DTO dto);

    /**
     * UPDATE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean actualizar(Integer pk, DTO dto) throws SQLException {
        String sentenciaActualizar = "UPDATE " + nombreTabla +
                " SET " + camposActualizacion(dto).toString().replace("[", "").replace("]", "") +
                " WHERE " + nombrePK + " = ?";
        System.out.println("SQL Actualizar: [" + sentenciaActualizar + "]");
        PreparedStatement statement = conexion.prepareStatement(sentenciaActualizar);
        statement.setInt(1, pk);
        return statement.executeUpdate() > 0;
    }

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
