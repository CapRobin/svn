/**
 *
 * 匀速横向运动动画
 * @param ele
 * @param endX
 */
function contantSpeedAnimateX(ele,endX){
    clearInterval(ele.timer);
    ele.timer=setInterval(function () {
        var step;
        var chaVal=endX-ele.offsetLeft;
        if(chaVal>0){
            step=10;
        }else if (chaVal<0){
            step=-10;
        }else {
            step=0
        }
        console.log(1);
        ele.style.left=ele.offsetLeft+step+"px";
        if(Math.abs(endX-ele.offsetLeft)<=Math.abs(step)){
            ele.style.left=endX+"px";
            clearInterval(ele.timer);
        }



    },30)

}
/**
 *  匀速纵向运动动画
 *
 *
 * @param ele
 * @param endY
 */

function contantSpeedAnimateY(ele,endY){
    clearInterval(ele.timer);
    ele.timer=setInterval(function () {
        var step;
        var chaVal=endY-ele.offsetTop;
        if(chaVal>0){
            step=10;
        }else if (chaVal<0){
            step=-10;
        }else {
            step=0
        }
        console.log(1);
        ele.style.top=ele.offsetTop+step+"px";
        if(Math.abs(endY-ele.offsetTop)<=Math.abs(step)){
            ele.style.top=endY+"px";
            clearInterval(ele.timer);
        }



    },30)

}
/**
 *
 * 缓动动画横向运动
 * @param ele
 * @param endX
 */
function linearSpeedAnimateX(ele, endX) {
    clearInterval(ele.timer);
    ele.timer = setInterval(function () {
        var step = (endX - ele.offsetLeft) / 10;
        step=step>0?Math.ceil(step):Math.floor(step);
        div.style.left = div.offsetLeft + step + "px";
        console.log(1);
        if (Math.abs(endX - ele.offsetLeft) <= Math.abs(step)) {
            div.style.left = endX + "px";
            clearInterval(ele.timer);
        }
    }, 60)


}
/**
 *
 * 缓动动画纵向运动
 * @param ele
 * @param endY
 */
function linearSpeedAnimateY(ele, endY) {
    clearInterval(ele.timer);
    ele.timer = setInterval(function () {
        var step = (endY - ele.offsetTop) / 10;
        step=step>0?Math.ceil(step):Math.floor(step);
        ele.style.top = ele.offsetTop + step + "px";
        console.log(1);
        if (Math.abs(endY - ele.offsetTop) <= Math.abs(step)) {
            ele.style.Top = endY + "px";
            clearInterval(ele.timer);
        }
    }, 30)


}

/**
 *
 *
 * 通过CSS选择器的方式获取节点对象
 * @param selector
 * @returns {*}
 */
function getEle(selector){

//        console.log(selector.startWith("."));
//        思路:  取出第一个元素 然后判断是. #  或者都没有开头
    var first=selector.charAt(0);
    if(first==="#"){
        return document.getElementById(selector.slice(1));
    }else if(first==="."){
        return document.getElementsByClassName(selector.slice(1));
    }else{
        return document.getElementsByTagName(selector);
    }



}
/**
 *scroll的函数封装
 *
 * @returns {{top: Number, left: Number}}
 */
function scroll(){
      if(window.pageYOffset!==undefined){
            //火狐 谷歌  IE9+
//          console.log(document.pageYOffset);
          return {
              top:parseInt(window.pageYOffset),
              left:parseInt(window.pageXOffset)

          };
      }else {
        return {
            top:parseInt(document.documentElement.scrollTop),
            left:parseInt(document.documentElement.scrollLeft)
        };

      }



}
//自己封装的pageX和pageY
//参数需要传入event事件对象

function  getPagex(event){

    return event.pageX || scroll().left+event.clientX;
}
function  getPagey(event){

    return event.pageY || scroll().top+event.clientY;
}

//元素的显示与隐藏
function show(ele){
    ele.style.display="block";
}

function hide(ele){
    ele.style.display="none";
}
