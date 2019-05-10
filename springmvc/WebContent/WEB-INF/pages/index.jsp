<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>练习项目</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="resources/plugins/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="resources/css/global.css" media="all">
    <link rel="stylesheet" href="resources/plugins/font-awesome/css/font-awesome.min.css">
</head>
<body>
    <div class="layui-layout layui-layout-admin" style="border-bottom: solid 5px #1aa094;">
      <div class="layui-header header header-demo">
        <div class="layui-main">
          <div class="admin-login-box">
            <a class="logo" style="left: 0;" href="index.html">
              <span style="font-size: 22px;">练习项目</span>
            </a>
            <div class="admin-side-toggle">
              <i class="fa fa-bars" aria-hidden="true" style="margin-top: 8px;"></i>
            </div>
            <div class="admin-side-full">
              <i class="fa fa-life-bouy" aria-hidden="true" style="margin-top: 8px;"></i>
            </div>
          </div>
          <ul class="layui-nav admin-header-item">
            <li class="layui-nav-item">
              <a href="javascript:;" class="admin-header-user">
                <img src="resources/img/0.jpg" />
                <span>user1</span>
              </a>
              <dl class="layui-nav-child">
                <dd>
                  <a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>
                </dd>
                <dd>
                  <a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
                </dd>
              </dl>
            </li>
          </ul>
          <ul class="layui-nav admin-header-item-mobile">
            <li class="layui-nav-item">
              <a href="javascript:;"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
            </li>
          </ul>
        </div>
      </div>
      <div class="layui-side layui-bg-black" id="admin-side">
        <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
      </div>
      <div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
        <div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
          <ul class="layui-tab-title">
            <li class="layui-this">
              <i class="fa fa-dashboard" aria-hidden="true"></i>
              <cite>控制面板</cite>
            </li>
          </ul>
          <div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
            <div class="layui-tab-item layui-show">
              <iframe src="main?path=main"></iframe>
            </div>
          </div>
        </div>
      </div>
      <div class="site-tree-mobile layui-hide">
        <i class="layui-icon">&#xe602;</i>
      </div>
      <div class="site-mobile-shade"></div>
      <script type="text/javascript" src="resources/plugins/layui/layui.js"></script>
      <script type="text/javascript" src="resources/datas/nav.js"></script>
      <script src="resources/js/index.js"></script>
    </div>
</body>
</html>