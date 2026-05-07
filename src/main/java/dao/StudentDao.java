package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class StudentDao extends Dao {
	public String baseSql = "select * from student where school_cd = ?";
	
	public Student get(String no) throws Exception {
		
		// 学校インスタンスを初期化
				Student student = new Student();
				// コネクションを確立
				Connection connection = getConnection();
				// プリペアードステートメント
				PreparedStatement statement = null;

				try {
					// プリペアードステートメントにSQL文をセット
					statement = connection.prepareStatement("select * from student where no=?");
					// プリペアードステートメントに教員IDをバインド
					statement.setString(1, no);
					// プリペアードステートメントを実行
					ResultSet resultSet = statement.executeQuery();
					
					SchoolDao schoolDao = new SchoolDao();

					if (resultSet.next()) {
						// リザルトセットが存在する場合
						// 学校インスタンスに検索結果をセット
						student.setNo(resultSet.getString("no"));
						student.setName(resultSet.getString("name"));
						student.setEntYear(resultSet.getInt("ent_year"));
						student.setClassNum(resultSet.getString("class_num"));
						student.setAttend(resultSet.getBoolean("is_attend"));
						//学生フィールドには学校コードで検索した学校インスタンスをセット
						student.setSchool(schoolDao.get(resultSet.getString("school_cd")));
					} else {
						// リザルトセットが存在しない場合
						// 学校インスタンスにnullをセット
						student = null;
					}
				} catch (Exception e) {
					throw e;
				} finally {
					// プリペアードステートメントを閉じる
					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
					// コネクションを閉じる
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}

				return student;
			}
	public List<Student> postFilter(ResultSet resultSet, School school)throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		try {
			// リザルトセットを全件走査
			while (resultSet.next()) {
				Student student = new Student();
				student.setNo(resultSet.getString("no"));
				student.setName(resultSet.getString("name"));
				student.setEntYear(resultSet.getInt("ent_year"));
				student.setClassNum(resultSet.getString("class_num"));
				student.setAttend(resultSet.getBoolean("is_attend"));
				student.setSchool(school);
				// リストにクラス番号を追加
				list.add(student);
					}
				} catch (SQLException | NullPointerException e) {
					e.printStackTrace();
				}
				return list;
	}
	public List<Student> filter(School school, int entYear, String classNum, boolean isAttend) throws Exception{
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String condition = "and ent_year = ? and class_num = ?";
		String order = " order by no asc";
		
		String conditionIsAttend = "";
		if(isAttend) {
			conditionIsAttend = "and is_attend = true";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			// プリペアードステートメントを実行
			resultSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(resultSet, school);
				} catch (Exception e) {
					throw e;
				} finally {
					// プリペアードステートメントを閉じる
					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
					// コネクションを閉じる
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}

				return list;
	}
	public List<Student> filter(School school, int entYear, boolean isAttend)throws Exception {
		// リストを初期化
		List<Student> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet resultSet = null;
		//SQL文の条件
		String condition = "and ent_year = ?";
		//SQL文のソート
		String order = " order by no asc";
		
		//SQL文の在学フラグ
		String conditionIsAttend = "";
		//在学フラグがtrueだった場合
		if(isAttend) {
			conditionIsAttend = "and is_attend = true";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection
					.prepareStatement(baseSql + condition + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			// プリペアードステートメントを実行
			resultSet = statement.executeQuery();
			
			list = postFilter(resultSet, school);
				} catch (Exception e) {
					throw e;
				} finally {
					// プリペアードステートメントを閉じる
					if (statement != null) {
						try {
							statement.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
					// コネクションを閉じる
					if (connection != null) {
						try {
							connection.close();
						} catch (SQLException sqle) {
							throw sqle;
						}
					}
				}

				return list;
	}
	public List<Student> filter(School school,boolean isAttend)throws Exception {
		List<Student> list = new ArrayList<>();
		// データベースへのコネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String order = " order by no asc";
		
		String conditionIsAttend = "";
		if (isAttend) {
			conditionIsAttend = "and is_attend = true";
		}

		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection
					.prepareStatement(baseSql + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			// プリペアードステートメントを実行
			resultSet = statement.executeQuery();
			list = postFilter(resultSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			// プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			// コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}
	public boolean save(Student student) throws Exception {
		
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		
		try {
			Student old = get(student.getNo());
			if (old == null) {
				//学生が存在しなかった場合
				//プリペアードステートメントにINSERT文をセット
				statement = connection.prepareStatement("insert into student(no, name,ent_year, class_num, is_attend, school_cd) values(?, ?, ?, ?, ?, ?)");
				//プリペアードテンドに値をバインド
				statement.setString(1,  student.getNo());
				statement.setString(2,  student.getName());
				statement.setInt(3,  student.getEntYear());
				statement.setString(4,  student.getClassNum());
				statement.setBoolean(5,  student.isAttend());
				statement.setString(6,  student.getSchool().getCd());
			} else {
				//学生が存在した場合
				//プリペアードステートメントにUPDATE文をセット
				statement = connection.prepareStatement("update student set name = ?, ent_year = ?, class_num = ?, is_attend = ? where no = ?");
				//プリペアードテンドに値をバインド
				statement.setString(1,  student.getName());
				statement.setInt(2,  student.getEntYear());
				statement.setString(3,  student.getClassNum());
				statement.setBoolean(4,  student.isAttend());
				statement.setString(5,  student.getNo());
			}
			
			//プリペアードステートメントを実行
			count = statement.executeUpdate();
			
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステーメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		
		if (count > 0) {
			//実行件数が1件以上ある場合
			return true;
		} else {
			//実行件数が0件の場合
			return false;
		}
	}
}
