package voyage.model;

public class Dislikes {

	protected int dislikeId;
	protected Users user;
	protected Listings listing;
	
	public Dislikes(int dislikeId, Users user, Listings listing) {
		this.dislikeId = dislikeId;
		this.user = user;
		this.listing = listing;
	}
	
	public Dislikes(Users user, Listings listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getDislikeId() {
		return dislikeId;
	}

	public void setDislikeId(int dislikeId) {
		this.dislikeId = dislikeId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Listings getListing() {
		return listing;
	}

	public void setListing(Listings listing) {
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "Dislikes [dislikeId=" + dislikeId + ", user=" + user + ", listing=" + listing + "]";
	}
}
