
$(document).ready(function() {
    $("#updateForm").submit(function() {
        var enabled = $("#isEnabled");
        if($("#isEnabled")[0].checked) {
            $("#isEnabledHidden").val(true);
        }
        else {
            $("#isEnabledHidden").val(false);
        }

        var admin = $("#isAdmin");
        if($("#isAdmin")[0].checked) {
            $("#isAdminHidden").val(true);
        }
        else {
            $("#isAdminHidden").val(false);
        }

        return true;
    });
});