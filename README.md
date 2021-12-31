# TP Final - Sistema para Hotel
El siguiente proyecto fue realizado en la materia "Diseño de Sistemas" de la Carrera de Ingenieria en Sistemas de Informacion de la UTN-FRSF. Trabaje junto a 3 compañeras en su desarrollo durante todo el año 2021.

## Indice
1. Acerca de la Implementacion
2. Imagenes de las Interfaces
3. Etapas del Proyecto


## Acerca de la Implementacion
El sistema fue realizado en formato escritorio con Java, en el IDE Netbeans. Se utilizo JDBC (Java Database Connectivity) con Base de Datos MySQL, se tuvieron en cuenta los principios de la Programacion Orientada a Objetos, Interfaces, Gestores, Patrones DAO y Singleton y Transacciones.

## Imagenes
<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147797056-fadabcab-fbe7-492f-9771-c27edbef5a7b.png" alt="interfaz-principal" width="20%">
</div>

<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147797111-3bb90f1b-7ea8-413b-a088-2b71bfdc17e5.png" height="300px" alt="alta-pasajero">

<img src="https://user-images.githubusercontent.com/75265449/147797123-edc50b30-295a-41df-9ce9-b47f566c848e.png" height="300px" alt="alta-pasajero">
</div>

<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147797136-c5c4a6e8-6937-40d9-a0a4-7af8d84dc8d2.png" height="300px" alt="alta-pasajero">

<img src="https://user-images.githubusercontent.com/75265449/147797151-17d14ede-8bc8-4dcc-a8cd-f60a623c1fe6.png" height="300px"  alt="alta-pasajero">
</div>

<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147797169-465dc2ac-421f-40a4-a9bc-07babd2ac09b.png"  height="300px" alt="alta-pasajero">

<img src="https://user-images.githubusercontent.com/75265449/147797200-52a2c572-231d-4419-94ca-41e775f14d46.png"  height="300px"  alt="alta-pasajero">
</div>

## Etapas del proyecto
1 - Se comenzo realizando el <code>Diseño de Entradas y Salidas</code> del sistema a partir del enunciado y los casos de uso brindados por la catedra. Se realizaron mock-ups de todas las interfaces y se especificaron las longitudes y los formatos de la informacion de entrada y salida.
<br>
2 - Se realizo el <code>Diagrama de Clases</code> teniendo en cuenta la presencia de Interfaces y Gestores. En los gestores se implemento el <b>Patron Singleton</b> y en las demas clases se utilizo el <b>Patron DAO</b>. 
<div align="center">
  <a href="https://user-images.githubusercontent.com/75265449/147794738-900f8a4c-1c07-48c6-8fea-08dc04f0b029.png" target="_blank">Ver Diagrama de Clases</a>
  <img src="https://user-images.githubusercontent.com/75265449/147794738-900f8a4c-1c07-48c6-8fea-08dc04f0b029.png" alt="diagrama-clases" width="30%">
</div>
<br>

3 - Se realizo el <code>Diagrama Entidad-Relacion</code> y el <code>Diagrama de Tablas</code>.
<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147796668-d71af687-385d-4474-9812-a81baff42fe3.png"  alt="diagrama-der" width="30%">
</div>

4 - Se realizo el <code>Diagrama de Transicion de Estados</code> de una Habitacion.
<br>
<div align="center">
  <img src="https://user-images.githubusercontent.com/75265449/147796081-3d8b3da6-2f52-4829-8017-7c0fc3913445.png" alt="diagrama-estados" width="50%">
</div>
<br>
5 - Se realizaron los <code>Diagramas de Secuencia</code> de los casos de uso:
<li>Gestionar Pasajero</li>
<li>Dar Alta de Pasajero</li>
<li>Reservar Habitacion</li>
<li>Mostrar Estado Habitacion</li>
<li>Ocupar Habitacion</li>
<li>Facturar</li>
<li>Ingresar Pago</li>
<div align="center">
<img src="https://user-images.githubusercontent.com/75265449/147796313-134f50ba-a851-469c-87e0-cd71165a047b.png" alt="diagrama-secuencia" width="50%">
</div>

<br>
6 - Se implementaron los siguientes casos de uso:

<li> <b>Dar Alta de Pasajero:</b> Se agrega un pasajero.</li>
<li> <b>Modificar Pasajero:</b> Se modifican los datos de un pasajero.</li>
<li> <b>Gestionar Pasajero:</b> Se buscan los pasajeros por "nombre", "apellido", "tipo de documento", "numero de documento" y se listan los resultados.</li>
<li> <b>Mostrar Estado Habitacion:</b> Tabla donde se muestra el estado de cada habitacion dentro de un rango de fechas.</li>
<li> <b>Ocupar Habitacion:</b> Se registra una estadia en una habitacion.</li>
<li> <b>Facturar:</b> Se facturan los servicios consumidos por los pasajeros de la ultima estadia de la habitacion.</li>

