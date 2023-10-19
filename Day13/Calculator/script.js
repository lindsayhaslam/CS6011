"use strict"

const xValue = document.getElementById("xValue");
const yValue = document.getElementById("yValue");
const resultInput = document.getElementById("result");

//No button - we might create it later on
//I will listen to key press event on xValue and yValue

xValue.addEventListener("keypress", handleKeyPress);
yValue.addEventListener("keypress", handleKeyPress);

let ws = new WebSocket("ws://localhost:8080/calculate");
let wsOpen = false;

ws.onopen = handleOpen ;
ws.onmessage = handleMsg;

//       function (){
//         wsOpen = true;
//       }


function handleKeyPress(event){
    if (event.code=="Enter" || event.code=="click"){
        let x = parseFloat(xValue.value);
        if (isNaN(x))
        {
        alert("X should be a number!");
         xValue.value = "Enter a number";
         xValue.select();
         return;
        }

        let y = parseFloat(yValue.value);

          if (isNaN(y))
             {
              alert("Y should be a number!");
              yValue.value = "Enter a number";
              yValue.select();
              return;
            }
       //This is a naive approach
       //resultInput.value = (x+y);

       //Option 1 using AJAX
//       let xhr = new XMLHttpRequest();
//       xhr.open("GET", "http://localhost:8080/calculate?x=" + x + "&y=" + y);
//       xhr.onerror = handleError;
//       xhr.addEventListener("load", "call a method");
//       xhr.onload = handleAjax;
//       xhr.send();

       //Option 2 - Web Sockets


       if (wsOpen)
       {
            ws.send(x + " " + y);
       }

       else
       {
        resultInput.value = "Couldn't open the websocket"
       }


       ws.onmessage - function(event){
           resultInput.value = event.data; //data coming from the socket
       }

       console.log("x value", x)
    }
}

function handleMsg(event){
    resultInput.value = event.data;
}

function handleOpen(){
    wsOpen = true;
}
function handleAjax(){
    resultInput.value = this.responseText;
}

function handleError(){
    resultInput.value = "Problem connecting to the server";
}