package day7.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet(urlPatterns = {"/"}, description = "모든 요청을 받는 서블릿")
public class FrontController extends HttpServlet{

	private static  final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(FrontController.class);
	
	// 서블릿이 톰캣에서 실행되기 시작할 때(메모리에 로드될 때) 자동으로 실행하는 메소드
	@Override
	public void init() throws ServletException {
		RequestControllerMapping.init();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();	// http request servlet path ex)
		String method = request.getMethod();	// http request method
		
		logger.info("service메소드 : url - {}, method - {}",url,method);
	
		RequestMap map = new RequestMap(url, method);
		logger.info("요청 key : {}", map);
		
		Controller controller = RequestControllerMapping.getController(map);

//		if(controller!=null) {		// RequestControllerMapping에 저장된메뉴
			logger.info("controller 객체 : {}", controller.getClass());
			controller.handle(request, response);
//		} else {
//			try {
//				throw new IllegalAccessException("잘못된 URL요청입니다.");
//			}catch (Exception e) {
//				
//			}
//		}
//		else {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.print("<script>");
//			out.print("alert('잘못된 url요청입니다');");
//			out.print("location.href='/jsp2_mvc/';");
//			out.print("</script>");
//		}
		
	}
		
			
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		String method = request.getMethod();
		
		logger.info("doGet메소드 : url - {}, method - {}",url,method);
		
	}
	
	
}

/*
	아래 if 문은 Map을 생성하여 저장하는 방법으로 대체하겠습니다
				ㄴ Map 의 Key는 url, methodd 로 구성된 객체,  Value는 Controller 
				
	if(url.equals("/member/join") && method.equals("GET")){
			controller = new MemberJoinController();
		}else if(url.equals("/member/modify") && method.equals("GET")) {
			controller = new MemberModifyController();
		}else if(url.equals("/product/list") && method.equals("GET")) {
			controller = new ProductListController();
		}else if(url.equals("/community/list") && method.equals("GET")) {
			controller = new CommunityListController();
		}else if(url.equals("/community/write") && method.equals("GET")) {
			controller = new CommunityWriteController();
		}else if(url.equals("/mypage") && method.equals("GET")) {
			controller = new MyPageController();
		}else if(url.equals("/cart") && method.equals("GET")) {
			controller = new Cartcontroller();
		}else if(url.equals("/login") && method.equals("GET")) {
			controller = new LoginController();
			
		}else if(url.equals("/member/save") && method.equals("POST")) {
			controller = new MemberSaveController();
		}
  
 */


