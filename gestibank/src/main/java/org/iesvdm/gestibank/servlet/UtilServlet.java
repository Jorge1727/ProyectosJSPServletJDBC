package org.iesvdm.gestibank.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.gestibank.model.Cliente;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

public class UtilServlet {

    public static Optional<Cliente> validaGrabar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        //int socioID = -1;
        String nombre = null;
        String direccion = null;
        String telefono = null;
        LocalDate fechaNac = null;
        try {

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("nombre"));
            //CONTRACT nonBlank..
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");


            direccion = request.getParameter("direccion");

            telefono = request.getParameter("telefono");

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("fecha_de_nacimiento"));
            if (request.getParameter("fecha_de_nacimiento").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            // Utilizar SimpleDateFormat para convertir el String de fecha a LocalDate
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaNac = sdf.parse(request.getParameter("fecha_de_nacimiento")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return Optional.of(new Cliente(-1, nombre, direccion, telefono, fechaNac));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }


    /**
     * valida para poder hacer update en la BBDD obteniendo el codigo del primer get en listadoSociosB
     * @param request
     * @return
     */
    public static Optional<Cliente> validaEditar(HttpServletRequest request) {

        //CÓDIGO DE VALIDACIÓN
        boolean valida = true;
        int id = Integer.parseInt(request.getParameter("codigo"));
        String nombre = null;
        String direccion = null;
        String telefono = null;
        LocalDate fechaNac = null;
        try {

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("nombre"));
            //CONTRACT nonBlank..
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            direccion = request.getParameter("direccion");

            telefono = request.getParameter("telefono");

            //UTILIZO LOS CONTRACTS DE LA CLASE Objects PARA LA VALIDACIÓN
            //             v---- LANZA NullPointerException SI EL PARÁMETRO ES NULL
            Objects.requireNonNull(request.getParameter("localidad"));
            //CONTRACT nonBlank
            //UTILIZO isBlank SOBRE EL PARÁMETRO DE TIPO String PARA CHEQUEAR QUE NO ES UN PARÁMETRO VACÍO "" NI CADENA TODO BLANCOS "    "
            //          |                                EN EL CASO DE QUE SEA BLANCO LO RECIBIDO, LANZO UNA EXCEPCIÓN PARA INVALIDAR EL PROCESO DE VALIDACIÓN
            //          -------------------------v                      v---------------------------------------|
            if (request.getParameter("fecha_de_nacimiento").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");

            // Utilizar SimpleDateFormat para convertir el String de fecha a LocalDate
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            fechaNac = sdf.parse(request.getParameter("fecha_de_nacimiento")).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return Optional.of(new Cliente(id, nombre, direccion, telefono, fechaNac));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //FIN CÓDIGO DE VALIDACIÓN
        return Optional.empty();

    }

}
