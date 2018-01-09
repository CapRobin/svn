//方法一(array.push())：向数组的末尾添加一个或更多元素，并返回新的长度。
function clickAction01() {
    var arr01 = [1, 2, 3, 4, 5];
    arr01.push("新增元素,88");
    //如下代码只能打印出新数组的长度值,无法返回新的数组
    // console.log(arr01.push("新增元素"));
    //打印新数组
    console.log(arr01);

    //修改字体样式并打印到页面
    var showStr = "向数组最两位插入指定元素<br>我是新增元素之前的数组：[1,2,3,4,5,6]<br>我是新增元素之后的数组：[" + arr01.toString() + "]";
    changeStyle(1, showStr);
}

//方法二(array.unshift())：向数组的开头添加一个或更多元素，并返回新的长度。
function clickAction02() {
    var arr02 = [1, 2, 3, 4, 5];
    arr02.unshift("Hello", "abc");
    //如下代码只能打印出新数组的长度值,无法返回新的数组
    // console.log(arr02.unshift("新增元素"));
    //打印新数组
    console.log(arr02);

    //修改字体样式并打印到页面
    var showStr = "向数组前两位插入指定元素<br>我是新增元素之前的数组：[1,2,3,4,5]<br>我是新增元素之后的数组：[" + arr02.toString() + "]";
    changeStyle(2, showStr);
}

//方法三(array.concat())：连接两个或更多的数组(合并数组)，合并后不改变原数组，只返回新的数组结果；
function clickAction03() {
    var arr03_1 = new Array(3);
    arr03_1[0] = "George";
    arr03_1[1] = "John";
    arr03_1[2] = "Thomas";
    var arr03_2 = new Array(3);
    arr03_2[0] = "James";
    arr03_2[1] = "Adrew";
    arr03_2[2] = "Martin";
    var arr03_3 = new Array(2);
    arr03_3[0] = "William";
    arr03_3[1] = "Franklin";

    //直接合并数组
    var arr03_4 = arr03_1.concat(arr03_2, arr03_3);
    //此时arr03_1、arr03_2、arr03_3三个数组本身都没有发生变化，只是他们合并后形成了一个新的数组(arr03_4)
    console.log(arr03_4);
    //合并数组元素
    var arr03_5 = arr03_1.concat("ABCD");
    //此时arr03_1数组本身还是没有发生改变(原理同上)
    console.log(arr03_5);

    //修改字体样式并打印到页面
    var showStr = "合并之前的数组为：[" + arr03_1.toString() + "]<br>合并之后的数组为：[" + arr03_4.toString() + "]<br>合并之后的数组元素为：[" + arr03_5.toString() + "]";
    changeStyle(3, showStr);
}

//方法四(array.join())：将数组中的所有元素转化成字符串，并按照指定的符号(传入的参数)进行分割
function clickAction04() {
    var arr04 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    //将数组内容转换成字符串
    var str1 = arr04.join();
    console.log("没有分割数组元素直接转换后：----->>" + str2);
    //将素组按照指定的符号分割后转换成字符串
    var str2 = arr04.join("");
    console.log("按照空字符串分割数组元素转换后：----->>" + str2);
    var str3 = arr04.join("***");
    console.log("按照“***”分割数组元素转换后：----->>" + str3);

    //修改字体样式并打印到页面
    var showStr = "原数组为：[" + arr04.toString() + "]<br>按照空字符串分割数组元素转换后的字符串为：" + str2 + "<br>按照“***”分割数组元素转换后的字符串为：" + str3;
    changeStyle(4, showStr);
}

//方法五(array.pop())：删除并返回数组的最后一个元素
function clickAction05() {
    var arr05 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var arr05_01 = arr05;
    //返回被删除的元素
    var str = arr05.pop();
    console.log("删除的最后一个元素为：---->>" + str);
    console.log("此时数组变为：-------->>" + arr05);

    //修改字体样式并打印到页面
    var showStr = "原数组为：['李彦宏','马化腾','马云','柳传志','雷军','周鸿祎']<br>删除的最后一个元素是：" + str + "<br>删除后原数组变为：[" + arr05 + "]";
    changeStyle(5, showStr);
}

//方法六(array.slice(start,end))：截取数组指定区域(start<=指定区域<end,不包含end)的元素并返回新的数组，原数组不变
// start和end都可以随便省略，
// array.slice(1,3)表示截取数组中索引值为1和2的元素，即截取第二个和第三个元素
// array.slice(1)表示截取数组中索引值>=1的所有元素，即截取第二个元素后面所有的元素
// array.slice()表示截取整个数组，即将原数组拷贝一份
// 如果start为负数，那么规定从数组尾部开始算起，-1表示最后一个元素，2-表示倒数第二个元素，以此类推
function clickAction06() {
    var arr06 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var arr06_01 = arr06.slice(1, 3);
    console.log(arr06_01);
    console.log(arr06);

    //修改字体样式并打印到页面
    var showStr = "原数组是：[" + arr06.toString() + "]" + "<br>截取的新数组是：[" + arr06_01.toString() + "]";
    changeStyle(6, showStr);
}

