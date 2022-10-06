const decreaseValue = () => {
    let decreaseButtons = document.querySelectorAll('[data-less-id]');

    for (let btn of decreaseButtons) {
        btn.addEventListener("click", async (e) => {
            e.preventDefault();


            let productId = btn.getAttribute("data-less-id");

            const dataToBePosted = {
                id: productId,
                quantity: 1,
            };
            await fetch("/decrease-value", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(dataToBePosted)
            });
            console.log("intra");
            let input = document.getElementById(productId);
            input.value = (parseInt(input.value) - 1).toString();

            let deleteWhen0 = document.querySelectorAll(".deleteWhen0");
            deleteWhen0.forEach(element=>{
                let input = document.getElementById(productId);
                if(parseInt(element.children[1].children[1].value) < 1){
                    element.remove();
                }
            })

            let numberOfItems = document.getElementById("numberOfItems");
            var number = parseInt(numberOfItems.innerHTML.substring(0,2));
            number= number - 1;
            numberOfItems.innerHTML = number + " products";
            totals();
            totalItem();
        });
    }
};

const totals=()=>{

    let allDivs = document.querySelectorAll(".deleteWhen0");
    let totalSum = 0
    let total = document.querySelector("#total");
    for (const allDiv of allDivs) {
        totalSum += (parseFloat(allDiv.children[1].children[1].value) * parseFloat(allDiv.children[2].children[0].innerHTML.substring(0,allDiv.children[2].children[0].innerHTML.indexOf(' '))));
    }
    total.innerHTML = "USD "+totalSum;
}

const totalItem = () => {

    let allDivs = document.querySelectorAll(".deleteWhen0");
    for (const allDiv of allDivs){
        let totalProdPrice = allDiv.children[2].children[1];
        let unit = allDiv.children[1].children[1].value;

        totalProdPrice.innerHTML = unit * parseFloat(allDiv.children[2].children[0].innerHTML.substring(0,allDiv.children[2].children[0].innerHTML.indexOf(' '))) + " USD";
    }
}

const init = () => {
    decreaseValue();
    totals();
    totalItem();
}
init();