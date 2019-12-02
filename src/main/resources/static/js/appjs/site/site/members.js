$.ajax({
    cache: true,
    type: "GET",
    url: "//127.0.0.1:8080/site/members/oe/lists",
//  async : true,
    datatype: "jsonp",
    jsonp:"callback",
    timeout: 3000,
    error: function (err) {
        alert(err.msg);
    },
    success: function (data) {
        if (data.code == 0) {
            var url_post = "//127.0.0.1:8080/files/";

            var len = data.memberList.length;


            var div_main_member = document.getElementById("main_member");
            var div_vip_member = document.getElementById("vip_member");
            var div_s_member_1 = document.getElementById("s_member_1");
            var div_s_member_2 = document.getElementById("s_member_2");
            // var event_li = document.createElement("li");
            var count = 0;
            var mem_count=0;
            for (; data.memberList[count];) {
                // 获取传来的数据并提出
                //var mid = data.memberList[count].mid;
                var mname = data.memberList[count].mname;
                var mnameEng = data.memberList[count].mnameEng;
                var pic = data.memberList[count].pic;
                var mjob = data.memberList[count].mjob;
                var mcategory = data.memberList[count].mcategory;
                var mintroduce = data.memberList[count].mintroduce;
                var mintroduceEng = data.memberList[count].mintroduceEng;
                var mResearea = data.memberList[count].mResearea;
                var mReseareaEng = data.memberList[count].mReseareaEng;

                //alert(mname);
                let page_box1 = document.createElement("div");
                page_box1.className = "page2-box1";
                let page_box2 = document.createElement("div");
                page_box2.className = "page2-box2";
                let page_box3 = document.createElement("div");
                page_box3.className = "page2-box3";

//              console.log(mname);
                if(mcategory==="m_member"){
                    page_box1.innerHTML+= "<figure class='page2-img2 img-bot'><img  width='120px' src='"+url_post+pic+"'  alt='"+mname+" "+mjob+"'/></figure>";
                    page_box1.innerHTML+= "<div class=\"clear\"></div>";
                    page_box1.innerHTML+= "<span class='text4'>"+mname+"</span>"
                    page_box1.innerHTML+= "<p class='p6'>"+mintroduce+"</p>"
                    page_box1.innerHTML+= " <a class='button2' href='#'>more</a>"
                    // console.log(mname);
                    div_main_member.appendChild(page_box1);
                }else if(mcategory==="v_member"){
                    page_box2.innerHTML+= "<figure class='page2-img2 img-bot'><img  width='120px' src='"+url_post+pic+"'  alt='"+mname+" "+mjob+"'/></figure>";
                    page_box2.innerHTML+= "<div class=\"extra-wrap\">" +
                        "<span class=\"text4\">"+mname+"</span>\n" +
                        "<p>"+mintroduce +
                        "<a class='link1' href='#'>" +
                        "</a></p></div>";
                    page_box2.innerHTML+= "<div class=\"clear\"></div>";
                   div_vip_member.appendChild(page_box2);
                }else if(mcategory==="s_member"){
                    mem_count++;
                    page_box3.innerHTML+= "<figure class='page2-img2 img-bot'><img  width='120px' src='"+url_post+pic+"'  alt='"+mname+" "+mjob+"'/></figure>";
                    page_box3.innerHTML+= "<div class=\"extra-wrap\">" +
                        "<span class=\"text4\">"+mname+"</span>\n" +
                        "<p>"+mintroduce+
                        "<a class='link1' href='#'>" +
                        "</a></p></div>";
                    page_box3.innerHTML+= "<div class=\"clear\"></div>";
                    if(mem_count%2!==0){
                        div_s_member_1.appendChild(page_box3);
                    }else {
                        div_s_member_2.appendChild(page_box3);
                    }

                }
                // else if(mcategory==="Former"){
                //     ;
                // }
                count++;
            }
        } else {
            alert(data.msg)
        }
    }
});