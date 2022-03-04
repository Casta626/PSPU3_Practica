package Practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	private final static String COD_TEXTO = "UTF-8";

	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("Ha ocurrido un error");
			System.exit(1);
		}
		String n_host = args[0];
		int n_puerto = Integer.parseInt(args[1]);
		try {

			Socket socketComunicacion = new Socket(n_host, n_puerto);
			System.out.println("Se ha establecido conexión con el servidor.");
			try {

				OutputStream os_serv = socketComunicacion.getOutputStream();
				InputStream is_servidor = socketComunicacion.getInputStream();
				
				OutputStreamWriter osw_serv = new OutputStreamWriter(os_serv, COD_TEXTO);
				InputStreamReader isr_serv = new InputStreamReader(is_servidor, COD_TEXTO);
				
				BufferedWriter bw_serv = new BufferedWriter(osw_serv);
				BufferedReader br_serv = new BufferedReader(isr_serv);

				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				
				String linea = "" ;
				
				System.out.println("Escribe el comando");
				System.out.print("Comando>");
				
				while (!(linea = br.readLine()).equalsIgnoreCase("exit")) {
					
					bw_serv.write(linea);
					bw_serv.newLine();
					bw_serv.flush();
					
					String line = br_serv.readLine();

					System.out.println("respuesta: " + line);
					
					do{
						System.out.println(linea);
						line = br_serv.readLine();
						
					}while(!(line.equals("MacarronesConTomatico")));
				
					System.out.print("Comando>");

				}
							
						} catch(IOException e) {
							System.err.println("Error");
							e.printStackTrace();
							System.exit(1);
						}
						
					} catch (UnknownHostException e) {
						System.err.println("Host desconocido: " + n_host);
						System.exit(1);
						
					} catch (IOException ex) {
						System.err.println("Error de E/S");
						ex.printStackTrace();
						System.exit(1);
					}
	}
}