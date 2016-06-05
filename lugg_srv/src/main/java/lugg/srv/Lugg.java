package lugg.srv;

import java.util.List;
import java.util.Map;

public class Lugg {

	private  String id;
	private  String customer;
	private  String pickup;
	private  String dropoff;
	private  String amount;
	private  String ate = "70"; 
	private  String masterCardId;
	private  String approvalStatus = "0";
	private  String delivered = "0";
	//
	private  List<Map<String,String>> tfl_journey;
	private  Integer tfl_journey_duration;
	
	private String authCode;
	
	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public List<Map<String, String>> getTfl_journey() {
		return tfl_journey;
	}

	public void setTfl_journey(List<Map<String, String>> tfl_journey) {
		this.tfl_journey = tfl_journey;
	}

	public Integer getTfl_journey_duration() {
		return tfl_journey_duration;
	}

	public void setTfl_journey_duration(Integer tfl_journey_duration) {
		this.tfl_journey_duration = tfl_journey_duration;
	}

	public String getDelivered() {
		return delivered;
	}

	public void setDelivered(String delivered) {
		this.delivered = delivered;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getMasterCardId() {
		return masterCardId;
	}

	public void setMasterCardId(String masterCardId) {
		this.masterCardId = masterCardId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public void setPickup(String pickup) {
		this.pickup = pickup;
	}

	public void setDropoff(String dropoff) {
		this.dropoff = dropoff;
	}

	public void setAte(String ate) {
		this.ate = ate;
	}

	public String getAte() {
		return ate;
	}

	public Lugg(String id, String customer, String pickup, String dropoff, String masterCardId, String amount) {
		super();
		this.id = id;
		this.customer = customer;
		this.pickup = pickup;
		this.dropoff = dropoff;
		this.masterCardId = masterCardId;
		this.amount = amount;
	}
	
	public Lugg() {
	}
	
	

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getId() {
		return id;
	}

	public String getCustomer() {
		return customer;
	}

	public String getPickup() {
		return pickup;
	}

	public String getDropoff() {
		return dropoff;
	}
	
	
	
}
