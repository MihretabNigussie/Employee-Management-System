const X_CLASS = "x";
const O_CLASS = "circle";
const winningCombinations = [
  [0, 1, 2],
  [3, 4, 5],
  [6, 7, 8],
  [0, 3, 6],
  [1, 4, 7],
  [2, 5, 8],
  [0, 4, 8],
  [2, 4, 6],
];

let result1 = 0;
let result2 = 0;

let player1 = document.getElementById('player1');
let player2 = document.getElementById('player2');
const cellElements = document.querySelectorAll('.cell');
const board = document.getElementById('board');
const winningMessageElement = document.getElementById('winningMessage');
const restartButton = document.getElementById("restartButton");
const winningMessageText = document.querySelector(".winning-message-text");
let circleTurn;

startGame();

restartButton.addEventListener("click", startGame);

function startGame() {
  circleTurn = false;
  cellElements.forEach((cell) => {
    cell.classList.remove(X_CLASS);
    cell.classList.remove(O_CLASS);
    cell.removeEventListener("click", handleClick);
    cell.addEventListener("click", handleClick, { once: true });
  });
  setBoardHoverClass();
  winningMessageElement.classList.remove("show");
}

function handleClick(e) {
  const cell = e.target;
  const currentClass = circleTurn ? O_CLASS : X_CLASS;
  placeMark(cell, currentClass);
  if (checkWin(currentClass)) {
    endGame(false);
  } else if (isDraw()) {
    endGame(true);
  } else {
    swapTurns();
    setBoardHoverClass();
  }
}

function endGame(draw) {
  if (draw) {
    winningMessageText.innerText = "Draw!";
  } else {
    winningMessageText.innerText = `${circleTurn ? "Player2" : "Player1"} Wins!`;
  }
  winningMessageElement.classList.add("show");
  if (circleTurn){
    result2++;
    player2.textContent = `${result2}`;
  }
  else if(!circleTurn && !(draw)){
    result1+=1;
    player1.textContent = `${result1}`;
  }
}


// Checking if it is a draw
function isDraw() {
  return [...cellElements].every((cell) => {  // array destructuring(cellElements doesnot have an every method)
    return (
      cell.classList.contains(X_CLASS) || cell.classList.contains(O_CLASS)
    );
  });
}

function placeMark(cell, currentClass) {
  cell.classList.add(currentClass);
}

function swapTurns() {
  circleTurn = !circleTurn;
}

function setBoardHoverClass() {
  board.classList.remove(X_CLASS);
  board.classList.remove(O_CLASS);
  if (circleTurn) {
    board.classList.add(O_CLASS);
  } else {
    board.classList.add(X_CLASS);
  }
}

function checkWin(currentClass) {
  return winningCombinations.some((combination) => {
    return combination.every((index) => {
      return cellElements[index].classList.contains(currentClass);
    });
  });
}
