function getClock(id) {
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth()+1;
    var date = now.getDate();
    var week = now.getDay();
    /*数组实例化可以简化这样写*/
    var weekArry = Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");
    /*var weekArry = new Array("星期日","星期一","星期二","星期三","星期四","星期五","星期六");*/
    var weekName = weekArry[week];
    var dateTime = year+"年"+month+"月"+date+"日&emsp;&emsp;"+weekName;
    id.innerHTML = "现在的时间为："+dateTime;


}
