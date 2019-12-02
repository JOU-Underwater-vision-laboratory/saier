$().ready(function() {

});

function jump(status,url) {
    prefix = "/jump";
    console.log("jump00地址"+url);
    if (status===0){
    // 门户网站页面跳转
        jumpTo(prefix+"/0/"+url);
    } else if (status===1) {
    // 非门户网站页面跳转

    }
}