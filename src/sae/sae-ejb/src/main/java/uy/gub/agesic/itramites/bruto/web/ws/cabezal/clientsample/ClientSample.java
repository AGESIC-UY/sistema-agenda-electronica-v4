package uy.gub.agesic.itramites.bruto.web.ws.cabezal.clientsample;

import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CabezalService;
import uy.gub.agesic.itramites.bruto.web.ws.cabezal.CapturaCabezalSOAP;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        CabezalService service1 = new CabezalService();
	        System.out.println("Create Web Service...");
	        CapturaCabezalSOAP port1 = service1.getCapturaCabezalSOAPPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.persist(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.ping());
	        System.out.println("Create Web Service...");
	        CapturaCabezalSOAP port2 = service1.getCapturaCabezalSOAPPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.persist(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.ping());
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
