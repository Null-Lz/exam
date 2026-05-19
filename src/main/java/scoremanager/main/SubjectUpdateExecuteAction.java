package scoremanager.main;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
		//ローカル変数の指定 1
		String cd ="";//科目コード
		String name="";//科目名
		Subject subject = new Subject();
		SubjectDao subjectDao = new SubjectDao();
		
		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");
		name = req.getParameter("name");
		
		//DBからデータ取得 3
		//なし
		
		//ビジネスロジック 4

		// subject に学生情報をセット
		subject.setCd(cd);
		subject.setName(name);
		subject.setSchool(teacher.getSchool());
				
		//変更内容を保存
		subjectDao.save(subject);
		
		
		//レスポンス値をセット 6
		//なし
		
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
	}
}
