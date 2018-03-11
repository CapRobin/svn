$(function () {
    /*控制NAV点击滑动效果*/
    $('.mNav a').click(function () {
        var item = $('.mNav a').index(this);
        var item_top = $('.mBody>div:nth-child(' + (item + 2) + ')').offset().top;
        $('html,body').animate({scrollTop: item_top - 70}, 700);
        if (item > 0) {
            $('.header').addClass('header-bg');
            console.log('设置');
        } else {
            $('.header').removeClass('header-bg');
            console.log('移除');
        }
    });

});