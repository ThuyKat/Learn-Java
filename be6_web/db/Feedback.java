package db;

import java.sql.Timestamp;

public class Feedback {

	private int id;
	private int productId;
	private int userId;
	private String feedbackText;
	private String subject;
	private Timestamp createAt;
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(int id, int productId, int userId, String feedbackText,String subject,Timestamp createAt) {
		super();
		this.id = id;
		this.productId = productId;
		this.userId = userId;
		this.feedbackText = feedbackText;
		this.subject = subject;
		this.setCreateAt(createAt);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFeedbackText() {
		return feedbackText;
	}
	public void setFeedbackText(String feedbackText) {
		this.feedbackText = feedbackText;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Timestamp getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}
}
