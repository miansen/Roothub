<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="Roothub" uri="/WEB-INF/tld/roothub.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/contextPath.jsp" %>

<Roothub:layout>
	<Roothub:header />
	<Roothub:sidebar />
	<Roothub:content>
		<iframe name="roothub-iframe" id="roothub-iframe" src="${contextPath}/admin/console" style="overflow:visible;" scrolling="auto" onload="iframeLoad()" width="100%" height="100%" frameborder="no" border="0" ></iframe>
		<script type="text/javascript">
			function iframeLoad() {
				$("#roothub-iframe").height(window.innerHeight);
		    }
		</script>
	</Roothub:content>
	<Roothub:footer />
</Roothub:layout>