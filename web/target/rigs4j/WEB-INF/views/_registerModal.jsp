<div id="modalRegister" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Register</h4>
      </div>
      <form action="http://localhost:8080/adduser" method="post" id="register-form">
          <div class="modal-body">
            <p>Register New User</p>
            <div class="form-group">
              <label path="register_usr">Name:</label>
              <input type="text" class="form-control" name="User.username">
            </div>
            <div class="form-group">
              <label path="register_pwd">Password:</label>
              <input type="password" class="form-control" name="User.password">
            </div>
          </div>
          <div class="modal-footer">
            <input id='register_btnSubmit' type="button" class="btn btn-default" data-dismiss="modal" value="submit">
          </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript">
    $(document).ready(function() {
        $("#register_btnSubmit").click(function() {
            var form = $("#myForm");

            $(form).submit();

            var x = 1;
        })
    })
</script>