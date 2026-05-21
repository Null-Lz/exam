package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        Subject subject = new Subject();
        SubjectDao subjectDao = new SubjectDao();
        Map<String, String> errors = new HashMap<>();

        // 入力チェック
        if (cd == null || cd.isEmpty()) {
            errors.put("1", "科目コードを入力してください");
        } else if (subjectDao.get(cd, teacher.getSchool()) != null) {   // ← 修正ポイント
            errors.put("1", "科目コードが重複しています");
        }

        if (name == null || name.isEmpty()) {
            errors.put("2", "科目名を入力してください");
        }

        // エラーなし → 登録
        if (errors.isEmpty()) {
            subject.setCd(cd);
            subject.setName(name);
            subject.setSchool(teacher.getSchool());

            subjectDao.save(subject);

            req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
            return;
        }

        // エラーあり → 入力画面へ戻す
        req.setAttribute("errors", errors);
        req.setAttribute("cd", cd);
        req.setAttribute("name", name);

        req.getRequestDispatcher("SubjectCreate.action").forward(req, res);
    }
}
