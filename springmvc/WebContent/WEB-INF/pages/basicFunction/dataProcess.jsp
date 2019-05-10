<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="resources/plugins/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="resources/css/global.css" media="all">
<link rel="stylesheet" href="resources/plugins/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/table.css" />
</head>
<body>
<% 
session.invalidate(); 
%>
  <div class="admin-main">
    <fieldset class="layui-elem-field layui-field-title">
      <legend>导入CSV</legend>
    </fieldset>
    <blockquote class="layui-elem-quote" id="firstStep">
      <input type="file" id="file">
      <span id="callback"></span>
      <div id="path" style="display: none;"></div>
    </blockquote>
    <blockquote class="layui-elem-quote layui-quote-nm" id="secondStep">
      <a href="#" class="layui-btn layui-btn-small" id="readCsv">
        <i class="layui-icon">&#xe608;</i>
        解析CSV
      </a>
      <a href="#" class="layui-btn layui-btn-small" id="getSelected">
        <i class="fa fa-shopping-cart" aria-hidden="true"></i>
        导出CSV
      </a>
    </blockquote>
    <div style="margin: 15px; display: none;" id="condition">
      <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>解析CSV条件</legend>
      </fieldset>
      <form class="layui-form" action="">
        <div class="layui-form-item">
          <label class="layui-form-label">解析格式</label>
          <div class="layui-input-inline">
            <select id="formatType">
              <option value="0">请选择解析格式</option>
              <option value="1">T010</option>
              <option value="2">T020</option>
            </select>
          </div>
          <div class="layui-input-inline">
            <button class="layui-btn" lay-submit="" lay-filter="sub">确定</button>
          </div>
        </div>
      </form>
    </div>

    <iframe src="readTable?path=dataProcessTable"  frameborder=0 width="100%" name="ifrmname" id="FrameID" height="400px"></iframe> 
    
    <div class="admin-table-page">
      <div id="paged" class="page"></div>
    </div>
  </div>
  <script type="text/javascript" src="resources/plugins/layui/layui.js"></script>
  <script type="text/javascript" src="resources/js/basicFunction/dataProcess.js"></script>
  <script>
      //首先监听input框的变动，选中一个新的文件会触发change事件
      document.querySelector("#file").addEventListener("change", function() {
        document.querySelector("#callback").innerText = "";
        document.querySelector("#path").innerText = "";
        document.getElementById('FrameID').contentWindow.location.reload(true);
        document.getElementById("firstStep").classList.remove("layui-quote-nm");
        document.getElementById("secondStep").classList.add("layui-quote-nm");
        //获取到选中的文件
        var file = document.querySelector("#file").files[0];
        //创建formdata对象
        var formdata = new FormData();
        formdata.append("file", file);
        //创建xhr，使用ajax进行文件上传
        var xhr = new XMLHttpRequest();
        xhr.open("post", "upload");
        xhr.send(formdata);
        //回调
        xhr.onreadystatechange = function() {
          if (xhr.readyState == 4 && xhr.status == 200) {
            var json = eval("(" + xhr.responseText + ")");
            console.log("result:" + json.result);
            document.getElementById('FrameID').contentWindow.location.reload(true);
            if (json.result) {
              document.getElementById("callback").innerHTML = "\u4e0a\u4f20\u6210\u529f\uff01"; // 上传成功！
              document.getElementById("callback").style.color='#000000';
              document.getElementById("path").innerHTML = json.path;
              document.getElementById("firstStep").classList.add("layui-quote-nm");
              document.getElementById("secondStep").classList.remove("layui-quote-nm");
            } else {
              if(json.tips==1){
                document.getElementById("callback").innerText = "\u4e0a\u4f20\u5931\u8d25\uff01"; // 上传失败！
                document.getElementById("callback").style.color='#ff0000';
              }else if(json.tips==2){
                document.getElementById("callback").innerText = "\u8bf7\u4e0a\u4f20\u6587\u4ef6\uff01"; // 请上传文件！
                document.getElementById("callback").style.color='#ff0000';
              }else if(json.tips==3){
                document.getElementById("callback").innerText = "\u8bf7\u4e0a\u4f20\u0063\u0073\u0076\u6587\u4ef6\uff01"; // 请上传csv文件！
                document.getElementById("callback").style.color='#ff0000';
              }
              
            }
          }
        }
      });
    </script>
</body>
</html>