$().ready(function() {
});
function jumpTo(urlTo) {
    console.log("jump地址:"+urlTo)
    $.ajax({
        cache : true,
        type : "get",
        url :  ""+urlTo,
        //data : $('#signupForm').serialize(),// 你的formid
        async : false,
        error : function(request) {
            console.log("Connection error");
        },
        success : function(request) {

        }
    });

}