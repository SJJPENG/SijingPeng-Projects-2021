package voyage.model;

import java.util.Date;

public class Reviews {

	protected int reviewId;
	protected Listings listing;
	protected Users user;
	protected Date date;
	protected String comments;
	protected double rating;
	
	public Reviews(int reviewId, Listings listing, Users user, Date date, String comments, double rating) {
		this.reviewId = reviewId;
		this.listing = listing;
		this.user = user;
		this.date = date;
		this.comments = comments;
		this.rating = rating;
	}
	
	public Reviews(Listings listing, Users user, Date date, String comments, double rating) {
		this.listing = listing;
		this.user = user;
		this.date = date;
		this.comments = comments;
		this.rating = rating;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Listings getListing() {
		return listing;
	}

	public void setListingId(Listings listing) {
		this.listing = listing;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Reviews [reviewId=" + reviewId + ", listing=" + listing + ", user=" + user + ", date=" + date
				+ ", comments=" + comments + ", rating=" + rating + "]";
	}

}
