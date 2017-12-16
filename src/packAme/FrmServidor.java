package packAme;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class FrmServidor extends JFrame implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea txtMensajes;

	public FrmServidor() {
		this.txtMensajes = new JTextArea();
		this.txtMensajes.setBounds(10, 10, 500, 500);
		this.add(txtMensajes);

		this.setLayout(null);
		this.setSize(400, 400);
		this.setVisible(true);
		
		Thread hilo = new Thread(this);
		hilo.start();
	}

	public static void main(String[] args) {
		new FrmServidor();
	}

	@Override
	public void run() {
		try {
			ServerSocket servidor = new ServerSocket(9090);
			Socket cliente;

			while (true) {
				cliente = servidor.accept();
				DataInputStream flujo = new DataInputStream(cliente.getInputStream());
				String mensaje = flujo.readUTF();

				txtMensajes.append("\n" + cliente.getInetAddress() + ": " + mensaje);
				cliente.close();
				
				if (mensaje.toUpperCase().compareTo("FIN") == 0)
					servidor.close();
			}

		} catch (Exception ex) {

		}

	}

}
