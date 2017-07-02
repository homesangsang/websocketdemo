package websocket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import websocket.dao.impl.Student;

public class DBOption {
	private Connection connection =DB.getConnection() ;
	private PreparedStatement ps;
	private ResultSet result ;
	
	private String sql;
	
	/**
	 * 增加一条记录
	 */
	public void insert(Object object){
		
	}
	
	/**
	 * 删除一条记录
	 */
	public void deleteOneRecord(Object object){
		
	}
	
	/**
	 * 更改一条数据
	 */
	public void updateOneRecord(Object object,String column,String value){
		
	}
	
	/**
	 * 查询吓一条没有初始的学生
	 * @throws SQLException 
	 */
	public Student next() throws SQLException{
		Student student = null ;
		ps = connection.prepareStatement("select * from student  where isselect=0 order by rank limit 1;");
		
		if(ps.execute()){
			result = ps.getResultSet();
		}
		while(result.next()){
			student = new Student(result.getString("name"),result.getString("academy"),result.getString("major"),result.getString("sex"),result.getString("mobile"),result.getString("qq"),result.getString("wechat"),result.getString("advantages"));
		}
		
		result.close();
		ps.close();
		return student;
	}
	
	/**
	 * 更新学生记录，将学生设置为已经上台过
	 * @param Student student
	 * @throws SQLException 
	 */
	public void setIsSelect(Student student) throws SQLException{
		ps = connection.prepareStatement("update student set isselect=1 where name=? ");
  
		ps.setString(1, student.getName());
		System.out.println(student.getName());
		System.out.println(ps.execute());
		System.out.println(ps.getUpdateCount());
		
	}
	
	public List<Student> list() throws SQLException{
		ps = connection.prepareStatement("select * from student order by rank");
		result = ps.executeQuery();
		List<Student> list = new ArrayList<Student>();
		while(result.next()){
			list.add(new Student(result.getInt(1), result.getInt(2), result.getString(3),
					result.getString(4), result.getString(5), 
					result.getString(6), result.getString(7),
					result.getString(8),result.getString(9),result.getString(10),result.getInt(11)));
		}
		return list;
	}
	// 向上移动，本条记录向与上一条交换
	public void up(int id) throws SQLException{
		int change;
		//获取当前行的数据
		Student student = null;
		ps = connection.prepareStatement("select * from student where id=? ");
		ps.setInt(1, id);
		result = ps.executeQuery();
		if(result.next()){
			student = new Student(result.getInt(1), result.getInt(2), result.getString(3),
					result.getString(4), result.getString(5), 
					result.getString(6), result.getString(7),
					result.getString(8),result.getString(9),result.getString(10),result.getInt(11));
			System.out.println(student.toString());
		}
		// 获得上一行记录，转存为student对象
		Student studentUp = null;
		ps = connection.prepareStatement(" select * from student where id <? or id=(SELECT MIN(id) from student) order by id desc limit 1 ");
		ps.setInt(1, id);
		result = ps.executeQuery();
		if(result.next()){
			studentUp = new Student(result.getInt(1), result.getInt(2), result.getString(3),
					result.getString(4), result.getString(5), 
					result.getString(6), result.getString(7),
					result.getString(8),result.getString(9),result.getString(10),result.getInt(11));
			System.out.println(studentUp.toString());
		}
		// 交换两行的rank
		change = studentUp.getRank();
		ps = connection.prepareStatement("update student set rank=? where id=? and name=?");
		ps.setInt(1, student.getRank());
		ps.setInt(2, studentUp.getId());
		ps.setString(3, studentUp.getName());
		ps.executeUpdate();
		ps.setInt(1, change);
		ps.setInt(2, student.getId());
		ps.setString(3, student.getName());
		ps.executeUpdate();
	}
	
	public static void main(String[] args) throws SQLException {
		DBOption db = new DBOption();
		db.up(3);
	}
}
