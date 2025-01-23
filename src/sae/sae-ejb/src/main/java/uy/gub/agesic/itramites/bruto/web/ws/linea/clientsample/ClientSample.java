package uy.gub.agesic.itramites.bruto.web.ws.linea.clientsample;

import uy.gub.agesic.itramites.bruto.web.ws.linea.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        LineaService service1 = new LineaService();
	        System.out.println("Create Web Service...");
	        CapturaLineaSOAP port1 = service1.getCapturaLineaSOAPPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.ping());
	        System.out.println("Server said: " + port1.persist(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Create Web Service...");
	        CapturaLineaSOAP port2 = service1.getCapturaLineaSOAPPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.ping());
	        System.out.println("Server said: " + port2.persist(null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
