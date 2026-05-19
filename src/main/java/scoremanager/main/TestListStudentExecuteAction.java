package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Student;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		//ローカル変数の宣言 1
		
		StudentDao studentDao = new StudentDao();
		
		List<TestListStudent> testList = null;
		TestListStudentDao testListStudentDao = new TestListStudentDao();
		Map<String, String> errors = new HashMap<>();

		//リクエストパラメータ―の取得 2
		String student_no = req.getParameter("studentNo");
		Student student = studentDao.get(student_no.trim());
		
		//DBからデータ取得 3
		//なし
		
		//ビジネスロジック 4
		if (student_no == null || student_no.isEmpty()) {
			errors.put("student", "学生番号を入力してください");
			req.setAttribute("errors", errors);
		} else {
			testList = testListStudentDao.filter(student);

			if (testList == null || testList.size() == 0) {
				errors.put("student", "成績情報が存在しませんでした");
				req.setAttribute("errors", errors);
			}
		}
		
		//DBへデータ保存 5
		//なし

		//レスポンス値をセット 6
		if (testList != null && testList.size() > 0) {
			req.setAttribute("studentName",
				student.getName());
		}
		
		req.setAttribute("studentNo", student_no);
		req.setAttribute("testList", testList);
		
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

		//JSPへフォワード 7
		req.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(req, res);
	}
}