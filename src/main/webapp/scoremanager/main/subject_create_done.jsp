<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section>

            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報登録
            </h2>

            <p class="alert alert-success">
                登録が完了しました
            </p>

            <p>
                <a href="SubjectCreate.action">戻る</a>
            </p>

            <p>
                <a href="SubjectList.action">科目一覧</a>
            </p>

        </section>
    </c:param>
</c:import>
