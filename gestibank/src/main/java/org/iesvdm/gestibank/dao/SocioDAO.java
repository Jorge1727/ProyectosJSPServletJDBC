package org.iesvdm.gestibank.dao;

import org.iesvdm.gestibank.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface SocioDAO {

    public void create(Cliente cliente);

    public List<Cliente> getAll();
    public Optional<Cliente> find(int id);

    public void update(Cliente cliente);

    public void delete(int id);
}
