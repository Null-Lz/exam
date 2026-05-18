package scoremanager.main;

import bean.School;
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
        School school = teacher.getSchool();

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        req.setAttribute("cd", cd);
        req.setAttribute("name", name);

        if (cd == null || cd.isEmpty() || name == null || name.isEmpty()) {
            req.setAttribute("error", "このフィールドを入力してください");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        if (cd.length() != 3) {
            req.setAttribute("error", "科目コードは3文字で入力してください");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        SubjectDao subjectDao = new SubjectDao();
        Subject subject = subjectDao.get(cd, school);

        if (subject != null) {
            req.setAttribute("error", "科目コードが重複しています");
            req.getRequestDispatcher("subject_create.jsp").forward(req, res);
            return;
        }

        Subject newSubject = new Subject();
        newSubject.setCd(cd);
        newSubject.setName(name);
        newSubject.setSchool(school);

        subjectDao.save(newSubject);

        req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
    }
}
