window.onload= function () {

    var per={
        setName:function (name){
            per.name=name;
            return this;
        },
        setAge:function (age){
            per.age=age;
            return this;
        }
    };

    per.setName("heheh").setAge(65);
}

function $(){
    alert("自己的$");
}




