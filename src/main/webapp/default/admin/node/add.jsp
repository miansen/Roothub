<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>    
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="../layout/header.jsp"%>
<!-- 内容主体区域 -->
<div class="content-wrapper" style="padding: 50px 0 40px;">
	<section class="content-header">
    <h1>
     节点
      <small>列表</small>
    </h1>
    <ol class="breadcrumb">
      <li><a href="/admin/index"><i class="fa fa-dashboard"></i> 首页</a></li>
      <li><a href="/admin/node/list"> 节点</a></li>
      <li class="active">添加</li>
    </ol>
  </section>
  <section class="content">
    <div class="box box-info">
      <div class="box-header with-border">
        <h3 class="box-title">节点编辑</h3>
      </div>
      <!-- /.box-header -->
      <div class="box-body">
        <div class="row">
          <div class="col-sm-6">
        <form id="form" action="/admin/node/add" method="post" enctype="multipart/form-data">
          <div class="form-group">
            <label>父节点</label>
            <div class="input-group">
            <input type="text" name="parentNodeCode" id="parentNodeCode" class="form-control node-parentNodeCode">
            <span class="input-group-btn">
              <button type="button" onclick="openParentNode(this)" class="btn btn-primary">选择</button>
            </span>
            </div>
          </div>
          <div class="form-group">
            <label>名称</label>
            <input type="text" name="nodeTitle" id="nodeTitle" class="form-control node-name">
          </div>
          <div class="form-group">
            <label>图标</label>
            <input type="hidden" name="avatarNormal" class="avatarNormal">
            <input type="file" name="file" id="icon-upload"><br>
            <a href="" target="_blank" id="icon-href"><img src="" width="50" alt="" id="icon-img"></a>
          </div>
          <div class="form-group">
            <label>背景图</label>
            <input type="hidden" name="avatarLarge" class="avatarLarge">
            <input type="file" name="file" id="background-upload"><br>
            <a href="" target="_blank" id="background-href"><img src="" width="50" alt="" id="background-img"></a>
          </div>
          <div class="form-group">
            <label>描述</label>
            <textarea name="nodeDesc" rows="7" class="form-control node-desc"></textarea>
          </div>
          <button type="submit" id="btn" class="btn btn-primary">提交</button>
        </form>
          </div>
        </div>
      </div>
    </div>
  </section>
  <script src="/default/admin/node/js/openParentNode.js"></script>
  <script type="text/javascript">

  	$(function(){

  		$(".sidebar-menu li:eq(4)").addClass("active");

  		layui.use('upload', function () {
  	      var upload = layui.upload;
  	      // 上传图标
  	      upload.render({
	            elem: '#icon-upload', //绑定元素
	            url: '/common/upload?customPath=node', //上传接口
	            done: function (res) {
	            	//请求成功回调
	                console.log(res)
	                console.log(res.data)
	                console.log(res.data[0])
	                $(".avatarNormal").val(res.data[0]);
	                $("#icon-href").attr("href",res.data[0]);
	                $("#icon-img").attr("src",res.data[0]);
	            }, 
	            error: function () {
	                //请求异常回调
	            }
	        });

  	      	// 上传背景图
		    upload.render({
	          elem: '#background-upload', //绑定元素
	          url: '/common/upload?customPath=node', //上传接口
	          done: function (res) {
	          	  //请求成功回调
	              console.log(res)
	              console.log(res.data)
	              console.log(res.data[0])
	              $(".avatarLarge").val(res.data[0]);
	              $("#background-href").attr("href",res.data[0]);
	              $("#background-img").attr("src",res.data[0]);
	          }, 
	          error: function () {
	              //请求异常回调
	          }
	      });
  	    });

  		// 添加节点
  		$("#form").submit(function(){
  		    // 添加一个询问框，点击 "确定" 才走后面的代码
  			if(confirm("确定要添加此节点吗？")){

                var nodeTitle = $("#nodeTitle").val();

                if(!nodeTitle){
                    toast('节点名称不能为空');
                    return false;
                }

  			    // 定义一个 json 对象
                var formObject = {};

                /**
                 * 描述：form 表单序列化成 JSON 对象数组
                 * 作用：把表单里面的每个输入框都转换成一个 json 对象，然后把这些 json 对象放到一个数组里面
                 * 格式：
                 *      [
                 *        {"name":"nodeTitle","value":"java"},
                 *        {"name":"nodeDes","value":"java语言"}
                 *      ]
                 * @type {*|jQuery}
                 */
                var formArray =$("#form").serializeArray();

                /**
                 * 描述：把 json 数组转化成 json 对象
                 * 作用：把 formArray 里面的每个 json 对象的 name 作为 formObject 的键，value 作为值。
                 * 格式：
                 *      {
                 *        nodeTitle: "java",
                 *        nodeDes: "java语言"
                 *      }
                 */
                $.each(formArray,function(i,item){
                    formObject[item.name] = item.value;

                });

                /**
                 * 作用：把 json 对象转化成 json 字符串
                 * 格式：
                 *      {
                 *        "nodeTitle": "java",
                 *        "nodeDes": "java语言"
                 *      }
                 */
                var formJson = JSON.stringify(formObject);

                // var data = JSON.stringify($("#form").serializeArray());

                // console.log("formObject："+formObject);
                // console.log("formArray："+formArray);
                // console.log("formObject："+formObject);
                // console.log("formJson："+formJson);
                // console.log("data："+data);

                $.ajax({
  					url: "/admin/node/add",
                    contentType:"application/json; charset=utf-8",
  					type: "post",
  					dataType: "json",
  					data:formJson,
  					success: function(data){
  						if(data.success == true){
  							toast('添加成功','success');
  	  						setTimeout(function(){
  	  							window.location.href = "/admin/node/list";
  	  						},1000);
  						}else{
  							toast(data.error,'error');
  						}
  					},
  					error: function(data){
  						
  					}
  				});
  			}
            // ajax 代码块后面要加入 "return false;",否则表单会提交到后台，ajax 不生效。
  			return false;
  		});
  	});
  </script>
</div>
<%@ include file="../layout/footer.jsp"%>