/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Gets a copy of the string with all duplicate characters removed.
 * @param {string} str Inputt string which will be duplicated and whose duplicate
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
    return pattern.test($("#form_modifica_perfil\\:passsword").val());
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmMP(){
    var password = $("#form_modifica_perfil\\:passsword").val();
    var confirm = $("#form_modifica_perfil\\:pconfirm").val();
    
    return (password === confirm);
}

/**
 * Validates value of password. [At least 8,20 char]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePictureMP(){
    var pattern = /.+\.(png|jpg|bmp|jpeg|PNG|JPG|BMP|JPEG)$/;
    return pattern.test($("#form_modifica_perfil\\:ppicture").val());
}

/**
 * Validates if there is at least one career selected.
 * @returns {Boolean} True if the test passes, false otherwise.
 */
function validateCareer(){
    var check = false;
    $(".carreras").each(function() {
        check = check || ($(this).val() !== "0");
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
    return $("#form_modifica_perfil\\:passsword").val().replace(/^\s*/, "").length >= 8 &&
           $("#form_modifica_perfil\\:passsword").val().length <= 20;
}

/**
 * Validates the length of the name. [At least 5 chars]
 * @return {boolean} True if the test passes, false otherwise.
 */
function validatePasswordConfirmMPLengthMP(){
    return $("#form_modifica_perfil\\:pconfirm").val().replace(/^\s*/, "").length >= 3 &&
           $("#form_modifica_perfil\\:pconfirm").val().length <= 50;
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
    return uniqCharsMP($("#form_modifica_perfil\\:passsword").val().replace(pattern,""));
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
    $("#form_modifica_perfil\\:pconfirm").addClass('is-valid');
    $("#form_modifica_perfil\\:pconfirm").removeClass('is-invalid');
    $("label[for='pconfirm']").addClass('text-success');
    $("label[for='pconfirm']").removeClass('text-danger');
    $("#error_pconfirm").addClass("hide");
}

/**
 * Set a success status for the password field.
 * @return {void} 
 */
function successStatusMPPicture(){
    $("#form_modifica_perfil\\:ppicture").addClass('is-valid');
    $("#form_modifica_perfil\\:ppicture").removeClass('is-invalid');
    $("label[for='ppicture']").addClass('text-success');
    $("label[for='ppicture']").removeClass('text-danger');
    $("#error_ppicture").addClass("hide");
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
 * @param {type} id
 * @returns {undefined}
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
    $("#form_modifica_perfil\\:passsword").addClass('is-invalid');
    $("#form_modifica_perfil\\:passsword").removeClass('is-valid');
    $("label[for='passsword']").addClass('text-danger');
    $("label[for='passsword']").removeClass('text-success');
    $("#error_passsword").removeClass("hide");
}

/**
 * Set a error status for the password confirm field.
 * @return {void} 
 */
function errorStatusMPPasswordConfirm(){
    $("#form_modifica_perfil\\:pconfirm").addClass('is-invalid');
    $("#form_modifica_perfil\\:pconfirm").removeClass('is-valid');
    $("label[for='pconfirm']").addClass('text-danger');
    $("label[for='pconfirm']").removeClass('text-success');
    $("#error_pconfirm").removeClass("hide");
}

/**
 * Set a error status for the picture field.
 * @return {void} 
 */
function errorStatusMPPicture(){
    $("#form_modifica_perfil\\:ppicture").addClass('is-invalid');
    $("#form_modifica_perfil\\:ppicture").removeClass('is-valid');
    $("label[for='ppicture']").addClass('text-danger');
    $("label[for='ppicture']").removeClass('text-success');
    $("#error_ppicture").removeClass("hide");
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
        case "namee":
            return validateNameMPLengthMP("namee");
            break;
        case "appatt":
            return validateNameMPLengthMP("appatt");
            break;
        case "apmatt":
            return validateNameMPLengthMP("apmatt");
            break;
        case "passsword":
            return validatePasswordLengthMP();
            break;
        case "pconfirm":
            return validatePasswordConfirmMPLengthMP();
            break;
    }
}

function validateMP(id){
    switch(id){
        case "namee":
            return validateNameMP("namee");
            break;
        case "appatt":
            return validateNameMP("appatt");
            break;
        case "apmatt":
            return validateNameMP("apmatt");
            break;
        case "passsword":
            return validatePasswordMP();
            break;
        case "pconfirm":
            return validatePasswordConfirmMP();
            break;
        case "ppicture":
            return validatePictureMP();
            break;
    }
}

function getInvalidCharsMP(id){
    switch(id){
        case "namee":
            return getNameInvalidCharsMP("namee");
            break;
        case "appatt":
            return getNameInvalidCharsMP("appatt");
            break;
        case "apmatt":
            return getNameInvalidCharsMP("apmatt");
            break;
        case "passsword":
            return getPasswordInvalidCharsMP();
            break;
    }
}

function successStatusMP(id){
    switch(id){
        case "namee":
            return successStatusMPName("namee");
            break;
        case "appatt":
            return successStatusMPName("appatt");
            break;
        case "apmatt":
            return successStatusMPName("apmatt");
            break;
        case "passsword":
            return successStatusMPPassword();
            break;
        case "pconfirm":
            return successStatusMPPasswordConfirm();
            break;
        case "ppicture":
            return successStatusMPPicture();
            break;
    }
}

function errorStatusMP(id){
    switch(id){
        case "namee":
            return errorStatusMPName("namee");
            break;
        case "appatt":
            return errorStatusMPName("appatt");
            break;
        case "apmatt":
            return errorStatusMPName("apmatt");
            break;
        case "passsword":
            return errorStatusMPPassword();
            break;
        case "pconfirm":
            return errorStatusMPPasswordConfirm();
            break;
        case "ppicture":
            return errorStatusMPPicture();
            break;
    }
}

window.onload = function(){
    
    var idd = ["namee","appatt","apmatt","passsword","pconfirm","ppicture"];
    var inputt = ["l nombre","l apellido paterno","l apellido materno","l correo",
                 " la contraseña"," la confirmación de contraseña"," la foto"];
    var rangoss = ["3 a 50", "3 a 50","3 a 50","18 a 100","8 a 20", "8 a 20"];
        
    //Inputt [something] event handler
    $("#form_modifica_perfil\\:"+idd[0]).on("keyup", function(){
        if(!validateLengthMP(idd[0])){
            errorStatusMP(idd[0]);
            $("#error_"+idd[0]).text("Lo sentimos, la longitud de"+inputt[0]+
                                    " debe ser de "+rangoss[0]+" caracteres.");
        }
        else if(!validateMP(idd[0])){
            errorStatusMP(idd[0]);
            $("#error_"+idd[0]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(idd[0]));
        }
        else
            successStatusMP(idd[0]);
    });
    
    $("#form_modifica_perfil\\:"+idd[1]).on("keyup", function(){
        if(!validateLengthMP(idd[1])){
            errorStatusMP(idd[1]);
            $("#error_"+idd[1]).text("Lo sentimos, la longitud de"+inputt[1]+
                                    " debe ser de "+rangoss[1]+" caracteres.");
        }
        else if(!validateMP(idd[1])){
            errorStatusMP(idd[1]);
            $("#error_"+idd[1]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(idd[1]));
        }
        else
            successStatusMP(idd[1]);
    });
    
    $("#form_modifica_perfil\\:"+idd[2]).on("keyup", function(){
        if(!validateLengthMP(idd[2])){
            errorStatusMP(idd[2]);
            $("#error_"+idd[2]).text("Lo sentimos, la longitud de"+inputt[2]+
                                    " debe ser de "+rangoss[2]+" caracteres.");
        }
        else if(!validateMP(idd[2])){
            errorStatusMP(idd[2]);
            $("#error_"+idd[2]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(idd[2]));
        }
        else
            successStatusMP(idd[2]);
    });
    
    $("#form_modifica_perfil\\:"+idd[3]).on("keyup", function(){
        if(!validateLengthMP(idd[3])){
            errorStatusMP(idd[3]);
            $("#error_"+idd[3]).text("La longitud de"+inputt[4]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validateMP(idd[3])){
            errorStatusMP(idd[3]);
            $("#error_"+idd[3]).text("Lo sentimos, los siguientes caracteres no "+
                                   "son válidos: "+getInvalidCharsMP(idd[4]));
        }
        else
            successStatusMP(idd[3]);
    });
    
    $("#form_modifica_perfil\\:"+idd[4]).on("keyup", function(){
        if(!validateLengthMP(idd[4])){
            errorStatusMP(idd[4]);
            $("#error_"+idd[4]).text("La longitud de"+inputt[5]+
                                    " debe tener entre 8 y 20 caracteres, al menos un dígito,"+
                                    "al menos una minúscula y al menos una mayúscula.");
        }
        else if(!validateMP(idd[4])){
            errorStatusMP(idd[4]);
            $("#error_"+idd[4]).text("Las contraseñas no coinciden");
        }
        else
            successStatusMP(idd[4]);
    });
    
    $("#form_modifica_perfil\\:"+idd[5]).on("change", function(){
        if(!validateMP(idd[5])){
            errorStatusMP(idd[5]);
            $("#error_"+idd[5]).text("Lo sentimos, el archivo no es válido; necesita ser una imagen (png|jpg|bmp|jpeg)");
        }
        else
            successStatusMP(idd[5]);
    });
    
    

    //Submit form event handler
    $("#form_modifica_perfil").submit(function(e){
        var check = false;
        check = check || (($("#form_modifica_perfil\\:"+idd[0]).val().length > 0) ? (!validateLengthMP(idd[0]) || !validateMP(idd[0])): false );
        check = check || (($("#form_modifica_perfil\\:"+idd[1]).val().length > 0) ? (!validateLengthMP(idd[1]) || !validateMP(idd[1])): false );
        check = check || (($("#form_modifica_perfil\\:"+idd[2]).val().length > 0) ? (!validateLengthMP(idd[2]) || !validateMP(idd[2])): false );
        check = check || (($("#form_modifica_perfil\\:"+idd[3]).val().length > 0) ? (!validateLengthMP(idd[3]) || !validateMP(idd[3])): false );
        check = check || (($("#form_modifica_perfil\\:"+idd[4]).val().length > 0) ? (!validateLengthMP(idd[4]) || !validateMP(idd[4])): false );
        check = check || (($("#form_modifica_perfil\\:"+idd[5]).val().length > 0) ? !validateMP(idd[5]) : false );

        
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
