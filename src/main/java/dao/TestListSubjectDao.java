package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql = "select * from test_list_subject where school_cd = ?";

    /**
     * postFilterメソッド
     * ResultSet から TestListSubject のリストを作成する
     *
     * @param rSet:ResultSet
     * @return List<TestListSubject>
     * @throws Exception
     */
    public List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

        List<TestListSubject> list = new ArrayList<>();

        while (rSet.next()) {
            TestListSubject tls = new TestListSubject();

            tls.setEntYear(rSet.getInt("ent_year"));
            tls.setClassNum(rSet.getString("class_num"));
            tls.setSubjectCd(rSet.getString("subject_cd"));
            tls.setNum(rSet.getInt("num"));

            list.add(tls);
        }

        return list;
    }

    /**
     * filterメソッド
     * 条件に一致するテスト科目一覧を取得する
     *
     * @param entYear:int
     * @param classNum:String
     * @param subject:Subject
     * @param school:School
     * @return List<TestListSubject>
     * @throws Exception
     */
    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

        List<TestListSubject> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            String sql = baseSql
                    + " and ent_year = ? and class_num = ? and subject_cd = ? order by num";

            statement = connection.prepareStatement(sql);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subject.getCd());

            ResultSet rSet = statement.executeQuery();

            list = postFilter(rSet);

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
}
