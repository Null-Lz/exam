<%-- 学生情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp" >
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section>
			<h2>科目情報変更</h2>
			<form action="SubjectUpdateExecute.action" method="get">
				<div>
					<label for="cd">科目コード</label><br>
					<input type="text" id="cd" value="${subject.cd }" name="cd" readonly />
				</div>
				<div>
					<label for="name">科目名</label><br>
					<input type="text" id="name" name="name" value="${name }" required maxlength="30" />
				</div>
				<div>
					<input type="submit" name="login" value="変更"/>
				</div>
			</form>
			<a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>