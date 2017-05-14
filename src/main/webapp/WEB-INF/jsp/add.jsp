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
		    margin-top: 52px;
		    margin-left: 20%;
		    width: 60%;
		    min-height: 30px;
		    background-color: #d9d6d6;
		    padding: 5px 10px 5px 10px;
}
    </style>
    <body>
    	<div>

    	</div>
        <div class="mytable">
        <a style="font-size: 10px" href="${pageContext.request.contextPath}/book/">添加</a>
        <p style="font-size: 20px;text-align: center;">Books Table </p>
        	<div id="mybook">
        		<form id ="form">
                    书名：<input type="text" name="bookname"/>
                    作者：<input type="text" name="author"/>
                    出版社：<input type="text" name="publish"/>
                    价格：<input type="text" name="price"/>
                    <input type="button" value="保存" onclick="save()"/>
                </form>

        	</div>
        </div>
        <script type="text/javascript">
        	function save(){
                $.ajax({
                url: rootPath+"/book",
                type: 'post',
                data:  $("#form").serialize(),
                dataType:'json',
                success: function(result){
                    if(result.success){
                            window.location.href= rootPath+'/books';
                    }
                }
                });
            }
        </script>
    </body>
</html>