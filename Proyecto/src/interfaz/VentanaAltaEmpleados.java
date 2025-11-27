package interfaz;

import dominio.Area;
import dominio.Empleado;
import dominio.Manager;
import dominio.Sistema;
import io.ArchivoGrabacion;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class VentanaAltaEmpleados extends javax.swing.JFrame implements PropertyChangeListener {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaAltaEmpleados.class.getName());

    public VentanaAltaEmpleados() {

    }
    private Sistema s;
    public VentanaAltaEmpleados(Sistema sistema) {
        initComponents();
        s = sistema;
        s.agregarListener(this);
        cargarListaCombo();
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        cargarListaCombo();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField3 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstEmpleados = new javax.swing.JList<>();
        Nombre = new javax.swing.JLabel();
        Cedula = new javax.swing.JLabel();
        Celular = new javax.swing.JLabel();
        Curriculum = new javax.swing.JLabel();
        SalarioMensual = new javax.swing.JLabel();
        Manager = new javax.swing.JLabel();
        Area = new javax.swing.JLabel();
        cmbManager = new javax.swing.JComboBox<>();
        cmbArea = new javax.swing.JComboBox<>();
        txtNombre = new javax.swing.JTextField();
        txtCi = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        Ingresar = new javax.swing.JButton();
        Cancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtCurriculum = new javax.swing.JTextArea();
        txtSalarioMensual = new javax.swing.JSpinner();

        jTextField3.setText("jTextField1");

        jTextField5.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alta de Empleados");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alta Empleados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(255, 204, 0))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 204, 0));

        lstEmpleados.setBackground(new java.awt.Color(255, 232, 145));
        lstEmpleados.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(lstEmpleados);

        Nombre.setForeground(new java.awt.Color(255, 204, 0));
        Nombre.setText("Nombre:");

        Cedula.setForeground(new java.awt.Color(255, 204, 0));
        Cedula.setText("Cedula (única):");

        Celular.setForeground(new java.awt.Color(255, 204, 0));
        Celular.setText("Celular:");

        Curriculum.setForeground(new java.awt.Color(255, 204, 0));
        Curriculum.setText("Texto del curriculum:");

        SalarioMensual.setForeground(new java.awt.Color(255, 204, 0));
        SalarioMensual.setText("Salario Mensual:");

        Manager.setForeground(new java.awt.Color(255, 204, 0));
        Manager.setText("Manager:");

        Area.setForeground(new java.awt.Color(255, 204, 0));
        Area.setText("Area:");

        cmbManager.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbManager.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbManagerActionPerformed(evt);
            }
        });

        cmbArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        Ingresar.setBackground(new java.awt.Color(255, 204, 0));
        Ingresar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Ingresar.setForeground(new java.awt.Color(255, 255, 255));
        Ingresar.setText("Ingresar");
        Ingresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IngresarActionPerformed(evt);
            }
        });

        Cancelar.setBackground(new java.awt.Color(255, 204, 0));
        Cancelar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Cancelar.setForeground(new java.awt.Color(255, 255, 255));
        Cancelar.setText("Cancelar");
        Cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarActionPerformed(evt);
            }
        });

        txtCurriculum.setColumns(20);
        txtCurriculum.setRows(5);
        jScrollPane2.setViewportView(txtCurriculum);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Cedula)
                                    .addComponent(Celular)
                                    .addComponent(Curriculum)
                                    .addComponent(Manager)
                                    .addComponent(SalarioMensual))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCelular)
                                            .addComponent(txtCi)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSalarioMensual))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbManager, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(Nombre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(Area, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(cmbArea, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Ingresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Cancelar)
                        .addGap(30, 30, 30))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nombre)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cedula)
                    .addComponent(txtCi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Celular)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Curriculum)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SalarioMensual)
                    .addComponent(txtSalarioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Manager)
                    .addComponent(cmbManager, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Area)
                    .addComponent(cmbArea, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Ingresar)
                    .addComponent(Cancelar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IngresarActionPerformed
        String nombre = txtNombre.getText().trim();
        String ci = txtCi.getText().trim();
        String celular = txtCelular.getText().trim();
        String textocurriculum = txtCurriculum.getText().trim();
        double salario = ((Number) txtSalarioMensual.getValue()).doubleValue();
        Manager manager = s.buscarManagerPorCI(cmbManager.getSelectedItem().toString());
        Area area = s.buscarAreaPorNombre(cmbArea.getSelectedItem().toString());

        if (nombre.isEmpty()
                || ci.isEmpty()
                || celular.isEmpty()
                || textocurriculum.isEmpty()
                || salario <= 0
                || manager == null
                || area == null
                || s.buscarEmpleadoPorCI(ci) != null
                || s.buscarManagerPorCI(ci) != null) {
            JOptionPane.showMessageDialog(this, "Error al agregar el empleado. Verifique los datos.");
        } else {
            Empleado e = new Empleado(nombre, ci, salario, manager, textocurriculum, area, celular);
            s.altaEmpleado(e);
            s.agregarEmpleadoArea(e, area);
            guardarCurriculum(ci, textocurriculum);
            JOptionPane.showMessageDialog(this, "Empleado agregado correctamente.");
            cargarListaCombo();
        }

    }//GEN-LAST:event_IngresarActionPerformed


    private void cmbManagerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbManagerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbManagerActionPerformed

    private void CancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarActionPerformed
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_CancelarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaAltaEmpleados().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Area;
    private javax.swing.JButton Cancelar;
    private javax.swing.JLabel Cedula;
    private javax.swing.JLabel Celular;
    private javax.swing.JLabel Curriculum;
    private javax.swing.JButton Ingresar;
    private javax.swing.JLabel Manager;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel SalarioMensual;
    private javax.swing.JComboBox<String> cmbArea;
    private javax.swing.JComboBox<String> cmbManager;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JList<String> lstEmpleados;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCi;
    private javax.swing.JTextArea txtCurriculum;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JSpinner txtSalarioMensual;
    // End of variables declaration//GEN-END:variables

    public void cargarListaCombo() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        ArrayList<Empleado> empleados = s.getEmpleadosPorSalario();
        for (Empleado e : empleados) {
            modelo.addElement(e.toString());
        }
        lstEmpleados.setModel(modelo);
        cmbArea.removeAllItems();
        for (Area a : s.getAreas()) {
            cmbArea.addItem(a.getNombre());
        }
        cmbManager.removeAllItems();
        for (Manager m : s.getManagers()) {
            cmbManager.addItem(m.getCi());
        }
    }

    private void guardarCurriculum(String ci, String contenidoCV) {
        File carpeta = new File("cvs");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        String ruta = "cvs/CV" + ci + ".txt";
        ArchivoGrabacion arch = new ArchivoGrabacion(ruta);
        arch.grabarLinea(contenidoCV);
        arch.cerrar();
    }

}
