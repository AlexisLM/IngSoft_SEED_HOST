/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Gets a copy of the string with all duplicate characters removed.
 * @param {string} str Input string which will be duplicated and whose duplicate
 *                     characters will be removed.
 * @returns {string}   A copy of str with all duplicate characters removed.
 */
function uniqCharsMP(str) {
    return str.replace(/[\s\S](?=([\s\S]+))/g, function(c, s) {
        return s.indexOf(c) + 1 ? '' : c;
    });
}

/**
 * Validates value of title. [At least 3,50 char]
 * @param {type} id
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateNameMP(id){
    var pattern = /^[A-Za-záéíóúÁÉÍÓÚñÑ]{2}[A-Za-záéíóúÁÉÍÓÚñÑ ]{0,47}$/;
    return pattern.test($("#form_modifica_perfil\\:"+id).val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordMP(){
    var pattern = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/;
    return pattern.test($("#form_modifica_perfil\\:password").val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmMP(){
    var password = $("#form_modifica_perfil\\:password").val();
    var confirm = $("#form_modifica_perfil\\:confirm").val();
    
    return (password === confirm);
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePictureMP(){
    var pattern = /.+\.(png|jpg|bmp|jpeg|PNG|JPG|BMP|JPEG)$/;
    return pattern.test($("#form_modifica_perfil\\:picture").val());
}

/**
 * Validates if there is at least one career selected.
 * @returns {Boolean} True if the test passes, false otherwise.
 */
