<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/contextPath.jsp" %>

<%-- <common:adminIndex>
	<common:sidebar />
</common:adminIndex> --%>

<common:layout>
	<common:header />
	<common:sidebar />
	<common:content>
		<iframe name="roothub-iframe" src="${contextPath}/admin/console"" width="100%" height="100%" frameborder="no" border="0" />
	</common:content>
	<common:footer />
</common:layout>