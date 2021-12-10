package gui.alumnos;

import datos.conexion.Conexion;
import negocio.alumnos.AlumnosNegocio;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import recursos.clases.Alumnos;
import recursos.clases.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class frmAlumnos {
    private JPanel jpaPrincipal;
    private JPanel jpaTitulo;
    private JLabel lblAlumnos;
    private JPanel jpaContain;
    private JTextField txtDni;
    private JTextField txtNumeroCuenta;
    private JLabel lblNumeroCuenta;
    private JLabel lblDni;
    private JTextField txtNombre;
    private JTextField txtFechaNacimiento;
    private JLabel lblNombre;
    private JLabel lblFechaNacimineto;
    private JPanel jpaBotones;
    private JButton btnRegistar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JLabel lblCombo;
    private JComboBox cboAlumno;
    private JButton btnListar;
    private JButton btnLeerCombo;
    private JPanel jpaDatos;
    private JScrollPane sclPaneDatos;
    private JTable tblDatos;
    private JButton btnLimpiar;
    private JButton btnGenerarReporte;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    DefaultTableModel modelo;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Alumnos");
        frame.setContentPane(new frmAlumnos().jpaPrincipal);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public frmAlumnos() {
        Iniciar();
        btnRegistar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Alumnos alumno = new Alumnos();
                    alumno.setNumeroCuenta(Long.parseLong(txtNumeroCuenta.getText()));
                    alumno.setDNI(Long.parseLong(txtDni.getText()));
                    alumno.setNombre(txtNombre.getText());
                    alumno.setFechaNacimiento(ConvertirFormatoTextoFecha(txtFechaNacimiento.getText()));
                    String respuesta =  new AlumnosNegocio().Insertar(alumno);
                    if (!respuesta.contains("Error")){
                        JOptionPane.showMessageDialog(null,"Guardado","Exito",JOptionPane.INFORMATION_MESSAGE);
                        leerDatos();
                        llenarComboAlumnos();
                    }else
                        throw new Exception(respuesta);

                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Error morir",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //con las teclas
        txtFechaNacimiento.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!((c>= '0') && (c<='9') || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE) || (c == KeyEvent.VK_SLASH))){
                    JOptionPane.showMessageDialog(null,"Porfavor Ingrese una fecha correcta");
                    e.consume();
                }
            }
        });
        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                leerDatos();
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Alumnos alumno = new Alumnos();
                    alumno.setNumeroCuenta(Long.parseLong(txtNumeroCuenta.getText()));
                    alumno.setDNI(Long.parseLong(txtDni.getText()));
                    alumno.setNombre(txtNombre.getText());
                    alumno.setFechaNacimiento(ConvertirFormatoTextoFecha(txtFechaNacimiento.getText()));
                    new AlumnosNegocio().Actualizar(alumno);
                    leerDatos();
                    llenarComboAlumnos();
                }catch (Exception ex){
                }
            }
        });
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Alumnos alumnoBuscar = new Alumnos();
                    alumnoBuscar.setNombre(txtNombre.getText());
                    List<Alumnos> listaAlumnos = new AlumnosNegocio().Buscar(alumnoBuscar);
                    //limpiamos la fila
                    modelo.setRowCount(0);
                    for (Alumnos alumno: listaAlumnos) {
                        Object [] registroLeido = {
                                alumno.getNumeroCuenta(),
                                alumno.getDNI(),
                                alumno.getNombre(),
                                sdf.format(alumno.getFechaNacimiento())
                        };
                        modelo.addRow(registroLeido);
                    }
                    tblDatos.setModel(modelo);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int confirmar = JOptionPane.showConfirmDialog(null,"Esta seguro que desea eliminar el registro?",
                            "Eliminar Precaucion",JOptionPane.WARNING_MESSAGE);
                    if (confirmar == JOptionPane.YES_OPTION){
                        Alumnos alumno = new Alumnos();
                        alumno.setNumeroCuenta(Long.parseLong(txtNumeroCuenta.getText()));
                        alumno.setDNI(Long.parseLong(txtDni.getText()));
                        alumno.setNombre(txtNombre.getText());
                        alumno.setFechaNacimiento(ConvertirFormatoTextoFecha(txtFechaNacimiento.getText()));
                        new AlumnosNegocio().Eliminar(alumno);
                        leerDatos();
                    }
                }catch (Exception ex){
                }
            }
        });
        tblDatos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSelecionada = tblDatos.getSelectedRow();
                //ver qui si se puede con tbl Datos
                txtNumeroCuenta.setText(modelo.getValueAt(filaSelecionada,0).toString());
                txtDni.setText(modelo.getValueAt(filaSelecionada,1).toString());
                txtNombre.setText(modelo.getValueAt(filaSelecionada,2).toString());
                txtFechaNacimiento.setText(modelo.getValueAt(filaSelecionada,3).toString());
            }
        });
        btnLeerCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object objeto = cboAlumno.getSelectedItem();
                Long itemAlumno = ((Item)objeto).getID();
                JOptionPane.showMessageDialog(null,itemAlumno);
            }
        });
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNumeroCuenta.setText("");
                txtDni.setText("");
                txtNombre.setText("");
                txtFechaNacimiento.setText("");
            }
        });
        btnGenerarReporte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection cn = Conexion.obtenerConexion();

                    JasperReport reporte = null;
                    String path = "C:\\Users\\Usuario\\JaspersoftWorkspace\\MyReports";
                    reporte = (JasperReport) JRLoader.loadObjectFromFile(path);

                    JasperPrint jPrint = JasperFillManager.fillReport(reporte,null,cn);

                    JasperViewer view = new JasperViewer(jPrint, false);
                    view.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    view.setVisible(true);
                } catch (JRException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
    private void Iniciar() {
        modelo = (DefaultTableModel) tblDatos.getModel();  //casteo asignamos el modelo de nuesra tabla
        modelo.addColumn("Numero de cuenta");//anadimos columnas
        modelo.addColumn("DNI");
        modelo.addColumn("Nombre del Alumno");
        modelo.addColumn("Fecha de Nacimiento");
        //para anadir filas las leemos de la capa de negocio
        leerDatos();
        llenarComboAlumnos();
    }

    private void llenarComboAlumnos() {
        List<Alumnos> listaAlumnos = new AlumnosNegocio().leer();
        DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
        for (Alumnos alumnos:listaAlumnos) {
            Item item = new Item(alumnos.getNumeroCuenta(), alumnos.getNombre());
            modeloCombo.addElement(item);
        }
        cboAlumno.setModel(modeloCombo);
    }

    private void leerDatos() {
        try{
            List<Alumnos> listaAlumnos =  new AlumnosNegocio().leer();
            //limpiamos las fila
            modelo.setRowCount(0);
            for (Alumnos alumno: listaAlumnos) {
                Object [] registroLeido = {
                        alumno.getNumeroCuenta(),
                        alumno.getDNI(),
                        alumno.getNombre(),
                        sdf.format(alumno.getFechaNacimiento())
                };
                modelo.addRow(registroLeido);
            }
            tblDatos.setModel(modelo);
        }catch (Exception e){
        }
    }
    private Date ConvertirFormatoTextoFecha(String TextoFecha){
        Date fecha = null;
        try{
            fecha = sdf.parse(TextoFecha);
        }catch (ParseException pe){
            JOptionPane.showMessageDialog(null,pe.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        }
        return fecha;
    }

}
