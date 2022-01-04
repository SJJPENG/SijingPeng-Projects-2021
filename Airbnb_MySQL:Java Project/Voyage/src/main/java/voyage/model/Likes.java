package voyage.model;

public class Likes {

	protected int likeId;
	protected Users user;
	protected Listings listing;
	
	public Likes(int likeId, Users user, Listings listing) {
		this.likeId = likeId;
		this.user = user;
		this.listing = listing;
	}
	
	public Likes(Users user, Listings listing) {
		this.user = user;
		this.listing = listing;
	}

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
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
		return "Likes [likeId=" + likeId + ", user=" + user + ", listing=" + listing + "]";
	}
	
}
