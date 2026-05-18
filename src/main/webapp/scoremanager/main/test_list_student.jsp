<%-- 成績参照JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">

	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">

		<section class="me-4">

			<c:choose>

				<c:when test="${not empty testList}">
					<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
						成績一覧（学生）
					</h2>
				</c:when>

				<c:otherwise>
					<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
						成績参照
					</h2>
				</c:otherwise>

			</c:choose>

			<div class="border rounded px-2 py-3">

				<div class="row align-items-center mb-4">

					<div class="col-2 fw-bold text-secondary">
						科目情報
					</div>

					<div class="col-10">

						<form action="TestListSubjectExecute.action" method="post">

							<div class="row align-items-end">

								<div class="col-2">
									<label class="form-label">入学年度</label>

									<select class="form-select" name="entYear">
										<option value="">--------</option>

										<c:forEach var="year" items="${entYearSet}">
											<option value="${year}">
												${year}
											</option>
										</c:forEach>

									</select>
								</div>

								<div class="col-2">
									<label class="form-label">クラス</label>

									<select class="form-select" name="classNum">
										<option value="">--------</option>

										<c:forEach var="num" items="${classNumSet}">
											<option value="${num}">
												${num}
											</option>
										</c:forEach>

									</select>
								</div>

								<div class="col-3">
									<label class="form-label">科目</label>

									<select class="form-select" name="subjectCd">
										<option value="">--------</option>

										<c:forEach var="subject" items="${subjectList}">
											<option value="${subject.cd}">
												${subject.name}
											</option>
										</c:forEach>

									</select>
								</div>

								<div class="col-2">
									<button class="btn btn-secondary">
										検索
									</button>
								</div>

							</div>

						</form>

					</div>

				</div>

				<c:if test="${not empty errors}">
					<div class="mx-3 mt-2 text-warning">
						${errors.get("1")}
						${errors.get("massage")}
						
					</div>
				</c:if>

				<hr class="my-4">

				<div class="row align-items-center mb-3">

					<div class="col-2 fw-bold text-secondary">
						学生情報
					</div>

					<div class="col-10">

						<form action="TestListStudentExecute.action" method="post">

							<div class="row align-items-end">

								<div class="col-4">
									<label class="form-label">学生番号</label>

									<input
										type="text"
										name="studentNo"
										class="form-control"
										placeholder="学生番号を入力してください"
										value="${studentNo}">
								</div>

								<div class="col-2">
									<button class="btn btn-secondary">
										検索
									</button>
								</div>

							</div>

						</form>

					</div>

				</div>

			</div>

			<c:if test="${not empty testList}">

				<h3 class="h5 mt-4 mb-3">
					成績一覧（学生）
				</h3>

				<div class="mb-2">
					氏名：${studentName}（${studentNo}）
				</div>

				<table class="table table-hover">

					<tr>
						<th>科目名</th>
						<th>科目コード</th>
						<th>回数</th>
						<th>点数</th>
					</tr>

					<c:forEach var="test" items="${testList}">

						<tr>
							<td>${test.subject.name}</td>
							<td>${test.subject.cd}</td>
							<td>${test.no}</td>
							<td>${test.point}</td>
						</tr>

					</c:forEach>

				</table>

			</c:if>

			<c:if test="${empty testList and not empty studentNo}">
				<div class="mt-3 text-warning">
					成績情報が存在しませんでした。
				</div>
			</c:if>

			<c:if test="${empty testList and empty errors}">
				<div class="mx-3 text-info mt-3">
					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
				</div>
			</c:if>

		</section>

	</c:param>

</c:import>