package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.Student;
import bean.Test;

public class TestDao extends Dao {

    private String baseSql = "select * from test where school_cd = ?";

    /**
     * getメソッド
     * 指定された条件のテストを1件取得
     */
    public Test get(Student student, Subject subject, School school, int no) throws Exception {

        Test test = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = baseSql + " and student_no = ? and subject_cd = ? and no = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            statement.setString(2, student.getNo());
            statement.setString(3, subject.getCd());
            statement.setInt(4, no);

            ResultSet rSet = statement.executeQuery();

            if (rSet.next()) {
                test = new Test();
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("point"));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return test;
    }

    /**
     * postFilterメソッド
     * ResultSet から Test のリストを作成
     */
    public List<Test> postFilter(ResultSet rSet, School school) throws Exception {

        List<Test> list = new ArrayList<>();

        StudentDao sDao = new StudentDao();
        SubjectDao subDao = new SubjectDao();

        while (rSet.next()) {
            Test test = new Test();
            test.setSchool(school);
            test.setNo(rSet.getInt("no"));
            test.setPoint(rSet.getInt("point"));

            test.setStudent(sDao.get(rSet.getString("student_no"), school));
            test.setSubject(subDao.get(rSet.getString("subject_cd")));

            list.add(test);
        }

        return list;
    }

    /**
     * filterメソッド
     * 条件に一致するテスト一覧を取得
     */
    public List<Test> filter(int year, String classNum, Subject subject, int num, School school) throws Exception {

        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = baseSql
                    + " and year = ? and class_num = ? and subject_cd = ? and no = ? order by student_no";

            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            statement.setInt(2, year);
            statement.setString(3, classNum);
            statement.setString(4, subject.getCd());
            statement.setInt(5, num);

            ResultSet rSet = statement.executeQuery();

            list = postFilter(rSet, school);

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return list;
    }

    /**
     * saveメソッド（複数登録）
     */
    public boolean save(List<Test> list) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "insert into test(school_cd, student_no, subject_cd, no, point) values(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            for (Test test : list) {
                statement.setString(1, test.getSchool().getCd());
                statement.setString(2, test.getStudent().getNo());
                statement.setString(3, test.getSubject().getCd());
                statement.setInt(4, test.getNo());
                statement.setInt(5, test.getPoint());
                count += statement.executeUpdate();
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return count == list.size();
    }

    /**
     * saveメソッド（1件登録・外部コネクション使用）
     */
    public boolean save(Test test, Connection connection) throws Exception {

        PreparedStatement statement = null;
        int count = 0;

        try {
            String sql = "insert into test(school_cd, student_no, subject_cd, no, point) values(?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            statement.setString(1, test.getSchool().getCd());
            statement.setString(2, test.getStudent().getNo());
            statement.setString(3, test.getSubject().getCd());
            statement.setInt(4, test.getNo());
            statement.setInt(5, test.getPoint());

            count = statement.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException sqle) { throw sqle; }
            }
        }

        return count > 0;
    }
}
