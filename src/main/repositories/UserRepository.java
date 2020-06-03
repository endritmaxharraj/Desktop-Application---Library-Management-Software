package main.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import main.models.User;
import main.models.UserRole;
import main.utils.DBConnector;

public class UserRepository {

	public static int count() throws Exception {
		Connection conn = DBConnector.getConnection();
		ResultSet res = conn.prepareStatement("SELECT COUNT(*) FROM users").executeQuery();
		res.next();
		return res.getInt(1);
	}

	public static List<User> getAll(int pageSize, int page) throws Exception {
		PreparedStatement stmt = DBConnector.getConnection()
				.prepareStatement("SELECT * FROM users ORDER BY id ASC LIMIT ? OFFSET ?");
		stmt.setInt(1, pageSize);
		stmt.setInt(2, page * pageSize);

		ResultSet res = stmt.executeQuery();
		List<User> list = new ArrayList<>();
		while (res.next()) {
			list.add(parseRes(res));
		}
		return list;
	}

	public static User find(String user_name, String user_pass) throws Exception {
		PreparedStatement stmt = DBConnector
				.getConnection()
				.prepareStatement("SELECT * FROM users WHERE user_name = ? AND user_pass = ? LIMIT 1");
		stmt.setString(1, user_name);
		stmt.setString(2, user_pass);

		ResultSet res = stmt.executeQuery();
		if (!res.next())
			return null;
		return parseRes(res);
	}
	public static User create(User model) throws Exception {
		String query = "INSERT INTO users (user_name, user_pass, user_type) VALUES (?, ?, ?)";
		PreparedStatement stmt = DBConnector.getConnection().prepareStatement(query);
		stmt.setString(1, model.getUser_name());
		stmt.setString(2, model.getUser_pass());
		stmt.setString(3, model.getUser_role() == UserRole.admin ? "admin" : "user");

		if (stmt.executeUpdate() != 1)
			throw new Exception("ERR_NO_ROW_CHANGE");

		stmt = DBConnector.getConnection().prepareStatement("SELECT * FROM users ORDER BY createdAt DESC LIMIT 1");
		ResultSet res = stmt.executeQuery();
		res.next();
		return parseRes(res);
	}

	public static User update(User model) throws Exception {
		String query = "UPDATE users SET user_name = ?, user_pass = ?, user_type = ? WHERE user_name = ?";
		PreparedStatement stmt = DBConnector.getConnection().prepareStatement(query);
		stmt.setString(1, model.getUser_name());
		stmt.setString(2, model.getUser_pass());
		stmt.setString(3, model.getUser_role() == UserRole.admin ? "admin" : "user");

		if (stmt.executeUpdate() != 1)
			throw new Exception("ERR_NO_ROW_CHANGE");

		return find(model.getUser_name(), model.getUser_pass());
	}

	public static boolean remove(String user_name) throws Exception {
		String query = "DELETE FROM users WHERE user_name = ?";
		PreparedStatement stmt = DBConnector.getConnection().prepareStatement(query);
		stmt.setString(1, user_name);
		return stmt.executeUpdate() == 1;
	}

	private static User parseRes(ResultSet res) throws Exception {
		String user_name = res.getString("user_name");
		String user_pass = res.getString("user_pass");
		UserRole user_type = res.getString("user_type").equals("admin") ? UserRole.admin : UserRole.user;
		return new User(user_name, user_pass, user_type);
	}

}
