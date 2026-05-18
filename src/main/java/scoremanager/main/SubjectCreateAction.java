package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        req.setAttribute("cd", "");
        req.setAttribute("name", "");

        req.getRequestDispatcher("subject_create.jsp").forward(req, res);
    }
}