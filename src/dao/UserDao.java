package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

/**
 * 用户Dao类
 * @author lyj80
 *
 */
public class UserDao {

	/**
	 * 用户登入验证
	 * @param con	数据库接口
	 * @param user	用户实体
	 * @return
	 * @throws Exception
	 */
	public User login(Connection con,User user)throws Exception{
		User resultUser=null;
		String sql="select * from t_user where userName=? and password=?";
		PreparedStatement pstmt=con.prepareStatement(sql);//对sql语句进行预编译
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getPassword());
		ResultSet rs=pstmt.executeQuery();//执行sql语句，返回结果集
		if(rs.next()) {
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));//把从数据库中获取到的数据全部设置到resultUser里去，然后在返回一个resultUser的实体
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		return resultUser;
		
	}
}
