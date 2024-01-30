package view;

import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.*;

import model.*;
import org.hibernate.SessionFactory;

import controller.ClienteDAO;
import controller.EspecialidadDAO;
import controller.EspecialidadesDAO;
import controller.IncidenteDAO;
import controller.ServicioDAO;
import controller.ServiciosDAO;
import controller.TecnicoDAO;

public class Menu {
    public static void main(String[] args) {
        SessionFactory conexion = ConexionHibernate.getSessionFactory();
        TecnicoDAO tecnicoDAO = new TecnicoDAO();
        EspecialidadesDAO especialidadesDAO = new EspecialidadesDAO();
        EspecialidadDAO especialidadDAO = new EspecialidadDAO();
        ServicioDAO servicioDAO = new ServicioDAO();
        ClienteDAO clienteDAO = new ClienteDAO();
        ServiciosDAO serviciosDAO = new ServiciosDAO();
        IncidenteDAO incidenteDAO = new IncidenteDAO();
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú general:");
            System.out.println("********* Mesa de ayuda *********");
            System.out.println("1. Agregar nuevo Incidente");
            System.out.println("********* RRHH *********");
            System.out.println("2. Reporte diario");
            System.out.println("3. Reporte Tecnico con mas incidentes");
            System.out.println("********* Area Comercial *********");
            System.out.println("4. Ingresar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("6. Actualizar cliente");
            System.out.println("7. Listar Clientes");
            System.out.println("********* AREA TECNICOS ->TO DO *********");
            System.out.println("8.CRUD tecnicos:posee la misma logica que la de cliente");

            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            int id;
            int id2;
            String rz;
            Integer cuit;
            String problema;
            Integer minutosAdicionales;
            Integer op;

            switch (opcion) {
                case 1:
                    LocalDateTime fechahoraActual = LocalDateTime.now();

                    System.out.println("--estas por agregar un nuevo incidente--\n");

                    clienteDAO.listarTodos().stream().forEach(x -> System.out.println(x));
                    System.out.println("ingrese el id del cliente:");
                    id = scanner.nextInt();
                    Cliente cliente = clienteDAO.leer(id);

                    tecnicoDAO.listarTodos().stream().forEach(x -> System.out.println(x));
                    System.out.println("ingrese el id del Tecnico:");
                    id2 = scanner.nextInt();
                    Tecnico tecnico = tecnicoDAO.leer(id2);
                    scanner.nextLine();
                    System.out.println("indique tipo de problema resumido:");
                    problema = scanner.nextLine();

                    System.out.println("agregue minutos al tiempo estimado:");
                    minutosAdicionales = scanner.nextInt();


                    Incidente incidente = new Incidente(cliente, problema, "", tecnico, fechahoraActual, fechahoraActual.plusMinutes(minutosAdicionales));
                    incidenteDAO.insertar(incidente);

                    System.out.println("INFORMAR AL CLIENTE:el incidente se resolvera aproximadamente :" + fechahoraActual.plusMinutes(minutosAdicionales));
                    System.out.println("INFORMAR AL TECNICO:tiene un nuevo incidente para resolver :" + incidente);
                    break;
                case 2:
                    System.out.println("obteniendo reporte diarios");
                    Map<Tecnico, List<Incidente>> incidentesPorTecnico = incidenteDAO.agruparIncidentesPorTecnico();

                    for (Map.Entry<Tecnico, List<Incidente>> entry : incidentesPorTecnico.entrySet()) {
                        Tecnico tecnico2 = entry.getKey();
                        List<Incidente> incidentes = entry.getValue();

                        System.out.println("Técnico: " + tecnico2.getNombreTecnico());
                        System.out.println("Incidentes:");

                        for (Incidente incidente2 : incidentes) {
                            System.out.println(incidente2);
                        }

                    }
                    System.out.println();

                    break;
                case 3:
                    System.out.println("Reporte del Tenico con mas incidentes asignados:");
                    Tecnico tecnicoConMasIncidentes = incidenteDAO.obtenerTecnicoConMasIncidentes();
                    System.out.println(tecnicoConMasIncidentes.getNombreTecnico());
                    break;
                case 4:


                    try {
                        scanner.nextLine();
                        System.out.println("Agregar nuevo Cliente");
                        System.out.println("ingrese razon social: ");
                        rz = scanner.nextLine();
                        System.out.println("ingrese cuit:");
                        cuit = scanner.nextInt();

                        //Mostrar Servicios disponibles por pantalla
                        System.out.println("estos son los servicios disponibles de la empresa:");
                        for (Servicio serv : servicioDAO.listarTodos()) {
                            System.out.println("id:" + serv.getIdServicio() + " servicio:" + serv.getNombreServicio());
                        }

                        List<Servicios> nuevosServicios = new ArrayList<>();
                        Cliente cliente3 = new Cliente(rz, cuit, nuevosServicios);

                        System.out.println("cuantos servicios desea contratar?");
                        op = scanner.nextInt();

                        for (int x = 1; x <= op; x++) {
                            System.out.println("ingrese el n° de servicio: " + x);
                            id = scanner.nextInt();
                            nuevosServicios.add(new Servicios(cliente3, servicioDAO.leer(id))); // Crear instancia de Servicios
                        }

                        // Asignar la lista de servicios al cliente3
                        cliente3.setListaDeServicios(nuevosServicios);




                        System.out.println("agregando cliente");

                        clienteDAO.insertar(cliente3);

                    } catch (Exception e) {
                        System.out.println("ocurrion un error" + e.getMessage());
                    }


                    break;
                case 5:
                    System.out.println("Eliminar cliente");

                    clienteDAO.listarTodos().stream().forEach(x -> System.out.println("id: "+x.getIdCliente()+" rs: "+x.getRazonSocial()));
                    System.out.println("ingrese id para eliminar cliente");
                    id = scanner.nextInt();
                    serviciosDAO.eliminarPorID(id);

                    Cliente cli = clienteDAO.leer(id);

                    clienteDAO.eliminar(cli);
                    break;

                case 6:
                    try {
                        System.out.println("Actualizar cliente");

                        clienteDAO.listarTodos().stream().forEach(x -> System.out.println("id: "+x.getIdCliente()+" rs: "+x.getRazonSocial()));

                        System.out.println("ingrese id para actualizar cliente");
                        id = scanner.nextInt();
                        Cliente cli2 = clienteDAO.leer(id);
                        scanner.nextLine();
                        System.out.println("desea actualizar razon social? S/N");
                        String eleccion = scanner.nextLine().toUpperCase();
                        if(eleccion.equals("S")) {
                            System.out.println("ingrese razon social");
                            cli2.setRazonSocial(scanner.nextLine());
                            clienteDAO.actualizar(cli2);
                        }

                            //TO DO para cuit seria lo mismo....

                        System.out.println("cliente actualizado");
                        break;
                    } catch (Exception e) {
                        System.out.println("ocurrio un error " + e.getMessage());
                    }
                case 7:
                
                    clienteDAO.listarTodos().stream().forEach(x ->{
                        System.out.println("id"+x.getIdCliente()+" cuit"+ x.getCuit()+" rz"+x.getRazonSocial()   );
                        x.getListaDeServicios().stream().forEach(y-> System.out.println(y.getServicio()));



                    });



                       break;

                case 8:
                    System.out.println("Posee la misma logica que para agregar un cliente,pero no llegue con el tiempo");
                    System.out.println("revisar los metodos implementados en el la clase PruebasMetodos");
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elige una opción del menú.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
}
