package lugg.srv;

public class Customer {

	private String id;
	private String masterCardId;
	
	public String getMasterCardId() {
		return masterCardId;
	}

	public void setMasterCardId(String masterCardId) {
		this.masterCardId = masterCardId;
	}

	public Customer(){
		
	}
	
	public Customer(String id, String masterCardId) {
		super();
		this.id = id;
		this.masterCardId = masterCardId;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