//方法七(array.splice())：向数组(指定位置)中添加/删除元素, 然后返回被删除元素组成的新数组，注意：该方法会改变原数组
/*语法：
* arrayObject.splice(index,howmany,item1,.....,itemX)
* index：必需。整数，规定添加/删除项目的位置，使用负数可从数组结尾处规定位置。
* howmany：必需。要删除的项目数量。如果设置为 0，则不会删除项目。
* item1,.....,itemX：可选。向数组添加的新项目*/

function clickAction07() {
    var arr07 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    //从第二个元素开始删除3个元素
    var arr07_01 = arr07.splice(1, 3);
    console.log("从第二个元素开始删除3个元素返回---->>" + arr07_01)
    console.log("打印原数组---->>" + arr07)

    //从第三个位置开始添加一个元素，0表示不删除任何元素
    var arr07_02 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var arr07_03 = arr07_02.splice(2, 0, "乔布斯");
    //返回的新数组为空，
    console.log("返回的新数组---->>" + arr07_03)
    console.log("打印原数组---->>" + arr07_02)

    //从第四个元素开始，删除2个元素，并添加一个新的元素
    var arr07_04 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var arr07_05 = arr07_04.splice(3, 2, "比尔盖茨");
    console.log("返回的新数组---->>" + arr07_05);
    console.log("打印原数组---->>" + arr07_04);

    //修改字体样式并打印到页面
    var showStr = "原数组为：['李彦宏','马化腾','马云','柳传志','雷军','周鸿祎']<br>从第二个元素开始删除3个元素后原数组变为：[" + arr07 + "]<br>从第三个位置开始添加一个元素(不删除任何元素)后原数组变为：[" + arr07_02 + "]<br>从第四个元素开始,删除2个元素,并添加一个新的元素后原数组变为：[" + arr07_04 + "]";
    changeStyle(7, showStr);

}

//方法八(array.toString())：将数组直接转换成字符串(即去掉“[]”直接输出),与array.join()方法类似
function clickAction08() {
    var arr08 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎'];
    var str = arr08.toString();
    var showStr = "数组直接转换成字符串后的效果是：" + str;
    changeStyle(8, showStr);
}

//方法九：查找具体元素的指定位置，返回元素的索引值(指定位置),如果找不到返回-1
function clickAction09() {
    var arr09 = ['李彦宏', '马化腾', '马云', '柳传志', '雷军', '周鸿祎', "100", 50];
    var index = arr09.indexOf("马云");
    console.log("查看马云所处的具体位置(索引值)：" + index);
    //此处所查找数组元素的值必须与数组元素绝对匹配，不会进行数据类型的强制转换
    var index2 = arr09.indexOf(100);
    console.log("100所处的具体位置(索引值)：" + index2);
    var index3 = arr09.indexOf(50);
    console.log("50所处的具体位置(索引值)：" + index3);
    var showStr = "原数组为：[" + arr09 + "]<br>马云所处的具体位置(索引值)为：" + index + "<br>100所处的具体位置为：" + index2 + "<br>50所处的具体位置(索引值)为：" + index3;
    changeStyle(9, showStr);
}

//方法十：对数组排序 原数组将会改变
//语法：arrayObject.sort(sortby)
//sortby：可选。规定排序顺序。必须是函数。如果不选择该参数则按照字符编码的顺序进行排序，
function clickAction10() {
    //默认按照数值大小排序
    var arr10 = [8, 6, 1, 2, 4];
    //备份原数组
    var arr10_05 = arr10.slice();
    var arr10_01 = arr10.sort();
    console.log("默认按照数值大小排序：" + arr10_01);
    //默认按照字符串的字母顺序进行排序
    var arr10_02 = ["808", "60", "1100", "28", "450"];
    //备份原数组
    var arr10_06 = arr10_02.slice();
    var arr10_07 = arr10_02.slice();

    var arr10_03 = arr10_02.sort();
    console.log("按照字符串的字母顺序进行排序：" + arr10_03);

    //加入参数(排序函数)将字符串数组按照数值排序
    var arr10_04 = arr10_07.sort(sortNumber);
    console.log("使用函数后按照数值大小排序：" + arr10_04);

    var showStr = "原数组(数值数值)：[" + arr10_05 + "]<br>默认按照数值大小排序后：[" + arr10_01 + "]<br>原数组(字符串)：[" + arr10_06 + "]<br>默认按照字符串的字母顺序进行排序后：[" + arr10_03 + "]<br>使用函数后按照数值大小排序后：[" + arr10_04 + "]";
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

//自定义排序函数
function sortNumber(a, b) {
    return a - b;
}
