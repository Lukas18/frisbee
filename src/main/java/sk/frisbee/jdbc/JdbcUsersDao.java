package sk.frisbee.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import sk.frisbee.domain.User;

public class JdbcUsersDao implements UsersDao {

	protected final Log logger = LogFactory.getLog(getClass());
	
	@Autowired  
	DataSource dataSource;

	@Override
	public List<User> getAllUsersList() {
		logger.info("getting users from DB");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		List<User> usersList = jdbcTemplate.query(
				"select meno from frisbee.profil_hrac",
				new UsersMapper());
		jdbcTemplate = null;	
		return usersList;
	}

	@Override
	public List<User> getUsersList(int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private static class UsersMapper implements ParameterizedRowMapper<User>{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setFirstName(rs.getString("meno"));
			return user;	
		}
		
	}

}
