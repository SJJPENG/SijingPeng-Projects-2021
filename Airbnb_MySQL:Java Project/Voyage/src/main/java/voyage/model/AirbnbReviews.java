package voyage.model;

import java.sql.Date;

public class AirbnbReviews {

  protected int listingId;
  protected long reviewId;
  protected Date date;
  protected long reviewerId;
  protected String comments;

  public AirbnbReviews(int listingId, long reviewId, Date date, long reviewerId,
      String comments) {
    this.listingId = listingId;
    this.reviewId = reviewId;
    this.date = date;
    this.reviewerId = reviewerId;
    this.comments = comments;
  }

  public int getListingId() {
    return listingId;
  }

  public void setListingId(int listingId) {
    this.listingId = listingId;
  }

  public long getReviewId() {
    return reviewId;
  }

  public void setReviewId(long reviewId) {
    this.reviewId = reviewId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getReviewerId() {
    return reviewerId;
  }

  public void setReviewerId(long reviewerId) {
    this.reviewerId = reviewerId;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "AirbnbReviews{" +
        "listingId=" + listingId +
        ", reviewId=" + reviewId +
        ", date=" + date +
        ", reviewerId=" + reviewerId +
        ", comments='" + comments + '\'' +
        '}';
  }
}
