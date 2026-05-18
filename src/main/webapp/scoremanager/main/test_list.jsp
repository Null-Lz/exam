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

			<c:if test="${empty testList}">
				<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
					成績参照
				</h2>
			</c:if>

			<c:if test="${not empty testList}">
				<h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
					成績一覧（学生）
				</h2>
			</c:if>


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
						
						<c:if test="${not empty errors.get('subject')}">
							<div class="mt-2 text-warning">
								${errors.get("subject")}
							</div>
						</c:if>

					</div>

				</div>


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
						
						<c:if test="${not empty errors.get('student')}">
							<div class="mx-3 mt-2 text-warning">
								${errors.get("student")}
								
							</div>
						</c:if>

					</div>

				</div>

			</div>


			<c:if test="${not empty testList}">

				<div class="mt-4">

					<div class="mb-2">
						氏名：${studentName} (${studentNo})
					</div>

					<table class="table table-hover">

						<tr>
							<th>入学年度</th>
							<th>クラス</th>
							<th>学生番号</th>
							<th>氏名</th>
							<th>1回</th>
							<th>2回</th>
						</tr>

						<c:forEach var="test" items="${testList}">

							<tr>
								<td>${test.student.entYear}</td>
								<td>${test.student.classNum}</td>
								<td>${test.student.no}</td>
								<td>${test.student.name}</td>
							
								<td>
									<c:if test="${test.no == 1}">
										${test.point}
									</c:if>
								</td>
							
								<td>
									<c:if test="${test.no == 2}">
										${test.point}
									</c:if>
								</td>
							</tr>

						</c:forEach>

					</table>

				</div>

			</c:if>


			<c:if test="${empty testList and empty studentNo}">
				<div class="mx-3 mt-3 text-info">
					科目情報を選択または学生情報を入力して検索ボタンをクリックしてください
				</div>
			</c:if>

		</section>

	</c:param>

</c:import>