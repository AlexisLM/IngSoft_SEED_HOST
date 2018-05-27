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
function uniqChars(str) {
    return str.replace(/[\s\S](?=([\s\S]+))/g, function(c, s) {
        return s.indexOf(c) + 1 ? '' : c;
    });
}

/**
 * Validates value of title. [At least 3,50 char]
 * @param {type} id
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateName(id){
    var pattern = /^[A-Za-záéíóúÁÉÍÓÚñÑ]{2}[A-Za-záéíóúÁÉÍÓÚñÑ ]{0,47}$/;
    return pattern.test($("#form_modifica_perfil\\:"+id).val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePassword(){
    var pattern = /^(?=\w*\d)(?=\w*[A-Z])(?=\w*[a-z])\S{8,16}$/;
    return pattern.test($("#form_modifica_perfil\\:password").val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirm(){
    var password = $("#form_modifica_perfil\\:password").val();
    var confirm = $("#form_modifica_perfil\\:confirm").val();
    
    return (password === confirm);
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePicture(){
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
        check = check || ($(this).val() == "0");
    });
    
    return check;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @param id recibe el id de la parte del nombre a revisar.
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateNameLength(id){
    return $("#form_modifica_perfil\\:"+id).val().replace(/^\s*/, "").length >= 3 &&
           $("#form_modifica_perfil\\:"+id).val().length <= 50;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordLength(){
    return $("#form_modifica_perfil\\:password").val().replace(/^\s*/, "").length >= 8 &&
           $("#form_modifica_perfil\\:password").val().length <= 20;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmLength(){
    return $("#form_modifica_perfil\\:confirm").val().replace(/^\s*/, "").length >= 3 &&
           $("#form_modifica_perfil\\:confirm").val().length <= 50;
}

/**
 * Gets the invalid chars of the name.
 * @param {type} id
 * @returns {String} The invalid chars of the name.
 */
function getNameInvalidChars(id){
    var pattern = /[A-Za-záéíóúÁÉÍÓÚñÑ\s]/g;
    return uniqChars($("#form_modifica_perfil\\:"+id).val().replace(pattern,""));
}

/**
 * Gets the invalid chars of the email.
 * @return {string} The invalid chars of the email.
 */
function getPasswordInvalidChars(){
    var pattern = /[A-Za-z/d]/g;
    return uniqChars($("#form_modifica_perfil\\:password").val().replace(pattern,""));
};


/**
 * Set a success status for the name field.
 * @param {type} id
 * @return {void} 
 */
function successStatusName(id){
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
function successStatusPassword(){
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
function successStatusPasswordConfirm(){
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
function successStatusPicture(){
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
function successStatusFill(){
    $("#error_fill").addClass("hide");
}

/**
 * Set a error status for the name field.
 * @param {string} id. 
 * @return {void} 
 */
function errorStatusName(id){
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
function errorStatusPassword(){
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
function errorStatusPasswordConfirm(){
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
function errorStatusPicture(){
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
function errorStatusFill(){
    $("#error_fill").removeClass("hide");
}

function validateLength(id){
    switch(id){
        case "name":
            return validateNameLength("name");
            break;
        case "appat":
            return validateNameLength("appat");
            break;
        case "apmat":
            return validateNameLength("apmat");
            break;
        case "password":
            return validatePasswordLength();
            break;
        case "confirm":
            return validatePasswordConfirmLength();
            break;
    }
}

function validate(id){
    switch(id){
        case "name":
            return validateName("name");
            break;
        case "appat":
            return validateName("appat");
            break;
        case "apmat":
            return validateName("apmat");
            break;
        case "password":
            return validatePassword();
            break;
        case "confirm":
            return validatePasswordConfirm();
            break;
        case "picture":
            return validatePicture();
            break;
    }
}

function getInvalidChars(id){
    switch(id){
        case "name":
            return getNameInvalidChars("name");
            break;
        case "appat":
            return getNameInvalidChars("appat");
            break;
        case "apmat":
            return getNameInvalidChars("apmat");
            break;
        case "password":
            return getPasswordInvalidChars();
            break;
    }
}

function successStatus(id){
    switch(id){
        case "name":
            return successStatusName("name");
            break;
        case "appat":
            return successStatusName("appat");
            break;
        case "apmat":
            return successStatusName("apmat");
            break;
        case "password":
            return successStatusPassword();
            break;
        case "confirm":
            return successStatusPasswordConfirm();
            break;
        case "picture":
            return successStatusPicture();
            break;
    }
}

function errorStatus(id){
    switch(id){
        case "name":
            return errorStatusName("name");
            break;
        case "appat":
            return errorStatusName("appat");
            break;
        case "apmat":
            return errorStatusName("apmat");
            break;
        case "password":
            return errorStatusPassword();
            break;
        case "confirm":
            return errorStatusPasswordConfirm();
            break;
        case "picture":
            return errorStatusPicture();
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
        if(!validateLength(id[0])){
            errorStatus(id[0]);
            $("#error_"+id[0]).text("Lo sentimos, la longitud de"+input[0]+
                                    " debe ser de "+rangos[0]+" caracteres.");
        }
        else if(!validate(id[0])){
            errorStatus(id[0]);
            $("#error_"+id[0]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[0]));
        }
        else
            successStatus(id[0]);
    });
    
    $("#form_modifica_perfil\\:"+id[1]).on("keyup", function(){
        if(!validateLength(id[1])){
            errorStatus(id[1]);
            $("#error_"+id[1]).text("Lo sentimos, la longitud de"+input[1]+
                                    " debe ser de "+rangos[1]+" caracteres.");
        }
        else if(!validate(id[1])){
            errorStatus(id[1]);
            $("#error_"+id[1]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[1]));
        }
        else
            successStatus(id[1]);
    });
    
    $("#form_modifica_perfil\\:"+id[2]).on("keyup", function(){
        if(!validateLength(id[2])){
            errorStatus(id[2]);
            $("#error_"+id[2]).text("Lo sentimos, la longitud de"+input[2]+
                                    " debe ser de "+rangos[2]+" caracteres.");
        }
        else if(!validate(id[2])){
            errorStatus(id[2]);
            $("#error_"+id[2]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[2]));
        }
        else
            successStatus(id[2]);
    });
    
    $("#form_modifica_perfil\\:"+id[3]).on("keyup", function(){
        if(!validateLength(id[3])){
            errorStatus(id[3]);
            $("#error_"+id[3]).text("La longitud de"+input[4]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validate(id[3])){
            errorStatus(id[3]);
            $("#error_"+id[3]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[4]));
        }
        else
            successStatus(id[3]);
    });
    
    $("#form_modifica_perfil\\:"+id[4]).on("keyup", function(){
        if(!validateLength(id[4])){
            errorStatus(id[4]);
            $("#error_"+id[4]).text("La longitud de"+input[5]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validate(id[4])){
            errorStatus(id[4]);
            $("#error_"+id[4]).text("Las contraseñas no coinciden");
        }
        else
            successStatus(id[4]);
    });
    
    $("#form_modifica_perfil\\:"+id[5]).on("change", function(){
        if(!validate(id[5])){
            errorStatus(id[5]);
            $("#error_"+id[5]).text("Lo sentimos, el archivo no es válido; necesita ser una imagen (png|jpg|bmp|jpeg)");
        }
        else
            successStatus(id[5]);
    });
    
    

    //Submit form event handler
    $("#form_modifica_perfil").submit(function(e){
        var check = false;
        check = check || (($("#form_modifica_perfil\\:"+id[0]).val().length > 0) ? (!validateLength(id[0]) || !validate(id[0])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[1]).val().length > 0) ? (!validateLength(id[1]) || !validate(id[1])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[2]).val().length > 0) ? (!validateLength(id[2]) || !validate(id[2])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[3]).val().length > 0) ? (!validateLength(id[3]) || !validate(id[3])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[4]).val().length > 0) ? (!validateLength(id[4]) || !validate(id[4])): false );
        check = check || (($("#form_modifica_perfil\\:"+id[5]).val().length > 0) ? !validate(id[5]) : false );

        
        if(check){
            errorStatusFill();
            $("#error_fill").text("Hay campos sin corregir.");
            return false;
        }else if(validateCareer()){
            errorStatusFill();
            $("#error_fill").text("*Se debe seleccionar al menos una carrera.");
            return false;
        }
        else
            successStatusFill();
            
    });
};
