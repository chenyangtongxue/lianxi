layui
    .config({
      base : 'resources/js/'
    })
    .use(
        [ 'form' ],
        function() {
          var $ = layui.jquery, form = layui.form, layerTips = parent.layer === undefined ? layui.layer : parent.layer, layer = layui.layer;

          $('#readCsv').on('click', function() {
            var callback = $('#callback').html();
            if (callback != "上传成功！") {
              parent.layer.alert('\u8bf7\u4e0a\u4f20\u6587\u4ef6\uff01'); // 请上传文件！
              return false;
            }
            var path = $('#path').html();
            var filename = path.slice(path.lastIndexOf("\\") + 1, path.length);
            console.log("filename:", filename);
            var filenameID = 0;
            if (filename.indexOf("TA010M") != -1) {
              filenameID = 1;
            } else if (filename.indexOf("TE010M") != -1) {
              filenameID = 2;
            } else if (filename.indexOf("TE020M") != -1) {
              filenameID = 3;
            }
            console.log("filenameID:" + filenameID);
            if (filenameID != 0) {
              analysis(path, filenameID);
            } else {
              $('#condition').css("display", "block");
            }
          });
          // 监听提交
          form.on('submit(sub)', function(data) {
            console.log("data", data);
            var filepath = $('#path').text();
            var formatType = $('#formatType').val();
            analysis(filepath, formatType);
            $('#formatType').val("0");
            form.render('select');
            return false;
          });

          function analysis(filepath, formatType) {
            console.log("filepath:" + filepath + "formatType:" + formatType);
            $
                .ajax({
                  url : 'readCsv',
                  type : 'post',
                  contentType : "application/json", // 必须有
                  dataType : "json", // 表示返回值类型，不必须
                  data : JSON.stringify({
                    'path' : filepath,
                    'type' : formatType
                  }),
                  async : false,
                  success : function(res) {
                    console.log("res", res);
                    if (res.result == "failure") {
                      // 上传文件解析失败，请检查文件数据是否符合格式
                      parent.layer
                          .alert('\u4e0a\u4f20\u6587\u4ef6\u89e3\u6790\u5931\u8d25\uff0c\u8bf7\u68c0\u67e5\u6587\u4ef6\u6570\u636e\u662f\u5426\u7b26\u5408\u683c\u5f0f');
                    }
                    document.getElementById('FrameID').contentWindow.location.reload(true);
                    $('#condition').css("display", "none");
                  }
                })
          }

          // 获取所有选择的列
          $('#getSelected').on('click', function() {
            var callback = $('#callback').html();
            if (callback != "\u4e0a\u4f20\u6210\u529f\uff01") {
              parent.layer.alert('\u8bf7\u4e0a\u4f20\u6587\u4ef6\uff01') // 请上传文件！
              return false;
            }
            ifrmname.window.say();

          });

        });
