package com.mycompany.tp5dn1;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Set;

public class VentanaBuscar extends JFrame {
    private JList<Long> listaTelefonos;
    private JTextField txtDni, txtNombre, txtApellido, txtCiudad, txtDireccion;
    private DirectorioTelefonico directorio;

    public VentanaBuscar(DirectorioTelefonico dir) {
        this.directorio = dir;
        setTitle("Buscar por Teléfono");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Set<Long> telefonos = directorio.obtenerTodosLosTelefonos();
        listaTelefonos = new JList<>(telefonos.toArray(new Long[0]));
        listaTelefonos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel panelIzq = new JPanel(new BorderLayout());
        panelIzq.add(new JLabel("Seleccione un teléfono:"), BorderLayout.NORTH);
        panelIzq.add(new JScrollPane(listaTelefonos), BorderLayout.CENTER);

        JPanel panelDer = new JPanel(new GridLayout(5, 2, 5, 5));
        panelDer.add(new JLabel("DNI:")); txtDni = new JTextField(); txtDni.setEditable(false); panelDer.add(txtDni);
        panelDer.add(new JLabel("Nombre:")); txtNombre = new JTextField(); txtNombre.setEditable(false); panelDer.add(txtNombre);
        panelDer.add(new JLabel("Apellido:")); txtApellido = new JTextField(); txtApellido.setEditable(false); panelDer.add(txtApellido);
        panelDer.add(new JLabel("Ciudad:")); txtCiudad = new JTextField(); txtCiudad.setEditable(false); panelDer.add(txtCiudad);
        panelDer.add(new JLabel("Dirección:")); txtDireccion = new JTextField(); txtDireccion.setEditable(false); panelDer.add(txtDireccion);

        add(panelIzq, BorderLayout.WEST);
        add(panelDer, BorderLayout.CENTER);

        listaTelefonos.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && listaTelefonos.getSelectedValue() != null) {
                    Long tel = listaTelefonos.getSelectedValue();
                    Contacto c = directorio.buscarContacto(tel);
                    if (c != null) {
                        txtDni.setText(c.getDni());
                        txtNombre.setText(c.getNombre());
                        txtApellido.setText(c.getApellido());
                        txtCiudad.setText(c.getCiudad());
                        txtDireccion.setText(c.getDireccion());
                    }
                }
            }
        });
    }
}
