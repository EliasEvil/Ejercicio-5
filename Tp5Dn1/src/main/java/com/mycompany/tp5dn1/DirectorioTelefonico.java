package com.mycompany.tp5dn1;

import java.util.*;
import java.util.TreeMap;

public class DirectorioTelefonico {
    private TreeMap<Long, Contacto> contactos;

    public DirectorioTelefonico() {
        contactos = new TreeMap<>();
    }

    // A. Agregar contacto
    public boolean agregarContacto(Long telefono, Contacto contacto) {
        if (contactos.containsKey(telefono)) {
            return false; 
        }
        contactos.put(telefono, contacto);
        return true;
    }

    public Contacto buscarContacto(Long telefono) {
        return contactos.get(telefono);
    }

    public Set<Long> buscarTelefono(String apellido) {
        Set<Long> resultado = new TreeSet<>();
        for (Map.Entry<Long, Contacto> entrada : contactos.entrySet()) {
            if (entrada.getValue().getApellido().equalsIgnoreCase(apellido)) {
                resultado.add(entrada.getKey());
            }
        }
        return resultado;
    }

    public ArrayList<Contacto> buscarContactos(String ciudad) {
        ArrayList<Contacto> lista = new ArrayList<>();
        for (Contacto c : contactos.values()) {
            if (c.getCiudad().equalsIgnoreCase(ciudad)) {
                lista.add(c);
            }
        }
        return lista;
    }

    public boolean borrarContacto(Long telefono) {
        if (contactos.containsKey(telefono)) {
            contactos.remove(telefono);
            return true;
        }
        return false;
    }

    public Set<Long> obtenerTodosLosTelefonos() {
        return contactos.keySet();
    }

    public Set<String> obtenerTodosLosApellidos() {
        Set<String> apellidos = new TreeSet<>();
        for (Contacto c : contactos.values()) {
            apellidos.add(c.getApellido());
        }
        return apellidos;
    }

    public Set<String> obtenerTodasLasCiudades() {
        Set<String> ciudades = new TreeSet<>();
        for (Contacto c : contactos.values()) {
            ciudades.add(c.getCiudad());
        }
        return ciudades;
    }
}
