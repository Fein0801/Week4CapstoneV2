var itemArray = [
	{
		item: "Hamburger",
		qty: 0,
		price: 8.99
	},
	{
		item: "Cheeseburger",
		qty: 0,
		price: 10.99
	}
];

generateItemHTML();

function addToCart(index) {
  var id = "qty" + index;

  var inputBox = document.getElementById(id);
  console.log(id);
  
  var num = inputBox.value;
  
  if(num < 0) {
    warnUser();
  } else {
    clearWarning();
    itemArray[index].qty = num;
  }
}

function warnUser() {
  var warningDiv = document.getElementById("warning");
  
  warningDiv.innerHTML = "<p>Please select a positive number</p>";

  // Adds a border and padding
  warningDiv.style.border = "1px solid red";
  warningDiv.style.padding = "5px 12px";
}

function clearWarning() {
  var warningDiv = document.getElementById("warning");
  
  // Removes warning message
  warningDiv.innerHTML = "";
  warningDiv.style.border = "none";
  warningDiv.style.padding = "0px 0px";
}

// Good function
function generateItemHTML() {
  var arrDiv = document.getElementById("array");

  var str = "";
  for (var i = 0; i < itemArray.length; i++) {
    str += "<div class=\"row\">";

    str += "<div class=\"col-lg-3\">";
    str += itemArray[i].item;
    str += "</div>";

    str += "<div class=\"col-lg-3\">";
    str += "$" + itemArray[i].price;
    str += "</div>";

    str += "<div class=\"col-lg-3\">";
    str += "<input id=\"qty" + i + "\" type=\"number\" value=\"0\">";
    str += "</div>";

    str += "<div class=\"col-lg-3\">";
    str += "<button onclick=\"addToCart(" + i + ");\" class=\"btn-primary\">Add to cart</button>";
    str += "</div>";

    str += "</div>";
    str += "<br>";
  }
  arrDiv.innerHTML = str;
}

function finalizeOrder() {
  var subtotal = 0;
  for(var i = 0; i < itemArray.length; i++) {
    addToCart(i);
  }
  for(var i = 0; i < itemArray.length; i++) {
    subtotal += itemArray[i].qty * itemArray[i].price;
  }

  var outputDiv = document.getElementById("output");
  var tax = 0.06 * subtotal;
  var total = subtotal + tax;

  outputDiv.innerHTML = "<p>Subotal:    $" + subtotal + "</p>";
  outputDiv.innerHTML += "<p>Total:    $" + roundTo2Places(total) + "</p>";
}

function roundTo2Places(num) {
  num *= 100;
  num = Math.round(num);
  return num / 100;
}
null

