import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import javax.ws.rs.core.MediaType;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;

public class AldiClient {
	private Client client = null;
	
	public static void main(String[] args) throws IOException {
		int i = 0;
		// Client for insert commodity information to Mobile App database
		//AldiClient offer = new AldiClient();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //System.out.print("Choose");
        //String s = br.readLine();
        System.out.print("Choose:");
        try{
            i = Integer.parseInt(br.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
		
		switch(i) {
		case 1: addOffer("{\"id\":101,\"Name\":\"test\",\"commodityBrand\":\"SAMSUNG\",\"commodityModel\":\"GalaxyS9\",\"commodityPrice\":\"10000000\",\"InstallmentAmount\":\"\",\"installmentDuration\":\"\",\"downPayment\":\"\",\"adminFee\":\"\",\"interestRate\":\"\",\"installmentFee\":\"\",\"cashPayment\":\"\"}");
		break;
		case 2: getOffer("101");
		break;
		default: System.out.print("end");
		}
		
		
	}
	
	public void proxySetup() {
		ClientBuilder clientBuilder = ClientBuilder.newBuilder();
		ClientConfig config = new ClientConfig();
		config.property(ClientProperties.PROXY_URI, "192.168.1.254:8080");  
		Client client = ClientBuilder.newClient();
		this.client = clientBuilder.withConfig(config).build();
	
	}
	
	public static void addOffer(String payloadstr) {
		Client client = ClientBuilder.newClient();		
				
		Entity payload = Entity.json("{\"id\":101,\"Name\":\"test\",\"commodityBrand\":\"SAMSUNG\",\"commodityModel\":\"GalaxyS9\",\"commodityPrice\":\"10000000\",\"InstallmentAmount\":\"\",\"installmentDuration\":\"\",\"downPayment\":\"\",\"adminFee\":\"\",\"interestRate\":\"\",\"installmentFee\":\"\",\"cashPayment\":\"\"}");
		Response response = client.target("http://private-d2207-aldi.apiary-mock.com/offer")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .post(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}
	
	public static void addOffers() {
		Client client = ClientBuilder.newClient();
		Entity payload = Entity.json("{  'id': 0,  'Name': 'test',  'commodityBrand': 'SAMSUNG',  'commodityModel': 'Galaxy S9',  'commodityPrice': '10000000',  'InstallmentAmount': '',  'installmentDuration': '',  'downPayment': '',  'adminFee': '',  'interestRate': '',  'installmentFee': '',  'cashPayment': ''}");
		Response response = client.target("https://private-d2207-aldi.apiary-mock.com/offer")
		  .request(MediaType.APPLICATION_JSON_TYPE)
		  .put(payload);

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
	}
	
	/**
	 * Triggers the getOffer API that will return the offer details based on the supplied id 
	 * This method always returns immediately, and will stall if an auto proxy configuration script is used
	 *
	 * @param  id   an id of the offer
	 * @return return the result of the offer search
	 * @see         addOffers
	 */
	public static int getOffer(String id) {
				
		Client client = ClientBuilder.newClient();
		Response response = client.target("https://private-d2207-aldi.apiary-mock.com/offer/?id="+id)
		  .request(MediaType.TEXT_PLAIN_TYPE)
		  .get();

		System.out.println("status: " + response.getStatus());
		System.out.println("headers: " + response.getHeaders());
		System.out.println("body:" + response.readEntity(String.class));
		return 0;
	}

}
