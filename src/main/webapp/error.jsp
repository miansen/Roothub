<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="Roothub" uri="/WEB-INF/tld/roothub.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./contextPath.jsp" %>

<Roothub:simpleLayout title="Roothub-出错了">
	<div class="content-wrapper" style="margin: 0px;">
		<section class="content">
			<div class="error-page container-fluid">
				<div class="error-content">
					<div class="panel panel-default" style="background-color: #ecf0f5;border: 0px;box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 0px;">
						<div class="panel-body">
							<h1 style="padding-bottom: .3em;font-size: 2.25em;line-height: 1.2;border-bottom: 1px solid #eee;margin-top: 1em;margin-bottom: 16px;font-weight: 700;">: (</h1>
							<h3 style="font-size: 1.5em;line-height: 1.43;margin-top: 1em;margin-bottom: 16px;font-weight: 700;">${errorCode}</h3>
							<p style="margin-top: 0;">${errorMsg}</p>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
</Roothub:simpleLayout>