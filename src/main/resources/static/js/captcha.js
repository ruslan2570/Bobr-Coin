let regBtn = document.getElementById("reg-btn");
let captchaContainer = document.getElementById("captcha-container");
let regForm = document.getElementById("reg-form");
let captchaModal = new bootstrap.Modal(document.getElementById('captcha-modal', {}));

regBtn.onclick = () =>
    {
        if(
            regForm.elements.email.value === '' ||
            regForm.elements.login.value === '' ||
            regForm.elements.password.value === ''
        )
            return;

        if(validateEmail(regForm.elements.email.value) == null)
            return;
        captchaModal.toggle();
        window.smartCaptcha.render(captchaContainer,
            {
                sitekey: '5mei2vyWK0z4egDvy4SwMkxUXFoDCgUHwzD9le5A',
                callback: (token) => sendForm(token, regForm),
                hl: 'ru',
                invisible: false,
                hideShield: true
            });
    };

const validateEmail = (email) => {
    return String(email)
        .toLowerCase()
        .match(
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
        );
};

function sendForm(token, form){
    form.elements.smarttoken.value = token;
    form.submit();
}