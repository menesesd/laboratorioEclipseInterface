package medicamento;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import valuebObject.OrdenPedido;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField txt_nombreMedicamento;
	private JTextField txt_cantidad;
	private JComboBox cmb_tipo;
	private ButtonGroup radioBoton = new ButtonGroup();
	private JCheckBox ckPrincipal;
	private JCheckBox ckSecundaria;
	public static OrdenPedido ordenPedido;

	/**
	 * * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 228);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre Medicamento");
		lblNewLabel.setBounds(10, 11, 126, 14);
		contentPane.add(lblNewLabel);

		txt_nombreMedicamento = new JTextField();
		txt_nombreMedicamento.setBounds(155, 8, 140, 20);
		contentPane.add(txt_nombreMedicamento);
		txt_nombreMedicamento.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Tipo Medicamento");
		lblNewLabel_1.setBounds(334, 11, 117, 14);
		contentPane.add(lblNewLabel_1);

		cmb_tipo = new JComboBox();
		cmb_tipo.setModel(new DefaultComboBoxModel(new String[] { "Analg\u00E9sico", "Anal\u00E9ptico",
				"Anest\u00E9sico", "Anti\u00E1cido", "Antidepresivo ", "Antibi\u00F3ticos" }));
		cmb_tipo.setBounds(460, 8, 140, 20);
		contentPane.add(cmb_tipo);

		JLabel lblNewLabel_2 = new JLabel("Cantidad");
		lblNewLabel_2.setBounds(10, 39, 103, 14);
		contentPane.add(lblNewLabel_2);

		txt_cantidad = new JTextField();
		txt_cantidad.setBounds(155, 36, 140, 20);
		contentPane.add(txt_cantidad);
		txt_cantidad.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Distribuidor");
		lblNewLabel_3.setBounds(431, 36, 103, 19);
		contentPane.add(lblNewLabel_3);

		JRadioButton rdCofarma = new JRadioButton("Cofarma");
		rdCofarma.setBounds(354, 50, 97, 23);
		contentPane.add(rdCofarma);

		JRadioButton rdEmpsephar = new JRadioButton("Empsephar ");
		rdEmpsephar.setBounds(354, 75, 100, 23);
		contentPane.add(rdEmpsephar);

		JRadioButton rdComefar = new JRadioButton("Cemefar");
		rdComefar.setBounds(354, 100, 81, 23);
		contentPane.add(rdComefar);

		radioBoton.add(rdCofarma);
		radioBoton.add(rdEmpsephar);
		radioBoton.add(rdComefar);

		JLabel lblNewLabel_4 = new JLabel("Sucursal");
		lblNewLabel_4.setBounds(10, 72, 65, 14);
		contentPane.add(lblNewLabel_4);

		ckPrincipal = new JCheckBox("Principal ");
		ckPrincipal.setBounds(140, 68, 103, 23);
		contentPane.add(ckPrincipal);

		ckSecundaria = new JCheckBox("Secundaria");
		ckSecundaria.setBounds(140, 93, 103, 23);
		contentPane.add(ckSecundaria);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ordenPedido = new OrdenPedido();
				String distribuidor = "";
				if (rdCofarma.isSelected()) {
					distribuidor = rdCofarma.getText();
				} else if (rdComefar.isSelected()) {
					distribuidor = rdComefar.getText();
				} else if (rdEmpsephar.isSelected()) {
					distribuidor = rdEmpsephar.getText();
				}

				String resultado;
				resultado = ordenPedido.validar(txt_nombreMedicamento.getText(),
						String.valueOf(cmb_tipo.getSelectedItem()), txt_cantidad.getText(), distribuidor,
						ckPrincipal.isSelected(), ckSecundaria.isSelected());

				if (resultado.equals("success")) {

					ordenPedido.setNombre(txt_nombreMedicamento.getText());
					ordenPedido.setCantidad(Integer.valueOf(txt_cantidad.getText()));
					ordenPedido.setDistribuidor(distribuidor);
					ordenPedido.setSucursalPrincipal(ckPrincipal.isSelected());
					ordenPedido.setSucursalSecundaria(ckSecundaria.isSelected());
					ordenPedido.setTipo(String.valueOf(cmb_tipo.getSelectedItem()));
					limpiarControles();
					VentanaResumen ventanaResumen = new VentanaResumen();
					ventanaResumen.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, resultado);
				}

			}
		});
		btnNewButton.setBounds(334, 155, 89, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpiarControles();
			}
		});
		btnNewButton_1.setBounds(477, 155, 89, 23);
		contentPane.add(btnNewButton_1);
	}

	public void limpiarControles() {
		txt_nombreMedicamento.setText("");
		txt_cantidad.setText("");
		cmb_tipo.setSelectedIndex(0);
		radioBoton.clearSelection();
		ckPrincipal.setSelected(false);
		ckSecundaria.setSelected(false);
	}

}
