package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();

		Teacher teacher = (Teacher) session.getAttribute("user");

		// DAO
		ClassNumDao classNumDao = new ClassNumDao();
		SubjectDao subjectDao = new SubjectDao();

		// クラス一覧取得
		List<String> class_Num =
				classNumDao.filter(teacher.getSchool());

		// 科目一覧取得
		List<Subject> subject =
				subjectDao.filter(teacher.getSchool());

		// 入学年度一覧作成
		List<Integer> entYearSet = new ArrayList<>();

		for (int i = 2020; i <= 2025; i++) {
			entYearSet.add(i);
		}

		// JSPへ値を渡す
		req.setAttribute("searched", true);

		req.setAttribute("classNumSet", class_Num);

		req.setAttribute("subjectList", subject);

		req.setAttribute("entYearSet", entYearSet);

		// JSPへフォワード
		req.getRequestDispatcher(
				"/scoremanager/main/test_list.jsp")
				.forward(req, res);
	}
}