package packAme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class FrmCliente extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnEnviar;
	private JTextField txtMensaje;

	public FrmCliente() {
		txtMensaje = new JTextField();
		txtMensaje.setBounds(10, 10, 200, 20);
		add(txtMensaje);
		
		btnEnviar = new JButton();
		btnEnviar.setText("Enviar");
		btnEnviar.setBounds(10, 40, 150, 20);
		btnEnviar.addActionListener(this);
		add(btnEnviar);
		
		setLayout(null);
		setSize(400,400);
		setVisible(true);		
	}

	public static void main(String[] args) {
		new FrmCliente();
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if (e.getSource() == btnEnviar) {
			try {
				Socket cliente = new Socket("127.0.0.1", 9090);
				DataOutputStream flujo = new DataOutputStream(cliente.getOutputStream());
				flujo.writeUTF(txtMensaje.getText());
				cliente.close();
				
			} catch (Exception ex) {
				System.out.println("Error en el Socket " + ex.getMessage());
			}
		}
	}

}
