package com.mycompany.tp5dn1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;
import java.util.TreeSet;

public class VentanaBuscarPorApellido extends JFrame {
    private JList<String> listaApellidos;
    private JTable tablaResultados;
    private DefaultTableModel modelo;
    private DirectorioTelefonico directorio;

    public VentanaBuscarPorApellido(DirectorioTelefonico dir) {
        this.directorio = dir;
        setTitle("Buscar Teléfonos por Apellido");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Set<String> apellidosUnicos = new TreeSet<>(directorio.obtenerTodosLosApellidos());
        listaApellidos = new JList<>(apellidosUnicos.toArray(new String[0]));
        JScrollPane scrollLista = new JScrollPane(listaApellidos);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Apellidos"));

        modelo = new DefaultTableModel();
        modelo.addColumn("Teléfono");
        modelo.addColumn("Nombre Completo");
        modelo.addColumn("Ciudad");

        tablaResultados = new JTable(modelo);
        JScrollPane scrollTabla = new JScrollPane(tablaResultados);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Teléfonos Encontrados"));

        add(scrollLista, BorderLayout.WEST);
        add(scrollTabla, BorderLayout.CENTER);

        listaApellidos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listaApellidos.getSelectedValue() != null) {
                String apellido = listaApellidos.getSelectedValue();
                modelo.setRowCount(0);
                Set<Long> telefonos = directorio.buscarTelefono(apellido);
                for (Long tel : telefonos) {
                    Contacto c = directorio.buscarContacto(tel);
                    modelo.addRow(new Object[]{
                            tel,
                            c.getNombre() + " " + c.getApellido(),
                            c.getCiudad()
                    });
                }
            }
        });
    }
}