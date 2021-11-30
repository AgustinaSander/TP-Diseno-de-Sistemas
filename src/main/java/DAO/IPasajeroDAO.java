
package DAO;

import Dominio.DTO.GestionarPasajeroDTO;
import Dominio.OcupadaPor;
import Dominio.Pasajero;
import java.sql.SQLException;
import java.util.List;


public interface IPasajeroDAO {
    public int crearPersona(Pasajero pasajero) throws SQLException;
    public int crearPasajero(Pasajero pasajero) throws SQLException;
    public List<Pasajero> verificarExistenciaPasajero(String tipoDoc, String numDoc) throws SQLException;
    public List<Pasajero> obtenerPasajeros(GestionarPasajeroDTO busquedaDTO) throws SQLException;
    public void modificarPasajero(Pasajero pasajero) throws SQLException;
    public Pasajero obtenerPasajero(int idPasajero) throws SQLException;
    public List<OcupadaPor> obtenerOcupantesEstadia(int idEstadia) throws SQLException;
}
