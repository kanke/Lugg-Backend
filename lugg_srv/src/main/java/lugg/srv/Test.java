package lugg.srv;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;

import com.simplify.payments.PaymentsApi;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.Authorization;
import com.simplify.payments.domain.Payment;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Lugg lugg = new Lugg();
//    	lugg.setPickup("Heathrow%20Airport,%20Heathrow%20Airport");
//    	lugg.setDropoff("Old%20Street,%20City%20of%20London");
//    	planTFLJourney(lugg);
		
		test_auth();
		 
	}
	
	private static void test_auth(){
		PaymentsApi.PUBLIC_KEY = "sbpb_ZjZlYzk2NzktMzhmZS00ZGVmLWExODktNTZjYTRjMjA1ZjBi";
		PaymentsApi.PRIVATE_KEY = "hA40Vaebshz63I5Dhbtz2kmOH0XxONVgx/RRVp4ljZh5YFFQL0ODSXAOkNtXTToq";
		 
		Authorization authorization;
		try {
			authorization = Authorization.create(new PaymentsMap()
			        .set("amount", "2500")
			        .set("card.cvc", "123")
			        .set("card.expMonth", 11)
			        .set("card.expYear", 19)
			        .set("card.number", "5555555555554444")
			        .set("currency", "USD")
			        .set("description", "test authorization")
			        .set("reference", "KP-76TBONES")
			);
			System.out.println(authorization);
			//
			System.out.println(" auth code : "+authorization.get("id"));
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	private void testpayment(){
		PaymentsApi.PUBLIC_KEY = "sbpb_ZjZlYzk2NzktMzhmZS00ZGVmLWExODktNTZjYTRjMjA1ZjBi";
		PaymentsApi.PRIVATE_KEY = "hA40Vaebshz63I5Dhbtz2kmOH0XxONVgx/RRVp4ljZh5YFFQL0ODSXAOkNtXTToq";
		 
		Authorization authorization;
		try {
			authorization = Authorization.create(new PaymentsMap()
			        .set("amount", "2500")
			        .set("card.cvc", "123")
			        .set("card.expMonth", 11)
			        .set("card.expYear", 19)
			        .set("card.number", "5555555555554444")
			        .set("currency", "USD")
			        .set("description", "test authorization")
			        .set("reference", "KP-76TBONES")
			);
			System.out.println(authorization);
			//
			System.out.println(" auth code : "+authorization.get("id"));
			
			
			//
			//
			Payment payment = Payment.create(new PaymentsMap()
			.set("currency", "USD")
			.set("authorization", authorization.get("id")) // returned by JavaScript call
			.set("amount", "2500") // In cents e.g. $10.00
			.set("description", "lugg description"));
			//
			if ("APPROVED".equals(payment.get("paymentStatus"))) {
		        System.out.println("Payment approved");
		    }else{
		    	System.out.println("Payment not approved");
		    }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static final String TFLAPICredentials = "&app_id=21453e90&app_key=dce0956f81edf2de0d370161e12bb007";
	private static final String reqOptions = "nationalSearch=False"
			+ "&timeIs=Departing"
			+ "&journeyPreference=LeastTime"
			+ "&walkingSpeed=Average"
			+ "&cyclePreference=None"
			+ "&alternativeCycle=False"
			+ "&alternativeWalking=True"
			+ "&applyHtmlMarkup=False"
			+ "&useMultiModalCall=False"
			+ "&walkingOptimization=False";
	private static final String TFL_EP = "https://api.tfl.gov.uk/Journey/JourneyResults/";
	
	static private void planTFLJourney(Lugg lugg){
    	try {
			String from = lugg.getPickup();
			String to = lugg.getDropoff();
			String tfl_journey_planner_req = TFL_EP+from+"/to/"+to+"?"+reqOptions+TFLAPICredentials;
			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet(tfl_journey_planner_req);
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = br.readLine()) != null) {
				    result.append(line);
				}

				JsonParser jsp = JsonParserFactory.getJsonParser();
				Map<String,Object> resp = jsp.parseMap(result.toString());
				List<Map> journeys = (java.util.ArrayList<Map>)resp.get("journeys");
				/*List<Map> tmp = new ArrayList<Map>();
				tmp.addAll(journeys);
				Collections.sort(tmp, TFL_journey_comp);*/
				Map qj = journeys.get(0);
				List<Map> steps = (List<Map>)qj.get("legs");
				List<Map<String,String>> stp = new ArrayList<Map<String,String>>();
				for(Map step : steps){
					Map<String,String> t = new HashMap<String, String>();
					t.put("summary", (String)((Map)step.get("instruction")).get("summary"));
					t.put("departure_point", (String)((Map)step.get("departurePoint")).get("commonName"));
					t.put("arrival_point", (String)((Map)step.get("arrivalPoint")).get("commonName"));
					t.put("mode", (String)((Map)step.get("mode")).get("name"));
					stp.add(t);
				}
				//
				lugg.setTfl_journey(stp);
				lugg.setTfl_journey_duration((Integer)qj.get("duration"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
			    response.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }


}
