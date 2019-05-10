var tab;
layui.config({
  base : 'resources/js/',
  version : new Date().getTime()
}).use([ 'element', 'layer', 'navbar', 'tab' ], function() {
  var element = layui.element, $ = layui.jquery, layer = layui.layer, navbar = layui.navbar();
  tab = layui.tab({
    elem : '.admin-nav-card' // 设置选项卡容器
    ,
    // maxSetting: {
    // max: 5,
    // tipMsg: '只能开5个哇，不能再开了。真的。'
    // },
    contextMenu : true,
    onSwitch : function(data) {
      console.log(data.id); // 当前Tab的Id
      console.log(data.index); // 得到当前Tab的所在下标
      console.log(data.elem); // 得到当前的Tab大容器

      console.log(tab.getCurrentTabId())
    },
    closeBefore : function(obj) { // tab 关闭之前触发的事件
      console.log(obj);
      // obj.title -- 标题
      // obj.url -- 链接地址
      // obj.id -- id
      // obj.tabId -- lay-id
      return true;
    }
  });
  // iframe自适应
  $(window).on('resize', function() {
    var $content = $('.admin-nav-card .layui-tab-content');
    $content.height($(this).height() - 147);
    $content.find('iframe').each(function() {
      $(this).height($content.height());
    });
  }).resize();

  // 设置navbar
  navbar.set({
    spreadOne : true,
    elem : '#admin-navbar-side',
    cached : true,
    data : navs
  /*
   * cached:true, url: 'datas/nav.json'
   */
  });
  // 渲染navbar
  navbar.render();
  // 监听点击事件
  navbar.on('click(side)', function(data) {
    tab.tabAdd(data.field);
  });

  $('.admin-side-toggle').on('click', function() {
    var sideWidth = $('#admin-side').width();
    if (sideWidth === 200) {
      $('#admin-body').animate({
        left : '0'
      }); // admin-footer
      $('#admin-footer').animate({
        left : '0'
      });
      $('#admin-side').animate({
        width : '0'
      });
    } else {
      $('#admin-body').animate({
        left : '200px'
      });
      $('#admin-footer').animate({
        left : '200px'
      });
      $('#admin-side').animate({
        width : '200px'
      });
    }
  });
  $('.admin-side-full').on('click', function() {
    var docElm = document.documentElement;
    // W3C
    if (docElm.requestFullscreen) {
      docElm.requestFullscreen();
    }
    // FireFox
    else if (docElm.mozRequestFullScreen) {
      docElm.mozRequestFullScreen();
    }
    // Chrome等
    else if (docElm.webkitRequestFullScreen) {
      docElm.webkitRequestFullScreen();
    }
    // IE11
    else if (elem.msRequestFullscreen) {
      elem.msRequestFullscreen();
    }
    layer.msg('按Esc即可退出全屏');
  });

  // 手机设备的简单适配
  var treeMobile = $('.site-tree-mobile'), shadeMobile = $('.site-mobile-shade');
  treeMobile.on('click', function() {
    $('body').addClass('site-mobile');
  });
  shadeMobile.on('click', function() {
    $('body').removeClass('site-mobile');
  });
});
