

    function checkUsername(input) {
        var re = new RegExp("^[A-Za-z0-9]{3,}$");
        var result = re.test(input);
        return result;
    }

    function checkPass(input) {
        var re = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        var result = re.test(input);
        return result;
    }

    $(document).ready(function() {

        $("#usernameInput").change(function() {
            var result = checkUsername($(this).val());

            if(result) {
                $("#usernameWarning").css("visibility", "hidden")
            }
            else {
                $("#usernameWarning").css("visibility", "visible")
            }
        });

        $("#passInput").change(function() {

            var result = checkPass($(this).val());

            if(result) {
                $("#passwordWarning").css("visibility", "hidden")
            }
            else {
                $("#passwordWarning").css("visibility", "visible")
            }
        });

        $("#registerForm").submit(function() {

            var usernameCheck = checkUsername($("#usernameInput").val());
            var passCheck = checkPass($("#passInput").val());

            if(usernameCheck && passCheck) {
                return true;
            }
            else {
                return true;
            }
        });
    });