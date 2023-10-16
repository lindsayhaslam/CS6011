
//PRINTING HELLO WORLD
document.writeln("Hello, World!");
console.log("Hello, World!")
//The difference between these two is that document.writeln will print "Hello, World!" on the webpage
//while console.log will print it in the console.

//ARRAY
myArray = ["Hello", false, 4, 3.14];
console.log(myArray);
myArray = ["Goodbye", false, 4, 7.0];
console.log(myArray);
//I noticed that myArray did change, but I didn't see anything particularly weird happening
//in my browser.

//FUNCTIONS
//C++ function
//int addNums(int num1, int num2){
// result = num1 + num2;
// return result;
//}
//Function literal
function addNumbers(num1, num2){
return num1 + num2;
}
//I prefer the Javascript syntax because it is easier for me to detect a function in my code
//I noticed that Javascript doesn't require you to indicate the data type that needs to be returned
//as well as the input

let number1 = 5;
let number2 = 10;
let sum = addNumbers(number1, number2);
console.log(sum);

let word1 = "pink";
let word2 = "blue";
let sumWord = addNumbers(word1, word2);
console.log(sumWord);

let float1 = 3.14;
let float2 = 3.00;
let sumFloat = addNumbers(float1, float2);
console.log(sumFloat);

//What's interesting is that it will add number types together but string will go next to each other




//
//function mainFunc(){
////console.log("Testing our JS");
//let myObj = {name:"cs6011", course:"Programming"};
//
//myObj.year = "2023";
//
//myObj.showDetails = function() {
//    console.log("Year is:" + myObj.year);
//}
//
////call the method
//myObj.showDetails();
//
////iterating over all properties of the object, then printing the name, course, etc.
//for(let prop in myObj)
//console.log("Property: ", prop);
//}
//
//let myArray = [3, 3.4, "hello"];
//
//for(let elem of myArray)
//console.log("Element: ", elem);


//after deleting all of the code above
//function mainFunc(){
//document.body.style.backgroundColor="lightblue";
//document.writeln("<p> CS 6011 </p>");

//let myPar document.createElement("p");
//let myText = document.createTextNode("This is my paragraph");
//myPar.appendChild(myText);
//document.body.appendChild(myPar);
//myPar.style.background="pink";
//myPar.style.fontSize="20px";
//myPar.style.fontWeight="bold";

//let h1s = document/getElementByTagName("h1"); //this is a bunch of headers in an array in the HTML file
//for (let i in h1s){
//conole.log('elements: ", i);
//let myH1 = h1s[0];
//myH1.innerHTML = "Changing H1 using JS";
//myH1.appendChild(myText.cloneNode()); //.cloneNode so you can use appendChild for myText twice
//myH1.innerHTML = myText.cloneNode().textContent;

//Instead of using a for loop, you can give the specific header an ID, and then call it by it's ID rather than
//going through an entire loop, which is extra work