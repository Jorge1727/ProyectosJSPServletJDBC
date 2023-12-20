package org.iesvdm.gestibank.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.gestibank.dao.SocioDAO;
import org.iesvdm.gestibank.dao.SocioDAOImpl;
import org.iesvdm.gestibank.model.Cliente;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarSociosServlet", value = "/ListarSociosServlet")
public class ListarSociosServlet extends HttpServlet {

    private SocioDAO socioDAO = new SocioDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");

        List<Cliente> listado = this.socioDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);

    }

}
