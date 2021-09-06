$(document).ready(function() {
    $("#btn").click(function() {
        // console.log("Hello");
        // console.log($("#urlinput").val());
        $.ajax({
            type : 'POST',
            url : 'http://localhost:8080/shortenurl',
            data : JSON.stringify({
                "full_url" : $("#urlinput").val()
            }),
            contentType : "application/json; charset=utf-8",
            success : function(data) {
                $("#fullURL").html($("#urlinput").val());
                $("#shorturltext").html(data.short_url);
                $("#shorturltext").attr("href", data.short_url);

                $("#lab").css({display:"none"});
                $("#urlinput").css({'background-image':"none"});
                if($(".shortend").is(':hidden')) {
                    $(".shortend").css({display: "flex"});
                    console.log("work");
                }
                if($("#copy").html() === "Copied") {
                    $("#copy").html("Copy");
                }
            },
            error : function () {
                $("#lab").css({display:"block"});
                $("#urlinput").css({'background-image':"url(images/icon-error.svg)"});
                if(($(".shortend").css('display') !== 'none')) {
                    $(".shortend").css({display: "none"});
                }
            }
        });
    });
    $("#copy").click(function() {
        copyToClipboard("#shorturltext");
        $("#copy").html("Copied");
    });
});

function copyToClipboard(element) {
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val($(element).text()).select();
    document.execCommand("copy");
    $temp.remove();
}