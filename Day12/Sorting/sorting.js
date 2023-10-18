function findMinLocation(arr, compare){
let minIdx = 0;
for (let i = 1; i < arr.length; i++){
 if (compare(arr[i], arr[minIdx])){
 minIdx = i;
 }
 }
 return minIdx;
}


function selectionSort(arr, compare){
    for (let i = 0; i < arr.length - 1; i++){
        const minIdx = findMinLocation(arr.slice(i), compare);
        const temp = arr[i];
        arr[i] = arr[i + minIdx];
        arr[i + minIdx] = temp;
      }
}

function compareNumbers(a, b){
    return a < b;
}

function compareStrings(a, b){
    return a.toLowerCase() < b.toLowerCase();
}

function comparePeopleByLastName(a, b){
    if (a.last === b.last){
        return a.first < b.first;
    }
    return a.last < b.last;
}

function comparePeopleByFirstName(a, b){
    if (a.first === b.first){
        return a.last < b.last;
    }
    return a.first < b.first;
}

const numbers = [9, 7, 2, 1, 5, 9, 4, 6, 5, 3, 5];
selectionSort(numbers, compareNumbers);
console.log("Sorted numbers:", numbers);

const strings = ["shark", "whale", "dolphin", "starfish", "jellyfish"];
selectionSort(strings, compareStrings);
console.log("Sorted strings:", strings);

const people = [
    {first: "John", last: "Doe"},
    {first: "Jane", last: "Smith"},
    {first: "John", last: "Smith"},
];
selectionSort(people, comparePeopleByLastName);
console.log("Sorted people by last name:", people);

selectionSort(people, comparePeopleByFirstName);
console.log("Sorted people by first name:", people);
