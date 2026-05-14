package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

private String baseSql = "select subject_cd,subject_num,num,point" + "from test "+ "where student_no=?";




private List<TestListStudent> postFilter(ResultSet rSet)
        throws Exception {

    List<TestListStudent> list =
            new ArrayList<>();

    while (rSet.next()) {

        TestListStudent t =
                new TestListStudent();

        t.setSubjectCd(
                rSet.getString("subject_cd"));

        t.setSubjectName(
                rSet.getString("subject_name"));

        t.setNum(
                rSet.getInt("num"));

        t.setPoint(
                rSet.getInt("point"));

        list.add(t);
    }

    return list;}

    
    public List<TestListStudent> filter(Student student)
            throws Exception {

        Connection connection = getConnection();

        PreparedStatement statement =
                connection.prepareStatement(baseSql);

        statement.setString(1, student.getNo());

        ResultSet rSet =
                statement.executeQuery();

        List<TestListStudent> list =
                postFilter(rSet);

        statement.close();
        connection.close();

        return list;
    }	
    

}