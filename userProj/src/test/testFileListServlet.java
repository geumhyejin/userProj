package test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@WebServlet("/testFileListServlet")
public class testFileListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public testFileListServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//json 데이터...DAO : 전체리스트 메소드,
		//[{"num":?,"title":?},{},{}]
		//JSONObject , JSONArray
		JSONArray ary = new JSONArray();
		
		userDAO dao = new userDAO();
		List<userVO> list = dao.getUserList();
		for(userVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", vo.getId());
			obj.put("name", vo.getName());
			obj.put("pass", vo.getPass());
			obj.put("phone", vo.getPhone());
			obj.put("gender", vo.getGender());    //대소문자 똑같이 맞춰야됨. (데이터못가져옴)
			
			ary.add(obj);
		}
		
		response.getWriter().print(ary);
	}

	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
