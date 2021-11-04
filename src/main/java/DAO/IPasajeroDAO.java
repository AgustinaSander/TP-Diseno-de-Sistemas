
package DAO;

import Dominio.GestionarPasajeroDTO;
import Dominio.Pasajero;
import java.sql.SQLException;
import java.util.List;


public interface IPasajeroDAO {
    public int crearPersona(Pasajero pasajero) throws SQLException;
    public int crearPasajero(Pasajero pasajero) throws SQLException;
    public Boolean verificarExistenciaPasajero(String tipoDoc, String numDoc) throws SQLException;
    public List<GestionarPasajeroDTO> obtenerPasajeros(GestionarPasajeroDTO busquedaDTO) throws SQLException;
    public void modificarPasajero(Pasajero pasajero) throws SQLException;
    public Pasajero obtenerPasajero(int idPasajero);
}
