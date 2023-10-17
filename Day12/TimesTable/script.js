const table = document.getElementById("multiplication-table");
const backgroundToggle = document.getElementById("background-toggle");
let isBackgroundObnoxious = false;
let obnoxiousBackgroundInterval;

function createMultiplicationTable(){
  for (let i = 1; i <=10; i++){
    const row = document.createElement("tr");
      for (let j = 1; j <= 10; j++){
      const cell = document.createElement(i === 1 || j === 1 ? "th":"td");
      cell.textContent = i === 1 ? j : j === 1 ? i : i * j;
      cell.addEventListener("mouseover", () => cell.classList.add("highlighted"));
      cell.addEventListener("mouseout", () => cell.classList.remove("highlighted"));
      cell.addEventListener("click", () => {
       clearSelection();
       cell.classList.add("Selected");
     });
   row.appendChild(cell);
  }
   table.appendChild(row);
 }
}

function clearSelection() {
  const selectedCell = document.querySelector(".selected");
      if(selectedCell) {
       selectedCell.classList.remove("selected");
     }
  }

function toggleBackground() {
  isBackgroundObnoxious = !isBackgroundObnoxious;
     if (isBackgroundObnoxious) {
      startObnoxiousBackground();
    } else {
     stopObnoxiousBackground();
    }
  }

function startObnoxiousBackground() {
  const colors = ["blue", "purple", "red", "purple"];
  let currentIndex = 0;

    function changeBackgroundColor() {
     document.body.style.backgroundColor = colors[currentIndex];
     currentIndex = (currentIndex + 1) % colors.length;
    }
     changeBackgroundColor();
     obnoxiousBackgroundInterval = setInterval(changeBackgroundColor, 1000);
    }

function stopObnoxiousBackground() {
  if (obnoxiousBackgroundInterval) {
     clearInterval(obnoxiousBackgroundInterval);
     document.body.style.backgroundColor = "";
   }
 }

 createMultiplicationTable();