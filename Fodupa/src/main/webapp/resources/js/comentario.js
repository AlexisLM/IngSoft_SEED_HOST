/**
 * Gets a copy of the string with all duplicate characters removed.
 * @param {string} str Input string which will be duplicated and whose duplicate
 *                     characters will be removed.
 * @returns {string}   A copy of str with all duplicate characters removed.
 */
function uniqChars(str) {
    return str.replace(/[\s\S](?=([\s\S]+))/g, function(c, s) {
        return s.indexOf(c) + 1 ? '' : c;
    });
}

/**
 * [toggleCommentModals description]
 * @return {[type]} [description]
 */
function toggleCommentModals(){
    $("#reply").toggleClass("hide");            //Create comment input
    $("#addComentario").toggleClass("hide");    //Comment modal
}

/**
 * [validateContentLength contenido]
 * @return {[type]} [contenido]
 */
function validateContentLength(){
    return $("#form_comment\\:contenido").val().replace(/^\s*/, "").length > 0;
}

/**
 * [validateDetails description]
 * @return {[type]} [description]
 */
function validateContent(){
    var pattern = /^[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$/;
    return pattern.test($("#form_comment\\:contenido").val());
};

/**
 * [getContenidoInvalidChars contenido]
 * @return {[type]} [contenido]
 */
function getContentInvalidChars(){
    var pattern = /[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]/g;
    return uniqChars($("#form_comment\\:contenido").val().replace(pattern,""));
};

/**
 * [successStatusContent contenido]
 * @return {[type]} [contenido]
 */
function successStatusContent(){
    $("#form_comment\\:contenido").addClass('is-valid');
    $("#form_contenido\\:contenido").removeClass('is-invalid');
    $("label[for='contenido']").addClass('text-success');
    $("label[for='contenido']").removeClass('text-danger');
    $("#error_contenido").addClass("hide");
}

/**
 * [errorStatusContent contenido]
 * @return {[type]} [contenido]
 */
function errorStatusContent(){
    $("#form_comment\\:contenido").addClass('is-invalid');
    $("#form_comment\\:contenido").removeClass('is-valid');
    $("label[for='contenido']").addClass('text-danger');
    $("label[for='contenido']").removeClass('text-success');
    $("#error_contenido").removeClass("hide");
}

window.onload = function(){

    // Div [reply] event handler
    $("#reply").on("click", toggleCommentModals);

    //Input [contenido] event handler
    $("#form_comment\\:contenido").on("keyup", function(){
        if(!validateContentLength()){
            errorStatusContent();
            $("#error_contenido").text("Lo sentimos, la longitud del título debe contener al menos 1 caracter.");
        } else if(!validateContent()){
            errorStatusContent();
            $("#error_contenido").text("Lo sentimos, los siguientes caracteres no son válidos: " + getContentInvalidChars());
        }
        else
            successStatusContent();
    });

    //Submit form event handler
    $("#form_comment").submit(function(e){
        if(!validateContentLength() || !validateContent())
            return false;
    });

}