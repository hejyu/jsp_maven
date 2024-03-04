package day7.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import day4.mybatis.dao.MybatisProductDao;
import day4.mybatis.dto.CateDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductsController implements Controller {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//카데고리 목록 보내 주기 
		MybatisProductDao dao = new MybatisProductDao();
		List<CateDto> cateList = dao.getCategories();
		
		request.setAttribute("cateList", cateList);
		log.info("카테고리 목록 : {} ", cateList);
		
		RequestDispatcher rd = request.getRequestDispatcher("products.jsp");
		rd.forward(request, response);
	}

}
