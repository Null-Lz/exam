package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectListAction extends Action {

	@Override
	public void execute(HttpServletRequest req,HttpServletResponse res) throws Exception {
		
		
		HttpSession session = req.getSession();
		Teacher teacher = (Teacher) session.getAttribute("user");
		
		School school = teacher.getSchool();                                                                           
		SubjectDao subjectDao = new SubjectDao();
		
		
		List<Subject> subject_set = subjectDao.filter(school);
		
		req.setAttribute("subjects", subject_set);
		req.getRequestDispatcher("subject_list.jsp").forward(req,res);
		
              
		
		}                                                                                                                        
}                                                                                                                                
                                                                                                                                 
                                                                                                                                 