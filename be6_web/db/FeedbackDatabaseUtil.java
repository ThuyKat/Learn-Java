package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDatabaseUtil {
	public List<Feedback> getFeedbackByProductId(String productId) throws SQLException {

		List<Feedback> feedbacks = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {

			String query = "SELECT * FROM feedbacks WHERE product_id=?";

			ps = connection.prepareStatement(query);
			ps.setInt(1, Integer.parseInt(productId));
			rs = ps.executeQuery();
			while (rs.next()) {
				Feedback feedback = new Feedback();
				feedback.setId(rs.getInt("id"));
				feedback.setProductId(rs.getInt("product_id"));
				feedback.setUserId(rs.getInt("user_id"));
				feedback.setFeedbackText(rs.getString("feedback_text"));
				feedback.setSubject(rs.getString("subject"));
				feedback.setCreateAt(rs.getTimestamp("created_at"));

				feedbacks.add(feedback);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ps.close();
			if(rs !=null) {
			rs.close();
			}
		}

		return feedbacks;
	}

	public int saveFeedback(int productId, int userId, String feedbackText, String subject) throws SQLException {
		int rowsInserted = 0;
		PreparedStatement statement = null;
		try (Connection connection = DatabaseConnectionUtil.getDatabaseConnection()) {
			statement = connection.prepareStatement(
					"insert into feedbacks(product_id,user_id,feedback_text,subject) values(?,?,?,?);");
			statement.setInt(1, productId);
			statement.setInt(2, userId);
			statement.setString(3, feedbackText);
			statement.setString(4, subject);
			// execute statement
			rowsInserted = statement.executeUpdate();
			return rowsInserted;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			statement.close();
		}
		return rowsInserted;
	}

}
