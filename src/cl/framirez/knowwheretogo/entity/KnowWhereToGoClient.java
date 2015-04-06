package cl.framirez.knowwheretogo.entity;

import java.util.List;

public class KnowWhereToGoClient {
	
	
	//Clients are PYMEs
	//Subclient are persons
	private String name;
	private String enterprise_name;
	private int id;
	private List<KnowWhereToGoOffer> listoffer;
	
	public KnowWhereToGoClient(String name, String local_name, int id) {
		super();
		this.name = name;
		this.enterprise_name = local_name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocal_name() {
		return enterprise_name;
	}

	public void setLocal_name(String local_name) {
		this.enterprise_name = local_name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
