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
 * Toggles question input and question modal
 * @return {void}
 */
function toggleQuestionModals(){
    $("#addQuestion-cont").toggleClass("hide"); //Create question input
    $("#ask").toggleClass("hide");        //Question modal
}

/**
 * Validates value of title. [At least 2 chars, letters, numbers, question 
 * marks, exclamation marks and spaces]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateTitle(){
    var pattern = /^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡"][A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\s"]{0,45}$/;
    return pattern.test($("#form_question\\:title").val());
};

/**
 * Validates value of details. [At least 2 chars, letters, numbers, question 
 * marks, exclamation marks, arithmetic operators, braces, parenthesis, pipes, 
 * ampersands, punctuation marks, at, underscores, sharps, line brakes and 
 * spaces]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateDetails(){
    var pattern = /^[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$/;
    return pattern.test($("#form_question\\:details").val());
};

/**
 * Validates the length of the title. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateTitleLength(){
    return $("#form_question\\:title").val().replace(/^\s*/, "").length >= 1 &&
           $("#form_question\\:title").val().length <= 50;
}

/**
 * Gets the invalid chars of the title.
 * @return {string} The invalid chars of the title.
 */
function getTitleInvalidChars(){
    var pattern = /[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\s"]/g;
    return uniqChars($("#form_question\\:title").val().replace(pattern,""));
}

/**
 * Gets the invalid chars of the details.
 * @return {string} The invalid chars of the details.
 */
function getDetailsInvalidChars(){
    var pattern = /[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]/g;
    return uniqChars($("#form_question\\:details").val().replace(pattern,""));
};

/**
 * Set a success status for the title field.
 * @return {void} 
 */
function successStatusTitle(){
    $("#form_question\\:title").addClass('is-valid');
    $("#form_question\\:title").removeClass('is-invalid');
    $("label[for='title']").addClass('text-success');
    $("label[for='title']").removeClass('text-danger');
    $("#error_title").addClass("hide");
}

/**
 * Set a success status for the details field.
 * @return {void} 
 */
function successStatusDetails(){
    $("#form_question\\:details").addClass('is-valid');
    $("#form_question\\:details").removeClass('is-invalid');
    $("label[for='details']").addClass('text-success');
    $("label[for='details']").removeClass('text-danger');
    $("#error_details").addClass("hide");
}

/**
 * Set a error status for the title field.
 * @return {void} 
 */
function errorStatusTitle(){
    $("#form_question\\:title").addClass('is-invalid');
    $("#form_question\\:title").removeClass('is-valid');
    $("label[for='title']").addClass('text-danger');
    $("label[for='title']").removeClass('text-success');
    $("#error_title").removeClass("hide");
}

/**
 * Set a error status for the details field.
 * @return {void} 
 */
function errorStatusDetails(){
    $("#form_question\\:details").addClass('is-invalid');
    $("#form_question\\:details").removeClass('is-valid');
    $("label[for='details']").addClass('text-danger');
    $("label[for='details']").removeClass('text-success');
    $("#error_details").removeClass("hide");
}

/* -------------------------------------------------------------------------- */
/**
 * [showCommentModal description]
 * @return {[type]} [description]
 */
function showCommentModal(element){
    $(element).toggleClass("hide");            //Create comment input
    $(element).parents(".comment-main-level").next().toggleClass("hide");
}

/**
 * [showCommentModal description]
 * @return {[type]} [description]
 */
function hideCommentModal(element){
    $(element).parents(".addComentario").toggleClass("hide");
    $(element).parents(".addComentario").prev().find(".reply_question").toggleClass("hide");
}

/**
 * [validateContentLength contenido]
 * @return {[type]} [contenido]
 */
function validateContentLength(element){
    return $(element).val().replace(/^\s*/, "").length > 0;
}

/**
 * [validateDetails description]
 * @return {[type]} [description]
 */
function validateContent(element){
    var pattern = /^[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$/;
    return pattern.test($(element).val());
}

/**
 * [getContenidoInvalidChars contenido]
 * @return {[type]} [contenido]
 */
function getContentInvalidChars(element){
    //console.log(element);
    var pattern = /[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]/g;
    return uniqChars($(element).val().replace(pattern,""));
}

/**
 * [successStatusContent contenido]
 * @return {[type]} [contenido]
 */
function successStatusContent(element){
    $(element).addClass('is-valid');
    $(element).removeClass('is-invalid');
    $(element).next().addClass("hide");
}

/**
 * [errorStatusContent contenido]
 * @return {[type]} [contenido]
 */
function errorStatusContent(element){
    $(element).addClass('is-invalid');
    $(element).removeClass('is-valid');
    //$("label[for='contenido']").addClass('text-danger');
    //$("label[for='contenido']").removeClass('text-success');
    $(element).next().removeClass("hide");
}


function showPageQuestion(e){
    $(".ocultable").hide();
    var j = parseInt($(e).text())*9;
    for (var i = j; i >= j-9; i--) {
        $("."+i).show();
    }
    
}


function initPage(){
    for (var i = 0; i <= 9; i++) {
        $("."+i).removeClass("hide");

    }
}

$(document).ready(function(){

    initPage();

    // Div [Realiza pregunta...] event handler
    $("#addQuestion-cont").on("click", toggleQuestionModals);

    //Button [cancel] event handler
    $(".btn-question").on("click", function(){
        toggleQuestionModals();
        //Reset form
        $("#form_question\\:title, #form_question\\:details").val("");
        $("#form_question\\:title").removeClass("is-valid is-invalid");
        $("label[for='title']").removeClass("text-success text-danger");
        $("#form_question\\:details").removeClass("is-invalid");
        $("#form_question\\:details").addClass("is-valid");
        $("label[for='details']").removeClass("text-danger");
        $("label[for='details']").addClass("text-success");
        $("#error_title").addClass("hide");
        $("#error_details").addClass("hide");
    });

    //Input [title] event handler
    $("#form_question\\:title").on("keyup", function(){
        if(!validateTitleLength()){
            errorStatusTitle();
            $("#error_title").text("Lo sentimos, la longitud del título debe "+
                                   "ser de 1 a 50 caracteres.");
        }
        else if(!validateTitle()){
            errorStatusTitle();
            if($(this).val().startsWith(" "))
                $("#error_title").text("Lo sentimos, no se permiten espacios "+
                                       "al inicio del título.");
            else    
                $("#error_title").text("Lo sentimos, los siguientes caracteres "+
                                       "no son válidos: "+getTitleInvalidChars());
        }
        else
            successStatusTitle();
    });

    //Input [details] event handler
    $("#form_question\\:details").on("keyup", function(){
        if(validateDetails())
            successStatusDetails();
        else{
            errorStatusDetails();
            $("#error_details").text("Lo sentimos, los siguientes caracteres "+
                                "no son válidos: "+getDetailsInvalidChars());
        }
    });

    //Submit form event handler
    $("#form_question").submit(function(e){
        if(!validateTitleLength() || !validateTitle() || !validateDetails())
            return false;
    });
    
    // Div [reply] event handler
    $(".reply_question").on("click", function(){showCommentModal(this);});
    $(".btn-comment").on("click", function(){
        $(this).siblings(".contenido").val("");
        $(this).siblings(".contenido").removeClass("is-valid is-invalid");
        $(this).siblings(".error_contenido").addClass("hide");
        hideCommentModal(this);
    });
    // 

    //Input [contenido] event handler
    $(".form_comment .contenido").on("keyup", function(){
        if(!validateContentLength(this)){
            errorStatusContent(this);
            $(this).parent().children(".error_contenido").text("Lo sentimos, la"+
            " longitud del título debe contener al menos 1 caracter.");
        } else if(!validateContent(this)){
            errorStatusContent(this);
            $(this).parent().children(".error_contenido").text("Lo sentimos, los"+
            " siguientes caracteres no son válidos: " + getContentInvalidChars(this));
        }
        else
            successStatusContent(this);
    });

    //Submit form event handler
    $(".form_comment").submit(function(e){
        if(!validateContentLength($(this).children(".contenido")) || 
           !validateContent($(this).children(".contenido")))
            return false;
    });

});
