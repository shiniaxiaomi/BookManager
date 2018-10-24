package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.BookType;
import util.StringUtil;

/**
 * 图书类别Dao类
 * @author lyj80
 *
 */
public class BookTypeDao {

	/**
	 * 图书类别添加
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public int add(Connection con,BookType bookType)throws Exception{
		String sql="insert into t_bookType values(null,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);//对sql语句进行预编译
		pstmt.setString(1, bookType.getBookTypeName());
		pstmt.setString(2, bookType.getBookTypeDesc());
		return pstmt.executeUpdate();//执行，并返回执行的结果
		
	}
	
	/**
	 * 查询图书类别
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public ResultSet list(Connection con,BookType bookType)throws Exception{
		StringBuffer sb=new StringBuffer("select * from t_bookType");
		if(StringUtil.isNotEmpty(bookType.getBookTypeName())) {
			sb.append(" and bookTypeName like '%"+bookType.getBookTypeName()+"%'");//进行字符串的拼接
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replace("and", "where"));//吧第一个and替换成where
		return pstmt.executeQuery();//执行sql语句
		
	}
	
	/**
	 * 删除图书类别
	 * @param con
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int delete(Connection con,String id)throws Exception{
		String sql="delete from t_bookType where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);//对sql语句进行预编译
		pstmt.setString(1,id);//设置id
		return pstmt.executeUpdate();//执行sql语句
	}
	
	/**
	 * 更新图书类别
	 * @param con
	 * @param bookType
	 * @return
	 * @throws Exception
	 */
	public int update(Connection con,BookType bookType)throws Exception{
		String sql="update t_bookType set bookTypeName=?,bookTypeDesc=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);//对sql语句进行预编译
		pstmt.setString(1,bookType.getBookTypeName());
		pstmt.setString(2,bookType.getBookTypeDesc());
		pstmt.setInt(3,bookType.getId());
		return pstmt.executeUpdate();//执行sql语句

	}
	
	
}
