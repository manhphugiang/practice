function getStudent(id){
    if(document.getElementById("student" +id).innerHTML==""){
       
       fetch("/getStudent/"+id)
       .then(data=>data.json())
       .then(function(student){
        var textToDisplay="";
        textToDisplay += "ID: " + student.id + "<br>";
        textToDisplay += "Name: " + student.name + "<br>";
        textToDisplay += "grade: " + student.grade + "<br>";
        textToDisplay += "Letter Grade: " + student.letterGrade + "<br>";
        document.getElementById("student" +id).innerHTML=textToDisplay;

       })
       
        document.getElementById("student"+id).innerHTML="hello";

    }else{
        document.getElementById("student"+id).innerHTML="";
    }
}