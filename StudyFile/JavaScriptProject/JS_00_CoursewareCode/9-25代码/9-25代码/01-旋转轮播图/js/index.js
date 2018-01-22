window.onload= function () {
    //需求:点击两侧按钮 实现旋转木马轮播图
    //原理:点击右侧按钮  把3号盒子的样式给2号盒子 2号盒子-->1号盒子  1号-->5号  5号--->4号  4号-->3号
    //        左侧按钮同理  方向相反


    //步骤
    //1.获取元素
    //2.动态设置轮播图每一个盒子的样式

    //3.鼠标移入和移除盒子  slide  显示与隐藏 左右侧按钮
    //4.按钮添加事件
    //    点击右侧按钮 正向旋转  把数组的的第一个元素删除 添加到数组的最后一个元素
    //    点击左侧按钮 反向旋转  把数组的的最后一个元素删除 添加到数组第一个元素上面
    //5. 根据变化过的数组 重新设置图片的样式

    var arr = [
        {   //  1
            width:400,
            top:70,
            left:50,
            opacity:20,
            zIndex:2
        },
        {  // 2
            width:600,
            top:120,
            left:0,
            opacity:80,
            zIndex:3
        },
        {   // 3
            width:800,
            top:100,
            left:200,
            opacity:100,
            zIndex:4
        },
        {  // 4
            width:600,
            top:120,
            left:600,
            opacity:80,
            zIndex:3
        },
        {   //5
            width:400,
            top:70,
            left:750,
            opacity:20,
            zIndex:2
        }
    ];



    //1.获取元素
    var  slide=document.getElementById("slide");
    var  liArr=slide.getElementsByTagName("li");
    var arrow=slide.children[1];

    setStyle();






    //3.鼠标移入和移除盒子  slide  显示与隐藏 左右侧按钮
    //利用缓动动画 显示与隐藏  两侧按钮
    slide.onmouseenter= function () {
        slowSpeedAnimate(arrow,{opacity:100});
        
    }
    slide.onmouseleave= function () {
        slowSpeedAnimate(arrow,{opacity:0});

    }

    var boo=true;
    //4.按钮添加事件
    //    点击右侧按钮 正向旋转  把数组的的第一个元素删除 添加到数组的最后一个元素
    //    点击左侧按钮 反向旋转  把数组的的最后一个元素删除 添加到数组第一个元素上面

    for(var a in arrow.children){
        arrow.children[a].onclick= function () {

            if(boo){
                if(this.className==="prev"){
                    //表示左侧按钮
                    //alert("反向旋转");
                    //    点击左侧按钮 反向旋转  把数组的的最后一个元素删除 添加到数组第一个元素上面
                    //arr.pop() 删除数组最后一个元素
                    //arr.push()) 添加最后一个元素

                    //arr.shift() 删除数组第一个元素
                    //arr.unshift() 添加到数组第一个元素
                    arr.push(arr.shift());
                }else{
                    //表示右侧按钮
                    //alert("正向旋转");
                    //点击右侧按钮 正向旋转  把数组的的第一个元素删除 添加到数组的最后一个元素
                    arr.unshift(arr.pop());
                }

                //5. 根据变化过的数组 重新设置图片的样式
                setStyle();
                boo=false;
            }


        }

    }


function setStyle(){
    //2.动态设置轮播图每一个盒子的样式
    for(var i=0;i<liArr.length;i++){
        //此种方式 没有动画效果 直接页面展开定死在当前位置
        //需要用缓动动画去让每个li缓动到指定位置
        //liArr[i].style.width=arr[i].width+"px";
        //liArr[i].style.top=arr[i].top+"px";
        //liArr[i].style.left=arr[i].left+"px";
        //liArr[i].style.opacity=arr[i].opacity/100;
        //liArr[i].style.zIndex=arr[i].zIndex;
        slowSpeedAnimate(liArr[i],arr[i], function () {
            //动画执行完的回调函数
            boo=true;
        });


    }
}








}
