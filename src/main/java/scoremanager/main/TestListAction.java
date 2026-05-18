package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

		//ローカル変数の宣言1
		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");
		ClassNumDao cNumDao = new ClassNumDao(); // クラス番号Dao
		SubjectDao subjectDao = new SubjectDao(); //科目Dao
		School teacherSchool = teacher.getSchool(); // テスト情報
		LocalDate todaysDate = LocalDate.now(); // LocalDateインスタンスを取得
		int year = todaysDate.getYear(); // 現在の年を取得

		//DBからデータ取得3
		List<String>cNumlist = cNumDao.filter(teacherSchool); //クラス情報
		List<Subject>list = subjectDao.filter(teacherSchool); //科目情報

		//ビジネスロジック4
		// リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		// 10年前から10年後まで年をリストに追加
		for (int i = year - 10; i < year + 11; i++) {
			entYearSet.add(i);
		}

		//レスポンス値をセット6
		//リクエストにクラス情報リストをセット
		req.setAttribute("cNumlist", cNumlist);
		//リクエストに科目情報リストをセット
		req.setAttribute("list", list);
		//リクエストに入学年度リストをセット
		req.setAttribute("entYearSet", entYearSet);

		//jspへのフォワード7
		req.getRequestDispatcher("test_list.jsp").forward(req , res);
	}

}
