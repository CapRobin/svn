$(document).ready(function () {
    //	事件:onmouseover
    //	对象:li
    //	响应:变色 + 切换内容
    $('.column-header ul li').on('mouseover', function () {
        $(this).addClass('active').siblings().removeClass('active');

        // 找到我的序号 -> 根据序号找到对应的div -> 让其显示 & 让他的兄弟们隐藏
        var index = $.inArray(this, $(this).parent().children().toArray());
        var $div = $(this).parent().parent()
            .siblings('.column-body')
            .children(':nth-child({0})'.replace('{0}', index + 1));

        $div.show().siblings().hide();
    });

    $('.column-header ul li:first-child').trigger('mouseover');

    slideBanner(cnt);

    $('.banner ul li').on('mouseover', function () {
        clearTimeout(timeHandler)
    }).on('mouseout', function () {
        slideBanner(cnt);
    })
})

function slideBanner(ratio) {
    var liHeight = 46;
    $('.banner ul').animate({
        "top": "-{0}px".replace('{0}', liHeight * (ratio % total))
    }, 1000);

    timeHandler = window.setTimeout('slideBanner(++cnt)', 2000);
}

var timeHandler;
var cnt = 0;
var total = $('.banner ul > li').length;
// 1.与上面的等价
// 2.页面全部资源加载完之后，执行函数里的内容
//$(function(){
//	
//})