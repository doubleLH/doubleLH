package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import entity.User;

public class UserMapperImpl implements RowMapper<User>{

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		int id = rs.getInt("id");
		String username = rs.getString("username");
		String password = rs.getString("password");
		
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		return user;
		
		
		
	}

}
