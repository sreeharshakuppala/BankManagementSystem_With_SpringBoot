
function createAccount() {

    let data = {
        name: document.getElementById("name").value,
        mobileNumber: document.getElementById("mobile").value,
        accountNumber: parseInt(document.getElementById("accountNumber").value),
        age: parseInt(document.getElementById("age").value),
        balance: 0
    };

    fetch("http://192.168.0.116:8080/account/create", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
        document.getElementById("result").innerText = result;
    })
    .catch(error => console.error(error));
}

function deposit() {

    let accountNumber = document.getElementById("depAccount").value;
    let amount = document.getElementById("depAmount").value;

    fetch(`http://192.168.0.116:8080/account/deposit?accountNumber=${accountNumber}&amount=${amount}`, {
        method: "POST"
    })
    .then(response => response.text())
    .then(result => {
        document.getElementById("depResult").innerText = result;
    })
    .catch(error => console.error(error));
}

function withdraw() {

    let accountNumber = document.getElementById("withAccount").value;
    let amount = document.getElementById("withAmount").value;

    fetch(`http://192.168.0.116:8080/account/withdraw?accountNumber=${accountNumber}&amount=${amount}`, {
        method: "POST"
    })
    .then(response => response.text())
    .then(result => {
        document.getElementById("withResult").innerText = result;
    })
    .catch(error => console.error(error));
}