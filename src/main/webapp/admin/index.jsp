<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="Roothub" uri="/WEB-INF/tld/roothub.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/contextPath.jsp" %>

<Roothub:simpleLayout title="Roothub 后台管理">
	<div class="container">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<h1>
				Top Navigation
				<small>Example 2.0</small>
			</h1>
			<ol class="breadcrumb">
				<li><a href="https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#"><i class="fa fa-dashboard"></i> Home</a></li>
				<li><a href="https://adminlte.io/themes/AdminLTE/pages/layout/top-nav.html#">Layout</a></li>
				<li class="active">Top Navigation</li>
			</ol>
		</section>

		<!-- Main content -->
		<section class="content">

            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>150</h3>

                            <p>统一门户</p>
                        </div>
                        <div class="icon">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>53<sup style="font-size: 20px">%</sup></h3>

                            <p>协同办公</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <h3>44</h3>

                            <p>知识管理</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-person-add"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>65</h3>

                            <p>权限管理</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-pie-graph"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
            </div>

            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>认证中心</h3>

                        </div>
                        <div class="icon">
                            <i class="fa fa-shopping-cart"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>授权中心</h3>

                        </div>
                        <div class="icon">
                            <i class="ion ion-stats-bars"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <h3>组织架构</h3>

                        </div>
                        <div class="icon">
                            <i class="ion ion-person-add"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>新闻管理</h3>

                            <p>发布新闻、删除新闻</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-pie-graph"></i>
                        </div>
                        <a href="#" class="small-box-footer">
                            More info <i class="fa fa-arrow-circle-right"></i>
                        </a>
                    </div>
                </div>
                <!-- ./col -->
            </div>

			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-info">
				<h4>Tip!</h4>

				<p>Add the layout-top-nav class to the body tag to get this layout. This feature can also be used with a
					sidebar! So use this class if you want to remove the custom dropdown menus from the navbar and use regular
					links instead.</p>
			</div>
			<div class="callout callout-danger">
				<h4>Warning!</h4>

				<p>The construction of this layout differs from the normal one. In other words, the HTML markup of the navbar
					and the content will slightly differ than that of the normal layout.</p>
			</div>
			<div class="box box-default">
				<div class="box-header with-border">
					<h3 class="box-title">Blank Box</h3>
				</div>
				<div class="box-body">
					The great content goes here
				</div>
				<!-- /.box-body -->
			</div>
			<!-- /.box -->
		</section>
		<!-- /.content -->
	</div>
</Roothub:simpleLayout>