let msgModal = document.getElementById('msg-modal');

if(msgModal != null){
    let myModal = new bootstrap.Modal(msgModal, {});
    myModal.toggle()
}