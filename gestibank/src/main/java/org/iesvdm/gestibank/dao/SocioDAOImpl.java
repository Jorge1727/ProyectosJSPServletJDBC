package org.iesvdm.gestibank.dao;

import org.iesvdm.gestibank.model.Cliente;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SocioDAOImpl extends AbstractDAOImpl implements SocioDAO {
    @Override
    public void create(Cliente cliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();


            //1 alternativas comentadas:
            //Ver también, AbstractDAOImpl.executeInsert ...
            //Columna fabricante.codigo es clave primaria auto_increment, por ese motivo se omite de la sentencia SQL INSERT siguiente.
            ps = conn.prepareStatement("INSERT INTO cliente (nombre, direccion, telefono, fecha_de_nacimiento) VALUES (?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setDate(idx++, Date.valueOf(cliente.getFechaDeNacimiento()));


            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de cliente con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                cliente.setClienteId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) { //o uno o otro error en un solo catch
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public List<Cliente> getAll() {

        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Cliente> listCliente = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM cliente");
            while (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setClienteId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                Date fechaSQL = rs.getDate("fecha_de_nacimiento");
                LocalDate fechaNacimiento = fechaSQL.toLocalDate();

                cliente.setFechaDeNacimiento(fechaNacimiento);
                listCliente.add(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listCliente;

    }

    @Override
    public Optional<Cliente> find(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("SELECT * FROM cliente WHERE id = ?");

            ps.setInt(1, id);

            rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente();

                cliente.setClienteId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                Date fechaSQL = rs.getDate("fecha_de_nacimiento");

                LocalDate fechaNacimiento = fechaSQL.toLocalDate();

                cliente.setFechaDeNacimiento(fechaNacimiento);
                return Optional.of(cliente);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

        return Optional.empty();

    }

    @Override
    public void update(Cliente cliente) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("UPDATE cliente SET nombre = ?, direccion = ?, telefono = ?, fecha_de_nacimiento = ?  WHERE id = ?");
            int idx = 1;
            ps.setString(idx++, cliente.getNombre());
            ps.setString(idx++, cliente.getDireccion());
            ps.setString(idx++, cliente.getTelefono());
            ps.setDate(idx++, Date.valueOf(cliente.getFechaDeNacimiento()));
            ps.setInt(idx++, cliente.getClienteId());

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Update de cliente con 0 registros actualizados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }

    @Override
    public void delete(int id) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("DELETE FROM cliente WHERE id = ?");
            int idx = 1;
            ps.setInt(idx, id);

            int rows = ps.executeUpdate();

            if (rows == 0)
                System.out.println("Delete de cliente con 0 registros eliminados.");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }

    }
}
