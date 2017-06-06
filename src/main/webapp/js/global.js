layui.use(['element', 'layer', 'util'], function () {
    var $ = layui.jquery;
    var element = layui.element();
    var layer = layui.layer;
    var util = layui.util;

    //加载弹出框
    /*var index = layer.load(1);
     layer.close(index);*/

    $("#totalCount").html(1);
    $("#articleCount").html(2);
    $("#todayRegist").html(2);
    $("#todayLogin").html(2);
    $("#resourceCount").html(2);
    /*$.post("/api/admin/GetSiteStatistics", {}, function (data) {
     layer.close(index);
     $("#totalCount").html(data.UserTotalCount);
     $("#articleCount").html(data.ArticleTotalCount);
     $("#todayRegist").html(data.TodayRegistCount);
     $("#todayLogin").html(data.TodayLoginCount);
     $("#resourceCount").html(data.ResourceTotalCount);
     });*/

    $('span.sys-title').click(function (event) {
        event.stopPropagation();    //阻止事件冒泡
        $('div.short-menu').slideToggle('fast');
    });
    $('div.short-menu').click(function (event) {
        event.stopPropagation();    //阻止事件冒泡
    });
    $(document).click(function () {
        $('div.short-menu').slideUp('fast');
    });

    //监听快捷菜单点击
    /*$('.short-menu .layui-field-box>div>div').click(function () {
     var elem = this;
     var url = $(elem).children('span').attr('data-url');
     var id = $(elem).children('span').attr('data-id');
     var title = $(elem).children('span').text();
     if (url == undefined) return;
     var tabTitleDiv = $('.layui-tab[lay-filter=\'tab\']').children('.layui-tab-title');
     var exist = tabTitleDiv.find('li[lay-id=' + id + ']');
     if (exist.length > 0) {
     //切换到指定索引的卡片
     element.tabChange('tab', id);
     } else {
     var index = layer.load(1);
     $.ajax({
     type: 'post',
     url: url,
     success: function (data) {
     layer.close(index);
     element.tabAdd('tab', { title: title, content: data, id: id });
     //切换到指定索引的卡片
     element.tabChange('tab', id);
     },
     error: function (e) {
     var message = e.responseText;
     layer.close(index);
     layer.msg(message, { icon: 2 });
     }
     });
     }
     $('div.short-menu').slideUp('fast');
     });
    layer.alert('目前已做：<br/>文章管理、资源管理、时光轴管理、文章回收站<br/>网站公告、更新日志、留言管理<br/><br/>点击顶部【部落格后台管理系统】有惊喜', {
        skin: 'layui-layer-molv'
        , closeBtn: 1
        , anim: 3 //动画类型
    });*/

    //监听左侧导航点击
    element.on('nav(leftnav)', function (elem) {
        var url = $(elem).children('a').attr('data-url');
        var id = $(elem).children('a').attr('data-id');
        var title = $(elem).children('a').text();
        if (title == "Home") {
            element.tabChange('tab', 0);
            return;
        }
        if (url == undefined) return;

        var tabTitleDiv = $('.layui-tab[lay-filter=\'tab\']').children('.layui-tab-title');
        var exist = tabTitleDiv.find('li[lay-id=' + id + ']');
        if (exist.length > 0) {
            //切换到指定索引的卡片
            element.tabChange('tab', id);
        } else {
            var index = layer.load(1);
            
            $.ajax({
                type: 'get',
                url: url,
                success: function (data) {
                    layer.close(index);
                    element.tabAdd('tab', { title: title, content: data, id: id });
                    //切换到指定索引的卡片
                    element.tabChange('tab', id);
                },
                error: function (e) {
                    var message = e.responseText;
                    layer.close(index);
                    layer.msg(message, { icon: 2 });
                }
            });
        }
    });

    //左侧导航展开与收缩
    var ishide = false;
    util.fixbar({
        bar1: '&#xe603',
        bgcolor: '#393D49',
        click: function (type) {
            if (type === 'bar1') {
                if (!ishide) {
                    $('.layui-side').animate({ left: '-200px' });
                    $('.layui-body').animate({ left: '0px' });
                    $('.layui-footer').animate({ left: '0px' });
                    var tishi = layer.msg('鼠标靠左自动显示菜单', { time: 1500 });
                    layer.style(tishi, {
                        top: 'auto',
                        bottom: '50px'
                    });
                    ishide = true;
                } else {
                    $('.layui-side').animate({ left: '0px' });
                    $('.layui-body').animate({ left: '200px' });
                    $('.layui-footer').animate({ left: '200px' });
                    ishide = false;
                }
            }
        }
    });

    $(document).mousemove(function (e) {
        if (e.pageX == 0 && ishide) {
            $('.layui-side').animate({ left: '0px' });
            $('.layui-body').animate({ left: '200px' });
            $('.layui-footer').animate({ left: '200px' });
            ishide = false;
        }
    });
});

Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];

    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));
    str = str.replace(/MM/, (this.getMonth() + 1) > 9 ? (this.getMonth() + 1).toString() : '0' + (this.getMonth() + 1));
    str = str.replace(/M/g, (this.getMonth() + 1));

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}
