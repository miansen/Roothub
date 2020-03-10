<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="common" uri="/WEB-INF/tld/common.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/contextPath.jsp" %>
<common:adminLayout>
<div class="content-wrapper" style="margin: 0px;">
    <section class="content">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">父节点列表</h3>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <form action="${contextPath}/admin/sidebar/list/parent" method="get" class="form-inline">
                    <div class="form-group" style="margin-bottom: 10px;">
                        <input type="text" name="sidebarName" value="${sidebarName}" class="form-control" placeholder="权限名" style="display: inline-block;width: 50%;">
                        <button type="submit" class="btn btn-primary btn-sm">搜索</button>
                    </div>
                </form>
                <c:choose>
                    	<c:when test="${page != null && fn:length(page.list) > 0}">
                    		<table class="table table-bordered table-hover">
                    			<thead>
                    				<tr>
                        				<th>侧边栏ID</th>
                        				<th>侧边栏名称</th>
                        				<th>选择</th>
                    				</tr>
                    			</thead>
                    		<tbody>
                    		<c:forEach var="sidebarVO" items="${page.list}">
                    			<tr>
                            		<td>${sidebarVO.sidebarId}</td>
                            		<td>${sidebarVO.sidebarName}</td>
                            		<td><button class="btn btn-primary btn-sm" type="button" onclick="toParent('${sidebarVO.sidebarId}','${sidebarVO.sidebarName}')">选择</button></td>
                        		</tr>
                    		</c:forEach>
                    		</tbody>
                		</table>
                		<common:paginate page="${page}" url="${contextPath}/admin/sidebar/list/parent" />
                	 </c:when>
                     <c:otherwise>
                        <div class="empty-data">
                        	<img src="${contextPath}/admin/resource/images/no-data.jpg" style="width: 100%;">
                        </div>
                     </c:otherwise>
               </c:choose>
            </div>
        </div>
    </section>
    <script type="text/javascript">
        function toParent(id, name) {
            parent.$('#parentSidebarId').val(id);
            parent.$('#parentSidebarName').val(name);
            parent.bootbox.hideAll();
        }
    </script>
</div>
</common:adminLayout>