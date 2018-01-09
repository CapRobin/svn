//页面加载时执行
$(function () {
    //页面加载的时候初始化数据库
    initDatabase();

    //点击按钮时获取相关文本框的数据内容
    $("#saveBut").click(function () {
        //得到数据库对象
        var db = getCurrentDb();
        db.transaction(function (trans) {
            //使用JQuery获取三个文本框的内容
            var textName = $("#textName").val();
            var textTitle = $("#textTitle").val();
            var textWords = $("#textWords").val();
            //参数一：Sql语句
            var sql = "insert into testDemo(uName,title,words)values(?,?,?)";
            //参数二：占位符
            var zwf = [textName, textTitle, textWords];
            //参数三：执行SQL语句成功时调用回调函数
            var isSuccessCallback = function (ts, data) {
            };
            //参数四：执行SQL语句失败时调用回调函数，此处并提示错误信息
            var isFailCallback = function (ts, message) {
                alert(message)
            };
            //执行SQL语句插入数据
            trans.executeSql(sql, zwf, isSuccessCallback, isFailCallback);
        });
        //显示数据表格
        showAllItemData();
    });
});

//初始化数据库
function initDatabase() {
    var db = getCurrentDb();
    //如果数据库不存在，给用户给出提示，并直接结束运行！
    if (!db) {
        alert("抱歉，您的浏览器不支持HTML5本地数据库存储");
        return;
    }
    //如果数据库存在，则执行SQL语句创建数据表
    //参数一：Sql语句
    var sql = "create table if not exists testDemo(uName text null,title text null,words text null)";
    //参数二：占位符
    var zwf = [];
    //参数三：执行SQL语句成功时调用回调函数
    var isSuccessCallback = function (trans, resultData) {
    };
    //参数四：执行SQL语句失败时调用回调函数，此处并提示错误信息
    var isFailCallback = function (trans, errorMessage) {
        alert(errorMessage)
    };
    //创建Demo数据表
    db.transaction(function (trans) {
        trans.executeSql(sql, zwf, isSuccessCallback, isFailCallback);
    }, function (trans, result) {
    }, function (trans, message) {
    });
}


//创建或直接连接现有的数据库
function getCurrentDb() {
    var db = openDatabase("testDb", "1.0", "demo data", 1024 * 1024);
    return db;
}

//展示数据函数事件
function showAllItemData() {
    //移除数据显示表格中的所有子元素(清空表格)
    $("#showDataTb").empty();
    //得到数据库对象
    var db = getCurrentDb();
//            db.transaction();
    db.transaction(function (trans) {
        var headTitleStr = "<tr>\n" +
            "        <th>\n" +
            "            序号\n" +
            "        </th>\n" +
            "        <th>\n" +
            "            用户名\n" +
            "        </th>\n" +
            "        <th>\n" +
            "            留言标题\n" +
            "        </th>\n" +
            "        <th>\n" +
            "            留言内容\n" +
            "        </th>\n" +
            "    </tr>";
        $("#showDataTb").append(headTitleStr);

        //参数一：Sql语句
        var sql = "select * from testDemo";
        //参数二：占位符
        var zwf = [];
        //参数三：执行SQL语句成功时调用回调函数
        var isSuccessCallback = function (tsn, data) {
            if (data) {

                //循环记录集中的数据
                for (var i = 0; i < data.rows.length; i++) {
                    //获取每一行数据的Json对象(键值对组合)，并将其拼接成表格中一行一行的数据
                    appendDataToTable(data.rows.item(i), i + 1);
                }
            }
        };
        //参数四：执行SQL语句失败时调用回调函数，此处并提示错误信息
        var isFailCallback = function (tsn, message) {
            alert(message)
        };
        //执行SQL语句查询所有数据
        trans.executeSql(sql, zwf, isSuccessCallback, isFailCallback);
    })
}

//将JSon串装载到Table表格中
function appendDataToTable(jsonData, n) {
    var textName = jsonData.uName;
    var textTitle = jsonData.title;
    var textWords = jsonData.words;
    var dataStr = "";
    dataStr += "<tr>";
    dataStr += "<td>" + n + "</td>";
    dataStr += "<td>" + textName + "</td>";
    dataStr += "<td>" + textTitle + "</td>";
    dataStr += "<td>" + textWords + "</td>";
    dataStr += "</tr>";

    //将该行数据通过JQuery脚本添加到Table表格中去
    $("#showDataTb").append(dataStr);

}
