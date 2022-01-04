package voyage.model;

public class ListingsWithMinPrice {
	Listings listing;
	double minPrice;
	public ListingsWithMinPrice(Listings listing, double minPrice) {
		this.listing = listing;
		this.minPrice = minPrice;
	}
	public Listings getListing() {
		return listing;
	}
	public void setListing(Listings listing) {
		this.listing = listing;
	}
	public double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}
	
	
}
