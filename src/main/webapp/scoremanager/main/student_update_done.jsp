<%-- 学生変更完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<div id="wrap_box">
			<h2>学生情報変更</h2>
			<div id="wrap_box">
				<p>変更が完了しました</p>
				<br>
				<br>
				<br>
				<a href="StudentList.action">学生一覧</a>
			</div>
		</div>
	</c:param>
</c:import>