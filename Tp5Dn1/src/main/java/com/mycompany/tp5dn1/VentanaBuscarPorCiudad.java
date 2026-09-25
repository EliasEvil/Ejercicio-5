package com.mycompany.tp5dn1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

public class VentanaBuscarPorCiudad extends JFrame {
    private JComboBox<String> cmbCiudades;
    private JTable tablaResultados;
    private DefaultTableModel modelo;
    private DirectorioTelefonico directorio;

    public VentanaBuscarPorCiudad(DirectorioTelefonico dir) {
        this.directorio = dir;
        setTitle("Buscar Contactos por Ciudad");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelArriba = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelArriba.add(new JLabel("Seleccione Ciudad:"));

        Set<String> ciudades = directorio.obtenerTodasLasCiudades();
        cmbCiudades = new JComboBox<>(ciudades.toArray(new String[0]));
        panelArriba.add(cmbCiudades);

        modelo = new DefaultTableModel();
        modelo.addColumn("Teléfono");
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Dirección");

        tablaResultados = new JTable(modelo);
        JScrollPane scrollTabla = new JScrollPane(tablaResultados);

        add(panelArriba, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

        cmbCiudades.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                String ciudadSeleccionada = (String) cmbCiudades.getSelectedItem();

                modelo.setRowCount(0);

                ArrayList<Contacto> lista = directorio.buscarContactos(ciudadSeleccionada);
                for (Contacto c : lista) {

                    Long tel = null;
                    for (var entry : directorio.obtenerTodosLosTelefonos()) {
                        if (directorio.buscarContacto(entry).equals(c)) {
                            tel = entry;
                            break;
                        }
                    }
                    modelo.addRow(new Object[]{
                            tel,
                            c.getDni(),
                            c.getNombre(),
                            c.getApellido(),
                            c.getDireccion()
                    });
                }
            }
        });
    }
}