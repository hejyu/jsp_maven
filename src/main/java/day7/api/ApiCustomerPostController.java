package day7.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
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
public class ApiCustomerPostController implements Controller {

	private static final Logger logger = LoggerFactory.getLogger(ApiCustomerPostController.class);
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 사용자가 보낸 데이터는 json 문자열입니다.
		// 이 데이터를 읽어오기 위해 입력스트림이 필요합니다.
		InputStream inputStream = request.getInputStream();	// http 전송방식의 입력스트림 생성, 요청 객체로 만들어집니다.
		
		// 입출력의 속도를 향상시키기 위해 버퍼(인력거 보조)를 사용합니다.
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
		StringBuffer sb = new StringBuffer();	// String은 불변 객체이므로 + 연결연산을 대신하는 메소드를 사용하기 위해 필요
		String line = null;
		
		while((line = br.readLine()) != null) {	// 입력 스트림으로 부터 1줄씩 읽어 옵니다.
			sb.append(line);
			log.info("line : {} ", line);
		}
		
		// json 문자열을 자바객체로 변환합니다
		ObjectMapper om = new ObjectMapper();
		CustomerDto dto = om.readValue(sb.toString(), CustomerDto.class);
		
		log.info("json 문자열을 변환한 dto(자바객체) : {} ", dto);
		
		// dao insert 처리
		int result = 0;
		try {
			MybatisCustomerDao dao = new MybatisCustomerDao();
			result = dao.insert(dto);
		} catch (Exception e) {
			log.info("insert 예외 : {}", e.getMessage());
		}
		
		// 방법2 : json 문자열로 result 결과값 보내기
		String jsonData = "{ \"result\" : "+result+" }";
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jsonData);
		
		
		// 방법1 : 응답으로 순수한 문자열을 보내기
//		String message = "회원 등록이 완료되었습니다.";
//		if(result == 0) message = "회원 등록에 실패하였습니다. id 중복확인하세요.";
//		
//		response.setContentType("text/plain; charset=UTF-8");
//		response.getWriter().print(message);
		
		log.info("[회원] 반영 개수 : {} ", result);
		

	}

}
