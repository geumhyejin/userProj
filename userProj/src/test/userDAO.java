package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;




public class userDAO {

		Connection conn;
		Statement stmt;
		ResultSet rs;
		PreparedStatement psmt;
		
		public List<userVO> getUserList() {
			// 사원정보를 가지고 오는 처리
			String sql = "select * from user_temp order by user_id";
			conn = DBCon.getConnect();
			List<userVO> list = new ArrayList<userVO>();

			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					userVO vo = new userVO();
					vo.setId(rs.getInt("id"));
					vo.setName(rs.getString("name"));
					vo.setPass(rs.getInt("pass"));
					vo.setPhone(rs.getInt("phone"));
					vo.setGender(rs.getString("gender"));
					

					list.add(vo);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			return list;
		}

		

		public void insertUserTemp(userVO vo) {
			String sql = "insert into user_temp(user_id, user_name, user_pass, user_phone, user_gender)" //
					+ "values ((select max(user_id)+1 from user_temp), ?, ?, ?, ?,?)";
			conn = DBCon.getConnect();
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, vo.getId());
				psmt.setString(2, vo.getName());
				psmt.setInt(3, vo.getPass());
				psmt.setInt(4, vo.getPhone());
				psmt.setString(5, vo.getGender());

				int r = psmt.executeUpdate();
				System.out.println(r + "건 입력됨.");

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				if (psmt != null) {
					try {
						psmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	

		
	

	

