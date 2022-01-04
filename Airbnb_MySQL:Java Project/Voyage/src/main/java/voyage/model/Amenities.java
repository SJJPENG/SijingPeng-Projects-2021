package voyage.model;


public class Amenities {
	
	protected int AmenityId;
	protected String Type;
	
	
	public Amenities(int amenityId, String type) {
		this.AmenityId = amenityId;
		this.Type = type;
	}


	public int getAmenityId() {
		return AmenityId;
	}


	public Amenities(String type) {
		this.Type = type;
	}


	public String getType() {
		return Type;
	}


	public void setType(String type) {
		this.Type = type;
	}


	@Override
	public String toString() {
		return "Amenities [AmenityId=" + AmenityId + ", Type=" + Type + "]";
	}
	
	

}
