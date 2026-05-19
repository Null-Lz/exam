package scoremanager.main;

//import javax.security.auth.Subject;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		String cd ="";//科目コード
		//String name="";//科目名
		Subject subject = new Subject();
		SubjectDao subjectDao = new SubjectDao();
		
				
		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");
		
		//DBからデータ取得 1
		//学生の詳細データを取得
		subject = subjectDao.get(cd,teacher.getSchool());
		
		
		//ビジネスロジック 4
		//name = subject.getName();
		
		//レスポンス値をセット 6
		//リクエストに科目コードをセット
		req.setAttribute("subject",subject);
		//JSPへフォワード 7
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}