function validateCareer(){
    var check = false;
    $(".carreras").each(function() {
        check = check || ($(this).val() != "0");
    });
    
    return !check;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @param id recibe el id de la parte del nombre a revisar.
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateNameMPLengthMP(id){
    return $("#form_modifica_perfil\\:"+id).val().replace(/^\s*/, "").length >= 3 &&
           $("#form_modifica_perfil\\:"+id).val().length <= 50;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordLengthMP(){
    return $("#form_modifica_perfil\\:password").val().replace(/^\s*/, "").length >= 8 &&
           $("#form_modifica_perfil\\:password").val().length <= 20;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmMPLengthMP(){
    return $("#form_modifica_perfil\\:confirm").val().replace(/^\s*/, "").length >= 3 &&
           $("#form_modifica_perfil\\:confirm").val().length <= 50;
}

/**
 * Gets the invalid chars of the name.
 * @param {type} id
 * @returns {String} The invalid chars of the name.
 */
function getNameInvalidCharsMP(id){
    var pattern = /[A-Za-záéíóúÁÉÍÓÚñÑ\s]/g;
    return uniqCharsMP($("#form_modifica_perfil\\:"+id).val().replace(pattern,""));
}

/**
 * Gets the invalid chars of the email.
 * @return {string} The invalid chars of the email.
 */
function getPasswordInvalidCharsMP(){
    var pattern = /[A-Za-z/d]/g;
    return uniqCharsMP($("#form_modifica_perfil\\:password").val().replace(pattern,""));
};


/**
 * Set a success status for the name field.
 * @param {type} id
 * @return {void} 
 */
function successStatusMPName(id){
    $("#form_modifica_perfil\\:"+id).addClass('is-valid');
    $("#form_modifica_perfil\\:"+id).removeClass('is-invalid');
    $("label[for='"+id+"']").addClass('text-success');
    $("label[for='"+id+"']").removeClass('text-danger');
    $("#error_"+id).addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusMPPassword(){
    $("#form_modifica_perfil\\:password").addClass('is-valid');
    $("#form_modifica_perfil\\:password").removeClass('is-invalid');
    $("label[for='password']").addClass('text-success');
    $("label[for='password']").removeClass('text-danger');
    $("#error_password").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusMPPasswordConfirm(){
    $("#form_modifica_perfil\\:confirm").addClass('is-valid');
    $("#form_modifica_perfil\\:confirm").removeClass('is-invalid');
    $("label[for='confirm']").addClass('text-success');
    $("label[for='confirm']").removeClass('text-danger');
    $("#error_confirm").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusMPPicture(){
    $("#form_modifica_perfil\\:picture").addClass('is-valid');
    $("#form_modifica_perfil\\:picture").removeClass('is-invalid');
    $("label[for='picture']").addClass('text-success');
    $("label[for='picture']").removeClass('text-danger');
    $("#error_picture").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusMPFill(){
    $("#error_fill").addClass("hide");
}

/**
 * Set a error status for the name field.
 * @param {string} id. 
 * @return {void} 
 */
function errorStatusMPName(id){
    $("#form_modifica_perfil\\:"+id).addClass('is-invalid');
    $("#form_modifica_perfil\\:"+id).removeClass('is-valid');
    $("label[for='"+id+"']").addClass('text-danger');
    $("label[for='"+id+"']").removeClass('text-success');
    $("#error_"+id).removeClass("hide");
}


/**
 * Set a error status for the password field.
 * @return {void} 
 */
function errorStatusMPPassword(){
    $("#form_modifica_perfil\\:password").addClass('is-invalid');
    $("#form_modifica_perfil\\:password").removeClass('is-valid');
    $("label[for='password']").addClass('text-danger');
    $("label[for='password']").removeClass('text-success');
    $("#error_password").removeClass("hide");
}

/**
 * Set a error status for the password confirm field.
 * @return {void} 
 */
function errorStatusMPPasswordConfirm(){
    $("#form_modifica_perfil\\:confirm").addClass('is-invalid');
    $("#form_modifica_perfil\\:confirm").removeClass('is-valid');
    $("label[for='confirm']").addClass('text-danger');
    $("label[for='confirm']").removeClass('text-success');
    $("#error_confirm").removeClass("hide");
}

/**
 * Set a error status for the picture field.
 * @return {void} 
 */
function errorStatusMPPicture(){
    $("#form_modifica_perfil\\:picture").addClass('is-invalid');
    $("#form_modifica_perfil\\:picture").removeClass('is-valid');
    $("label[for='picture']").addClass('text-danger');
    $("label[for='picture']").removeClass('text-success');
    $("#error_picture").removeClass("hide");
}

/**
 * Set a error status for the picture field.
 * @return {void} 
 */
function errorStatusMPFill(){
    $("#error_fill").removeClass("hide");
}

function validateLengthMP(id){
    switch(id){
        case "name":
            return validateNameMPLengthMP("name");
            break;
        case "appat":
            return validateNameMPLengthMP("appat");
            break;
        case "apmat":
            return validateNameMPLengthMP("apmat");
            break;
        case "password":
            return validatePasswordLengthMP();
            break;
        case "confirm":
            return validatePasswordConfirmMPLengthMP();
            break;
    }
}

function validateMP(id){
    switch(id){
        case "name":
            return validateNameMP("name");
            break;
        case "appat":
            return validateNameMP("appat");
            break;
        case "apmat":
            return validateNameMP("apmat");
            break;
        case "password":
            return validatePasswordMP();
            break;
        case "confirm":
            return validatePasswordConfirmMP();
            break;
        case "picture":
            return validatePictureMP();
            break;
    }
}

function getInvalidCharsMP(id){
    switch(id){
        case "name":
            return getNameInvalidCharsMP("name");
            break;
        case "appat":
            return getNameInvalidCharsMP("appat");
            break;
        case "apmat":
            return getNameInvalidCharsMP("apmat");
            break;
        case "password":
            return getPasswordInvalidCharsMP();
            break;
    }
}

function successStatusMP(id){
    switch(id){
        case "name":
            return successStatusMPName("name");
            break;
        case "appat":
            return successStatusMPName("appat");
            break;
        case "apmat":
            return successStatusMPName("apmat");
            break;
        case "password":
            return successStatusMPPassword();
            break;
        case "confirm":
            return successStatusMPPasswordConfirm();
            break;
        case "picture":
            return successStatusMPPicture();
            break;
    }
}

function errorStatusMP(id){
    switch(id){
        case "name":
            return errorStatusMPName("name");
            break;
        case "appat":
            return errorStatusMPName("appat");
            break;
        case "apmat":
            return errorStatusMPName("apmat");
            break;
        case "password":
            return errorStatusMPPassword();
            break;
        case "confirm":
            return errorStatusMPPasswordConfirm();
            break;
        case "picture":
            return errorStatusMPPicture();
            break;
    }
}

window.onload = function(){
    
    var id = ["name","appat","apmat","password","confirm","picture"];
    var input = ["l nombre","l apellido paterno","l apellido materno","l correo",
                 " la contraseña"," la confirmación de contraseña"," la foto"];
    var rangos = ["3 a 50", "3 a 50","3 a 50","18 a 100","8 a 20", "8 a 20"];
        
    //Input [something] event handler
    $("#form_modifica_perfil\\:"+id[0]).on("keyup", function(){
        if(!validateLengthMP(id[0])){
            errorStatusMP(id[0]);
            $("#error_"+id[0]).text("Lo sentimos, la longitud de"+input[0]+
                                    " debe ser de "+rangos[0]+" caracteres.");
        }
        else if(!validateMP(id[0])){
            errorStatusMP(id[0]);
            $("#error_"+id[0]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(id[0]));
        }
        else
            successStatusMP(id[0]);
    });
    
    $("#form_modifica_perfil\\:"+id[1]).on("keyup", function(){
        if(!validateLengthMP(id[1])){
            errorStatusMP(id[1]);
            $("#error_"+id[1]).text("Lo sentimos, la longitud de"+input[1]+
                                    " debe ser de "+rangos[1]+" caracteres.");
        }
        else if(!validateMP(id[1])){
            errorStatusMP(id[1]);
            $("#error_"+id[1]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(id[1]));
        }
        else
            successStatusMP(id[1]);
    });
    
    $("#form_modifica_perfil\\:"+id[2]).on("keyup", function(){
        if(!validateLengthMP(id[2])){
            errorStatusMP(id[2]);
            $("#error_"+id[2]).text("Lo sentimos, la longitud de"+input[2]+
                                    " debe ser de "+rangos[2]+" caracteres.");
        }
        else if(!validateMP(id[2])){
            errorStatusMP(id[2]);
            $("#error_"+id[2]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(id[2]));
        }
        else
            successStatusMP(id[2]);
    });
    
    $("#form_modifica_perfil\\:"+id[3]).on("keyup", function(){
        if(!validateLengthMP(id[3])){
            errorStatusMP(id[3]);
            $("#error_"+id[3]).text("La longitud de"+input[4]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validateMP(id[3])){
            errorStatusMP(id[3]);
            $("#error_"+id[3]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(id[4]));
        }
        else
            successStatusMP(id[3]);
    });
    
    $("#form_modifica_perfil\\:"+id[4]).on("keyup", function(){
        if(!validateLengthMP(id[4])){
            errorStatusMP(id[4]);
            $("#error_"+id[4]).text("La longitud de"+input[5]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validateMP(id[4])){
            errorStatusMP(id[4]);
            $("#error_"+id[4]).text("Las contraseñas no coinciden");
        }
        else
            successStatusMP(id[4]);
    });
    
    $("#form_modifica_perfil\\:"+id[5]).on("change", function(){
        if(!validateMP(id[5])){
            errorStatusMP(id[5]);
            $("#error_"+id[5]).text("Lo sentimos, el archivo no es válido; necesita ser una imagen (png|jpg|bmp|jpeg)");
        }
        else
            successStatusMP(id[5]);
    });
    
    

    //Submit form event handler
    $("#form_modifica_perfil").submit(function(e){
        var check = false;
        check = check || (($("#form_modifica_perfil\\:"+id[0]).val().length > 0) ? (!validateLengthMP(id[0]) || !validateMP(id[0])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[1]).val().length > 0) ? (!validateLengthMP(id[1]) || !validateMP(id[1])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[2]).val().length > 0) ? (!validateLengthMP(id[2]) || !validateMP(id[2])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[3]).val().length > 0) ? (!validateLengthMP(id[3]) || !validateMP(id[3])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[4]).val().length > 0) ? (!validateLengthMP(id[4]) || !validateMP(id[4])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[5]).val().length > 0) ? !validateMP(id[5]) : false );

        
        if(check){
            errorStatusMPFill();
            $("#error_fill").text("Hay campos sin corregir.");
            return false;
        }else if(validateCareer()){
            errorStatusMPFill();
            $("#error_fill").text("*Se debe seleccionar al menos una carrera.");
            return false;
        }
        else
            successStatusMPFill();
    });
};
