$(function() {
  $(".navbar-expand-toggle").click(function() {
    $(".app-container").toggleClass("expanded");
    return $(".navbar-expand-toggle").toggleClass("fa-rotate-90");
  });
  return $(".navbar-right-expand-toggle").click(function() {
    $(".navbar-right").toggleClass("expanded");
    return $(".navbar-right-expand-toggle").toggleClass("fa-rotate-90");
  });
});

$(function() {
  return $('select').select2();
});

$(function() {
  return $('.toggle-checkbox').bootstrapSwitch({
    size: "small"
  });
});

$(function() {
  return $('.match-height').matchHeight();
});

$(function() {
  return $('.datatable').DataTable({
    "dom": '<"top"fl<"clear">>rt<"bottom"ip<"clear">>'
  });
});

$(function() {
  return $(".side-menu .nav .dropdown").on('show.bs.collapse', function() {
    return $(".side-menu .nav .dropdown .collapse").collapse('hide');
  });
});

// 写cookie
function setCookie(cname,cvalue){
    var d = new Date();
    d.setTime(d.getTime()+(30*60*1000));//30分钟
    var expires = "expires="+d.toGMTString();
    document.cookie = cname+"="+cvalue+"; "+expires;
};
// 读cookie
function getCookie(cname){
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name)==0) return c.substring(name.length,c.length);
    }
    return "";
};

function delCookie() {
    window.location.href="/toIndex";
        setCookie("ok","");
        setCookie("name","");
};

function decryption(cookie) {
    return (parseInt(cookie.substring(4, cookie.indexOf("S524ddnsub$72-//34)85"))) + 222 - 461)/ 313;
};

function getOk() {
    return "0";
};

window.onload=function(){
    if(getCookie("ok")=="" || getCookie("ok")=="error" || getCookie("ok")==null){
        if(window.location.pathname!="/toIndex")
            window.location.href="/toIndex";
        setTimeout(function(){
            $('#loginModal').modal('show');
        }, 230);
    }
    // 停车场管理员
    else{
        setCookie("ok",getCookie("ok"));
        setCookie("name",getCookie("name"));
        $('#tabName').text ("欢迎： " + getCookie("name"));
        $('#tabAdminName').text(getCookie("name"));

        if("0" != decryption(getCookie("ok"))){
            var te =/^\/toAdmin.{1,}$/;
            if(!te.test(window.location.pathname))
                window.location.href="/toAdminParking";
        }
        // 总管理员
        else {
            var te =/^\/toAdmin.{1,}$/;
            if(te.test(window.location.pathname))
                window.location.href="/toIndex";
        }
    }
};

function getLocalTime(d) {
    var date = new Date(d);
    var year = date.getFullYear();
    if(year == 2000)
        return "暂无数据";
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var min = date.getMinutes();
    var s = date.getSeconds();
    return getZero(year)+"-"+
            getZero(month)+"-"+
            getZero(day)+" "+
            getZero(hour)+":"+
            getZero(min)+":"+
            getZero(s);
}
function getZero(d) {
    if(0 <= d && 10>d){
        return "0"+d;
    }
    return d;
}