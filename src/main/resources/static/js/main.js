const serverUrl = "http://localhost:8090/";

let msgModal = document.getElementById('msg-modal');

if (msgModal != null) {
    let myModal = new bootstrap.Modal(msgModal, {});
    myModal.toggle()
}

let buyBtns = document.getElementsByClassName("buy-btn");

const buy = (e) => {
    var clickedElement = e.target;

    // Поднимаемся вверх по иерархии до тех пор, пока не найдем элемент с атрибутом "bobr-id"
    while (clickedElement.getAttribute('bobr-id') === null) {
        clickedElement = clickedElement.parentElement;
    }

    clickedElement.children[0].removeAttribute('hidden');
    var bobrId = clickedElement.getAttribute('bobr-id');

    var formdata = new FormData();
    formdata.append("bobrtype-id", bobrId);

    var requestOptions = {
        method: 'POST',
        body: formdata,
        redirect: 'follow'
    };

    let buyErrMsg = document.getElementById('msg-buy-err');
    let buyErrModal = new bootstrap.Modal(buyErrMsg, {});
    
    let modalContent = buyErrMsg.getElementsByClassName("modal-dialog")[0].getElementsByClassName("modal-content")[0];
    let modalTitle = modalContent.getElementsByClassName("modal-header")[0].getElementsByClassName("modal-title")[0];
    let modalText = modalContent.getElementsByClassName("modal-body")[0].getElementsByTagName('p')[0];


    fetch(serverUrl + "api/game/buy", requestOptions)
        .then(async response  => {
            if (response.status != 201) {
                modalTitle.innerText = "Ошибка " + response.status;
                modalText.innerText = await response.text();
                buyErrModal.show();
            }
            clickedElement.children[0].setAttribute('hidden', '');
        })
        .catch(error => {
            modalTitle.innerText = "Очень серьёзная ошибка";
            modalText.innerText = error;
        }
        );

    e.target.children[0].setAttribute('hidden', '');
}

[...buyBtns].forEach(element => {


    element.addEventListener('click', buy);

    // element.onclick = buy;
});

