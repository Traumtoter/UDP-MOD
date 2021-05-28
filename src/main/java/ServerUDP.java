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
public class ServerUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        try
        {
            System.out.println("\n*****Arrancando el servidor*****\n");
            DatagramSocket Coneccion = new DatagramSocket(5000);
            String Cerrar = new String("cierre");
            
            while(true)
            {
                byte[]Cadenarecivida = new byte[1024];
                byte[] CadenaEnviar  = new  byte[1024];
                DatagramPacket PacketRecivido = new DatagramPacket(Cadenarecivida,Cadenarecivida.length);
                Coneccion.receive(PacketRecivido);
                
                String CadenaServer = new String(PacketRecivido.getData());
                //CadenaEnviar = CadenaServer.getBytes();
                
               InetAddress Ip = PacketRecivido.getAddress();
               int puerto = PacketRecivido.getPort();
               System.out.println("Enviando a: " + PacketRecivido.getAddress());
               
               
               String Senviada = new String(CadenaServer);
               System.out.println("Mensaje recibido: "+CadenaServer);
               
               // Senviada += Senviada;
               CadenaServer = CadenaServer.concat(Senviada);
                CadenaEnviar = CadenaServer.getBytes();
                
                DatagramPacket enviarpaqket = new DatagramPacket(CadenaEnviar,CadenaEnviar.length,
               Ip,puerto);
                Coneccion.send(enviarpaqket);  

              // Coneccion.send(enviarpaqket);
               
               
              
               if(CadenaServer.indexOf("cierre") <0 )
               {
                   System.out.println("Enviando... " +new String(enviarpaqket.getData()));
                   
                }
               else
               {
                   
                   System.out.println("Se detecto cadena vacia para cerrar la conection");
                    Coneccion.close();
                    System.out.println("El servidor a cerrado secion\n");
                    break;
               }
               
              // System.out.println("Hola server Abajo");
            }
        }
        catch(Exception e)
        {
            System.out.println("Ocurrio un error: "+e.getMessage());
        }
        System.out.println("Adios clientete");
    }
    
}
