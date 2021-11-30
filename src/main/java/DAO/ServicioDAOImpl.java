
package DAO;

import static Conexion.Conexion.close;
import static Conexion.Conexion.getConnection;
import Dominio.Bar;
import Dominio.ItemBar;
import Dominio.LavadoYPlanchado;
import Dominio.Sauna;
import Dominio.Servicio;
import Dominio.TipoPrenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicioDAOImpl implements IServicioDAO{
    private Connection conexionTransaccional;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ServicioDAOImpl() {
    }

    public ServicioDAOImpl(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }
    
    

    @Override
    public List<Servicio> obtenerServiciosEstadia(int idEstadia) throws SQLException{
        List<Servicio> servicios = new ArrayList<>();
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            
            stmt = conn.prepareStatement("SELECT * FROM servicio WHERE idEstadia = ?");
            stmt.setInt(1, idEstadia);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                int idServicio = rs.getInt("idServicio");
               
                //Me fijo si existe un servicio con ese id en tabla sauna
                Sauna sauna = new SaunaDAOImpl(conn).obtenerSauna(idServicio);
                
                //Me fijo si existe un servicio con ese id en tabla lavadoyplanchado
                LavadoYPlanchado lyp = new LavadoYPlanchadoDAOImpl(conn).obtenerLavadoYPlanchado(idServicio);
                
                //Me fijo si existe un servicio con ese id en tabla bar
                Bar bar = new BarDAOImpl(conn).obtenerBar(idServicio);

                if(sauna != null){
                    servicios.add(sauna);
                }
                else if(lyp != null){
                    servicios.add(lyp);
                }
                else{
                    servicios.add(bar);
                }
            }
            
        }finally{
            try {
                if(this.conexionTransaccional == null){
                    close(stmt);
                    close(rs);
                    close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        
        return servicios;
    }
}
