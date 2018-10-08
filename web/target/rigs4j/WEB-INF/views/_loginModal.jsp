<div id="modalLogin" class="modal fade" role="dialog">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal">&times;</button>
        <h4 class="modal-title">Login</h4>
      </div>
      <form action="/login/" method="post">
          <div class="modal-body">
            <p>Login Credentials: </p>
            <div class="form-group">
              <label for="login_usr">Name:</label>
              <input type="text" class="form-control" id="login_usr" name="username">
            </div>
            <div class="form-group">
              <label for="login_pwd">Password:</label>
              <input type="password" class="form-control" id="login_pwd" name="password">
            </div>
          </div>
          <div class="modal-footer">
            <button id='login_btnSubmit' type="submit" class="btn btn-default" data-dismiss="modal">Submit</button>
          </div>
      </form>
    </div>
  </div>
</div>