/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author suneater
 */
import java.net.*;
import java.io.*;
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("\tArrancando la aplicacion Cliente\n");
        
        BufferedReader EntradaUser = new BufferedReader(new InputStreamReader(System.in));
        String NameServer ="localhost";
        
        String Message;
        try
        {
            InetAddress IpAd = InetAddress.getByName(NameServer);
            DatagramSocket ConeccionCliente = new DatagramSocket();
            String cierre = new String("cierre");
                
            while(true)
            {
                byte[]DatosEnviar = new byte[1024];
                byte[]DataRecive = new byte[1024];

                System.out.println("Ingresa el mensage para el servidor");
                Message = EntradaUser.readLine();

                if(Message.equals(""))Message = "cierre";
                
                System.out.println("Cadena a enviar: " + Message);
                DatosEnviar = Message.getBytes();
                DatagramPacket EnviarPakete = new DatagramPacket(DatosEnviar,DatosEnviar.length,IpAd,5000);

                ConeccionCliente.send(EnviarPakete);

                DatagramPacket RecivirPakete = new DatagramPacket(DataRecive,DataRecive.length);
                ConeccionCliente.receive(RecivirPakete);

                String Respuesta = new String(RecivirPakete.getData());
                
                
                if(Respuesta.indexOf(cierre)<0)
                {
                    
                    System.out.println("Respuesta del servidor: "+Respuesta + "\n");
                }
                else
                {
                    
                    
                    System.out.println("Respuesta del servidor: "+Respuesta);
                    
                    System.out.println("Cerrando la conccecion\n");
                    ConeccionCliente.close();
                    break;
               
                }
                
                
               
            }
            
            
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error al tratar de conectar: "+e.getMessage() + "\n");
        }
        
        
        
        System.out.println("Adios ....");
    }
    
}
