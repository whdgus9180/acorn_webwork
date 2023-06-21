package test.member.dao;
/*
 *  Application 전역에서 MemberDao 객체는 오직 한개만 생성해서 사용하도록 구조를 만들어야 한다.
 *  
 *  1. 외부에서 객체 생성하지 못하도록 생성자의 접근 지정자는 private 
 *  2. 자신의 참조값을 저장할수 있는 static 필드 만들기
 *  3. 자신의 참조값을 리턴해주는 public static 메소드 만들기
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import test.member.dto.MemberDto;
import test.util.DbcpBean;

public class MemberDao {
   //2.
   private static MemberDao dao;
   
   //1. 
   private MemberDao() {}
   
   //3.
   public static MemberDao getInstance() {
      //서버 시작후 최초 요청이라면
      if(dao==null) {
         //객체를 생성해서 static 필드에 저장해 놓는다.
         dao=new MemberDao();
      }
      //필드에 저장된 참조값 리턴해주기
      return dao;
   }
   
   //회원 목록을 리턴하는 메소드
   public List<MemberDto> getList(){
	   
	   //회원 목록을 담을 객체 미리 생성하기 
       List<MemberDto> list=new ArrayList<>();
       //필요한 객체의 참조값을 담을 지역변수 미리 만들기
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   ResultSet rs=null;
	   try {
		   //DbcpBean 객체를 이용해서 Connection 객체를 얻어온다(Connection Pool 에서 얻어오기)
			conn=new DbcpBean().getConn();
			//실행할 sql 문 
			String sql="SELECT num, name, addr"
					+ " FROM member"
					+ " ORDER by num DESC";
			pstmt=conn.prepareStatement(sql);
			//sql 문이 미완성이라면 여기서 완성
			
			//select 문 수행하고 결과값 받아오기
			rs=pstmt.executeQuery();
			//반복문 돌면서 ResultSet 에 담긴 내용 추출
			while(rs.next()) {
				//ResultSet 에 cursor 가 위치한곳의 칼럼 정보를 얻어와서 MemberDto 객체에 담고
				MemberDto dto = new MemberDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setAddr(rs.getString("addr"));
				//ArrayList에 누적시키기
				list.add(dto);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e) {}
		}
		//회원 정보가 누적된 List 객체의 참조값을 리턴한다.
		return list;
}
 //회원 한명의 정보를 저장하고 해당 작업의 성공여부를 리턴해주는 메소드
   public boolean insert(MemberDto dto) {
      
      //필요한 객체를 담을 지역 변수를 미리 만들기
      Connection conn=null;
      PreparedStatement pstmt=null;
      //insert, update, delete 작업을 통해서 변화된(추가, 수정, 삭제) row 의 갯수를 담을 변수
      int rowCount=0; //초기값을 0으로 부여한다. 
      try {
         //Connection 객체의 참조값 얻어오기
         conn=new DbcpBean().getConn();
         //실행할 sql 문
         String sql="INSERT INTO member"
        		  + " (num, name, addr)"
        		  + " VALUES(member_seq.NEXTVAL, ?, ?)";
         //sql 문을 대신 실행해줄 PreparedStatement 객체의 참조값 얻어오기
         pstmt=conn.prepareStatement(sql);
         //sql 문이 ? 가 존재하는 미완성이라면 여기서 완성한다.
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getAddr());
         // insert or update or delete 문을 실제 수행한다. 변화된 row 의 갯수가 리턴된다.
         rowCount=pstmt.executeUpdate();//수행하고 리턴되는값을 변수에 담는다.
      }catch(SQLException se) {
         se.printStackTrace();
      }finally { //예외가 발생을 하던 안하던 실행이 보장되는 블럭에서 마무리 작업
         try {
            if(pstmt!=null)pstmt.close();
            if(conn!=null)conn.close();
         }catch(Exception e) {}
      }
      //변화된 row 의 갯수가 0 보다 크면 작업 성공
      if(rowCount > 0) {
         return true;
      }else {//그렇지 않으면 작업 실패
         return false;
      }
   }
   //회원 한명의 정보를 삭제하는 메소드
   public boolean delete(int num) {
      //필요한 객체를 담을 지역 변수를 미리 만들기
      Connection conn = null;
      PreparedStatement pstmt = null;
      //insert, update, delete 작업을 통해서 변화된(추가, 수정, 삭제) row 의 갯수를 담을 변수
      int rowCount = 0; //초기값을 0으로 부여한다. 
      try {
         //Connection 객체의 참조값 얻어오기
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "DELETE FROM member"
        		    + " WHERE num=?";
         //sql 문을 대신 실행해줄 PreparedStatement 객체의 참조값 얻어오기
         pstmt = conn.prepareStatement(sql);
         //sql 문이 ? 가 존재하는 미완성이라면 여기서 완성한다.
         pstmt.setInt(1, num);
         // insert or update or delete 문을 실제 수행한다. 변화된 row 의 갯수가 리턴된다.
         rowCount = pstmt.executeUpdate();//수행하고 리턴되는값을 변수에 담는다.
      } catch (Exception e) {
         e.printStackTrace();
      } finally { //예외가 발생을 하던 안하던 실행이 보장되는 블럭에서 마무리 작업
         try {
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
         }
      }
      //변화된 row 의 갯수가 0 보다 크면 작업 성공
      if (rowCount > 0) {
         return true;
      } else {//그렇지 않으면 작업 실패
         return false;
      }
     
   }
   public  MemberDto getData(int num) {
	   MemberDto dto=null;
	   //필요한 객체의 참조값을 담을 지역변수 미리 만들기
	   Connection conn=null;
	   PreparedStatement pstmt=null;
	   ResultSet rs=null;
	   try {
		   //DbcpBean 객체를 이용해서 Connection 객체를 얻어온다(Connection Pool 에서 얻어오기)
		   conn=new DbcpBean().getConn();
		   //실행할 sql 문 
		   String sql="SELECT name, addr"
				    + " FROM member"
			 	    + " WHERE num=?";
		   pstmt=conn.prepareStatement(sql);
		   //sql 문이 미완성이라면 여기서 완성
		   pstmt.setInt(1, num);
		   //select 문 수행하고 결과값 받아오기
		   rs=pstmt.executeQuery();
		   //반복문 돌면서 ResultSet 에 담긴 내용 추출
		   while(rs.next()) {
			   dto=new MemberDto();
			   dto.setNum(num); // 번호는 지역 변수에 있는 값을 담고
			   //이름과 주소는 ResultSet 으로 부터 얻어내서 담는다.
			   dto.setName(rs.getString("name"));
			   dto.setAddr(rs.getString("addr"));
		   }
	   }catch(SQLException se) {
		   se.printStackTrace();
	   }finally {
		   try {
			   if(rs!=null)rs.close();
			   if(pstmt!=null)pstmt.close();
			   if(conn!=null)conn.close(); //Connection 이 Connection Pool 에 반납된다.
		   }catch(Exception e) {}
	   }
	   //회원 한명의 정보가 담긴 MemberDto 객체 리턴해주기
	   return dto;
   }//getData()
   
   //회원 한명의 정보를 수정하는 메소드
 //회원 한명의 정보를 수정하고 해당 작업의 성공여부를 리턴해주는 메소드
   public boolean update(MemberDto dto) {
      //필요한 객체를 담을 지역 변수를 미리 만들기
      Connection conn = null;
      PreparedStatement pstmt = null;
      //insert, update, delete 작업을 통해서 변화된(추가, 수정, 삭제) row 의 갯수를 담을 변수
      int rowCount = 0; //초기값을 0으로 부여한다. 
      try {
         //Connection 객체의 참조값 얻어오기
         conn = new DbcpBean().getConn();
         //실행할 sql 문
         String sql = "UPDATE member"
               + " SET name=?, addr=?"
               + " WHERE num=?";
         //sql 문을 대신 실행해줄 PreparedStatement 객체의 참조값 얻어오기
         pstmt = conn.prepareStatement(sql);
         //sql 문이 ? 가 존재하는 미완성이라면 여기서 완성한다.
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getAddr());
         pstmt.setInt(3, dto.getNum());
         // insert or update or delete 문을 실제 수행한다. 변화된 row 의 갯수가 리턴된다.
         rowCount = pstmt.executeUpdate();//수행하고 리턴되는값을 변수에 담는다.
      } catch (Exception e) {
         e.printStackTrace();
      } finally { //예외가 발생을 하던 안하던 실행이 보장되는 블럭에서 마무리 작업
         try {
            if (pstmt != null)
               pstmt.close();
            if (conn != null)
               conn.close();
         } catch (Exception e) {
         }
      }
      //변화된 row 의 갯수가 0 보다 크면 작업 성공
      if (rowCount > 0) {
         return true;
      } else {//그렇지 않으면 작업 실패
         return false;
      }
   }
}


















