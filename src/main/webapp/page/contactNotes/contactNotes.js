
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
      $.post("/customer/statuss/modifyOrSave",data.field,function(result){
        if(result.success){
            alert("操作成功");
            setTimeout(function () {
                        initPage(1, 8);
                    }, 500);
        }
      });
      return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

 



    form.render();
});

//添加文章
$('#addlayer').click(function () {
    alert("请在客户栏目添加");
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
        url: '/customer/contactNotes/list',
        data:{ "page": currentIndex, "size": pageSize },
        datatype: 'json',
        success: function (res) {
            layer.close(index);
            if (res.success) {
                var data = res.data;
                var html = '';
                html += '<table style="" class="layui-table">';
                html += '<colgroup><col width="10%"><col width="15%"><col width="15%"><col width="15%"><col width="15%"><col></colgroup>';
                html += '<thead><tr><th>ID</th><th>客户名称</th><th>联系类型</th><th>联系时间</th><th>备注</th><th>操作</th></tr></thead>';
                html += '<tbody>';
                //遍历文章集合
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    html += "<tr>";
                    /*html += "<td><input type='checkbox' lay-skin='primary' lay-filter='allChoose'></td>";*/
                    html += "<td>" + item.id+ "</td>";
                    html += "<td>" + item.name + "</td>";
                    html += "<td>" + item.contactType + "</td>";
                    html += "<td>" + item.contactTime + "</td>";
                    html += "<td style='color:green'>" + item.description + "</td>";

                    html += "<td>" + '<div class="layui-btn-group"><button onclick="modifylayer('+item.id
                                   + ');" class="do-action layui-btn layui-btn-small">编辑</button><button onclick="deletelayer('+item.id
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
    alert("没有权限");
}

//删除（作废）
function deletelayer(articleId) {
    alert("没有权限");
}