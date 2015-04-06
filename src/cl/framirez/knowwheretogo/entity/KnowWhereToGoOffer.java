package cl.framirez.knowwheretogo.entity;

public class KnowWhereToGoOffer {
	
	private int id;
	private String offer_name;
	private int time_hh;
	private int time_mm;
	private String description;
	private String address;
	private int number_days;
	private double longitude;
	private double latitude;
	
	
	public KnowWhereToGoOffer(int id, String offer_name, int time_hh,
			int time_mm, String description, int number_days, double longitude,
			double latitude,String address) {
		super();
		this.id = id;
		this.offer_name = offer_name;
		this.time_hh = time_hh;
		this.time_mm = time_mm;
		this.description = description;
		this.number_days = number_days;
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address; 
	} 
	
	public KnowWhereToGoOffer(int id, String offer_name, int time_hh,
			int time_mm, String description, int number_days,String address) {
		super();
		this.id = id;
		this.offer_name = offer_name;
		this.time_hh = time_hh;
		this.time_mm = time_mm;
		this.description = description;
		this.number_days = number_days;
		this.address = address; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOffer_name() {
		return offer_name;
	}

	public void setOffer_name(String offer_name) {
		this.offer_name = offer_name;
	}

	public int getTime_hh() {
		return time_hh;
	}

	public void setTime_hh(int time_hh) {
		this.time_hh = time_hh;
	}

	public int getTime_mm() {
		return time_mm;
	}

	public void setTime_mm(int time_mm) {
		this.time_mm = time_mm;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getNumber_days() {
		return number_days;
	}

	public void setNumber_days(int number_days) {
		this.number_days = number_days;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	
	
}
