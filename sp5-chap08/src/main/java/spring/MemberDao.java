package spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

public class MemberDao {

	private JdbcTemplate jdbcTemplate;

	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER where  EMAIL = ?",
				(ResultSet resultSet, int rowNum) -> {
						Member member = new Member(
								resultSet.getString("EMAIL"),
								resultSet.getString("PASSWORD"),
								resultSet.getString("NAME"),
								resultSet.getTimestamp("REGDATE").toLocalDateTime());
						member.setId(resultSet.getLong("ID"));
						return member;
				},
				email);
		return results.isEmpty() ? null : results.get(0);
	}

	public void insert(Member member) {

	}
	public void update(Member member) {

	}
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER",
				(ResultSet resultSet, int rowNum) -> {
					Member member = new Member(
							resultSet.getString("EMAIL"),
							resultSet.getString("PASSWORD"),
							resultSet.getString("NAME"),
							resultSet.getTimestamp("REGDATE").toLocalDateTime());
					member.setId(resultSet.getLong("ID"));
					return member;
				}
		);
		return results;
	}

	public int count() {
		// queryForObject는 실행결과 행이 1개일 경우 사용
		return jdbcTemplate.queryForObject(
				"select count(*) from MEMBER",
				Integer.class
		);
	}
}
