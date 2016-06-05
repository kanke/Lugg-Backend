package lugg.srv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.simplify.payments.PaymentsApi;
import com.simplify.payments.PaymentsMap;
import com.simplify.payments.domain.Authorization;
import com.simplify.payments.domain.Payment;

@RestController
@SuppressWarnings({"unchecked","rawtypes"})
public class LuggEndPoint {
	
	private static final Map<String, Lugg> LuggStore = new HashMap<String, Lugg>();
	private static final Map<String, Customer> customerStore = new HashMap<String, Customer>();
	
	static{
		
		PaymentsApi.PUBLIC_KEY = "sbpb_ZjZlYzk2NzktMzhmZS00ZGVmLWExODktNTZjYTRjMjA1ZjBi";
		PaymentsApi.PRIVATE_KEY = "hA40Vaebshz63I5Dhbtz2kmOH0XxONVgx/RRVp4ljZh5YFFQL0ODSXAOkNtXTToq";
		
	}
	
	// TFL api
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
    
	@RequestMapping(value = "/lugg/register", method = RequestMethod.POST)
    public Customer register(@RequestBody Customer cust) {
        System.out.println("registering customer " + cust.getId());
        customerStore.put(cust.getId(), cust);
        return cust;   
    }
	
    @RequestMapping(value = "/lugg/dropoff", method = RequestMethod.POST)
    public Lugg dropoff(@RequestBody Lugg lugg) {
        System.out.println("registering lugg " + lugg.getId());
        lugg = LuggStore.get(lugg.getId());
        planTFLJourney(lugg);
        return lugg;   
    }
    
    @RequestMapping(value = "/lugg/received", method = RequestMethod.POST)
    public Lugg received(@RequestParam(value="id") String id) {
        System.out.println("receiveing lugg " + id);
        Lugg lugg = LuggStore.get(id);
        makePayment(lugg);
        return lugg;   
    }
    
    // order authorisation
    
    @RequestMapping(value = "/lugg/orderAuthorisation", method = RequestMethod.POST)
    public Lugg orderAuthorisation(@RequestBody Lugg lugg) {
        System.out.println("authorising lugg " + lugg.getId());
        //
        Customer c = customerStore.get(lugg.getCustomer());
        lugg.setMasterCardId(c.getMasterCardId());
        // amount 10.00
        makeAuthorisation(lugg);
        //
        LuggStore.put(lugg.getId(), lugg);
        return lugg;   
    }
    
    private void makeAuthorisation(Lugg lugg){
    	try {
			Authorization authorization = Authorization.create(new PaymentsMap()
			.set("amount", lugg.getAmount())
			.set("currency", "USD")
			.set("description", "payment description")
			.set("reference", lugg.getId())
			.set("token", lugg.getMasterCardId()));
			
			String authCode = (String)authorization.get("id");
			lugg.setAuthCode(authCode);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    
    @RequestMapping(value = "/lugg/track", method = RequestMethod.POST)
    public Lugg track(@RequestBody Lugg lugg) {
        System.out.println("tracking lugg " + lugg.getId());
        return lugg;   
    }
    
    private void makePayment(Lugg lugg){
    	Payment payment;
		try {
			//
			payment = Payment.create(new PaymentsMap()
			.set("currency", "USD")
			.set("authorization", lugg.getAuthCode()) // returned by JavaScript call
			.set("amount", lugg.getAmount()) // In cents e.g. $10.00
			.set("description", "lugg description"));
			//
			lugg.setApprovalStatus((String)payment.get("paymentStatus"));
			if ("APPROVED".equals(payment.get("paymentStatus"))) {
		        System.out.println("Payment approved");
		    }else{
		    	System.out.println("Payment not approved");
		    }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lugg.setApprovalStatus("-1");
		}
    }
    

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
    
//    private static final Comparator<Map> TFL_journey_comp = new Comparator<Map>() {
//        public int compare(Map c1, Map c2) {
//            return ((Integer)c1.get("duration")).compareTo((Integer)c1.get("duration")) ; 
//        }
//    };

}