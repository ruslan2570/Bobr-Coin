
// var myModal = new bootstrap.Modal(document.getElementById('msgModal'), {})
// myModal.toggle()

let modal = document.getElementById('msgModal');

if(modal != null){
    let myModal = new bootstrap.Modal(modal, {});
    myModal.toggle()
}