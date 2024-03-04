package day7.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import day4.mybatis.dao.MybatisCustomerDao;
import day4.mybatis.dto.CustomerDto;
import lombok.extern.slf4j.Slf4j;


/**
 * 고객 전체 목록 (List) 자바 객체를 json 문자열로 변환해서 응답(response)으로 보냅니다.
 * 										ㄴ jackson-bind 라이브러리를 사용해서 매우 쉽게 할 수 있습니다.
 * @author Administrator
 *
 */
@Slf4j
public class ApiCustomerGetController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(ApiCustomerGetController.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MybatisCustomerDao dao = new MybatisCustomerDao();
		CustomerDto dto = dao.getCustomer(request.getParameter("id"));
		
		log.info("getParameter : {}", request.getParameter("id"));
		
		log.info("getCustomer : {}", dto);
		
		
		// CustomerDto 자바 객체를 json 문자열로 변환
		// 1.아이디 중복 검사가 아닌 고객 조회이면 사용합니다
//		ObjectMapper om = new ObjectMapper();
//		String jsonData = null;
//		jsonData = om.writeValueAsString(dto);
//		
//		log.info("전송할 json 문자열 : {}", jsonData);
		
		int result = 0;
		if(dto != null) result = 1;
		
		// 2.아이디 중복 검사인 경우에는 json 으로 있다 또는 없다 에 대한 값으로 전달합니다
		String jsonData = "{ \"result\" : "+result+" }";
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonData);

		log.info("중복 아이디 개수 : {}", result);
	}

}
