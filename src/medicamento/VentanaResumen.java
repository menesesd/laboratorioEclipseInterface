package medicamento;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import valuebObject.OrdenPedido;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaResumen extends JFrame {

	private JPanel contentPane;
	private static OrdenPedido ordenPedido;

	/**
	 * Create the frame.
	 */
	public VentanaResumen() {

		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		this.ordenPedido = ventanaPrincipal.ordenPedido;

		setTitle("Pedido al Destribuidor " + this.ordenPedido.getDistribuidor());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 487, 267);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblResumenPedido = new JLabel("Resumen del Pedido");
		lblResumenPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenPedido.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblResumenPedido.setBounds(38, 25, 367, 25);
		contentPane.add(lblResumenPedido);

		JLabel lblDescripcionPedido = new JLabel("");
		lblDescripcionPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescripcionPedido.setBounds(48, 61, 351, 30);
		contentPane.add(lblDescripcionPedido);

		JLabel lblDireccionFarmacia = new JLabel("New label");
		lblDireccionFarmacia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDireccionFarmacia.setBounds(48, 102, 357, 44);
		contentPane.add(lblDireccionFarmacia);

		JButton btnEnviarPedido = new JButton("Enviar Pedido");
		btnEnviarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				System.out.println("Pedido Enviado");
				setVisible(false);
			}
		});
		btnEnviarPedido.setBounds(157, 185, 139, 23);
		contentPane.add(btnEnviarPedido);

		JButton btnCancelarPedido = new JButton("Cancelar");
		btnCancelarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setVisible(false);
			}
		});
		btnCancelarPedido.setBounds(322, 185, 139, 23);
		contentPane.add(btnCancelarPedido);

		if (this.ordenPedido != null) {
			String descripcionPedido = "<html>";
			descripcionPedido += this.ordenPedido.getCantidad() + " unidades del " + this.ordenPedido.getTipo() + " "
					+ this.ordenPedido.getNombre();
			descripcionPedido += "</html>";
			lblDescripcionPedido.setText(descripcionPedido);

			String direccionFarmacia = "<html>";
			if (this.ordenPedido.isSucursalPrincipal()) {
				direccionFarmacia += "Para la farmacia Pricipal situada en la dirección  Calle de la Rosa n. 28 ";
				if (this.ordenPedido.isSucursalSecundaria()) {
					direccionFarmacia += " y para la farmacia Secundaria situada en la dirección Calle Alcazabilla n. 3.";
				}
			} else if (this.ordenPedido.isSucursalSecundaria()) {
				direccionFarmacia += " Para la farmacia Secundaria situada en la dirección Calle Alcazabilla n. 3.";
			}
			direccionFarmacia += "</html>";

			lblDireccionFarmacia.setText(direccionFarmacia);
		}

	}
}
