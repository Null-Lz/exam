package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {

    public String baseSql = "select * from student where school_cd = ?";

    /**
     * get(no) — 学校コードなし版
     */
    public Student get(String no) throws Exception {

        Student student = new Student();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select * from student where no=?");
            statement.setString(1, no);

            ResultSet resultSet = statement.executeQuery();
            SchoolDao schoolDao = new SchoolDao();

            if (resultSet.next()) {
                student.setNo(resultSet.getString("no"));
                student.setName(resultSet.getString("name"));
                student.setEntYear(resultSet.getInt("ent_year"));
                student.setClassNum(resultSet.getString("class_num"));
                student.setAttend(resultSet.getBoolean("is_attend"));
                student.setSchool(schoolDao.get(resultSet.getString("school_cd")));
            } else {
                student = null;
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return student;
    }

    /**
     * ★★★ 追加：get(no, school) — TestDao が必要とする版 ★★★
     */
    public Student get(String no, School school) throws Exception {

        Student student = null;
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement(
                "select * from student where no = ? and school_cd = ?"
            );
            statement.setString(1, no);
            statement.setString(2, school.getCd());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                student = new Student();
                student.setNo(rs.getString("no"));
                student.setName(rs.getString("name"));
                student.setEntYear(rs.getInt("ent_year"));
                student.setClassNum(rs.getString("class_num"));
                student.setAttend(rs.getBoolean("is_attend"));
                student.setSchool(school);
            }

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return student;
    }

    /**
     * postFilter
     */
    public List<Student> postFilter(ResultSet resultSet, School school) throws Exception {

        List<Student> list = new ArrayList<>();

        while (resultSet.next()) {
            Student student = new Student();
            student.setNo(resultSet.getString("no"));
            student.setName(resultSet.getString("name"));
            student.setEntYear(resultSet.getInt("ent_year"));
            student.setClassNum(resultSet.getString("class_num"));
            student.setAttend(resultSet.getBoolean("is_attend"));
            student.setSchool(school);

            list.add(student);
        }

        return list;
    }

    /**
     * filter(entYear, classNum, isAttend)
     */
    public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String condition = " and ent_year = ? and class_num = ?";
        String conditionIsAttend = isAttend ? " and is_attend = true" : "";
        String order = " order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);

            ResultSet resultSet = statement.executeQuery();
            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    /**
     * filter(entYear)
     */
    public List<Student> filter(School school, int entYear, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String condition = " and ent_year = ?";
        String conditionIsAttend = isAttend ? " and is_attend = true" : "";
        String order = " order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);

            ResultSet resultSet = statement.executeQuery();
            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    /**
     * filter(all)
     */
    public List<Student> filter(School school, boolean isAttend) throws Exception {

        List<Student> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        String conditionIsAttend = isAttend ? " and is_attend = true" : "";
        String order = " order by no asc";

        try {
            statement = connection.prepareStatement(baseSql + conditionIsAttend + order);
            statement.setString(1, school.getCd());

            ResultSet resultSet = statement.executeQuery();
            list = postFilter(resultSet, school);

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return list;
    }

    /**
     * save
     */
    public boolean save(Student student) throws Exception {

        Connection connection = getConnection();
        PreparedStatement statement = null;
        int count = 0;

        try {
            Student old = get(student.getNo());

            if (old == null) {
                statement = connection.prepareStatement(
                    "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)"
                );
                statement.setString(1, student.getNo());
                statement.setString(2, student.getName());
                statement.setInt(3, student.getEntYear());
                statement.setString(4, student.getClassNum());
                statement.setBoolean(5, student.isAttend());
                statement.setString(6, student.getSchool().getCd());

            } else {
                statement = connection.prepareStatement(
                    "update student set name = ?, ent_year = ?, class_num = ?, is_attend = ? where no = ?"
                );
                statement.setString(1, student.getName());
                statement.setInt(2, student.getEntYear());
                statement.setString(3, student.getClassNum());
                statement.setBoolean(4, student.isAttend());
                statement.setString(5, student.getNo());
            }

            count = statement.executeUpdate();

        } finally {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return count > 0;
    }
}
