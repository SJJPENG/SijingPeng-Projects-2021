package voyage.model;

public class ListingAmenityAssociation {
	
	protected int AmenityAssoId;
	protected Listings listings;
	protected Amenities amenity;
	
	
	public ListingAmenityAssociation(int amenityAssoId, Listings listings, Amenities amenity) {
		this.AmenityAssoId = amenityAssoId;
		this.listings = listings;
		this.amenity = amenity;
	}


	public int getAmenityAssoId() {
		return AmenityAssoId;
	}


	public void setAmenityAssoId(int amenityAssoId) {
		AmenityAssoId = amenityAssoId;
	}
	
	public ListingAmenityAssociation(Listings listings, Amenities amenity) {
		this.listings = listings;
		this.amenity = amenity;
	}


	public Listings getListings() {
		return listings;
	}


	public void setListings(Listings listings) {
		this.listings = listings;
	}


	public Amenities getAmenity() {
		return amenity;
	}


	public void setAmenity(Amenities amenity) {
		this.amenity = amenity;
	}


	@Override
	public String toString() {
		return "ListingAmenityAssociation [AmenityAssoId=" + AmenityAssoId + ", listings=" + listings + ", amenity="
				+ amenity + "]";
	}
	
}
