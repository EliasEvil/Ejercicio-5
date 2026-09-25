package com.mycompany.tp5dn1;

import javax.swing.*;
import java.awt.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class VentanaPrincipal extends JFrame {
    private DirectorioTelefonico directorio;

    public VentanaPrincipal() {
        directorio = new DirectorioTelefonico();
        inicializarDatosPrueba();

        setTitle("Directorio Telefónico");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JButton btnAgregar = new JButton("➕ Agregar Contacto");
        JButton btnBuscarTel = new JButton("🔍 Buscar por Teléfono");
        JButton btnBuscarApellido = new JButton("📋 Buscar Teléfonos por Apellido");
        JButton btnBuscarCiudad = new JButton("🏙️ Buscar por Ciudad");
        JButton btnBorrar = new JButton("🗑️ Borrar Contacto");
        JButton btnSalir = new JButton("❌ Salir");

        btnAgregar.addActionListener(e -> new VentanaAgregar(directorio, this).setVisible(true));
        btnBuscarTel.addActionListener(e -> new VentanaBuscar(directorio).setVisible(true));
        btnBuscarApellido.addActionListener(e -> new VentanaBuscarPorApellido(directorio).setVisible(true));
        btnBuscarCiudad.addActionListener(e -> new VentanaBuscarPorCiudad(directorio).setVisible(true));
        btnBorrar.addActionListener(e -> new VentanaBorrar(directorio).setVisible(true));
        btnSalir.addActionListener(e -> System.exit(0));

        add(btnAgregar); add(btnBuscarTel); add(btnBuscarApellido);
        add(btnBuscarCiudad); add(btnBorrar); add(btnSalir);
    }

    private void inicializarDatosPrueba() {
        directorio.agregarContacto(2661234567L, new Contacto("12345678", "María", "Pérez", "San Luis", "Calle Falsa 123"));
        directorio.agregarContacto(2662345678L, new Contacto("23456789", "Juan", "Pérez", "Mendoza", "Av. Libertad 456"));
        directorio.agregarContacto(2663456789L, new Contacto("34567890", "Ana", "López", "San Luis", "San Martín 789"));
        directorio.agregarContacto(2664567890L, new Contacto("45678901", "Luis", "Gómez", "San Luis", "Belgrano 321"));
        directorio.agregarContacto(2665678901L, new Contacto("56789012", "Sofía", "Pérez", "Córdoba", "Mitre 654"));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
    
   

}
