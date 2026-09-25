package com.mycompany.tp5dn1;

import javax.swing.*;
import java.awt.*;

public class VentanaAgregar extends JFrame {
    
    private JTextField txtDni, txtNombre, txtApellido, txtCiudad, txtDireccion, txtTelefono;
    private DirectorioTelefonico directorio;
    private VentanaPrincipal ventanaPrincipal;

    public VentanaAgregar(DirectorioTelefonico dir, VentanaPrincipal vp) {
        this.directorio = dir;
        this.ventanaPrincipal = vp;

        setTitle("Agregar Nuevo Contacto");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(); add(txtTelefono);

        add(new JLabel("DNI:"));
        txtDni = new JTextField(); add(txtDni);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(); add(txtNombre);

        add(new JLabel("Apellido:"));
        txtApellido = new JTextField(); add(txtApellido);

        add(new JLabel("Ciudad:"));
        txtCiudad = new JTextField(); add(txtCiudad);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField(); add(txtDireccion);

        JButton btnGuardar = new JButton("💾 Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        btnGuardar.addActionListener(e -> guardarContacto());
        btnCancelar.addActionListener(e -> dispose());

        add(btnGuardar); add(btnCancelar);
    }

    private void guardarContacto() {
        try {
            Long tel = Long.parseLong(txtTelefono.getText().trim());
            String dni = txtDni.getText().trim();
            String nom = txtNombre.getText().trim();
            String ape = txtApellido.getText().trim();
            String ciu = txtCiudad.getText().trim();
            String dir = txtDireccion.getText().trim();

            if (dni.isEmpty() || nom.isEmpty() || ape.isEmpty() || ciu.isEmpty() || dir.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Completar todos los campos", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Contacto nuevo = new Contacto(dni, nom, ape, ciu, dir);
            if (directorio.agregarContacto(tel, nuevo)) {
                JOptionPane.showMessageDialog(this, "✅ Contacto agregado correctamente");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "⚠️ Ese número ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}