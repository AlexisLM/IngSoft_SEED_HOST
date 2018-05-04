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
    return pattern.test($("#form_registra\\:"+id).val());
}

/**
 * Validates value of email. [End with '@ciencias.unam.mx']
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateEmail(){
    var pattern = /^[A-Za-z0-9._-]{1,83}@ciencias\.unam\.mx$/;
    return pattern.test($("#form_registra\\:email").val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePassword(){
    var pattern = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,20}$/;
    return pattern.test($("#form_registra\\:password").val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirm(){
    var password = $("#form_registra\\:password").val();
    var confirm = $("#form_registra\\:confirm").val();
    
    return (password === confirm);
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePicture(){
    var pattern = /\.(gif|jpg|jpeg|tiff|png)$i/;
    return pattern.test($("#form_registra\\:password").val());
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateNameLength(id){
    return $("#form_registra\\:"+id).val().replace(/^\s*/, "").length >= 3 &&
           $("#form_registra\\:"+id).val().length <= 50;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validateEmailLength(){
    return $("#form_registra\\:email").val().replace(/^\s*/, "").length >= 18 &&
           $("#form_registra\\:email").val().length <= 100;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordLength(){
    return $("#form_registra\\:password").val().replace(/^\s*/, "").length >= 8 &&
           $("#form_registra\\:password").val().length <= 20;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmLength(){
    return $("#form_registra\\:confirm").val().replace(/^\s*/, "").length >= 3 &&
           $("#form_registra\\:confirm").val().length <= 50;
}


/**
 * Gets the invalid chars of the name.
 * @param {type} id
 * @return {string} The invalid chars of the name.
 */
function getNameInvalidChars(id){
    var pattern = /[A-Za-záéíóúÁÉÍÓÚñÑ\s]/g;
    return uniqChars($("#form_registra\\:"+id).val().replace(pattern,""));
}

/**
 * Gets the invalid chars of the email.
 * @return {string} The invalid chars of the email.
 */
function getEmailInvalidChars(){
    var pattern = /[A-Za-z0-9._-]/g;
    var email = $("#form_registra\\:email").val();
    var arroba = email.indexOf("@");
    if(arroba === -1 ){
        return "@ - Necesita una extensión de correo, ej: juanito@ciencias.unam.mx"
    }else if(email.substring(arroba+1) !== "ciencias.unam.mx"){
        return email.substring(arroba+1);
    }else
        return uniqChars($("#form_registra\\:email").val().replace(pattern,""));
};

/**
 * Gets the invalid chars of the email.
 * @return {string} The invalid chars of the email.
 */
function getPasswordInvalidChars(){
    var pattern = /(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]/g;
    return uniqChars($("#form_registra\\:password").val().replace(pattern,""));
};


/**
 * Set a success status for the name field.
 * @param {type} id
 * @return {void} 
 */
function successStatusName(id){
    $("#form_registra\\:"+id).addClass('is-valid');
    $("#form_registra\\:"+id).removeClass('is-invalid');
    $("label[for='"+id+"']").addClass('text-success');
    $("label[for='"+id+"']").removeClass('text-danger');
    $("#error_"+id).addClass("hide");
}

/**
 * Set a success status for the email field.
 * @return {void} 
 */
function successStatusEmail(){
    $("#form_registra\\:email").addClass('is-valid');
    $("#form_registra\\:email").removeClass('is-invalid');
    $("label[for='email']").addClass('text-success');
    $("label[for='email']").removeClass('text-danger');
    $("#error_email").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusPassword(){
    $("#form_registra\\:password").addClass('is-valid');
    $("#form_registra\\:password").removeClass('is-invalid');
    $("label[for='password']").addClass('text-success');
    $("label[for='password']").removeClass('text-danger');
    $("#error_password").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusPasswordConfirm(){
    $("#form_registra\\:confirm").addClass('is-valid');
    $("#form_registra\\:confirm").removeClass('is-invalid');
    $("label[for='confirm']").addClass('text-success');
    $("label[for='confirm']").removeClass('text-danger');
    $("#error_confirm").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusPicture(){
    $("#form_registra\\:picture").addClass('is-valid');
    $("#form_registra\\:picture").removeClass('is-invalid');
    $("label[for='picture']").addClass('text-success');
    $("label[for='picture']").removeClass('text-danger');
    $("#error_picture").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusFill(){
    $("#error_picture").addClass("hide");
}

/**
 * Set a error status for the name field.
 * @return {void} 
 */
function errorStatusName(id){
    $("#form_registra\\:"+id).addClass('is-invalid');
    $("#form_registra\\:"+id).removeClass('is-valid');
    $("label[for='"+id+"']").addClass('text-danger');
    $("label[for='"+id+"']").removeClass('text-success');
    $("#error_"+id).removeClass("hide");
}

/**
 * Set a error status for the email field.
 * @return {void} 
 */
function errorStatusEmail(){
    $("#form_registra\\:email").addClass('is-invalid');
    $("#form_registra\\:email").removeClass('is-valid');
    $("label[for='email']").addClass('text-danger');
    $("label[for='email']").removeClass('text-success');
    $("#error_email").removeClass("hide");
}

/**
 * Set a error status for the password field.
 * @return {void} 
 */
function errorStatusPassword(){
    $("#form_registra\\:password").addClass('is-invalid');
    $("#form_registra\\:password").removeClass('is-valid');
    $("label[for='password']").addClass('text-danger');
    $("label[for='password']").removeClass('text-success');
    $("#error_password").removeClass("hide");
}

/**
 * Set a error status for the password confirm field.
 * @return {void} 
 */
function errorStatusPasswordConfirm(){
    $("#form_registra\\:confirm").addClass('is-invalid');
    $("#form_registra\\:confirm").removeClass('is-valid');
    $("label[for='confirm']").addClass('text-danger');
    $("label[for='confirm']").removeClass('text-success');
    $("#error_confirm").removeClass("hide");
}

/**
 * Set a error status for the picture field.
 * @return {void} 
 */
function errorStatusPicture(){
    $("#form_registra\\:picture").addClass('is-invalid');
    $("#form_registra\\:picture").removeClass('is-valid');
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
        case "email":
            return validateEmailLength();
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
        case "email":
            return validateEmail();
            break;
        case "password":
            return validatePassword();
            break;
        case "confirm":
            return validatePasswordConfirm();
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
        case "email":
            return getEmailInvalidChars();
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
        case "email":
            return successStatusEmail();
            break;
        case "password":
            return successStatusPassword();
            break;
        case "confirm":
            return successStatusPasswordConfirm();
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
        case "email":
            return errorStatusEmail();
            break;
        case "password":
            return errorStatusPassword();
            break;
        case "confirm":
            return errorStatusPasswordConfirm();
            break;
    }
}

window.onload = function(){
    
    var id = ["name","appat","apmat","email","password","confirm","picture"];
    var input = ["l nombre","l apellido paterno","l apellido materno","l correo",
                 " la contraseña"," la confirmación de contraseña"," la foto"];
    var rangos = ["3 a 50", "3 a 50","3 a 50","18 a 100","8 a 20", "8 a 20"];
        
    //Input [something] event handler
    $("#form_registra\\:"+id[0]).on("keyup", function(){
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
    
    $("#form_registra\\:"+id[1]).on("keyup", function(){
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
    
    $("#form_registra\\:"+id[2]).on("keyup", function(){
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
    
    $("#form_registra\\:"+id[3]).on("keyup", function(){
        if(!validateLength(id[3])){
            errorStatus(id[3]);
            $("#error_"+id[3]).text("Lo sentimos, la longitud de"+input[3]+
                                    " debe ser de "+rangos[3]+" caracteres. "+
                                    "El correo debe tener extensión: @ciencias.unam.mx");
        }
        else if(!validate(id[3])){
            errorStatus(id[3]);
            $("#error_"+id[3]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[3]));
        }
        else
            successStatus(id[3]);
    });
    
    $("#form_registra\\:"+id[4]).on("keyup", function(){
        if(!validateLength(id[4])){
            errorStatus(id[4]);
            $("#error_"+id[4]).text("Lo sentimos, la longitud de"+input[4]+
                                    " debe ser de "+rangos[4]+" caracteres.");
        }
        else if(!validate(id[4])){
            errorStatus(id[4]);
            $("#error_"+id[4]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidChars(id[4]));
        }
        else
            successStatus(id[4]);
    });
    
    $("#form_registra\\:"+id[5]).on("keyup", function(){
        if(!validateLength(id[5])){
            errorStatus(id[5]);
            $("#error_"+id[5]).text("Lo sentimos, la longitud de"+input[5]+
                                    " debe ser de "+rangos[5]+" caracteres.");
        }
        else if(!validate(id[5])){
            errorStatus(id[5]);
            $("#error_"+id[5]).text("Las contraseñas no coinciden");
        }
        else
            successStatus(id[5]);
    });
    
    $("#form_registra\\:"+id[6]).on("keyup", function(){
        if(!validate(id[6])){
            errorStatus(id[6]);
            $("#error_"+id[6]).text("Lo sentimos, el archivo no es válido; necesita ser una imagen (gif|jpg|jpeg|tiff|png)");
        }
        else
            successStatus(id[6]);
    });
    
    

    //Submit form event handler
    $("#form_registra").submit(function(e){
        var check = false;
        check = check || !validateLength(id[0]) || !validate(id[0]);
        check = check || !validateLength(id[1]) || !validate(id[1]);
        check = check || !validateLength(id[2]) || !validate(id[2]);
        check = check || !validateLength(id[3]) || !validate(id[3]);
        check = check || !validateLength(id[4]) || !validate(id[4]);
        check = check || !validateLength(id[5]) || !validate(id[5]);
        if($("#form_registra\\:picture").val() !== "")
            check = check || !validate(id[6]);
        
        if(check){
            errorStatusFill();
            $("#error_fill").text("*Necesita llenar todos los campos (Imagen de perfil opcional)");
            return false;
        }else
            successStatusFill();
            
    });
};
