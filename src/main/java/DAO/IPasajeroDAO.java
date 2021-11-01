
package DAO;

import Dominio.BusquedaDTO;
import Dominio.Pasajero;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IPasajeroDAO {
    public int crearPersona(Pasajero pasajero) throws SQLException;
    public int crearPasajero(Pasajero pasajero) throws SQLException;
    public Boolean verificarExistenciaPasajero(String tipoDoc, String numDoc) throws SQLException;
    public ArrayList<Pasajero> obtenerPasajeros(BusquedaDTO busquedaDTO) throws SQLException;
    public void modificarPasajero(Pasajero pasajero) throws SQLException;
}
