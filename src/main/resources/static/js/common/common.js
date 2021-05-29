let header = $("meta[name='_csrf_header']").attr('content');
let token = $("meta[name='_csrf']").attr('content');
function ajaxRequest(type, url,requestObject, successFunction, errorFunction) {
    $.ajax({
        type : type,
        url : "/api/" + url,
        data : JSON.stringify(requestObject),
        beforeSend : function(xhr) {
            xhr.setRequestHeader(header, token);
        },
        contentType : "application/json",
        success : function(result) {
            successFunction(result);
        },
        error : function(err) {
            errorFunction(err);
        }
    });
}