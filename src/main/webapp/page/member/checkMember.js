layui.use(['laypage', 'layer', 'form'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        form = layui.form();
    form.on('submit(formSearch)', function(data){
      $.post("/customer/member/findListByName",data.field,function(result){
        if(result.success){
            if (result.success) {
                var data = result.data;
                console.info(data);
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
                
                $('#pageContent').html(html);
			}
        }
      });
      return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });	

});

//编辑文章
function modifylayer(articleId) {
    var $ = layui.jquery;
    $.get('/customer/page/member/xiangxi.html', function(str){
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