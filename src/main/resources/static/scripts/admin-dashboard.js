let messageField = document.getElementById('messages');
console.log("hello");
const queryString = window.location.search;
const urlParams = new URLSearchParams(queryString);

if(urlParams.get('msg') === 'addedHotel') {
    messageField.firstElementChild.classList.add('alert');
    messageField.firstElementChild.classList += ' alert-success'
    messageField.firstElementChild.innerHTML += '<p>Dodano nowy hotel</p>'
    messageField.firstElementChild.classList += ' animate_animated'
    messageField.firstElementChild.classList += ' animate_fadeOut'
}

$("#showFormBtn").click(function(){
    $("#newHotelForm").toggle();
});

