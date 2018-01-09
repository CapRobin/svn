/*Break与Continue
* Break：表示终止(中断)当前(整个)循环，循环余下次数不会再执行循环
* Continue：表示终止本次循环，进行下一次循环
* Break和Continue之后不能再写代码(写了也不执行)，“之后”表示处在同一个顺序结构中*/

/*案例一*/
function showView01() {
    var sum = 0;
    for (var i = 1; i <= 1000; i++) {
        if (i % 7 === 0) {
            console.log("能被整除的整数为*********************>>" + i);
            continue;
        }
        sum += i;
        console.log("不能被整除的整数为——————>>" + i);
    }
    //JavaScript修改CSS样式
    changeStyle(1);
    showView.innerHTML = "不能被7整除的整数之和为：" + sum;
}

/*案例二*/
function showView02() {
    var sumStr = 0;
    var j = 0;
    for (var i = 1; i <= 100; i++) {
        if (i % 3 !== 0) {
            sumStr += i;
            j++;
            if (sumStr > 2000) {
                console.log("不能被3整除且求和大于2000的整数为***************>" + i);
                break;
            } else {
                console.log("不能被3整除且求和小于2000的第" + j + "个整数为——————>" + i);
            }
        }
    }

    //JavaScript修改CSS样式
    changeStyle(2);
    showView.innerHTML = "第一个大于2000的数为：" + sumStr;
}


//案例三
function showView03() {
    var j = 0;
    for (var i = 200; i <= 300; i++) {
        if (i % 7 === 0) {
            break;
        } else {
            j++;
            console.log("200~300之间第一个能被7整除的数的之前的第" + j + "个数是———————>>" + i)
        }
    }

    //JavaScript修改CSS样式
    changeStyle(3);
    showView.innerHTML = "第一个能被7整除的整数为：" + i;
}

/*JavaScript修改CSS样式*/
function changeStyle(key) {
    var getId1 = document.getElementById("pId01");
    var getId2 = document.getElementById("pId02");
    var getId3 = document.getElementById("pId03");

    switch (key) {
        case 1:
            getId1.style.color = "red";
            getId2.style.color = "black";
            getId3.style.color = "black";
            break;
        case 2:
            getId1.style.color = "black";
            getId2.style.color = "red";
            getId3.style.color = "black";
            break;
        case 3:
            getId1.style.color = "black";
            getId2.style.color = "black";
            getId3.style.color = "red";
            break;
        default:
            break;
    }
}
