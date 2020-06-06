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

function Login()
{ 
var loginrequest = new XMLHttpRequest();
var loginemail = document.getElementById("loginemail").value;
var loginpassword = document.getElementById("loginpassword").value;
var url = 'http://localhost:8080/login/'+loginemail+'/'+loginpassword;
loginrequest.open('GET', url);
loginrequest.onload = function () {
  if(loginrequest.status==200){
    document.getElementById("loginmessage").innerHTML='<p style="color:green;text-align:center;">Logged In Successfully<\p>';
    var id=JSON.parse(loginrequest.responseText).id;
    localStorage.setItem("gid",id);
    setTimeout(function(){window.location="mylist.html";},1000)
  }
  else if(loginrequest.status==403){
    document.getElementById("loginmessage").innerHTML='<p style="color:red;text-align:center;">Email Not Found<\p>';
  }
  else if(loginrequest.status==401){
    document.getElementById("loginmessage").innerHTML='<p style="color:red;text-align:center;">Wrong Password<\p>';
  }
  else{
    document.getElementById("loginmessage").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
loginrequest.send();
}


$(function() {
  $("form[name='registration']").validate({
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
    onkeyup: function() {
      if($("form[name='registration']").valid()){
        $('#signupbtn').removeAttr("disabled");
      } else {
        $('#signupbtn').attr("disabled",true);
      }
    },
    submitHandler: function(form) {
      form.submit();
    }
  });
  $("form[name='loginForm']").validate({
    rules: {
      loginemail: {
        required: true,
        email: true
      },
      loginpassword: {
        required: true,
        minlength: 6
      }
    },
    messages: {
      loginpassword: {
        required: "Please provide a password",
        minlength: "Your password must be at least 6 characters long"
      },
      loginemail: "Please enter a valid email address"
    },
    onkeyup: function() {
      if($("form[name='loginForm']").valid()){
        $('#loginbtn').removeAttr("disabled");
      } else {
        $('#loginbtn').attr("disabled",true);
      }
    },
    submitHandler: function(form) {
      form.submit();
    }
  });
});