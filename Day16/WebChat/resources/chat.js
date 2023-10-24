"use strict";

//Set parts of the document into variables
//TOP HALF
const usernameInput = document.getElementById('myname');
const roomInput = document.getElementById('room');
const joinButton = document.getElementById('joinButton');
//BOTTOM HALF
const messageInput = document.getElementById('messagehere');
const sendMessageButton = document.getElementById('button');
//Leave button
let leaveButton = document.getElementById('leave');
leaveButton.onclick = handleClose;

//Server
const serverURL = "ws://localhost:8080/";
let ws = new WebSocket(serverURL);
let wsOpen = false;

ws.onopen = handleOpen;
ws.onmessage = handleMsg;
ws.onerror = handleError;
//ws.onclose = handleClose;


// Function to join a room
function joinRoom(username, roomName) {
  if (wsOpen) {
  const joinMessage = `join ${username} ${roomName}`;
    ws.send(joinMessage);
    console.log("Attempted to join!");
  }
}
 // Event listener for the "Join" button
 joinButton.addEventListener('click', function () {
    const username = usernameInput.value;
    const roomName = roomInput.value;
    //Filter for invalid roomName inputs
    if (username && roomName) {
       if (roomName >= 'a' && roomName <= 'z') {
       joinRoom(username, roomName);
       console.log("Joined!")
       }
       else {
       alert("Your room-name must contain only lowercase letters (and no spaces).");
      }
     }
  });

  function sendMessage(message) {
    if (wsOpen) {
      ws.send(message);
      console.log(message);
    }
  }
  // Event listener for the "Send" button
  sendMessageButton.addEventListener('click', function () {
        ws.send("message " +messageInput.value);
        console.log("message " +messageInput.value);

  });

  function handleMsg(event) {
    let message = event.data;
    let msg = JSON.parse(message)
    if(msg.type == "message"){
        displayMessage(msg.message);
    }
    else if (msg.type == "join"){
          const messageToDisplay = `${msg.user} has joined room ${msg.room}!`;
          displayPeopleMessage(messageToDisplay);
    }
  }

    function displayPeopleMessage(users) {
      const messageDiv = document.createElement('p');
      messageDiv.textContent = users;
      people.appendChild(messageDiv);
      people.scrollTop = people.scrollHeight; // Automatically scroll to the latest message
    }

  function displayMessage(message) {
    const messageDiv = document.createElement('div');
    messageDiv.textContent = message;
    messageArea.appendChild(messageDiv);
    messageArea.scrollTop = messageArea.scrollHeight; // Automatically scroll to the latest message
  }

function handleOpen(event){
    wsOpen = true;
    console.log(event);
}
function handleClose(event){
    let you = document.createTextNode("You left the room.");
    let lineBreak = document.createElement("br");
    messageArea.appendChild(lineBreak);
    messageArea.appendChild(you);
    ws.send("leave");
}

//function handleError(event){
//    const roomName = roomInput.value;
//
//    if (!lowercaseLetters.test(roomName)) {
//        alert("Your room name must contain only lowercase letters.");
//        return; // Return early if the input is invalid
//    }
//
//    // Proceed with other error handling or actions if needed
//    console.log(event);
//}

