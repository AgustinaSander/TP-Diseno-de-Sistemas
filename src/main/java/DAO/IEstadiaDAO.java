
package DAO;

import Dominio.Estadia;
import Dominio.Habitacion;
import Dominio.OcupadaPor;
import Dominio.Pasajero;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;


public interface IEstadiaDAO {
    
    public List<Estadia> obtenerListaEstadias(Habitacion hab, Date fechaDesde, Date fechaHasta) throws SQLException;
    public int crearEstadia(Estadia estadia) throws SQLException;
    public List<Pasajero> obtenerPasajerosEstadia(int idEstadia);
    public Estadia obtenerUltimaEstadia(String nroHabitacion);
    public List<OcupadaPor> obtenerOcupantesEstadia(int idEstadia) throws SQLException;
    public Estadia obtenerEstadia(int idEstadia) throws SQLException;
}
