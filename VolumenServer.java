import VolumenApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA.*;


import java.util.Properties;

class VolumenImpl extends VolumenPOA {
     private ORB orb;

	public void setORB(ORB orb_val){
	orb =orb_val;
	}

//implement volumen() method
	public double volumen(double radio)
	{
	   double vol=0;

           double resPar = 4*3.14159*(radio*radio*radio);

           vol = resPar/3;

	   return vol;
	}

//implement shutdown() method
	public void shutdown(){
	orb.shutdown(false);
	}
}

public class VolumenServer {
    
    public static void main(String args[]) {
        try{
            // create and initialize
            ORB orb = ORB.init(args, null);
            
//
POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
rootpoa.the_POAManager().activate();

//

VolumenImpl volumenImpl = new VolumenImpl();
volumenImpl.setORB(orb);

org.omg.CORBA.Object ref = rootpoa.servant_to_reference(volumenImpl);
Volumen href = VolumenHelper.narrow(ref);

org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

String name = "Volumen";
NameComponent path[] = ncRef.to_name(name);
ncRef.rebind(path,href);

System.out.println("VolumenServer ready and waiting ...");

//wait for invocations from clients
orb.run();
}

catch (Exception e){
System.err.println("ERROR:"+e);
e.printStackTrace(System.out);
}
System.out.println("VolumenServer Exiting...");

}
}
