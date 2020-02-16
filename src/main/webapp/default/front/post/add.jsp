<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp" %>
<div class="row">
    <div class="col-md-9">
        <div class="panel panel-default">
            <div class="panel-heading"><a href="/">首页</a> / 发布帖子</div>
            <div class="panel-body">
                <form id="form">

                    <%--标题--%>
                    <div class="form-group">
                        <label for="title">标题</label>
                        <input type="text" class="form-control" id="title" name="title"
                               placeholder="请输入帖子标题，如果标题能够表达完整内容，则正文可以为空">
                    </div>
                    
                    <%--链接--%>
                    <div class="form-group">
                        <label for="url">链接</label>
                        <input type="text" class="form-control" id="url" name="url"
                               placeholder="如果是转载的帖子，请务必填上原文的链接">
                    </div>

                    <%--正文（富文本编辑器）--%>
                    <div class="form-group" id="wangEditor">
                        <label for="content">内容&nbsp;
                            <a href="javascript:void(0);" onclick="switchEditor(1)" style="color: #66afe9">
                                <small>Markdown编辑器</small>
                            </a>
                        </label>
                        <div id="wangEditor-content" style="margin-bottom: 10px;"></div>
                    </div>

                    <%--正文（Markdown编辑器）--%>
                    <div class="form-group" id="codemirror" style="display: none;">
                        <label for="content">内容&nbsp;
                            <a href="javascript:void(0);" onclick="switchEditor(0)" style="color: #66afe9">
                                <small>富文本编辑器</small>
                            </a>
                        </label>
                        <textarea name="content" id="codemirror-content" class="form-control" placeholder="内容，支持Markdown语法"></textarea>
                    </div>
                    <%--节点--%>
                    <c:if test="${nodeVOPage != null && fn:length(nodeVOPage.list) > 0}">
                    	<div class="form-group">
                           <label for="node">节点</label>
                           <select id="node" class="form-control" name="node">
                               <c:forEach var="nodeVO" items="${nodeVOPage.list}" varStatus="status">
                                   <option value="${nodeVO.nodeId}">${nodeVO.nodeName}</option>
                               </c:forEach>
                           </select>
                        </div>
                    </c:if>
                    <%--标签--%>
                    <!-- <div class="form-group">
                        <div class="form-group">
                            <label for="title">标签</label>
                            <input type="text" class="form-control" id="tag" name="title"
                                   placeholder="请为你的主题选择一个标签。恰当的归类会让你发布的信息更加有用">
                        </div>
                    </div> -->

                    <button type="button" id="wangEditor-btn" class="btn btn-primary">发布</button>
                    <button type="button" id="codemirror-btn" class="btn btn-primary" style="display: none;">发布</button>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-3 hidden-sm hidden-xs">
        <div class="panel panel-default">
            <div class="panel-heading">
                <b>帖子发布指南</b>
            </div>
            <div class="panel-body">
                <p>• 在标题中描述内容要点。如果一件事情在标题的长度内就已经可以说清楚，那就没有必要写正文了。</p>
                <p>• 保持对陌生人的友善。用知识去帮助别人。</p>
                <p>• 如果是转载的帖子，请务必只填上原文的URL，内容就不用复制过来了。</p>
                <p>• 请为你的主题选择一个节点。恰当的归类会让你发布的信息更加有用。</p>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading"><b>Markdown 语法参考</b></div>
            <div class="panel-body">
                <p># 标题</p>
                <p>- 列表</p>
                <p>> 引用</p>
                <p>**粗体**</p>
                <p>[显示文本](链接地址)</p>
                <p>![显示文本](图片链接地址)</p>
                <p>`System.out.println('行内代码')`</p>
                <p>```java 标记代码块 ```</p>


            </div>
        </div>
    </div>
</div>
<script src="/default/front/post/js/add.js" />
<%@ include file="../layout/footer.jsp" %>