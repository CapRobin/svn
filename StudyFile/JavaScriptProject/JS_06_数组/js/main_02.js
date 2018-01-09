//练习一
function clickAction01() {
    //声明一个空数组
    var array = [];
    var j = 0;
    for (var i = 1; i <= 100; i++) {
        if (i % 2 !== 0) {
            array[j] = i;
            j++;
        }
    }

    //修改字体样式并打印到页面
    var showStr = "所有奇数组成的数组如下：<br><br>[" + array.toString() + "]";
    changeStyle(1, showStr);
}


//练习二
function clickAction02() {
    //声明一个空数组
    var array = [];
    for (var i = 1; i <= 100; i++) {
        if (i % 2 !== 0) {
            array[array.length] = i;
        }
    }

    //修改字体样式并打印到页面
    var showStr = "所有奇数组成的数组如下：<br><br>[" + array.toString() + "]";
    changeStyle(2, showStr);
}


//练习三
function clickAction03() {
    //声明一个空数组
    var array = [];
    for (var i = 1; i <= 100; i++) {
        if (i % 3 === 0) {
            //当前数组长度就是下一个数组元素的索引值
            array[array.length] = i;
        }
    }

    //修改字体样式并打印到页面
    var showStr = "所有能被3整除的数字组成的数组如下：<br><br>[" + array.toString() + "]";
    changeStyle(3, showStr);
}

//练习四
function clickAction04() {
    var arr04 = [57, 43, 27, 82, 7, 34, 43, 88];
    var sum = 0;
    var average = 0;
    for (var i = 0; i < arr04.length; i++) {
        //累加求和
        sum += arr04[i];
    }
    //求平均值
    average = sum / arr04.length;

    //修改字体样式并打印到页面
    var showStr = "原数组：[" + arr04.toString() + "]<br><br>所有数组元素的和为：" + sum + "<br>他们的平均值为：" + average;
    changeStyle(4, showStr);
}

//练习五
function clickAction05() {
    var arr05 = [57, 43, 27, 82, 7, 34, 88, 43];
    //此处声明的变量不能为数组元素以外的值，maxNum = 0;则原数组元素全部为负数时，
    // 那么最大值为0，显然错误！所以maxNum的初始化值应该为数组内的某个元素值
    var maxNum = arr05[0];
    //57与57比较大小没意义，所以直接从i=1开始遍历比较
    for (var i = 1; i < arr05.length; i++) {
        if (arr05[i] > maxNum) {
            maxNum = arr05[i];
        }
    }
    //获取最大值得位置
    var index = arr05.indexOf(maxNum) + 1;
    //修改字体样式并打印到页面
    var showStr = "原数组：[" + arr05.toString() + "]<br><br>数组中第" + index + "个元素是最大值，最大值数是：" + maxNum + "<br>";
    changeStyle(5, showStr);
}

//练习六
function clickAction06() {
    var arr06 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    //数组转化成字符串，并按照"**"分割
    var str1 = arr06.join("**");
    //数组转化成字符串，并按照"|"分割
    var str2 = arr06.join("|");
    //数组转化成字符串，并按照"++"分割
    var str3 = arr06.join("++");
    //修改字体样式并打印到页面
    var showStr = "原数组：[" + arr06 + "]<br><br>数组转化成字符串，并按照'**'分割：" + str1 + "<br>数组转化成字符串，并按照'|'分割：" + str2 + "<br>数组转化成字符串，并按照'++'分割：" + str3;
    changeStyle(6, showStr);
}

//练习七
function clickAction07() {
    var arr07 = [57, 0, 43, 27, 0, 7, 0, 34, 88, 0, 43];

    var arr07_1 = [];
    for (var i = 0; i < arr07.length; i++) {
        if (arr07[i] !== 0) {
            //向数组的最后一位添加一个元素
            arr07_1.push(arr07[i]);
        }
    }

    //备份数组
    // var arr07_1 = arr07.join();
    // var index = arr07.indexOf(0);
    // var arr07_2 = arr07.splice(index,1);

    //修改字体样式并打印到页面
    var showStr = "原数组：[" + arr07 + "]<br><br>原数组中的0被删除后变为：[" + arr07_1 + "]";
    changeStyle(7, showStr);
}

