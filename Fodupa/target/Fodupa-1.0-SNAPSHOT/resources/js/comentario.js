/**
 * [validateTitleLength description]
 * @return {[type]} [description]
 */
function validateContentLength(){
    return $("#form_content\\:contenido").val().replace(/^\s*/, "").length > 0;
}

/**
 * [errorStatusContent contenido]
 * @return {[type]} [contenido]
 */
function errorStatusContent(){
    $("#form_content\\:contenido").addClass('is-invalid');
    $("#form_content\\:contenido").removeClass('is-valid');
    $("label[for='contenido']").addClass('text-danger');
    $("label[for='contenido']").removeClass('text-success');
    $("#error_contenido").removeClass("hide");
}

window.onload = function(){

    //Input [conten] event handler
    $("#form_content\\contenido").on("keyup", function(){
        if(!validateContentLength()){
            errorStatusContent();
            $("#error_content").text("Lo sentimos, la longitud del título debe contener a lo menos 1 caracter.");
        }
    });

}