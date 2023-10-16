//import "./myStyle.css";
document.writeln("<h1> Lindsay's First HTML </h1>");


//H2
var h2 = document.createElement("h2");
h2.textContent = "Random Facts About Lindsay";
h2.style.fontFamily = "Georgia, 'Times New Roman', Times, serif";
h2.style.fontSize = "24px";
h2.style.color = "#604B62";

// Append the h2 element to the body
document.body.appendChild(h2);

//FOR AUDIO
var audio = document.createElement("audio");
audio.controls = true;
audio.autoplay = true;

var source = document.createElement("source");
source.src = "classical.mp3";
source.type = "audio/mpeg";

audio.appendChild(source);

//Append audio to the body
document.body.appendChild(audio);


//FOR IMAGE
let imgDiv = document.getElementById("imgID1");
let img1 = document.createElement('img');
img1.src="nyc.jpeg";
img1.style.width = "20%";
//Append image to the body
imgDiv.appendChild(img1);

//FOR TEXT
var paragraph1 = document.createElement("p");
paragraph1.textContent = "Lindsay was born and raised in Salt Lake City, Utah. She has one older brother and one younger sister." +
" Lindsay is studying software development at the University of Utah."
paragraph1.style.fontFamily = "Georgia, 'Times New Roman', Times, serif";
paragraph1.style.fontSize = "16px";
paragraph1.style.color = "#604B62";
document.body.appendChild(paragraph1);
var paragraph2 = document.createElement("p");
paragraph2.textContent = "If Lindsay could be any animal in the world, she would be a starfish. Her favorite place to go on vacation is the Pacific Northwest or New York City."
paragraph2.style.fontFamily = "Georgia, 'Times New Roman', Times, serif";
paragraph2.style.fontSize = "16px";
paragraph2.style.color = "#604B62";
document.body.appendChild(paragraph2);

document.body.style.background = "#fadadd";