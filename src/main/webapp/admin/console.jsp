<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="Roothub" uri="/WEB-INF/tld/roothub.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="./common/contextPath.jsp" %>
<Roothub:layout useTopNav="true">
    <Roothub:header/>
    <div class="content-wrapper" style="margin: 0px;">
        <section class="content">
            <div class="row">
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-aqua">
                        <div class="inner">
                            <h3>${topic_count}</h3>

                            <p>今天新增话题数</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-ios-list-outline"></i>
                        </div>
                        <a href="/admin/topic/list" class="small-box-footer">更多 <i
                                class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-green">
                        <div class="inner">
                            <h3>${node_count}</h3>

                            <p>今天新增节点数</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-pricetags"></i>
                        </div>
                        <a href="/admin/node/list" class="small-box-footer">更多 <i
                                class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-yellow">
                        <div class="inner">
                            <h3>${comment_count}</h3>
                            <p>今天新增评论数</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-chatboxes"></i>
                        </div>
                        <a href="/admin/comment/list" class="small-box-footer">更多 <i
                                class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
                <div class="col-lg-3 col-xs-6">
                    <!-- small box -->
                    <div class="small-box bg-red">
                        <div class="inner">
                            <h3>${user_count}</h3>

                            <p>今天新增用户数</p>
                        </div>
                        <div class="icon">
                            <i class="ion ion-person-add"></i>
                        </div>
                        <a href="/admin/user/list" class="small-box-footer">更多 <i
                                class="fa fa-arrow-circle-right"></i></a>
                    </div>
                </div>
                <!-- ./col -->
            </div>
        </section>
    </div>
    <Roothub:footer/>
</Roothub:layout>