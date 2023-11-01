"use strict";


//Get the user and message area and store them in their respective variables
//user box
let peopleArea=document.getElementById("people");
//message box
let messageArea=document.getElementById("messageArea");

//Get buttons by their ID and store it in their respective variables
let joinButton=document.getElementById("joinButton");
let sendMessageButton=document.getElementById("button");
let leaveButton=document.getElementById("leave");

//Get areas for text inputs and store them in their respective variables
let usernameText=document.getElementById("myname");
let roomNameText=document.getElementById("room");
let messageText=document.getElementById("messagehere");

//Allow clicks and and key presses for the join button, username input, and roomname input
joinButton.addEventListener("click", handleEnterChat);
usernameText.addEventListener("keypress", handleEnterChat);
roomNameText.addEventListener("keypress", handleEnterChat);
sendMessageButton.addEventListener("click", handleSendMessage);
messageText.addEventListener("keypress", handleSendMessage);

//handle keyboard/mouse click events for leaving the chatroom
leaveButton.addEventListener("click", leaveChat);


//Global variables
//Default wsOpen to "false" to indicate websocket is not open
let wsOpen=false;
//Default to false to indicate user is not in chatroom
let inChatRoom = false;
//Create websocket and store url
let ws = new WebSocket('ws://localhost:8080');

//Handling the websocket by assigning actions to respective methods
ws.onopen=handleOpen;
ws.onmessage = function(e) {
    handleMsg(e);
};
ws.onclose=handleClose;
ws.onerror=handleError;


//When websocket opens, switch "wsOpen" to true
function handleOpen(){
    wsOpen=true;
}

//When websocket receives message, parse the message
//Store pieces of websocket message in their respective variables
function handleMsg(e){
    let msgObj = JSON.parse(e.data);
    let type=msgObj.type;
    let room = msgObj.room;
    let user = msgObj.user;
    let message=msgObj.message;

    //Create a break element
    let lineBreak = document.createElement("br");

    //If the type is message
    if(type==="message"){
        //Call displayTimeStamp
        const timestamp=displayTimeStamp();
        //Format of time stamp in message area
        let timeTextStamp = document.createElement('span');
        timeTextStamp.textContent = `[${timestamp}] `;
        timeTextStamp.classList.add('timestamp');

        let sentText=document.createTextNode(user + ": " + message)


        messageArea.appendChild(lineBreak);
        messageArea.appendChild(timeTextStamp);
        messageArea.appendChild(sentText);
  }

    if (type === "join"){
        let chatParticipants = document.createElement("div");
        chatParticipants.textContent = user;
        chatParticipants.id = user;

        peopleArea.appendChild(chatParticipants);
    }

    if (type === "leave"){
        let outText = document.createTextNode(user + " left " + room + ".");

        // Remove the element with the specified id
        let chatParticipant = document.getElementById("people");
               let leaveUser = document.getElementById(user)
               chatParticipant.removeChild(leaveUser)

        messageArea.appendChild(lineBreak);
        messageArea.appendChild(outText);
    }
}
function handleClose(){
    wsOpen=false;
    alert("Websocket Connection Closed");
}


function handleError(errorMessage){

    console.error("Server error: " + errorMessage);

}

function handleEnterChat (event){

    if (event.key === "Enter" || event.type === "click"){

        console.log("name is: " + getName());
        console.log("room is: " + getRoom());

        if(getName()!=="" && getRoom()!=="" && !inChatRoom){
            ws.send("join:" + getName() + ":" + getRoom());
            inChatRoom = true;
        }

        else if (inChatRoom) {
            alert("You are already in a chat room. Leave the room before joining a new one.");
            return;
        }

        else{
            alert("Incorrect entry, please try again.");
            return;
        }
    }
}

function getName(){
    return usernameText.value.toLowerCase();
}

function getRoom(){
    return roomNameText.value.toLowerCase();
}

function handleSendMessage(event){

    if (event.key === "Enter" || event.type === "click"){
        let message=messageText.value;

        console.log("message is: "+ message);

        if(message!==""){
            ws.send("message:" + getName() + ":" + getRoom() + ":" + message);
            messageText.value = "";
        }
        else{
            alert("Entry is null, please try again");
            return;
        }
    }
}

function leaveChat(event){

    console.log("leave button pressed");
    if (event.type==="click"){

        document.getElementById("people").innerHTML = "";
        document.getElementById("messageArea").innerHTML = "";

        inChatRoom=false;

        ws.send("leave:" + getName() + ":" + getRoom());
    }
}

function displayTimeStamp() {
    const now = new Date();
    const hours = now.getHours().toString().padStart(2, '0');
    const minutes = now.getMinutes().toString().padStart(2, '0');
    const seconds = now.getSeconds().toString().padStart(2, '0');
    return `${hours}:${minutes}:${seconds}`;
}