
layui.use(['laypage', 'layer', 'form'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form();
    initPage(1, 8);


    //搜索
    form.on('submit(formSearch)', function (data) {
        layer.msg('太少，此功能赞不提供');
        return false;
    });
    //保存或则跟新
    form.on('submit(formSave)', function(data){
      $.post("/customer/member/modifyOrSave",data.field,function(result){
        if(result.success){
            alert("操作成功");
            setTimeout(function () {
                        initPage(1, 8);
                    }, 500);
        }
      });
      return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });
    form.on('switch(F_switch)', function(data){
      console.log(data.elem); //得到checkbox原始DOM对象
      console.log(data.elem.checked); //开关是否开启，true或者false
      console.log(data.value); //开关value值，也可以通过data.elem.value得到
      
      console.log(data.othis); //得到美化后的DOM对象
    });  
     



    form.render();
});

//添加
$('#addlayer').click(function () {
    var $ = layui.jquery;
    $.get('/customer/page/member/add.html', function(str){
      layer.open({
        type: 1,
          title: '添加',
          closeBtn: 1,
          shade:0.5,
          shadeClose: true,
          anim:1,
          maxmin:true,
          isOutAnim: true,
          skin: 'yourclass',
          area: ['720px', '100%'],
          offset: ['1px','61%'],
        content: str //注意，如果str是object，那么需要字符拼接。
      });
    });  
});

//页数据初始化
//currentIndex：当前也下标
//pageSize：页容量（每页显示的条数）
function initPage(currentIndex, pageSize) {
    var index = layer.load(1);
    var $ = layui.jquery;
    var form = layui.form();
    var laypage = layui.laypage;
    var laypageId = 'pageNav';
    $.ajax({
        type: 'post',
        url: '/customer/member/list',
        data:{ "page": currentIndex, "size": pageSize,"creator":"遇见小星" },
        datatype: 'json',
        success: function (res) {
            layer.close(index);
            if (res.success) {
                var data = res.data;
                var html = '';
                html += '<table style="" class="layui-table">';
                html += '<colgroup><col width="10%"><col width="15%"><col width="15%"><col width="15%"><col width="15%"><col></colgroup>';
                html += '<thead><tr><th>ID</th><th>客户名称</th><th>客户经理</th><th>客户状态</th><th>类型</th><th>操作</th></tr></thead>';
                html += '<tbody>';
                //遍历文章集合
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    if(item.status==1){
                        item.status='启用';
                    }else{
                        item.status='未启用';
                    }
                    html += "<tr>";
                    /*html += "<td><input type='checkbox' lay-skin='primary' lay-filter='allChoose'></td>";*/
                    html += "<td>" + item.id+ "</td>";
                    html += "<td>" + item.name + "</td>";
                    html += "<td>" + item.user.username + "</td>";
                    html += "<td style='color:green'>" + item.bmStatus.name + "</td>";
                    html += "<td style='color:green'>" + item.bmType.name + "</td>";
                    html += "<td>" + '<div class="layui-btn-group"><button onclick="modifylayer('+item.id
                                   + ');" class="do-action layui-btn layui-btn-small">查看</button><button onclick="deletelayer('+item.id
                                   + ');" class="do-action layui-btn layui-btn-small">删除</button></div>'
                                   + "</td>";
                    html += "</tr>";
                }
                html += '</tbody>';
                html += '</table>';
                html += '<div id="' + laypageId + '"></div>';
                $('#pageContent').html(html);
                form.render('checkbox');  //重新渲染CheckBox，编辑和添加的时候
                //$('#articleConsole,#articleList').attr('style', 'display:block'); //显示FiledBox
                //$('#articleBoxTitle').text("告警列表");    //FileBox标题改为告警列表

                var pages = res.total;
                laypage({
                    cont: laypageId,
                    pages: pages,
                    groups: 5,
                    skip: true,
                    curr: currentIndex,
                    jump: function (obj, first) {
                        var currentIndex = obj.curr;
                        if (!first) {
                            initPage(currentIndex, pageSize);
                        }
                    }
                });
                //该模块是我定义的拓展laypage，增加设置页容量功能
                //laypageId:laypage对象的id同laypage({})里面的cont属性
                //pagesize当前页容量，用于显示当前页容量
                //callback用于设置pagesize确定按钮点击时的回掉函数，返回新的页容量
                layui.pagesize(laypageId, pageSize).callback(function (newPageSize) {
                    //这里不能传当前页，因为改变页容量后，当前页很可能没有数据
                    initilArticle(1, newPageSize);
                });
            } else {
                layer.msg(res.Message);
            }
        },
        error: function (e) {
            layer.close(index);
            layer.msg(e.responseText);
        }
    });
}



//编辑文章
function modifylayer(articleId) {
    var $ = layui.jquery;
    $.get('/customer/page/member/add.html', function(str){
      layer.open({
        type: 1,
          title: '客户',
          closeBtn: 1,
          shade:0.5,
          shadeClose: true,
          anim:1,
          maxmin:true,
          isOutAnim: true,
          skin: 'yourclass',
          area: ['867px', '100%'],
          offset: ['1px','47%'],
        content: str //注意，如果str是object，那么需要字符拼接。
      });
    });

    var $ = layui.jquery;
    var index = layer.load(1);
    $.ajax({
        type: 'post',
        url: '/customer/member/find',
        data: 'id=' + articleId,
        success: function (result) {
            data = result.data;
            console.info(data)
            layer.close(index);
            $('#id').val(data.id);
            $('#name').val(data.name);
            $('#user_username').val(data.user.username);
            $('#status').val(data.status);
            /*$('#articleBack').bind('click', function () {
                initilArticle(1, 8);
            });*/
            
        },
        error: function (e) {
            layer.close(index);
            layer.msg(e.responseText);
        }
    });
}

//删除（作废）
function deletelayer(articleId) {
    layer.confirm('确定删除？', {
        btn: ['确定', '取消'] //按钮
    }, function () {
        var $ = layui.jquery;
        var index = layer.load(1);
        $.ajax({
            type: 'post',
            url: ' /customer/member/delete',
            data: { "id": articleId },
            success: function (res) {
                layer.close(index);
                if (res.success) {
                    layer.msg("操作成功", {icon: 1});
                    setTimeout(function () {
                        initPage(1, 8);
                    }, 500);
                }
            },
            error: function (e) {
                layer.close(index);
                layer.msg(e.responseText);
            }
        });
    }, function () {}
    );
}