<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>学习互助系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- 引入 Bootstrap -->
<link href="/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="/resources/css/app.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="/resources/images/favicon.ico">
<!-- 引入layui.css -->
<link rel="stylesheet" href="/resources/layui/css/layui.css" media="all">
</head>
<body>
	<div class="wrapper">
		<jsp:include page="components/head.jsp"></jsp:include>
		<div class="row">
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">热门帖子</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${findHot != null}">
									<c:forEach var="item" items="${findHot}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">最新帖子</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${pageNew != null}">
									<c:forEach var="item" items="${pageNew}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>

		</div>
		<div class="row">
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">无人回复的帖子</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${findTodayNoReply != null}">
									<c:forEach var="item" items="${findTodayNoReply}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">热门板块</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${nodeList2 != null}">
									<c:forEach var="item" items="${nodeList2}">
          								<div class="col-md-6" style="margin-bottom: 10px; padding-left: 10px;">
            								<a href="${item.url}"><span class="label label-primary text-muted">${item.nodeTitle}</span></a>
            								<small class="excerpt text-muted" style="display: block; margin-top: 10px;"></small>
          								</div>
          							</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">学习动态</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${page1100 != null}">
									<c:forEach var="item" items="${page1100}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">教学动态</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${page1200 != null}">
									<c:forEach var="item" items="${page1200}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">招募信息</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${page1300 != null}">
									<c:forEach var="item" items="${page1300}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
			<div class="col-lg-6">
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">咨询信息</h3>

						<div class="box-tools pull-right">
							<button type="button" class="btn btn-box-tool"
								data-widget="collapse">
								<i class="fa fa-minus"></i>
							</button>
						</div>
					</div>
					<!-- /.box-header -->
					<div class="box-body">
						<div class="table-responsive">
							<table class="table" style="font-size: 14px;">
								<tbody>
								<c:if test="${page1400 != null}">
									<c:forEach var="item" items="${page1400}">
										<tr>
											<c:if test="${fn:length(item.avatar) > 0}">
												<td width="24" valign="middle" align="center">
													<img src="${item.avatar}" class="avatar img-circle"
													border="0" align="default"
													style="max-width: 24px; max-height: 24px;">
												<!-- </a> -->
												</td>
											</c:if>
											<td><c:choose>
													<c:when test="${item.url != null}">
														<a href="${item.url}">${item.title}</a>
													</c:when>
													<c:otherwise>
														<a href="/topic/${item.topicId}">${item.title}</a>
													</c:otherwise>
												</c:choose></td>
										</tr>
									</c:forEach>
									</c:if>
								</tbody>
							</table>
						</div>
						<!-- /.table-responsive -->
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<div id="back2Top" class="backTop___6Q-ki" style="display: none">
		<div class="line___F1WY0"></div>
		<div class="arrow___3UCwo"></div>
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