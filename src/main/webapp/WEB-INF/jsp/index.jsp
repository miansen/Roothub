<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Roothub-首页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="components/head.jsp" />
		<c:forEach var="entry" items="${map}" varStatus="status">
		<c:if test="${status.index % 3 == 0}">
		<div class="row">
		</c:if>
			<div class="col-md-4">
				<div class="box box-primary">
					<div class="box-header with-border">
						<h3 class="box-title">${entry.key}</h3>
						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
						</div>
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;margin-bottom: 0px;">
								<tbody>
									<c:if test="${entry.value != null}">
										<c:forEach var="item" items="${entry.value}">
											<tr>
												<c:if test="${fn:length(item.avatar) > 0}">
													<td width="24" valign="middle" align="center" style="border-top: 0px">
														<img src="${item.avatar}" class="avatar img-circle" border="0" align="default" style="max-width: 24px; max-height: 24px;">
													</td>
												</c:if>
												<td style="border-top: 0px">
													<c:choose>
														<c:when test="${item.url != null}">
															<a href="${item.url}">${item.title}</a>
														</c:when>
														<c:otherwise>
															<a href="/topic/${item.topicId}">${item.title}</a>
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${status.index % 3 == 2}">
			</div>
			</c:if>
		</c:forEach>				
</div>
<div id="back2Top" class="backTop___6Q-ki" style="display: none">
	<div class="line___F1WY0"></div>
	<div class="arrow___3UCwo"></div>
</div>
</div>
</div>
<jsp:include page="components/foot.jsp"></jsp:include>
<script src="/resources/js/jquery.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<script src="/resources/js/index.js"></script>
<script src="/resources/layui/layui.js"></script>
<script src="/resources/layui/layui-paginate.js"></script>
<!-- <script src="/resources/js/login_info.js"></script> -->
<script src="/resources/js/formatDate.js"></script>
<script type="text/javascript">
//var tab = "${tab}";//板块
var tab = "${tab}";//父板块
//var url = "/?tab="+tab+"&ptab="+ptab+"&"
//$(".pagination2").pagination("${page.pageNumber}","${page.totalPage}",10);
 var count = ${page.totalRow};//数据总量
 var limit = ${page.pageSize};//每页显示的条数
 var url = "?tab="+tab+"&p=";//url
 function page(){
     var page = location.search.match(/p=(\d+)/);  
     return page ? page[1] : 1;  
 }
 var p = page();//当前页数
 //console.log("p:"+p);
 //console.log(count);
 //console.log(url);
 paginate(count,limit,p,url);
</script>
</body>
</html>