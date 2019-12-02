$(function() {
    load();
    tms();
});
function load() {
    $.ajax({
        cache: true,
        type: "GET",
        url: "//127.0.0.1:8080/downPage/oe/lists",
//  async : true,
        datatype: "jsonp",
        jsonp: "callback",
        timeout: 3000,
        error: function (err) {
            alert(err.msg);
        },
        success: function (data) {
            if (data.code === 0) {
                // TODO 域名修改
                var url_post = "//127.0.0.1:8080";

                var len = data.lunbo.length;
                console.log(data.lunbo);

                let items = document.getElementById("lunbo_items");

                let event_li = document.createElement("li");
                var count = 0;
                for (; data.lunbo[count];) {
                    // 获取传来的数据并提出
                    let url = data.lunbo[count].url;
                    console.log("innnerHTML" + count);

                    event_li.innerHTML+="<img  height='470px'  src='"+url_post+url+"'  alt=''/>" ;
                    event_li.innerHTML+="<div class='banner'>" +
                        "<span class='text3'>水下视觉 </span>" +
                        "<span class='text1'>研究室</span>" +
                        "<span class='text2'>江苏海洋大学电子工程学院</span>" +
                        "</div>";
                    items.appendChild(event_li);
                    count++;
                }
            } else {
                alert(data.msg)
            }
        }
    })
}
function tms(){
$('.slider')._TMS({
	show:0,
	pauseOnHover:false,
	duration:1000,
	preset:'simpleFade',
	prevBu:'.prev',
	nextBu:'.next',
	pagination:false,
	pagNums:false,
	slideshow:7000,
	numStatus:false,
	banners:'fade',// fromLeft, fromRight, fromTop, fromBottom
	waitBannerAnimation:false})
}