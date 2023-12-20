package org.iesvdm.gestibank.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * POJO o BEAN QUE REPRESENTA LA TABLA cliente
 */

public class Cliente {

    private int clienteId;
    private String nombre;
    private String direccion;
    private String telefono;
    private LocalDate fechaDeNacimiento;

    public Cliente() {
    }

    public Cliente(int clienteId, String nombre, String direccion, String telefono, LocalDate fechaDeNacimiento) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {return telefono;}


    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return clienteId == cliente.clienteId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clienteId);
    }

    @Override
    public String toString() {
        return "Socio{" +
                "clienteId=" + clienteId +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                ", telefono=" + telefono +
                ", fecha de nacimiento='" + fechaDeNacimiento + '\'' +
                '}';
    }
}
