package tpws;

import javax.xml.ws.Endpoint;
import service.BanqueService;

public class TPWS {

    public static void main(String[] args) {
        // TODO code application logic here
        String url = "http://localhost:8585/";
        Endpoint.publish(url, new BanqueService());
        System.out.println(url);
    }
    
}
