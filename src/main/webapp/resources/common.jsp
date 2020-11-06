<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:url value="/resources/css/bootstrap.min.css" var="bootstrapCSS"></c:url>
<c:url value="/resources/js/bootstrap.min.js" var="bootstrapJS"></c:url>
<c:url value="/resources/js/jquery.min.js" var="jqueryJS"></c:url>

<link rel="stylesheet" href="${bootstrapCSS }" />
<script type="text/javascript" src="${jqueryJS }"></script>
<script type="text/javascript" src="${bootstrapJS }"></script>