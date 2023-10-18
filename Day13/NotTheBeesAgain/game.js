"use strict";

//Store gameCanvas in canvas
//What does 2d do?
//Get the height and width of canvas and store it in cWidth and cHeight for later
const canvas = document.getElementById("gameCanvas");
const context = canvas.getContext("2d");
const cWidth = canvas.width;
const cHeight = canvas.height;
//Adjust this as necessary
const numberOfBees = 5;
const beeSpeed = 4;


//For game over screen
let isGameOver = false;
//Create new image for player
//Adjust image and size
const playerImage = new Image();
playerImage.src = "Honey.png"; // Replace with the path to your mouse cursor image
const playerSize = 70; // Adjust the size of the mouse cursor image

//Create new image for bees
//Load image, change size
const chasingObjectImage = new Image();
chasingObjectImage.src = "Bee.png"; // Replace with the path to your chasing object image
const chasingObjectSize = 40; // Adjust the size of the chasing object image

//Audio
const gameAudio = document.getElementById("gameAudio");
gameAudio.volume = 0.5;

function playGameAudio(){
    gameAudio.play();
}

function stopGameAudio(){
    gameAudio.pause();
    gameAudio.currentTime = 0;
}

//For default starting position, divide width and height by 2
const player = {
    x: cWidth / 2,
    y: cHeight / 2,
};

//Declare array of bees, right now it is empty
const chasingObjects = [];

//to generate random positions of the bees
//.push is JavaScripts' "pushback"
function createChasingObject() {
    chasingObjects.push({
        x: Math.random() * cWidth,
        y: Math.random() * cHeight,
    });
}

function draw() {
    playGameAudio();
    //Clear the entire canvas
    context.clearRect(0, 0, cWidth, cHeight);
    if (isGameOver){
        drawGameOverScreen();
        return;
    }
    //Draw image at the center of the cursor
    context.drawImage(playerImage, player.x - playerSize / 2, player.y - playerSize / 2, playerSize, playerSize);
    context.fillStyle = "#D1E8FF"
    //Loop through array
    //Draw the bee at the center of it's randomly set position
    chasingObjects.forEach(object => {
        context.drawImage(chasingObjectImage, object.x - chasingObjectSize / 2, object.y - chasingObjectSize / 2, chasingObjectSize, chasingObjectSize);

        // Calculate the direction to the player (mouse cursor)
        const dx = player.x - object.x;
        const dy = player.y - object.y;
        const distance = Math.sqrt(dx * dx + dy * dy);

        //Change the object's x and y position by the distance, direction, and beeSpeed
        if (distance > 1) {
            object.x += (dx / distance) * beeSpeed;
            object.y += (dy / distance) * beeSpeed;
        }
        //For Game Over
        if (distance < playerSize/2 + chasingObjectSize/2){
            isGameOver = true;
        }
    });

    requestAnimationFrame(draw);
}

function drawGameOverScreen(){
    context.clearRect(0, 0, cWidth, cHeight);
    context.fillStyle = "black";
    context.font = "100px Arial";
    context.fillText("You died.", cWidth/2 - 100, cHeight/2 - 20);
    context.font = "20px Arial";
    context.fillText("Click to restart", cWidth/2 - 80, cHeight/2 +20);

}


//Updates player x and y positions to follow mouse cursor
canvas.addEventListener("mousemove", (e) => {
    player.x = e.clientX - canvas.getBoundingClientRect().left;
    player.y = e.clientY - canvas.getBoundingClientRect().top;
});

//Creates a new chasing object when mouse is clicked
canvas.addEventListener("click", () => {
    if (isGameOver) {
        // Reset the game
        isGameOver = false;
        player.x = cWidth / 2;
        player.y = cHeight / 2;
        chasingObjects.length = 0; // Clear chasing objects
        for (let i = 0; i < numberOfBees; i++) {
            createChasingObject();
        }
        draw(); // Start the game loop again
    }
});

// Initialize the game
//Set for 3
for (let i = 0; i < numberOfBees; i++) {
    createChasingObject();
}

draw();