layui.use('element','form', function(){
  var $ = layui.jquery
  ,element = layui.element() //Tab的切换功能，切换事件监听等，需要依赖element模块
  ,form = layui.form();
  //触发事件

  /*element.on('tab(chartTab)', function(data){
    console.log(data);
  });*/
});

//init
getData("/customer/member/countByDate?time=7");
//点击获取数据
$("#chartTab li").on("click",function(elem){
  var log = elem.target.outerHTML;
  $("#myid").html(log);
  var url = document.getElementById("myid").firstElementChild.getAttribute("data-url");
  getData(url);
});

function getData(url){
  $.ajax({
    type:'post',
    url:url,
    success: function(result){
      if (result.success) { 
        var data = result.data,
            html = '<table style="" class="layui-table">';
                //html += '<colgroup><col width="10%"><col width="15%"><col width="15%"><col width="15%"><col width="15%"><col></colgroup>';
          var t = '<thead><tr><th>人员</th>';
          var c = '<tbody><tr><th>小星</th>';  
          var count =data.length+1;    
                //遍历文章集合
                for (var i = 0; i < data.length; i++) {
                    var item = data[i];
                    t += "<td>" + item.time+ "</td>";
                    c += "<td>" + item.count+ "</td>";
                }
                t += '<th>合计</th></tr></thead>';
                c += '<th>'+count+'</th></tr></tbody>';
                html += t+c;
                $('#pageContent').empty();
                $('#pageContent').html(html);

        mycanvas(result.data,'bar');
      }
    }
  });
}

function mycanvas(data,type){
    console.info(data);
    var name = new Array();  
        count=[];
        myData = {};
        eData = [];
        for (var i = data.length - 1; i >= 0; i--) {
          name.push(data[i].time);
          count.push(data[i].count);

        }
        myData["name"]=name;
        myData["count"]=count;
    console.info(myData);
var option1 = {
    title : {
        text: '持有总数',
        subtext: '数据来自数据库',
        x:'center'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['持有总数']
    },
    toolbox: {
        show : true,
        feature : {
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'value',
            boundaryGap : [0, 0.1]
        }
    ],
    yAxis : [
        {
            type : 'category',
            data : myData.name
        }
    ],
    series : [
        {
            name:'2011年',
            type:'bar',
            data:myData.count,
            itemStyle: {
                  normal:{
                    "color": "rgba(60,169,196,0.5)",
                  }
              },
        },
    ]
};
option2 = {

  title : {
        text: '平均分布',
        subtext: '数据来自数据库',
        x:'center'
    },
    toolbox:{
    show:true,
    feature:{
      restore:{show:true},
      saveAsImage:{show:true}
    }
  },
    tooltip : {
          trigger: 'item',
          formatter: "{a} <br/>{b} : {c} ({d}%)"
      },
      color:['#00cc55','#11dd77','#11cc99','#1ddbb','#11ccdd','#11ddff'],
      legend: {
          orient: 'vertical',
          x: 'left',
          data: myData.name,
          
      },
      series : [
          {
              name: '签到比例分析',
              type: 'pie',
              radius : '55%',
              center: ['40%', '50%'],
              data:data,
              itemStyle: {
                  emphasis: {
                      shadowBlur: 10,
                      shadowOffsetX: 0,
                      shadowColor: 'rgba(0, 0, 0, 0.5)'
                  }
              },
              itemStyle: {
                  normal: {
                      label:{ 
                            show: true, 
//                              position:'inside',
                            formatter: '{b} : {c} ({d}%)' 
                        }
                  },
                    labelLine :{show:true}
              }
          }
      ]
};

option3 = {
    
    title : {
        text: '客户新建',
        subtext: '数据来自数据库',
        x:'center'
    },
    toolbox:{
    show:true,
    feature:{
      restore:{show:true},
      saveAsImage:{show:true}
    }
  },

    xAxis : [
        {
            type : 'category',
            boundaryGap:false,
            data : myData.name
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'蒸发量',
            type:'line',
            data: myData.count,
              //2.0 定直线
               markLine : {
               data : 
                 [ {name: '起始',xAxis: 0, yAxis: 100},
                 {name: '结束',value:100, xAxis: 1000, yAxis: 100} ],
               
            },
        },
    ]
};
                    

/*   var myChart1 = echarts.init(document.getElementById('dataBox1')); 
   myChart1.setOption(option1); 
    var myChart2 = echarts.init(document.getElementById('dataBox2')); 
   myChart2.setOption(option2); */
   var myChart3 = echarts.init(document.getElementById('dataBox3')); 
   myChart3.setOption(option3);                
}
  

  