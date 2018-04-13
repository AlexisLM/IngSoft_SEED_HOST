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
 * [toggleQuestionModals description]
 * @return {[type]} [description]
 */
function toggleQuestionModals(){
    $("#addQuestion-cont").toggleClass("hide"); //Create question input
    $("#ask").toggleClass("hide");        //Question modal
}

/**
 * [validateTitle description]
 * @return {[type]} [description]
 */
function validateTitle(){
    var pattern = /^[A-Za-z0-9áéíóúÁÉÍÓÚñÑ¿?!¡"]{2}[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\s"]{0,48}$/;
    return pattern.test($("#form_question\\:title").val());
};

/**
 * [validateDetails description]
 * @return {[type]} [description]
 */
function validateDetails(){
    var pattern = /^[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]*$/;
    return pattern.test($("#form_question\\:details").val());
};

/**
 * [validateTitleLength description]
 * @return {[type]} [description]
 */
function validateTitleLength(){
    return $("#form_question\\:title").val().replace(/^\s*/, "").length >= 5 &&
           $("#form_question\\:title").val().length <= 50;
}

/**
 * [getTitleInvalidChars description]
 * @return {[type]} [description]
 */
function getTitleInvalidChars(){
    var pattern = /[A-Za-z0-9áéíóúÁÉÍÓÚñÑ!¡¿?\s"]/g;
    return uniqChars($("#form_question\\:title").val().replace(pattern,""));
}

/**
 * [getDetailsInvalidChars description]
 * @return {[type]} [description]
 */
function getDetailsInvalidChars(){
    var pattern = /[A-Za-z0-9\s¿?+-_.*/{}()%&amp;#$@|!¡;,:áé\níóúÁÉÍÓÚñÑ"]/g;
    return uniqChars($("#form_question\\:details").val().replace(pattern,""));
};

/**
 * [successStatusTitle description]
 * @return {[type]} [description]
 */
function successStatusTitle(){
    $("#form_question\\:title").addClass('is-valid');
    $("#form_question\\:title").removeClass('is-invalid');
    $("label[for='title']").addClass('text-success');
    $("label[for='title']").removeClass('text-danger');
    $("#error_title").addClass("hide");
}

/**
 * [successStatusDetails description]
 * @return {[type]} [description]
 */
function successStatusDetails(){
    $("#form_question\\:details").addClass('is-valid');
    $("#form_question\\:details").removeClass('is-invalid');
    $("label[for='details']").addClass('text-success');
    $("label[for='details']").removeClass('text-danger');
    $("#error_details").addClass("hide");
}

/**
 * [errorStatusTitle description]
 * @return {[type]} [description]
 */
function errorStatusTitle(){
    $("#form_question\\:title").addClass('is-invalid');
    $("#form_question\\:title").removeClass('is-valid');
    $("label[for='title']").addClass('text-danger');
    $("label[for='title']").removeClass('text-success');
    $("#error_title").removeClass("hide");
}

/**
 * [errorStatusDetails description]
 * @return {[type]} [description]
 */
function errorStatusDetails(){
    $("#form_question\\:details").addClass('is-invalid');
    $("#form_question\\:details").removeClass('is-valid');
    $("label[for='details']").addClass('text-danger');
    $("label[for='details']").removeClass('text-success');
    $("#error_details").removeClass("hide");
}

window.onload = function(){

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
                                   "ser de 5 a 50 caracteres.");
        }
        else if(!validateTitle()){
            errorStatusTitle();
            $("#error_title").text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getTitleInvalidChars());
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
}