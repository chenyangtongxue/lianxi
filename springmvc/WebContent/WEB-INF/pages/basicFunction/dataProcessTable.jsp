<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
  <div class="admin-main">
    <fieldset class="layui-elem-field">
      <legend>数据列表</legend>
      <div class="layui-field-box layui-form">
        <table class="layui-table admin-table">
          <thead>
            <tr>
              <th style="width: 30px;">
                <input type="checkbox" lay-filter="allselector" lay-skin="primary">
              </th>
              <c:forEach items="${sessionScope._LISTBHEADER_}" var="hl" varStatus="i">
                <th>${hl}</th>
              </c:forEach>
            </tr>
          </thead>
          <tbody id="content">
            <c:forEach items="${sessionScope._LISTBODY_}" var="bl" varStatus="j">
              <tr>
                <td style="width: 30px;">
                  <input type="checkbox" lay-skin="primary" class="${j.index}">
                </td>
                <c:forEach items="${bl}" var="tl" varStatus="k">
                  <td>${tl}</td>
                </c:forEach>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </fieldset>
  </div>
  <script type="text/javascript" src="resources/plugins/layui/layui.js"></script>
  <script>
      layui.config({
        base : 'resources/js/'
      });
      layui.use([ 'form' ], function() {
        var $ = layui.jquery, layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
        layer = layui.layer, //获取当前窗口的layer对象
        form = layui.form;

        //重新渲染复选框
        form.render('checkbox');
        form.on('checkbox(allselector)', function(data) {
          console.log("data", data);
          var ischeck = data.elem.checked;
          console.log("ischeck", ischeck);
          $('#content').children('tr').each(function() {
            var $that = $(this);
            //全选或反选
            $that.children('td').eq(0).children('input[type=checkbox]')[0].checked = ischeck;
            form.render('checkbox');
          });
        });

        
      });
      function say(){
        layui.use([ 'form' ], function() {
          var $ = layui.jquery, layerTips = parent.layer === undefined ? layui.layer : parent.layer, //获取父窗口的layer对象
          layer = layui.layer, //获取当前窗口的layer对象
          form = layui.form;
          var dataIdList=[];
          $('#content').children('tr').each(function() {
            var $that = $(this);
            var $cbx = $that.children('td').eq(0);
            var ischecked = $cbx.children('input[type=checkbox]')[0].checked;
            if (ischecked) {
              var n = $cbx.children('input[type=checkbox]')[0].className;
              console.log("dataIdList"+n);
              dataIdList.push(n);
            }
          });
          console.log("dataIdList" , dataIdList);
          if(dataIdList==null || dataIdList.length==0){
            parent.layer.alert('\u8bf7\u5148\u9009\u62e9\u6570\u636e\u518d\u5bfc\u51fa\uff01'); // 请先选择数据再导出!
          }else{
            window.location.href='http://localhost:8080/springmvc/writeCsv?dataIdList='+dataIdList.toString();
          }
          
          

        });
          

      }
    </script>
</body>

</html>