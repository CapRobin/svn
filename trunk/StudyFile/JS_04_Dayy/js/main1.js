/*打印正方形*/
function showView1() {
    var str1 = "";
    for (var i = 1; i <= 10; i++) {
        for (var j = 1; j <= 10; j++) {
            if (j > 1 && j < 10) {
                if (i > 1 && i < 10) {
                    str1 += "&nbsp;&nbsp;&nbsp;";
                } else {
                    str1 += "*&nbsp;&nbsp;";
                }
            } else {
                str1 += "*&nbsp;&nbsp;";
            }
        }
        str1 += "<br>"
    }
    isShow(str1, "", "", "", "");
}

//打印直角三角形
function showView2() {
    var str2 = "";
    for (var i = 1; i <= 9; i++) {
        for (var j = 1; j <= i; j++) {
            str2 += "*";
        }
        str2 += "<br>"
    }
    isShow("", str2, "", "", "");
}

//打印九九乘法表
function showView3() {
    var str3 = "";
    for (var i = 1; i <= 9; i++) {
        var trStrStart = "<tr>";
        for (var j = 1; j <= i; j++) {
            str3 += "<td>" + i + "x" + j + "=" + i * j + "</td>";
        }
        var trStrEnd = "<tr>";
        str3 = trStrStart + str3 + trStrEnd;
    }
    isShow("", "", str3, "", "");
}

//求被7整除的整数的数字之和
function showView4() {
    var sum = 0;
    var numStr = "";
    for (var i = 1; i <= 100; i++) {
        if (i % 7 !== 0) {
            numStr = numStr + i.toString() + "、 ";
            sum += i;
        } else {

        }
    }
    var str = "1~100内能被7整除的数有：<br>" + numStr + "<br>他们的求和结果为：" + sum;
    isShow("", "", "", str, "");
}

//求被3整除的整数的和大于或等于2000的所有数字
function showView5() {
    var numStr1 = "";
    var numStr2 = "";
    var numStr3 = "";
    var sum1 = 0;
    var sum2 = 0;
    var sum3 = 0;
    for (var i = 1; i <= 100; i++) {
        if (i % 3 !== 0) {
            //输出1~100内所有能被3整除的数
            numStr1 = numStr1 + i.toString() + "、 ";
            //输出1~100内所有能被3整除的整数的数字之和
            sum1 += i;
            if (sum1 < 2000) {
                //输出1~100内能被3整除的整数的和小于2000的所有数字
                numStr2 = numStr2 + i.toString() + "、 ";
                //输出1~100内能被3整除的整数的和小于2000的所有数字之和
                sum2 = sum1;
            } else {
                //输出1~100内能被3整除的整数的和大于或等于2000的所有数字
                numStr3 = numStr3 + i.toString() + "、 ";
                //输出1~100内能被3整除的整数的和大于或等于2000的所有数字之和
                sum3 += i;
                console.log("++++求和大于2000的数；" + numStr2);
            }
        }
    }
    console.log(sum2);
    // console.log(sum3);
    var str = "1~100内能被3整除的整数有：<br>" + numStr1 + "<br>他们的求和结果为：" + sum1 + "<br>1~100内能被3整除的整数的和小于2000的所有数字<br>" + numStr2 + "<br>他们的求和结果为：" + sum2 + "<br>1~100内能被3整除的整数的和大于或等于2000的所有数字<br>" + numStr3 + "<br>他们的求和结果为：" + sum3;
    isShow("", "", "", "", str);
}

function isShow(inner1, inner2, inner3, inner4, inner5) {
    showId01.innerHTML = inner1;
    showId02.innerHTML = inner2;
    showId03.innerHTML = inner3;
    showId04.innerHTML = inner4;
    showId05.innerHTML = inner5;
}
