/*方式一 === 方式二*/
/*方式一*/
$(function () {
    /*相应变色*/
    $('.column-header ul li').on('mouseover', function () {
        $(this).addClass('active').siblings().removeClass('active');

        /*相应变色*/
        $(this).parent().children();
        var index = $.inArray(this, $(this).parent().children().toArray());
        $(this).parent().parent().siblings('.column-body').children(':nth-child(' + (index) + ')');
        $div.show().siblings().hide();
    });
    $('.column-header ul li:first-child').trigger('mouseover');


});
/*方式二*/
// $(document).ready(function () {
//
// });