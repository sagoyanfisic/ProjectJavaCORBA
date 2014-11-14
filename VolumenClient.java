import VolumenApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

import javax.swing.JOptionPane;

public class VolumenClient
{
    static Volumen volumenImpl;
    
public static void main(String args[])
{
    try
    {
    
            //
            ORB orb = ORB.init(args, null);
    
    //get
    org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
    //
    //
    NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
    
    //
    String name = "Volumen";
    volumenImpl = VolumenHelper.narrow(ncRef.resolve_str(name));
    
    System.out.println("Obtener el volumen de una esfera con un radio dado: ");// + helloImpl);

    String aux="";
    double num = 0;

    while( true ) 
    {
       aux = JOptionPane.showInputDialog("Ingresa el radio de la esfera:");
            	
       num = Double.parseDouble(aux);

       JOptionPane.showMessageDialog(null,"Volumen de la esfera: \n "+volumenImpl.volumen(num));

       //System.out.println(luisImpl.Gobernadores(num));
    }

    
    //luisImpl.shutdown();
    
} catch (Exception e) {
    System.out.println("ERROR : " + e);
    e.printStackTrace(System.out);
}
}
}