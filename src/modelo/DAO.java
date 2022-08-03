package modelo;

import java.sql.SQLException;

public interface DAO<DTO> {

    /**
     * CREATE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean crear(DTO dto) throws SQLException;

    /**
     * RETRIEVE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public DTO recuperar(Integer pk) throws SQLException;

    /**
     * UPDATE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean actualizar(Integer pk, DTO dto) throws SQLException;

    /**
     * DELETE (En términos de CRUD)
     * 
     * @throws SQLException
     */
    public boolean remover(Integer pk) throws SQLException;
}
