package org.iesvdm.gestibank.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.iesvdm.gestibank.dao.SocioDAO;
import org.iesvdm.gestibank.dao.SocioDAOImpl;
import org.iesvdm.gestibank.model.Cliente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BorrarSociosServlet", value = "/BorrarSociosServlet")
public class BorrarSociosServlet extends HttpServlet {
    //EL SERVLET TIENE INSTANCIADO EL DAO PARA ACCESO A BBDD A LA TABLA SOCIO
    //                                  |
    //                                  V
    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        try {
            int codigo = Integer.parseInt(request.getParameter("codigo"));

            //elimino el socio en la base de datos
            this.socioDAO.delete(codigo);

            //obtengo la lista de los socios
            List<Cliente> listado = this.socioDAO.getAll();

            request.setAttribute("listado", listado);

            request.setAttribute("borradoSocioID", this.socioDAO.find(codigo) );

            //redirección a listadoSociosB
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
        } catch (NumberFormatException ex) {
            ex.printStackTrace();

            request.setAttribute("error", "Error al borrar el socio.");
        }


        //SIEMPRE PARA HACER EFECTIVA UNA REDIRECCIÓN INTERNA DEL SERVIDOR
        //TENEMOS QUE HACER FORWARD CON LOS OBJETOS request Y response
        dispatcher.forward(request,response);

    }
}
