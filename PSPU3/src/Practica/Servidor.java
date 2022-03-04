package Practica;

import java.net.ServerSocket; 
import java.net.Socket; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; import 
java.io.OutputStreamWriter; 
import java.io.BufferedReader; 
import java.io.BufferedWriter;

public class Servidor { 
	private final static String COD_TEXTO ="UTF-8"; 
	
	public static void main(String[] args) { 
		if (args.length < 1) { 
			System.err.println("Se ha producido un error"); 
			System.exit(1); 
		} 
		int n_puerto = Integer.parseInt(args[0]); 
		
		try { 
			ServerSocket server_socket = new ServerSocket(n_puerto);
			System.out.printf("Se ha creado el socket de servidor en puerto %d, esperando clientes...\n", n_puerto); 
			String l_cliente=""; 

			while (true) { 
				try {
					Socket socket = server_socket.accept();
					System.out.printf("Cliente conectado desde %s:%d.\n", socket.getInetAddress().getHostAddress(), socket.getPort());
					
					try { 
						InputStream is_cliente = socket.getInputStream(); 
						OutputStream os_cliente = socket.getOutputStream();
						
						InputStreamReader isr_cliente = new InputStreamReader(is_cliente, COD_TEXTO); 
						BufferedReader br_cliente = new BufferedReader(isr_cliente);
						
						OutputStreamWriter osw_cliente = new OutputStreamWriter(os_cliente, COD_TEXTO); 
						BufferedWriter bw_cliente = new BufferedWriter(osw_cliente);
						
						while ((l_cliente = br_cliente.readLine()) != null && l_cliente.length() > 0) { 							
							
						    String ist = imprimirResultado(l_cliente);
							
						    bw_cliente.write(ist); 
						    bw_cliente.newLine(); 
						    bw_cliente.flush(); 
						} 
							} catch (IOException ex) {
								
							} 
						} catch (IOException e) {
							
						}
						System.out.println("El cliente se ha desconectado."); 
					} 
				} catch (IOException ex) { 
					System.out.println("Error de E/S");
					ex.printStackTrace(); 
					System.exit(1); 
				} 
	}
	public static String imprimirResultado(String l) {
        String respuesta_recibida = "";
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/C", l);
        pb.redirectErrorStream(true);
        Process proceso;
        try {
        	proceso = pb.start();
            InputStream is = proceso.getInputStream();
            int cad;

            try {
            	cad = is.read();

                while (cad != -1) {
                	respuesta_recibida += (char) cad;
                	cad = is.read();
                }
                respuesta_recibida+="\nMacarronesConTomatico";
                is.close();
                proceso.destroy();
            } catch (IOException e) {

			            }
			        } catch (IOException e1) {
			            e1.printStackTrace();
			            System.out.println("Ha ocurrido un error");
			        }

        			return respuesta_recibida;

    }
}