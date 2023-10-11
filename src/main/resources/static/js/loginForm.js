


// $("form-control").on("keyup", function(){
    document.addEventListener('DOMContentLoaded', function() {
        const emailInput = document.getElementsByClassName('form-control')[0];
        const errorMessage = document.getElementsByClassName('error-message')[0];

    emailInput.addEventListener('keyup', function() {
    const email = emailInput.value;
    if (!validateEmail(email)) {
        errorMessage.textContent = '이메일 주소를 입력해주세요.';
      } else {
        errorMessage.textContent = '';
      }


});

function validateEmail(email) {
    // 이메일 형식 검사 정규식
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
  }
});