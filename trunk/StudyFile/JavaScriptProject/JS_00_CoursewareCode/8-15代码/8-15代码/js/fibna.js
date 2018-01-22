/**
 * Created by Administrator on 2017-8-15.
 */
//求斐波那契数列Fibonacci中的第n个数是多少？
function fib(n){
    var n1=1;
    var n2=1;
    for(var i=3;i<=n;i++){
        var temp=n2;
        n2=n1+n2;
        n1=temp;


    }
    return n2;
}

alert(fib(14));