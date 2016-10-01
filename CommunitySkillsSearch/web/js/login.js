
var main = function(){
    $('.togglelink').click(function(){
       $('#login_login').toggle();
       $('#login_create').toggle(); 
       $('#edit_visible').bootstrapToggle();
    });
};

function isPasswordsMatch() {
    var pass1 = document.getElementById("pass1").value;
    var pass2 = document.getElementById("pass2").value;
    var ok = true;
    
    if (pass1 !== pass2) {
        alert("Passwords Do not match");
        document.getElementById("pass1").style.borderColor = "#E34234";
        document.getElementById("pass2").style.borderColor = "#E34234";
        ok = false;
    }
    
    return ok;
}

$(document).ready(main);