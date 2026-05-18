package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");

		// ローカル変数の宣言
		String entYear = "";
		String classNum = "";
		String subjectCd = "";

		List<Test> testList = null;

		TestDao testDao = new TestDao();

		Map<String, String> errors = new HashMap<>();

		// リクエストパラメータ取得
		entYear = req.getParameter("entYear");
		classNum = req.getParameter("classNum");
		subjectCd = req.getParameter("subjectCd");

		// ビジネスロジック
		if (entYear == null || entYear.isEmpty()
			|| classNum == null || classNum.isEmpty()
			|| subjectCd == null || subjectCd.isEmpty()) {

			errors.put("subject", "入学年度とクラスと科目を選択してください");

		} else {

			testList =
				testDao.filter(
					entYear,
					classNum,
					subjectCd,
					teacher.getSchool()
				);

			if (testList == null || testList.size() == 0) {

				errors.put("subject", "成績情報が存在しませんでした");
			}
		}
		
		ClassNumDao cNumDao = new ClassNumDao();
		SubjectDao sDao = new SubjectDao();

		List<Integer> entYearSet = new ArrayList<>();

		for (int i = 2020; i <= 2025; i++) {
			entYearSet.add(i);
		}

		req.setAttribute("entYearSet", entYearSet);

		req.setAttribute(
			"classNumSet",
			cNumDao.filter(teacher.getSchool())
		);

		req.setAttribute(
			"subjectList",
			sDao.filter(teacher.getSchool())
		);

		// レスポンス値をセット
		req.setAttribute("errors", errors);

		req.setAttribute("testList", testList);

		// JSPへフォワード
		req.getRequestDispatcher("/scoremanager/main/test_list.jsp")
			.forward(req, res);
	}
}