//练习八
function clickAction08() {

    var arr08 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var arr08_01 = [];
    //使用For循环实现
    for (var i = arr08.length - 1; i >= 0; i--) {
        //循环向数组的最后一位添加元素
        arr08_01.push(arr08[i]);
    }
    //使用数组对象方法实现
    var arr08_02 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    arr08_02.reverse();

    //修改字体样式并打印到页面
    var showStr = "原数组：[" + arr08.toString() + "]<br><br>翻转后的新数组为(For循环实现)：[" + arr08_01 + "]<br>翻转后的新数组为(Push方法实现)：[" + arr08_02 + "]";
    changeStyle(8, showStr);
}

//练习九
function clickAction09() {
    var arr09 = [6, 4, 1, 8, 3, 5, 9, 2, 7];
    var nStr = arr09.toString();
    console.log("原数组——————————>>" + nStr);
    var inTimes = 0;
    var outTimes = 0;
    /*第一次优化：N个数比较(N-1)次即可比较出其的大小顺序来，所以外层循环的判断条件为(i<arr09.length-1)*/
    for (var i = 0; i < arr09.length - 1; i++) {
        /*第三次优化：加入数组本来就是按照从大到小的顺序排列的，那么就需要开关门原则，此处定义开关门标记变量*/
        var isNeedSort = true;
        //内部循环执行结束后最小的值将被排到最后面
        //注意：因为当内部循环条件为(j<arr09.length)时，循环的过程中j最大就能取到(arr09.length-1),当j=(arr09.length-1)(最后一次遍历)时arr09[j+1]的值不存在，所以比较也没什么意义，所以在此将省略数组的最后一次遍历，将遍历条件改为:(j<arr09.length-1)
        /*第二次优化：由于每一次外部循环执行结束后数组的尾部都有i个元素已经排好了顺序，所以执行每一次内部循环的次数将减去i次(不考虑已排好顺序的部分)*/
        for (var j = 0; j < arr09.length - 1 - i; j++) {
            //将索引值为j的元素与下一位元素进行比较，如当前元素的值小于下一个元素是，互换位置,
            if (arr09[j] < arr09[j + 1]) {
                //如果该段代码从来没有执行过(在第一次进行内部循环时即可知道)，即表示数组元素中前面的元素比后面的元素都要大， 数组元素已经是从大到小的顺序排列了
                isNeedSort = false;
                var temp = arr09[j];
                temp = arr09[j];
                arr09[j] = arr09[j + 1];
                arr09[j + 1] = temp;
            }
            inTimes++;
        }
        outTimes++;
        //如果isNeedSort=true，则直接终止所有循环
        if (isNeedSort) {
            break;
        }
    }
    console.log("排序后的数组—————>>" + arr09);
    console.log("内部循环次数————————>>" + inTimes + "次");
    console.log("外部循环次数————————>>" + outTimes + "次");

    //修改字体样式并打印到页面
    var showStr = "原数组：[" + nStr + "]<br><br>冒泡排序后的数组：[" + arr09 + "]";
    changeStyle(9, showStr);
}

//练习十
function clickAction10() {

    //修改字体样式并打印到页面
    var showStr = "该练习还没完成，请稍等......";
    changeStyle(10, showStr);
}

/*JavaScript修改CSS样式*/
function changeStyle(key, showStr) {
    var switchArr = [];
    //将获取到的ID装载到数组中
    switchArr[0] = document.getElementById("pId01");
    switchArr[1] = document.getElementById("pId02");
    switchArr[2] = document.getElementById("pId03");
    switchArr[3] = document.getElementById("pId04");
    switchArr[4] = document.getElementById("pId05");
    switchArr[5] = document.getElementById("pId06");
    switchArr[6] = document.getElementById("pId07");
    switchArr[7] = document.getElementById("pId08");
    switchArr[8] = document.getElementById("pId09");
    switchArr[9] = document.getElementById("pId10");
    //开始遍历数组，并给相关的ID添加样式
    for (var i = 0; i < 10; i++) {
        if (i === (key - 1)) {
            switchArr[i].style.color = "red";
        } else {
            switchArr[i].style.color = "black";
        }
    }
    //页面展示结果
    showView.innerHTML = showStr;
}
