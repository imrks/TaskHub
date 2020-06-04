function SignUp()
{ 
var request = new XMLHttpRequest();
var url = 'http://localhost:8080/signup';
request.open('POST', url);
request.setRequestHeader("Content-Type", "application/json");
var name = document.getElementById("name").value;
var email = document.getElementById("email").value;
var password = document.getElementById("password").value;
var data = {
	name: name,
	email: email,
	password: password
}
var data1 = JSON.stringify(data);
request.onload = function () {
  if(request.status==200){
    document.getElementById("signupmessage").innerHTML='<p style="color:green;text-align:center;">Account Successfully Created<\p>';
  }
  else if(request.status==409){
    document.getElementById("signupmessage").innerHTML='<p style="color:red;text-align:center;">Email Already Exists<\p>';
  }
  else{
    document.getElementById("signupmessage").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
request.send(data1);
}

$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='registration']").validate({
    // Specify validation rules
    rules: {
      name: {
        required: true,
        minlength: 2
      },
      email: {
        required: true,
        email: true
      },
      password: {
        required: true,
        minlength: 6
      }
    },
    messages: {
      name: "Please enter your name",
      password: {
        required: "Please provide a password",
        minlength: "Your password must be at least 6 characters long"
      },
      email: "Please enter a valid email address"
    },
    submitHandler: function(form) {
      form.submit();
    }
  });
});