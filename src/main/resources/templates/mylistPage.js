var cid=0;
var alldata;
var geditdata;
function setCid(){
    cid=localStorage.getItem("gid");
    console.log(cid);
}

function myTasks(taskname)
{ 
  var flag;
var getTasksReq = new XMLHttpRequest();
var url = 'http://localhost:8080/home/'+cid;
getTasksReq.open('GET', url);
getTasksReq.onload = function () {
  if(getTasksReq.status==200){
    var allTaskData=JSON.parse(getTasksReq.responseText);
    if(allTaskData.length>0){
      if(taskname=='all'){
        flag=renderAllTask(allTaskData);
        if(flag==false){
          document.getElementById("myTaskPage").innerHTML='<div class="well">No Tasks Added Yet? Go on, Set your Tasks</div>';
        }
      }
      else if(taskname=='archive'){
        renderArchiveTask(allTaskData);
      }
    }
    else{
        document.getElementById("myTaskPage").innerHTML='<div class="well">No Tasks Added Yet? Go on, Set your Tasks</div>';
    }
  }
  else{
    document.getElementById("myTaskPage").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
getTasksReq.send();
}

function renderAllTask(data){
  alldata=data;
  var flag=false;
  var htmlString='';
  for(i=0;i< data.length;i++){
    if(data[i].status.status=='Completed'){
      continue;
    }
      var imgsrc='img/';
      imgsrc=imgsrc+data[i].label.label+'.jpg';
      var cdate=new Date(data[i].date);
      var ddate=new Date(data[i].dueDate);
      var currentDate=new Date(new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0,10));
      var time_diff=(ddate.getTime()-currentDate.getTime())/(1000 * 3600 * 24);
      var daysRem='';
      if(time_diff<0){
        daysRem='<p style="color:red">Task is OverDue</p>'
      }
      else{
        daysRem='<p>Days Remaining: '+time_diff+'</p>'
      }
      var date_created=cdate.toDateString();
      var due_date=ddate.toDateString();
      htmlString = htmlString + '<div class=" col-md-3 col-sm-6"><div class="thumbnail"><img src="'+imgsrc+'" alt="'+data[i].task_desc+'"><div class="caption"><p>Task Description: '+data[i].task_desc+'</p><p>Label: '+data[i].label.label+'</p><p>Status: '+data[i].status.status+'</p><p>Date Created: '+date_created+'</p><p>Due Date: '+due_date+'</p>'+daysRem+'</div><button class="btn btn-primary" data-toggle="modal" data-target="#editTaskModal" onclick="getStatus(2); getTaskForEdit(alldata['+i+']);">Edit</button></div></div>';
      flag=true;
  }
  document.getElementById("myTaskPage").insertAdjacentHTML('beforeend',htmlString);
  return flag;
}

function getTaskForEdit(data){
      document.getElementById('editTaskDesc').value=data.task_desc;
      document.getElementById('editTaskLabel').value=data.label.label;
      //document.getElementById('editTaskStatus').value=data.status.id;
      document.getElementById('editTaskDueDate').value=data.dueDate.toString().slice(0,10);
      geditdata=data;
}

function EditTask(editdata)
{ 
var editTaskRequest = new XMLHttpRequest();
var statusId = document.getElementById("getStatusListEdit").value;
var taskDesc = document.getElementById("editTaskDesc").value;
var dueDate = document.getElementById("editTaskDueDate").value;
var editTaskurl = 'http://localhost:8080/edittask/'+editdata.id;
editTaskRequest.open('PUT', editTaskurl);
editTaskRequest.setRequestHeader("Content-Type", "application/json");
var editTaskData = {
	task_desc: taskDesc,
  dueDate: editdata.dueDate,
  status:{
    id:statusId
  },
  label:{
    id:editdata.label.id
  },
  date:editdata.date
}
var editTaskData1 = JSON.stringify(editTaskData);
editTaskRequest.onload = function () {
  if(editTaskRequest.status==200){
    document.getElementById("editTaskMessage").innerHTML='<p style="color:green;text-align:center;">Task Edited Successfully<\p>';
  }
  else{
    document.getElementById("editTaskMessage").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
editTaskRequest.send(editTaskData1);
}

function renderArchiveTask(data){
  document.getElementById("myTaskPage").innerHTML='';
  var htmlString='';
  var flag=0;
  for(i=0;i< data.length;i++){
    if(data[i].status.status=='Completed'){
      flag=1;
      var imgsrc='img/';
      imgsrc=imgsrc+data[i].label.label+'.jpg';
      var cdate=new Date(data[i].date);
      var ddate=new Date(data[i].dueDate);
      var currentDate=new Date(new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0,10));
      var time_diff=(ddate.getTime()-currentDate.getTime())/(1000 * 3600 * 24);
      var daysRem='';
      if(time_diff<0){
        daysRem='<p style="color:red">Task Was OverDue</p>'
      }
      else{
        daysRem='<p style="color:green">Task Was Completed</p>'
      }
      var date_created=cdate.toDateString();
      var due_date=ddate.toDateString();
      htmlString = htmlString + '<div class=" col-md-3 col-sm-6"><div class="thumbnail"><img src="'+imgsrc+'" alt="'+data[i].task_desc+'"><div class="caption"><p>Task Description: '+data[i].task_desc+'</p><p>Label: '+data[i].label.label+'</p><p>Status: '+data[i].status.status+'</p><p>Date Created: '+date_created+'</p><p>Due Date: '+due_date+'</p>'+daysRem+'</div></div></div>';
  }
}
if(flag==0){
  document.getElementById("myTaskPage").innerHTML='<div class="well">No Tasks in Archive? Go on, Finish your Tasks</div>';
}
else{
  document.getElementById("myTaskPage").insertAdjacentHTML('beforeend',htmlString);
}
}

function getStatus(sid)
{ 
var getstatus = new XMLHttpRequest();
var statusUrl = 'http://localhost:8080/getstatus';
getstatus.open('GET', statusUrl);
getstatus.onload = function () {
  if(getstatus.status==200){
    var allStatusData=JSON.parse(getstatus.responseText);
    var getStatusHtml='<option  value="" disabled selected>Status</option>';
    for(i=0;i<allStatusData.length;i++){
      if(sid==1 && allStatusData[i].status=='Completed'){
        getStatusHtml=getStatusHtml + '<option disabled="true" value="'+allStatusData[i].id+'">'+allStatusData[i].status+'</option>';
        continue;
      } 
      getStatusHtml=getStatusHtml + '<option value="'+allStatusData[i].id+'">'+allStatusData[i].status+'</option>';
    }
    if(sid==1){
      document.getElementById("getStatusListAdd").innerHTML=getStatusHtml;
    }
    else if(sid==2){
      document.getElementById("getStatusListEdit").innerHTML=getStatusHtml;
    }
  }
  else{
    document.getElementById("getStatusList").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
getstatus.send();
}

function getLabel()
{ 
var getlabel = new XMLHttpRequest();
var labelUrl = 'http://localhost:8080/getlabel';
getlabel.open('GET', labelUrl);
getlabel.onload = function () {
  if(getlabel.status==200){
    var allLabelData=JSON.parse(getlabel.responseText);
    var getLabelHtml='<option  value="" disabled selected>Label</option>';
    for(i=0;i<allLabelData.length;i++){
      getLabelHtml=getLabelHtml + '<option value="'+allLabelData[i].id+'">'+allLabelData[i].label+'</option>';
    }
      document.getElementById("getLabelListAdd").innerHTML=getLabelHtml;
  }
  else{
    document.getElementById("getLabelList").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
getlabel.send();
}

function AddTask()
{ 
var addTaskRequest = new XMLHttpRequest();
var statusId = document.getElementById("getStatusListAdd").value;
var labelId = document.getElementById("getLabelListAdd").value;
var addTaskurl = 'http://localhost:8080/addtask/'+cid+'/'+statusId+'/'+labelId;
addTaskRequest.open('POST', addTaskurl);
addTaskRequest.setRequestHeader("Content-Type", "application/json");
var taskDesc = document.getElementById("addTaskDesc").value;
var dueDate = document.getElementById("addTaskDueDate").value;
var createDate=new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0,10);
var addTaskData = {
	task_desc: taskDesc,
	date: createDate,
	dueDate: dueDate
}
var addTaskData1 = JSON.stringify(addTaskData);
addTaskRequest.onload = function () {
  if(addTaskRequest.status==200){
    document.getElementById("addTaskMessage").innerHTML='<p style="color:green;text-align:center;">Task Added Successfully<\p>';
  }
  else{
    document.getElementById("addTaskMessage").innerHTML='<p style="color:red;text-align:center;">Something went wrong<\p>';
  }
};
addTaskRequest.send(addTaskData1);
}

function Logout(){
  cid=0;
  localStorage.setItem("gid",0);
  window.location="index.html";
}

$.validator.addMethod(
  "myDateFormat",
  function(value, element) {
    var curDate = new Date(new Date(new Date().getTime() - (new Date().getTimezoneOffset() * 60000)).toISOString().slice(0,10));
    var inputDate = new Date(value);
    if (inputDate >= curDate){
      return value.match(/^\d\d\d\d?\-\d\d?\-\d\d$/);
    }
  },
  "Please enter a future date in the format YYYY-MM-DD"
);

$(function() {
  $("form[name='addTaskForm']").validate({
    rules: {
      addTaskDesc: {
        required: true
      },
      addTaskLabel:{
        required: true
      },
      addTaskStatus:{
        required:true
      },
      addTaskDueDate:{
        required:true,
        date: true,
        myDateFormat:true,
      }
    },
    messages: {
      addTaskDesc: "Task Desc should not be empty",
      addTaskLabel:"Choose One Label",
      addTaskStatus:"Choose One Status",
    },
    onkeyup: function() {
      if($("form[name='addTaskForm']").valid()){
        $('#addTaskBtn').removeAttr("disabled");
      } else {
        $('#addTaskBtn').attr("disabled",true);
      }
    },
    submitHandler: function(form) {
      form.submit();
    }
  });
});