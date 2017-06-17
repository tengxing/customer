layui.use('element','form', function(){
  var $ = layui.jquery
  ,element = layui.element() //Tab的切换功能，切换事件监听等，需要依赖element模块
  ,form = layui.form();
  //触发事件
  
  $('.site-demo-active').on('click', function(){
    var othis = $(this), type = othis.data('type');
    active[type] ? active[type].call(this, othis) : '';
  });
  
  //Hash地址的定位
  var layid = location.hash.replace(/^#test=/, '');
  element.tabChange('test', layid);
  
  element.on('tab(test)', function(elem){
    location.hash = 'test='+ $(this).attr('lay-id');
  });
  
});