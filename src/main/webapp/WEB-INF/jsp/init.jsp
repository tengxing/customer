<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>init</title>
        <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>

        <script type="text/javascript">
        	var rootPath = "${pageContext.request.contextPath}";
        </script>

    </head>
    <style type="text/css">
    	.mytable {
            text-align: center;
		    margin-top: 52px;
		    margin-left: 20%;
		    width: 55%;
		    min-height: 30px;
		    background-color: #d9d6d6;
		    padding: 5px 10px 5px 10px;
        }.mydiv {
            padding-top: 200px;
            line-height: 20px;
            border: 5px solid #686c70;
            font-size: 30px;
            color: #ffffff;
            text-align: center;
            z-index: 999;
            width: 960px;
            height: 480px;
            left: 30%;
            top: 10%;
            margin-left: -150px!important;
            margin-top: -60px!important;
            margin-top: 0px;
            position: fixed!important;
            position: absolute;
            background-color: #8e8282;
            opacity: 0.9;
        }
    </style>
    <body>
    	<div>

    	<div id="popDiv" class="mydiv" style="display:none;">
        <a href="112.74.13.211/ssm/ssm.tar">代码流程图下载</a>

        </div>

        <div class="mytable">
        <a style="font-size: 10px" href="${pageContext.request.contextPath}/book/">添加</a>
        <p style="font-size: 20px;text-align: center;">Books Table </p>
        <div style="padding-left:240px">
        	<div id="mybook" style="text-align: center;">
        </div>

        	</div>
        </div>
        </div>

        <script type="text/javascript">
            function load(id){
                $.ajax({
                    type:"post",
                    url: rootPath+"/book/"+id,
                    data:{
                        _method:"delete",
                    },
                    dataType:"json",
                    success:function(result){
                       if(result.success){
                          alert("操作成功");
                          window.location.href= rootPath+'/books';
                       }
                   }
                });
            }
            function load1(id){
                $.ajax({
                    url: rootPath+"/book/"+id,
                    type: "get",
                    dataType:"json",
                    success:function(result){
                        console.info(result)
                        if (result.success) {
                            var book = result.book;
                            var html = '';
                            html += '<form id ="form">';
                            html += '<input type="hidden" id="id" name="id" value="'+book.id+'"/>';
                            html += '书名:<input type="text" id="bookname" value="'+book.bookname+'"/><br><br>';
                            html += '作者:<input type="text" id="author" value="'+book.author+'"/><br><br>';
                            html += '出版社:<input type="text" id="publish" value="'+book.publish+'"/><br><br>';
                            html += '价格:<input type="text" id="price" value="'+book.price+'"/><br><br>';
                            html += '<input type="button" value="保存" onclick="save()"/></form>';
                            $("#popDiv").html(html);
                            document.getElementById('popDiv').style.display='block';
                        }
                    }
                });
            }
            function save(){
                var id = $("#id").val();
                $.ajax({
                url: rootPath+"/book/"+id,
                type: 'post',
                data:  {
                    _method:'put',
                    "id" : $("#id").val(),
                    "bookname" : $("#bookname").val(),
                    "author" : $("#author").val(),
                    "publish" : $("#publish").val(),
                    "price" : $("#price").val(),
                },
                dataType:'json',
                success: function(result){
                    if(result.success){
                            alert("修改成功");
                            window.location.href= rootPath+'/books';
                    }
                }
                });
            }
        	var mybook = document.getElementById("mybook");
        	$.ajax({
        		url: rootPath+"/book/list",
        		type: 'post',
        		dataType:'json',
        		success: function(result){
        			if(result){
        					var data = result.books;
        				  	var html = '';
			                html += '<table style="text-align:center" class="layui-table">';
			                html += '<colgroup><col width="20"><col width="60"><col width="60"><col width="60"><col width="80"><col width="60"><col width="80"></colgroup>';
			                html += '<thead><tr><th>id</th></th><th>书名</th><th>作者</th><th>出版社</th><th>更新时间</th><th>价格</th><th>操作</th></tr></thead>';
			                html += '<tbody>';
			                for (var i = 0; i < data.length; i++) {
			                    var item = data[i];
			                    html += "<tr>";
			                    html += "<td>"+item.id+"</td>";
			                    html += "<td>" + item.bookname+ "</td>";
			                    html += "<td>" + item.author + "</td>";
			                    html += "<td>" + item.publish + "</td>";
			                    html += "<td>" + item.time + "</td>";
			                    html += "<td>" + item.price + "</td>";
			                    html += "<td>" +
                                "<span><input type='button' value='查看' onclick='load1("+item.id+")'/><input type='button' value='删除' onclick='load("+item.id+")'/></span>" + "</td>";
			                    html += "</tr>";
			                }
			                html += '</tbody>';
			                html += '</table>';
			                $("#mybook").html(html);
        			}
        		}
        	});
        </script>
    </body>
</html>