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

  $.ajax({
    type:'post',
    url:'/customer/holdChart/memberByStatus',
    success: function(result){
      if (result.success) {
        
        mycanvas(dataBox1,result.data,'bar');
      }
    }
  });

  function mycanvas(dom,data,type){

    var name = new Array();  
        count=[];
        myData = {};
        eData = [];
        for (var i = data.length - 1; i >= 0; i--) {
          name.push(data[i].name);
          count.push(data[i].value);

        }
        myData["name"]=name;
        myData["count"]=count;
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
    "title": {
        "text": "按状态统计", 
        "x": "center"
    }, 
    "tooltip": {
        "trigger": "axis", 
        "axisPointer": {
            "type": "shadow"
        },
    }, 
    "grid": {
        "borderWidth": 0, 
        "y2": 120
    }, 
    "legend": {
        "x": "right", 
        "data": [ ]
    }, 
    "toolbox": {
        "show": true, 
        "feature": {
            "restore": { }, 
            "saveAsImage": { }
        }
    }, 
    "calculable": true, 
    "xAxis": [
        {
            "type": "category", 
            "splitLine": {
                "show": false
            }, 
            "axisTick": {
                "show": false
            }, 
            "splitArea": {
                "show": false
            }, 
            "axisLabel": {
                "interval": 0, 
                "rotate": 45, 
                "show": true, 
                "splitNumber": 15, 
                "textStyle": {
                    "fontFamily": "微软雅黑", 
                    "fontSize": 12
                }
            }, 
            "data": myData.name,
        }
    ], 
    "yAxis": [
        {
            "type": "value", 
            "splitLine": {
                "show": false
            }, 
            "axisLine": {
                "show": true
            }, 
            "axisTick": {
                "show": false
            }, 
            "splitArea": {
                "show": false
            }
        }
    ], 
    "dataZoom": [
        {
            "show": true, 
            "height": 30, 
            "xAxisIndex": [
                0
            ], 
            bottom:40,
            "start": 0, 
            "end": 80
        }, 
        {
            "type": "inside", 
            "show": true, 
            "height": 15, 
            "xAxisIndex": [
                0
            ], 
            "start": 1, 
            "end": 35
        }
    ], 
    "series": [
        {
            "name": "昨日", 
            "type": "bar", 
            "stack": "总量", 
            "barMaxWidth": 50, 
            "barGap": "10%", 
            "itemStyle": {
                "normal": {
                    "barBorderRadius": 0, 
                    "color": "rgba(60,169,196,0.5)", 
                    "label": {
                        "show": true, 
                        "textStyle": {
                            "color": "rgba(0,0,0,1)"
                        }, 
                        "position": "insideTop",
                        formatter : function(p) {
                                                  return p.value > 0 ? (p.value ): '';
                                              }
                    }
                }
            }, 
            "data": myData.count, 
        }
    ]
  }

   var myChart1 = echarts.init(document.getElementById('dataBox1')); 
   myChart1.setOption(option1); 
    var myChart2 = echarts.init(document.getElementById('dataBox2')); 
   myChart2.setOption(option2); 
   var myChart3 = echarts.init(document.getElementById('dataBox3')); 
   myChart3.setOption(option3);                
}