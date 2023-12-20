package org.iesvdm.gestibank.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.iesvdm.gestibank.dao.SocioDAO;
import org.iesvdm.gestibank.dao.SocioDAOImpl;
import org.iesvdm.gestibank.model.Cliente;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "BuscarSociosServlet", value = "/BuscarSociosServlet")

public class BuscarSociosServlet  extends HttpServlet
{
    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        try {
            String nombre = request.getParameter("nombreBuscar");

            //obtengo la lista de los socios
            List<Cliente> listaBusqueda = this.socioDAO.getAll();

            listaBusqueda = listaBusqueda.stream()
                    .filter(c -> c.getNombre().contains(nombre))
                    .collect(Collectors.toList());

            request.setAttribute("listaBusqueda", listaBusqueda);


            //redirección a listadoBusqueda
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoBusqueda.jsp");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();

            request.setAttribute("error", "Error al borrar el socio.");
        }


        //SIEMPRE PARA HACER EFECTIVA UNA REDIRECCIÓN INTERNA DEL SERVIDOR
        //TENEMOS QUE HACER FORWARD CON LOS OBJETOS request Y response
        dispatcher.forward(request,response);

    }

}
