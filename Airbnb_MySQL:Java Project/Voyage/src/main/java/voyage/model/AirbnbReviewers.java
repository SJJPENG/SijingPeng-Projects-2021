package voyage.model;

public class AirbnbReviewers {

  protected long reviewerId;
  protected String reviewerName;


  public AirbnbReviewers(long reviewerId, String reviewerName) {
    this.reviewerId = reviewerId;
    this.reviewerName = reviewerName;
  }

  public long getReviewerId() {
    return reviewerId;
  }

  public void setReviewerId(long reviewerId) {
    this.reviewerId = reviewerId;
  }

  public String getReviewerName() {
    return reviewerName;
  }

  public void setReviewerName(String reviewerName) {
    this.reviewerName = reviewerName;
  }

  @Override
  public String toString() {
    return "AirbnbReviewers{" +
        "reviewerId=" + reviewerId +
        ", reviewerName='" + reviewerName + '\'' +
        '}';
  }
}
