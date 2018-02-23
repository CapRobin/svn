$(function () {
    /*相应变色*/
    $('.column-header ul li').on('mouseover', function () {
        $(this).addClass('active').siblings().removeClass('active');

        /*相应变色*/
        var index = $.inArray(this, $(this).parent().children().toArray());
        var $div = $(this)
            .parent().parent()
            .siblings('.column-body')
            .children(':nth-child(' + (index) + ')');
        console.info(typeof $div);
        $div.show().siblings().hide();
    });
    $('.column-header ul li:first-child').trigger('mouseover');


});
/*方式二*/
// $(document).ready(function () {
//
// });