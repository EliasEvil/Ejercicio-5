package com.mycompany.tp5dn1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class VentanaBorrar extends JFrame {
    private JList<Long> listaTelefonos;
    private JTable tablaDatos;
    private DefaultTableModel modelo;
    private DirectorioTelefonico directorio;

    public VentanaBorrar(DirectorioTelefonico dir) {
        this.directorio = dir;
        setTitle("Borrar Contacto");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Set<Long> telefonos = directorio.obtenerTodosLosTelefonos();
        listaTelefonos = new JList<>(telefonos.toArray(new Long[0]));
        listaTelefonos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollLista = new JScrollPane(listaTelefonos);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Seleccione Teléfono"));

        modelo = new DefaultTableModel();
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Dirección");
        tablaDatos = new JTable(modelo);
        tablaDatos.setPreferredScrollableViewportSize(new Dimension(400, 0));
        JScrollPane scrollTabla = new JScrollPane(tablaDatos);
        scrollTabla.setBorder(BorderFactory.createTitledBorder("Datos del Contacto"));

        JButton btnConfirmarBorrado = new JButton("🗑️ Eliminar Contacto");
        btnConfirmarBorrado.addActionListener(e -> borrarSeleccionado());

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.add(scrollTabla, BorderLayout.CENTER);
        panelDerecho.add(btnConfirmarBorrado, BorderLayout.SOUTH);

        add(scrollLista, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        listaTelefonos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listaTelefonos.getSelectedValue() != null) {
                Long tel = listaTelefonos.getSelectedValue();
                Contacto c = directorio.buscarContacto(tel);
                modelo.setRowCount(0);
                if (c != null) {
                    modelo.addRow(new Object[]{
                            c.getDni(),
                            c.getNombre(),
                            c.getApellido(),
                            c.getCiudad(),
                            c.getDireccion()
                    });
                }
            }
        });
    }

    private void borrarSeleccionado() {
        Long telSeleccionado = listaTelefonos.getSelectedValue();
        if (telSeleccionado == null) {
            JOptionPane.showMessageDialog(this, "Seleccione un contacto primero", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Seguro desea eliminar el contacto " + telSeleccionado + "?",
                "Confirmar Borrado",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            if (directorio.borrarContacto(telSeleccionado)) {
                JOptionPane.showMessageDialog(this, "✅ Contacto eliminado");
                // Refrescar lista
                listaTelefonos.setListData(directorio.obtenerTodosLosTelefonos().toArray(new Long[0]));
                modelo.setRowCount(0);
            } else {
                JOptionPane.showMessageDialog(this, "❌ No se pudo eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
